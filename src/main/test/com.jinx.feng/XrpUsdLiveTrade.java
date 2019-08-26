package com.jinx.feng;

import com.jinx.feng.messages.livetrade.LiveTrade;
import com.jinx.feng.messages.livetrade.LiveTradeData;
import com.jinx.feng.observers.LiveTradeObserver;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:54
 * @Desc:
 */
@Slf4j
public class XrpUsdLiveTrade implements LiveTradeObserver {

    @Override
    public void receive(LiveTrade liveTrade) {
        // A live trade has been received, now we can do anything with it:
        LiveTradeData data = liveTrade.getData();
        if (data.getAmount() > 5000) {
            log.info("Live Trade: {}", liveTrade);
        } else {
            log.info("Received trade {} but it was ignored.", data.getId());
        }
    }

}
