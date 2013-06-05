package org.waldo.hm.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: wangyin
 * Date: 13-6-2
 * Time: 下午11:02
 */
public class IPv4Test {
    @Test
    public void testParse() throws Exception {
        IPv4 address = new IPv4();
    }

    @Test
    public void testGetAddress() throws Exception {
        IPv4 address = new IPv4("127.0.0.1");
        Assert.assertEquals("127.0.0.1", address.getAddress());
    }
}
