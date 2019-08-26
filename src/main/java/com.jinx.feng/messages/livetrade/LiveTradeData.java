package com.jinx.feng.messages.livetrade;

import lombok.Data;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:40
 * @Desc:
 */
@Data
public class LiveTradeData {
    private long microtimestamp;
    private float amount;
    private long buy_order_id;
    private long sell_order_id;
    private String amount_str;
    private String price_str;
    private long timestamp;
    private double price;
    private int type;
    private long id;

}
