package org.waldo.hm.model;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.util.Arrays;

/**
 * User: wangyin
 * Date: 13-6-3
 * Time: 上午12:28
 */
public class IPv6 implements IP {

    private static final String JOIN_STRING = ":";

    private static final int MAX_SIZE_OF_SEGMENTS = 8;

    private Integer[] segments = new Integer[MAX_SIZE_OF_SEGMENTS];

    public IPv6() {
        Arrays.fill(segments, 0);
    }

    public IPv6(String address) {
        this();
        parse(address);
    }

    public static IP getInstance(String address) {
        return new IPv6(address);
    }

    @Override
    public IP parse(String address) {
        if (StringUtils.isNotBlank(address)) {
            if (StringUtils.startsWith(address, "::") || StringUtils.startsWith(address, ":::") || StringUtils.startsWithIgnoreCase(address, "::ffff:")) { // IPv4的兼容格式

            } else {
                String[] addressSegment = StringUtils.splitPreserveAllTokens(address, JOIN_STRING, MAX_SIZE_OF_SEGMENTS);
                if (addressSegment.length < 8) {
                    for (int i = 0, offset = 0, len = addressSegment.length; i < len; i++) {
                        if (StringUtils.isEmpty(addressSegment[i])) {
                            for (int j = 0, pad = MAX_SIZE_OF_SEGMENTS - addressSegment.length; j <= pad; j++) {
                                segments[offset] = 0;
                                offset++;
                            }
                        } else {
                            segments[offset] = Integer.parseInt(StringUtils.defaultIfBlank(addressSegment[i], "0"), 16);
                            offset++;
                        }
                    }
                } else {
                    for (int i = 0, len = addressSegment.length; i < len; i++) {
                        segments[i] = Integer.parseInt(StringUtils.defaultIfBlank(addressSegment[i], "0"), 16);
                    }
                }
            }
        }
        return this;
    }

    @Override
    public String getAddress() {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < MAX_SIZE_OF_SEGMENTS; i++) {
            if (i > 0) {
                if (!(buf.length() > 2 && buf.charAt(buf.length() - 1) == ':' && buf.charAt(buf.length() - 2) == ':')) {
                    buf.append(JOIN_STRING);
                }
            }
            if (segments[i] > 0 || (i + 1) == MAX_SIZE_OF_SEGMENTS) {
                buf.append(Integer.toHexString(segments[i]));
            } else {
                if (!(buf.length() > 2 && buf.charAt(buf.length() - 1) == ':' && buf.charAt(buf.length() - 2) == ':')) {
                    buf.append(JOIN_STRING);
                }
            }
        }
        return buf.toString();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{address=" + getAddress() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IPv6 iPv6 = (IPv6) o;

        if (!Arrays.equals(segments, iPv6.segments)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return segments != null ? Arrays.hashCode(segments) : 0;
    }
}
