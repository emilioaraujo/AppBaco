package com.appbaco.appbaco.models.entity;

import java.io.Serializable;

/**
 * Created by SMP on 19/01/2017.
 */

public class TransactionDetail implements Serializable {
    Integer id;
    Integer sync;
    Integer transactionId;
    Integer accountId;
    Integer transactionCategoryId;
    Double amount;

    public TransactionDetail() {
        this.id = 0;
        this.sync = 0;
        this.transactionId = 0;
        this.accountId = 0;
        this.transactionCategoryId = 0;
        this.amount = 0D;
    }

    public TransactionDetail(Integer id, Integer sync, Integer transactionId, Integer accountId, Integer transactionCategoryId, Double amount) {
        this.id = id;
        this.sync = sync;
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.transactionCategoryId = transactionCategoryId;
        this.amount = amount;
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

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getTransactionCategoryId() {
        return transactionCategoryId;
    }

    public void setTransactionCategoryId(Integer transactionCategoryId) {
        this.transactionCategoryId = transactionCategoryId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionDetail that = (TransactionDetail) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (sync != null ? !sync.equals(that.sync) : that.sync != null) return false;
        if (transactionId != null ? !transactionId.equals(that.transactionId) : that.transactionId != null)
            return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null)
            return false;
        if (transactionCategoryId != null ? !transactionCategoryId.equals(that.transactionCategoryId) : that.transactionCategoryId != null)
            return false;
        return amount != null ? amount.equals(that.amount) : that.amount == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sync != null ? sync.hashCode() : 0);
        result = 31 * result + (transactionId != null ? transactionId.hashCode() : 0);
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (transactionCategoryId != null ? transactionCategoryId.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransactionDetail{" +
                "id=" + id +
                ", sync=" + sync +
                ", transactionId=" + transactionId +
                ", accountId=" + accountId +
                ", transactionCategoryId=" + transactionCategoryId +
                ", amount=" + amount +
                '}';
    }
}
