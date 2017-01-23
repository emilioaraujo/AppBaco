package com.appbaco.appbaco.models.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by SMP on 19/01/2017.
 */

public class AppbacoConfiguration implements Serializable {
    Integer id;
    Integer sync;
    Integer security;
    Integer appPin;
    String monetarySymbol;
    String dateFormat;
    String hourFormat;
    String appTheme;

    public AppbacoConfiguration() {
    }

    public AppbacoConfiguration(Integer id, Integer sync, Integer security, Integer appPin, String monetarySymbol, String dateFormat, String hourFormat, String appTheme) {
        this.id = id;
        this.sync = sync;
        this.security = security;
        this.appPin = appPin;
        this.monetarySymbol = monetarySymbol;
        this.dateFormat = dateFormat;
        this.hourFormat = hourFormat;
        this.appTheme = appTheme;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSync() {
        return sync;
    }

    public void setSync(Integer sync) {
        this.sync = sync;
    }

    public Integer getSecurity() {
        return security;
    }

    public void setSecurity(Integer security) {
        this.security = security;
    }

    public Integer getAppPin() {
        return appPin;
    }

    public void setAppPin(Integer appPin) {
        this.appPin = appPin;
    }

    public String getMonetarySymbol() {
        return monetarySymbol;
    }

    public void setMonetarySymbol(String monetarySymbol) {
        this.monetarySymbol = monetarySymbol;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getHourFormat() {
        return hourFormat;
    }

    public void setHourFormat(String hourFormat) {
        this.hourFormat = hourFormat;
    }

    public String getAppTheme() {
        return appTheme;
    }

    public void setAppTheme(String appTheme) {
        this.appTheme = appTheme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppbacoConfiguration that = (AppbacoConfiguration) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(sync, that.sync) &&
                Objects.equals(security, that.security) &&
                Objects.equals(appPin, that.appPin) &&
                Objects.equals(monetarySymbol, that.monetarySymbol) &&
                Objects.equals(dateFormat, that.dateFormat) &&
                Objects.equals(hourFormat, that.hourFormat) &&
                Objects.equals(appTheme, that.appTheme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sync, security, appPin, monetarySymbol, dateFormat, hourFormat, appTheme);
    }

    @Override
    public String toString() {
        return "AppbacoConfiguration{" +
                "id=" + id +
                ", sync=" + sync +
                ", security=" + security +
                ", appPin=" + appPin +
                ", monetarySymbol='" + monetarySymbol + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                ", hourFormat='" + hourFormat + '\'' +
                ", appTheme='" + appTheme + '\'' +
                '}';
    }
}
