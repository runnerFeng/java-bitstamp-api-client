package com.jinx.feng.messages.subscription;

import com.jinx.feng.BitstampConnector;

import java.io.IOException;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:39
 * @Desc:
 */
public class SubscriptionMessage {

    public void subscribeChannel(String channel) throws IOException {
        String json = messageAsJson("bts:subscribe", channel);
        BitstampConnector.getSession().getBasicRemote().sendText(json);
    }

    public void unsubscribeChannel(String channel) throws IOException {
        String json = messageAsJson("bts:unsubscribe", channel);
        BitstampConnector.getSession().getBasicRemote().sendText(json);
    }

    private String messageAsJson(String event, String channel) {

        Subscription message = new Subscription();

        message.setEvent(event);

        SubscriptionData data = new SubscriptionData();
        data.setChannel(channel);

        message.setData(data);

        SubscriptionEncoder encoder = new SubscriptionEncoder();

        return encoder.encode(message);
    }

}
