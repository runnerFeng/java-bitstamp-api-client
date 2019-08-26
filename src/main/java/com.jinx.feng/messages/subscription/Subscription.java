package com.jinx.feng.messages.subscription;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:34
 * @Desc:
 */
@Data
@ToString
public class Subscription {
    private SubscriptionData data;
    private String event;
    private String channel;
}
