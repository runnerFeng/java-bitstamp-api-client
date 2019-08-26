package com.jinx.feng.messages.liveorder;

import lombok.Data;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:41
 * @Desc:
 */
@Data
public class LiveOrder {
    private LiveOrderData data;
    private String event;
    private String channel;
}
