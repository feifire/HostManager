package org.waldo.hm.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: wangyin
 * Date: 13-6-3
 * Time: 上午2:03
 */
public class IPv6Test {
    @Test
    public void testParse() throws Exception {

    }

    @Test
    public void testGetAddress() throws Exception {
        String sIPv6 = "ff02::0";
        IPv6 iPv6 = new IPv6();
        iPv6.parse(sIPv6);

        Assert.assertEquals(sIPv6, iPv6.getAddress());
    }
}
