package com.saimon.tuBank.entity.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Document
public class CreditCard {
    @Id
    private String id;
    private Agency agency;
    private Client client;
    private Long cardLimit;
    private Long dailyLimit;
    private Long preApproved;
    @NotNull
    private String numberCard;
    private Date expiration;
    private Manager updatedBy;
    private Date createdAt;
    private Date updatedAt;

    public CreditCard(){

    }

    public CreditCard(Agency agency, Client client) {
        this.agency = agency;
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(Long cardLimit) {
        this.cardLimit = cardLimit;
    }

    public Long getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(Long dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public Long getPreApproved() {
        return preApproved;
    }

    public void setPreApproved(Long preApproved) {
        this.preApproved = preApproved;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Manager getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Manager updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard)) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getAgency(), that.getAgency()) && Objects.equals(getClient(), that.getClient()) && Objects.equals(getCardLimit(), that.getCardLimit()) && Objects.equals(getDailyLimit(), that.getDailyLimit()) && Objects.equals(getPreApproved(), that.getPreApproved()) && Objects.equals(getNumberCard(), that.getNumberCard()) && Objects.equals(getExpiration(), that.getExpiration()) && Objects.equals(getUpdatedBy(), that.getUpdatedBy()) && Objects.equals(getCreatedAt(), that.getCreatedAt()) && Objects.equals(getUpdatedAt(), that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAgency(), getClient(), getCardLimit(), getDailyLimit(), getPreApproved(), getNumberCard(), getExpiration(), getUpdatedBy(), getCreatedAt(), getUpdatedAt());
    }
}
