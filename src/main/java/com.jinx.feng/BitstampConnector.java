package com.jinx.feng;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:31
 * @Desc:
 */
@Slf4j
public class BitstampConnector {


    static final CountDownLatch messageLatch = new CountDownLatch(1);

    private static Session session;

    private BitstampConnector() { }

    public static void connect() throws DeploymentException, InterruptedException, IOException {

        String uri = "wss://ws.bitstamp.net";

        log.info("2.Connecting to {}", uri);

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(BitstampConnectorEndpoint.class, URI.create(uri));

        messageLatch.await(100, TimeUnit.SECONDS);
    }

    public static void disconnect() {

        try {
            log.info("BitstampConnector is going to close session");
            session.close();

        } catch (IOException e) {
            log.error("Error while disconnecting", e);
        }
    }

    public static void reconnect() {

        disconnect();

        try {
            BitstampConnector.connect();

        } catch (InterruptedException | DeploymentException | IOException e) {
            log.error("Exception while connecting");
        }
    }


    public static void setSession(Session newSession) {
        session = newSession;
    }

    public static Session getSession() {
        return session;
    }



}
