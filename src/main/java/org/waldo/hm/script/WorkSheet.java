package org.waldo.hm.script;

/**
 * Created with IntelliJ IDEA.
 * User: wangyin
 * Date: 13-6-6
 * Time: 上午1:11
 * To change this template use File | Settings | File Templates.
 */
public class WorkSheet implements ICase {

    private String type;

    private Long orderId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
