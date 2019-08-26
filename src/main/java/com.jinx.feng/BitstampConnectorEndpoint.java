package com.jinx.feng;

import com.google.gson.Gson;
import com.jinx.feng.messages.Message;
import com.jinx.feng.messages.liveorder.LiveOrder;
import com.jinx.feng.messages.livetrade.LiveTrade;
import com.jinx.feng.messages.subscription.SubscriptionMessage;
import com.jinx.feng.observers.CloseObserver;
import com.jinx.feng.observers.ConnectionOpenObserver;
import com.jinx.feng.observers.LiveOrderObserver;
import com.jinx.feng.observers.LiveTradeObserver;
import com.jinx.feng.observers.MessageObserver;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:32
 * @Desc:
 */
@ClientEndpoint
@Slf4j
public class BitstampConnectorEndpoint {

    private Gson gson = new Gson();

    private static Set<String> liveTradePairSubscriptions = new HashSet<>();
    private static Set<String> liveOrderPairSubscriptions = new HashSet<>();

    private static List<ConnectionOpenObserver> openObservers = new ArrayList<>();
    private static List<MessageObserver> messageObservers = new ArrayList<>();
    private static List<CloseObserver> closeObservers = new ArrayList<>();
    private static List<LiveTradeObserver> liveTradeObservers = new ArrayList<>();
    private static List<LiveOrderObserver> liveOrderObservers = new ArrayList<>();

    public static void onOpen(ConnectionOpenObserver observer) {
        log.info("1.onOpen");
        openObservers.add(observer);
    }

    public static void onMessage(MessageObserver observer) {
        messageObservers.add(observer);
    }

    public static void onLiveTrade(String pair, LiveTradeObserver observer) {
        log.info("3.onLiveTrade");
        if (!liveTradePairSubscriptions.contains(pair)) {

            SubscriptionMessage message = new SubscriptionMessage();
            try {
                message.subscribeChannel("live_trades_" + pair.toLowerCase().trim());
            } catch (Exception e) {
                log.error("Error", e);
            }

            liveTradePairSubscriptions.add(pair);
            liveTradeObservers.add(observer);
        }

    }

    public static void onLiveOrder(String pair, LiveOrderObserver observer) {
        log.info("4.onLiveOrder");
        if (!liveOrderPairSubscriptions.contains(pair)) {

            SubscriptionMessage message = new SubscriptionMessage();
            try {
                message.subscribeChannel("live_orders_" + pair.toLowerCase().trim());

            } catch (Exception e) {
                log.error("Error", e);
            }

            liveOrderPairSubscriptions.add(pair);
            liveOrderObservers.add(observer);
        }
    }

    public static void onClose(CloseObserver observer) {
        closeObservers.add(observer);
    }

    @OnOpen
    public void eventOpen(Session session) {

        log.debug("Connected to endpoint: {}", session.getBasicRemote());

        BitstampConnector.setSession(session);

        for (ConnectionOpenObserver observer : openObservers) {
            observer.receive();
        }
    }

    @OnMessage
    public void eventMessage(String jsonMessage) {

        log.debug("Received: {}", jsonMessage);

        for (MessageObserver observer : messageObservers) {
            observer.receive(jsonMessage);
        }

        Message message = gson.fromJson(jsonMessage, Message.class);

        switch (message.getEvent()) {
            case "trade":
                LiveTrade trade = gson.fromJson(jsonMessage, LiveTrade.class);
                for (LiveTradeObserver observer : liveTradeObservers) {
                    observer.receive(trade);
                }
                break;
            case "order_created":
            case "order_changed":
            case "order_deleted":
                LiveOrder order = gson.fromJson(jsonMessage, LiveOrder.class);
                for (LiveOrderObserver observer : liveOrderObservers) {
                    observer.receive(order);
                }
                break;
            case "bts:subscription_succeeded":
                break;
            case "bts:request_reconnect":
                BitstampConnector.reconnect();
                break;
            default:
                log.warn("Unknown event: {}", message.getEvent());
        }
        BitstampConnector.messageLatch.countDown();
    }

    @OnError
    public void eventError(Throwable t) {
        log.error("Error", t);
    }

    @OnClose
    public void eventClose() {
        log.info("onClose event");
        for (CloseObserver observer : closeObservers) {
            observer.receive();
        }
    }

}
