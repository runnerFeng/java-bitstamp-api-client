package com.jinx.feng.observers;

import com.jinx.feng.messages.liveorder.LiveOrder;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:43
 * @Desc:
 */
public interface LiveOrderObserver {

    /**
     * Receive an order that was created, changed or deleted.
     */
    void receive(LiveOrder liveOrder);

}
