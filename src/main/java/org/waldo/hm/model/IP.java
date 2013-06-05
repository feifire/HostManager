package org.waldo.hm.model;

/**
 * IP接口
 */
public interface IP {

    IP parse(String address);

    String getAddress();

}
