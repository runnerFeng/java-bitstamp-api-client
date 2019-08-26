package com.jinx.feng.messages.liveorder;

import com.google.gson.Gson;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:41
 * @Desc:
 */
public class LiveOrderDecoder implements Decoder.Text<LiveOrder> {

    private static Gson gson = new Gson();

    @Override
    public LiveOrder decode(String s) {
        return gson.fromJson(s, LiveOrder.class);
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
