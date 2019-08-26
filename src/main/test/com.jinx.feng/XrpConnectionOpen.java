package com.jinx.feng;

import com.jinx.feng.observers.ConnectionOpenObserver;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:53
 * @Desc:
 */
public class XrpConnectionOpen implements ConnectionOpenObserver {

    @Override
    public void receive() {
        // Connection is open, now we can start subscribing to channels:
        BitstampConnectorEndpoint.onLiveTrade("xrpusd", new XrpUsdLiveTrade());
        BitstampConnectorEndpoint.onLiveOrder("xrpusd", new XrpUsdLiveOrder());
    }

}
