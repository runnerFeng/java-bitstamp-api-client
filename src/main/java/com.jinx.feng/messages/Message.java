package com.jinx.feng.messages;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:33
 * @Desc:
 */
@Data
@Slf4j
public class Message {

    private String event;
    private String channel;
    private Object data;

    public String getPair() {

        if (channel == null || !channel.startsWith("live_trades_")) {
            return "";
        }

        String[] split = channel.split("_");
        return split[2];
    }

}
