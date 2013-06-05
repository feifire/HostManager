package org.waldo.hm.model;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.util.Arrays;

/**
 * IPv4
 */
public class IPv4 implements IP {

    private static final String JOIN_STRING = ".";

    private static final int MAX_SIZE_OF_SEGMENTS = 4;

    private Byte[] segments = new Byte[MAX_SIZE_OF_SEGMENTS];

    public enum SEGMENT {
        A, B, C, D
    }

    public IPv4() {
    }

    public IPv4(String address) {
        parse(address);
    }

    public void setSegment(SEGMENT segment, byte value) {
        if (segment != null) {
            this.segments[segment.ordinal()] = value;
        }
    }

    @Override
    public IP parse(String address) {
        if (StringUtils.isNotBlank(address)) {
            String[] addressSegment = StringUtils.split(address, JOIN_STRING, MAX_SIZE_OF_SEGMENTS);
            if (addressSegment.length != MAX_SIZE_OF_SEGMENTS) {
                throw new RuntimeException("Wrong IPv4 format!");
            }
            for (int i = 0, len = addressSegment.length; i < len; i++) {
                segments[i] = NumberUtils.toByte(addressSegment[i]);
            }
        }
        return this;
    }

    @Override
    public String getAddress() {
        return StringUtils.join(segments, JOIN_STRING);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{address=" + getAddress() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IPv4 iPv4 = (IPv4) o;

        return Arrays.equals(segments, iPv4.segments);

    }

    @Override
    public int hashCode() {
        return segments != null ? Arrays.hashCode(segments) : 0;
    }

    public static IPv4 getInstance(String address) {
        return new IPv4(address);
    }
}
