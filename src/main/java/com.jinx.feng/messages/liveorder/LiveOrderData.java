package com.jinx.feng.messages.liveorder;

import lombok.Data;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:41
 * @Desc:
 */
@Data
public class LiveOrderData {
    private long id;
    private double amount;
    private String amount_str;
    private double price;
    private String price_str;
    private int order_type;
    private String datetime;
    private long microtimestamp;
}
