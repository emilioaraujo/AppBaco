package com.appbaco.appbaco.models.entity;

import java.io.Serializable;

/**
 * Created by SMP on 19/01/2017.
 */

public class TransactionCategory implements Serializable {
    Integer id;
    Integer sync;
    Integer transactionTypeId;
    String name;
    String description;
    Integer color;

    public TransactionCategory() {
        this.id = 0;
        this.sync = 0;
        this.transactionTypeId = 0;
        this.name = "";
        this.description = "";
        this.color = 0xffe57373;
    }

    public TransactionCategory(Integer id, Integer sync, Integer transactionTypeId, String name, String description, Integer color) {
        this.id = id;
        this.sync = sync;
        this.transactionTypeId = transactionTypeId;
        this.name = name;
        this.description = description;
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

    public Integer getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
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

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionCategory that = (TransactionCategory) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (sync != null ? !sync.equals(that.sync) : that.sync != null) return false;
        if (transactionTypeId != null ? !transactionTypeId.equals(that.transactionTypeId) : that.transactionTypeId != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        return color != null ? color.equals(that.color) : that.color == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sync != null ? sync.hashCode() : 0);
        result = 31 * result + (transactionTypeId != null ? transactionTypeId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransactionCategory{" +
                "id=" + id +
                ", sync=" + sync +
                ", transactionTypeId=" + transactionTypeId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", color=" + color +
                '}';
    }
}
