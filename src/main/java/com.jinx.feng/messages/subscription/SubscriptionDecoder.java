package com.jinx.feng.messages.subscription;

import com.google.gson.Gson;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:37
 * @Desc:
 */
public class SubscriptionDecoder implements Decoder.Text<Subscription> {
    private static Gson gson = new Gson();

    @Override
    public Subscription decode(String s) {
        return gson.fromJson(s, Subscription.class);
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }

}
