package com.jinx.feng.messages.subscription;

import com.google.gson.Gson;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:38
 * @Desc:
 */
public class SubscriptionEncoder implements Encoder.Text<Subscription> {

    private static Gson gson = new Gson();

    @Override
    public String encode(Subscription message){
        return gson.toJson(message);
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
