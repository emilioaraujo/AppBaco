package com.appbaco.appbaco.models.entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by SMP on 19/01/2017.
 */

public class TransactionHeader {
    Integer id;
    Integer sync;
    Integer transactionTypeId;
    Date dateTimeCreate;
    Date dateTime;
    Integer complete;
    String concept;
    String image;
    String location;
    ArrayList<TransactionDetail> transactionDetails;

    public TransactionHeader() {
        this.id = 0;
        this.sync = 0;
        this.transactionTypeId = 0;
        this.dateTimeCreate = new Date();
        this.dateTime = new Date();
        this.complete = 0;
        this.concept = "";
        this.image = "";
        this.location = "";
        this.transactionDetails = new ArrayList<>();
    }

    public TransactionHeader(Integer id, Integer sync, Integer transactionTypeId, Date dateTimeCreate, Date dateTime, Integer complete, String concept, String image, String location, ArrayList<TransactionDetail> transactionDetails) {
        this.id = id;
        this.sync = sync;
        this.transactionTypeId = transactionTypeId;
        this.dateTimeCreate = dateTimeCreate;
        this.dateTime = dateTime;
        this.complete = complete;
        this.concept = concept;
        this.image = image;
        this.location = location;
        this.transactionDetails=transactionDetails;
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

    public Date getDateTimeCreate() {
        return dateTimeCreate;
    }

    public void setDateTimeCreate(Date dateTimeCreate) {
        this.dateTimeCreate = dateTimeCreate;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<TransactionDetail> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(ArrayList<TransactionDetail> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionHeader that = (TransactionHeader) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (sync != null ? !sync.equals(that.sync) : that.sync != null) return false;
        if (transactionTypeId != null ? !transactionTypeId.equals(that.transactionTypeId) : that.transactionTypeId != null)
            return false;
        if (dateTimeCreate != null ? !dateTimeCreate.equals(that.dateTimeCreate) : that.dateTimeCreate != null)
            return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null)
            return false;
        if (complete != null ? !complete.equals(that.complete) : that.complete != null)
            return false;
        if (concept != null ? !concept.equals(that.concept) : that.concept != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        return location != null ? location.equals(that.location) : that.location == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sync != null ? sync.hashCode() : 0);
        result = 31 * result + (transactionTypeId != null ? transactionTypeId.hashCode() : 0);
        result = 31 * result + (dateTimeCreate != null ? dateTimeCreate.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (complete != null ? complete.hashCode() : 0);
        result = 31 * result + (concept != null ? concept.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransactionHeader{" +
                "id=" + id +
                ", sync=" + sync +
                ", transactionTypeId=" + transactionTypeId +
                ", dateTimeCreate=" + dateTimeCreate +
                ", dateTime=" + dateTime +
                ", complete=" + complete +
                ", concept='" + concept + '\'' +
                ", image='" + image + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
