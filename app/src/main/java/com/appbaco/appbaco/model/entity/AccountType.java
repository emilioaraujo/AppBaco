package com.appbaco.appbaco.model.entity;

import java.io.Serializable;

/**
 * Created by SMP on 19/01/2017.
 */

public class AccountType implements Serializable {
    Integer id;
    Integer sync;
    Integer accountCategoryId;
    String name;

    public AccountType() {
    }

    public AccountType(Integer id, Integer sync, Integer accountCategoryId, String name) {
        this.id = id;
        this.sync = sync;
        this.accountCategoryId = accountCategoryId;
        this.name = name;
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

    public Integer getAccountCategoryId() {
        return accountCategoryId;
    }

    public void setAccountCategoryId(Integer accountCategoryId) {
        this.accountCategoryId = accountCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountType that = (AccountType) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (sync != null ? !sync.equals(that.sync) : that.sync != null) return false;
        if (accountCategoryId != null ? !accountCategoryId.equals(that.accountCategoryId) : that.accountCategoryId != null)
            return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sync != null ? sync.hashCode() : 0);
        result = 31 * result + (accountCategoryId != null ? accountCategoryId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "id=" + id +
                ", sync=" + sync +
                ", accountCategoryId=" + accountCategoryId +
                ", name='" + name + '\'' +
                '}';
    }
}
