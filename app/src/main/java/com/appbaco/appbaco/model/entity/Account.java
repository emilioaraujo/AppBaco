package com.appbaco.appbaco.model.entity;

import java.io.Serializable;

/**
 * Created by SMP on 19/01/2017.
 */

public class Account implements Serializable {
    Integer id;
    Integer sync;
    Integer accountTypeId;
    String name;
    String description;
    Double initialBalance;
    Double amountLimit;
    Integer dayPay;
    Integer expireMonth;
    Integer expireYear;
    Integer color;

    public Account() {
    }

    public Account(Integer id, Integer sync, Integer accountTypeId, String name, String description, Double initialBalance, Double amountLimit, Integer dayPay, Integer expireDay, Integer expireYear, Integer color) {
        this.id = id;
        this.sync = sync;
        this.accountTypeId = accountTypeId;
        this.name = name;
        this.description = description;
        this.initialBalance = initialBalance;
        this.amountLimit = amountLimit;
        this.dayPay = dayPay;
        this.expireMonth = expireDay;
        this.expireYear = expireYear;
        this.color = color;
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

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Double getAmountLimit() {
        return amountLimit;
    }

    public void setAmountLimit(Double amountLimit) {
        this.amountLimit = amountLimit;
    }

    public Integer getDayPay() {
        return dayPay;
    }

    public void setDayPay(Integer dayPay) {
        this.dayPay = dayPay;
    }

    public Integer getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(Integer expireDay) {
        this.expireMonth = expireDay;
    }

    public Integer getExpireYear() {
        return expireYear;
    }

    public void setExpireYear(Integer expireYear) {
        this.expireYear = expireYear;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer expireColor) {
        this.color = expireColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (id != null ? !id.equals(account.id) : account.id != null) return false;
        if (sync != null ? !sync.equals(account.sync) : account.sync != null) return false;
        if (accountTypeId != null ? !accountTypeId.equals(account.accountTypeId) : account.accountTypeId != null)
            return false;
        if (name != null ? !name.equals(account.name) : account.name != null) return false;
        if (description != null ? !description.equals(account.description) : account.description != null)
            return false;
        if (initialBalance != null ? !initialBalance.equals(account.initialBalance) : account.initialBalance != null)
            return false;
        if (amountLimit != null ? !amountLimit.equals(account.amountLimit) : account.amountLimit != null)
            return false;
        if (dayPay != null ? !dayPay.equals(account.dayPay) : account.dayPay != null) return false;
        if (expireMonth != null ? !expireMonth.equals(account.expireMonth) : account.expireMonth != null)
            return false;
        if (expireYear != null ? !expireYear.equals(account.expireYear) : account.expireYear != null)
            return false;
        return color != null ? color.equals(account.color) : account.color == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sync != null ? sync.hashCode() : 0);
        result = 31 * result + (accountTypeId != null ? accountTypeId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (initialBalance != null ? initialBalance.hashCode() : 0);
        result = 31 * result + (amountLimit != null ? amountLimit.hashCode() : 0);
        result = 31 * result + (dayPay != null ? dayPay.hashCode() : 0);
        result = 31 * result + (expireMonth != null ? expireMonth.hashCode() : 0);
        result = 31 * result + (expireYear != null ? expireYear.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", sync=" + sync +
                ", accountTypeId=" + accountTypeId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", initialBalance=" + initialBalance +
                ", amountLimit=" + amountLimit +
                ", dayPay=" + dayPay +
                ", expireMonth=" + expireMonth +
                ", expireYear=" + expireYear +
                ", color=" + color +
                '}';
    }
}
