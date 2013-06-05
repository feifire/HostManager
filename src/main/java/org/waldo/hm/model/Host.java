package org.waldo.hm.model;

import org.apache.commons.lang.StringUtils;

/**
 * User: wangyin
 * Date: 13-6-2
 * Time: 下午10:27
 */
public class Host {

    private static final String COMMENT_SYMBOL = "#";

    /**
     * 是否无效的
     */
    private boolean invalid;

    /**
     * IP地址
     */
    private IP ip;

    /**
     * 域名
     */
    private String domainName;

    /**
     * 注释
     */
    private String comment;

    public Host() {

    }

    public Host(String hostBind) {
        parse(hostBind);
    }

    public static Host getInstance(String hostBind) {
        return new Host(hostBind);
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public IP getIp() {
        return ip;
    }

    public void setIp(IP ip) {
        this.ip = ip;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Host parse(String hostBind) {
        if (StringUtils.isNotBlank(hostBind)) {
            if (StringUtils.startsWith(StringUtils.trim(hostBind), COMMENT_SYMBOL)) {
                comment = StringUtils.trim(StringUtils.substringAfter(hostBind, COMMENT_SYMBOL));
            } else {
                String[] parts = StringUtils.split(hostBind, null, 2);
                if (parts.length < 2) {
                    throw new RuntimeException("Wrong host bind.");
                }
                try {
                    ip = IPv4.getInstance(parts[0]);
                } catch (Exception e) {
                    ip = IPv6.getInstance(parts[0]);
                }
                if (StringUtils.contains(parts[1], COMMENT_SYMBOL)) { // 包含注释
                    String[] temp = StringUtils.split(parts[1], COMMENT_SYMBOL, 2);
                    domainName = StringUtils.trim(temp[0]);
                    comment = StringUtils.trim(temp[1]);
                } else {
                    domainName = parts[1];
                }
                invalid = true;
            }
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Host host = (Host) o;

        if (invalid != host.invalid) return false;
        if (comment != null ? !comment.equals(host.comment) : host.comment != null) return false;
        if (domainName != null ? !domainName.equals(host.domainName) : host.domainName != null) return false;
        if (ip != null ? !ip.equals(host.ip) : host.ip != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (invalid ? 1 : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (domainName != null ? domainName.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Host{" +
                "invalid=" + invalid +
                ", ip=" + ip +
                ", domainName='" + domainName + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
