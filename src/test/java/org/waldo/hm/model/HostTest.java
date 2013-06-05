package org.waldo.hm.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: wangyin
 * Date: 13-6-2
 * Time: 下午11:54
 */
public class HostTest {

    @Test
    public void test_parse_normal() throws Exception {
        String hostBind = "127.0.0.1     localhost";

        Host host = new Host();
        host.parse(hostBind);

        Assert.assertEquals(host.getIp(), IPv4.getInstance("127.0.0.1"));
        Assert.assertEquals(host.getDomainName(), "localhost");
        Assert.assertNull(host.getComment());
    }

    @Test
    public void test_parse_with_comment_1() throws Exception {
        String hostBind = "10.20.24.47    hm.org  #行后注释";

        Host host = new Host();
        host.parse(hostBind);

        Assert.assertEquals(host.getIp(), IPv4.getInstance("10.20.24.47"));
        Assert.assertEquals(host.getDomainName(), "hm.org");
        Assert.assertEquals(host.getComment(), "行后注释");
    }

    @Test
    public void test_parse_with_comment_2() throws Exception {
        String hostBind = "10.20.24.47    hm.org#行后注释";

        Host host = new Host();
        host.parse(hostBind);

        Assert.assertEquals(host.getIp(), IPv4.getInstance("10.20.24.47"));
        Assert.assertEquals(host.getDomainName(), "hm.org");
        Assert.assertEquals(host.getComment(), "行后注释");
    }

    public void test_parse_comment_line() throws Exception {
        String commentLine = "      #   测试整行注释";

        Host host = new Host();
        host.parse(commentLine);

        Assert.assertNull(host.getIp());
        Assert.assertNull(host.getDomainName());
        Assert.assertEquals(host.getComment(), "测试整行注释");
    }

}
