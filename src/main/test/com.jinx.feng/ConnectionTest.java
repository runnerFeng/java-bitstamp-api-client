package com.jinx.feng;

import com.oracle.tools.packager.Log;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @Author: jinx
 * @Date: 2019-08-26 17:53
 * @Desc:
 */
public class ConnectionTest {

    @Test
    public void connect() {
        Log.info("start...");
        BitstampConnectorEndpoint.onOpen(new XrpConnectionOpen());
        try {
            BitstampConnector.connect();
        } catch (InterruptedException | DeploymentException | IOException e) {
            Assert.fail("Exception while connecting");
        }
        new Scanner(System.in).nextLine();
        Log.info("end...");
    }

}
