package com.jinx.feng.messages.livetrade;

import lombok.Data;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:39
 * @Desc:
 */
@Data
public class LiveTrade {
    private LiveTradeData data;
    private String event;
    private String channel;
}
