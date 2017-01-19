package com.appbaco.appbaco.model.entity;

import java.io.Serializable;

/**
 * Created by SMP on 19/01/2017.
 */

public class Configuration implements Serializable {
    Integer id;
    Integer sync;
    String appPin;
    String monetarySymbol;
    String dateFormat;
    String hourFormat;
    String appTheme;

    public Configuration() {
    }

    public Configuration(Integer id, Integer sync, String appPin, String monetarySymbol, String dateFormat, String hourFormat, String appTheme) {
        this.id = id;
        this.sync = sync;
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

    public String getAppPin() {
        return appPin;
    }

    public void setAppPin(String appPin) {
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

        Configuration that = (Configuration) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (sync != null ? !sync.equals(that.sync) : that.sync != null) return false;
        if (appPin != null ? !appPin.equals(that.appPin) : that.appPin != null) return false;
        if (monetarySymbol != null ? !monetarySymbol.equals(that.monetarySymbol) : that.monetarySymbol != null)
            return false;
        if (dateFormat != null ? !dateFormat.equals(that.dateFormat) : that.dateFormat != null)
            return false;
        if (hourFormat != null ? !hourFormat.equals(that.hourFormat) : that.hourFormat != null)
            return false;
        return appTheme != null ? appTheme.equals(that.appTheme) : that.appTheme == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sync != null ? sync.hashCode() : 0);
        result = 31 * result + (appPin != null ? appPin.hashCode() : 0);
        result = 31 * result + (monetarySymbol != null ? monetarySymbol.hashCode() : 0);
        result = 31 * result + (dateFormat != null ? dateFormat.hashCode() : 0);
        result = 31 * result + (hourFormat != null ? hourFormat.hashCode() : 0);
        result = 31 * result + (appTheme != null ? appTheme.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "id=" + id +
                ", sync=" + sync +
                ", appPin='" + appPin + '\'' +
                ", monetarySymbol='" + monetarySymbol + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                ", hourFormat='" + hourFormat + '\'' +
                ", appTheme='" + appTheme + '\'' +
                '}';
    }
}
