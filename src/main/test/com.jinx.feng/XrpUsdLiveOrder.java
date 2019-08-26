package com.jinx.feng;

import com.jinx.feng.messages.liveorder.LiveOrder;
import com.jinx.feng.messages.liveorder.LiveOrderData;
import com.jinx.feng.observers.LiveOrderObserver;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:54
 * @Desc:
 */
@Slf4j
public class XrpUsdLiveOrder implements LiveOrderObserver {

    @Override
    public void receive(LiveOrder liveOrder) {
        // A live order has been received, now we can do anything with it:
        if (liveOrder.getEvent().equals("order_created")) {
            LiveOrderData data = liveOrder.getData();
            if (data.getAmount() > 5000) {
                log.info("Live Order: {}", liveOrder);
            } else {
                log.info("Received order {} but it was ignored.", data.getId());
            }
        }
    }


}
