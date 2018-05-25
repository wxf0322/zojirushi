/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/4/26 8:45
 */
@Entity
@Table(name = "APPLICATIONS")
public class ApplicationEntity {
    /**
     * 标识
     */
    private Long id;

    /**
     * 唯一标识码
     */
    private String clientId;

    /**
     * 接入应用密码
     */
    private String clientSecret;

    /**
     * 接入应用编码
     */
    private String label;

    /**
     * 接入应用名称
     */
    private String name;

    /**
     * 接入应用首页地址
     */
    private String clientUri;

    /**
     * 接入应用验证回调地址
     */
    private String redirectUri;
    /**
     * 接入应用登出地址，用于单点登出
     */
    private String logoutUri;

    /**
     * 接入应用图标地址
     */
    private String logoUri;

    /**
     * 接入应用描述
     */
    private String clientDesc;

    /**
     * 可用状态(1;正常 0:冻结)
     */
    private Long enabled;

    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CLIENT_ID")
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "CLIENT_SECRET")
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Basic
    @Column(name = "LABEL")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "CLIENT_URI")
    public String getClientUri() {
        return clientUri;
    }

    public void setClientUri(String clientUri) {
        this.clientUri = clientUri;
    }

    @Basic
    @Column(name = "REDIRECT_URI")
    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    @Basic
    @Column(name = "LOGOUT_URI")
    public String getLogoutUri() {
        return logoutUri;
    }

    public void setLogoutUri(String logoutUri) {
        this.logoutUri = logoutUri;
    }

    @Basic
    @Column(name = "LOGO_URI")
    public String getLogoUri() {
        return logoUri;
    }

    public void setLogoUri(String logoUri) {
        this.logoUri = logoUri;
    }

    @Basic
    @Column(name = "CLIENT_DESC")
    public String getClientDesc() {
        return clientDesc;
    }

    public void setClientDesc(String clientDesc) {
        this.clientDesc = clientDesc;
    }

    @Basic
    @Column(name = "ENABLED")
    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationEntity that = (ApplicationEntity) o;

        if (id != that.id) return false;
        if (clientId != null ? !clientId.equals(that.clientId) : that.clientId != null) return false;
        if (clientSecret != null ? !clientSecret.equals(that.clientSecret) : that.clientSecret != null) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (clientUri != null ? !clientUri.equals(that.clientUri) : that.clientUri != null) return false;
        if (redirectUri != null ? !redirectUri.equals(that.redirectUri) : that.redirectUri != null) return false;
        if (logoutUri != null ? !logoutUri.equals(that.logoutUri) : that.logoutUri != null) return false;
        if (logoUri != null ? !logoUri.equals(that.logoUri) : that.logoUri != null) return false;
        if (clientDesc != null ? !clientDesc.equals(that.clientDesc) : that.clientDesc != null) return false;
        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        result = 31 * result + (clientSecret != null ? clientSecret.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (clientUri != null ? clientUri.hashCode() : 0);
        result = 31 * result + (redirectUri != null ? redirectUri.hashCode() : 0);
        result = 31 * result + (logoutUri != null ? logoutUri.hashCode() : 0);
        result = 31 * result + (logoUri != null ? logoUri.hashCode() : 0);
        result = 31 * result + (clientDesc != null ? clientDesc.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApplicationEntity{" +
                "id=" + id +
                ", clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", label='" + label + '\'' +
                ", name='" + name + '\'' +
                ", clientUri='" + clientUri + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                ", logoutUri='" + logoutUri + '\'' +
                ", logoUri='" + logoUri + '\'' +
                ", clientDesc='" + clientDesc + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
