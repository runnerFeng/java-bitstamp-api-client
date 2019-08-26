package com.jinx.feng.messages.livetrade;

import com.google.gson.Gson;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:40
 * @Desc:
 */
public class LiveTradeDecoder implements Decoder.Text<LiveTrade> {

    private static Gson gson = new Gson();

    @Override
    public LiveTrade decode(String s) {
        return gson.fromJson(s, LiveTrade.class);
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
