package com.jinx.feng.observers;

import com.jinx.feng.messages.livetrade.LiveTrade;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:43
 * @Desc:Live ticker
 */
public interface LiveTradeObserver {

    void receive(LiveTrade liveTrade);
}
