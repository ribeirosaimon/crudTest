package com.saimon.tuBank.entity.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import java.util.Date;
import java.util.Objects;


@Document
public class Client {

    @Id
    private String id;
    private String login;
    private String password;
    private String name;
    @Min(18)
    private Integer old;
    private CreditCard creditCard;
    private GENDER gender;
    private SCORE score;
    private Date createdAt;
    private Date updatedAt;

    public Client() {

    }

    public Client(String login, String password, String name, Integer old, GENDER gender) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.old = old;
        this.gender = gender;
    }

    public CreditCard getCreditCard() {
        return creditCard;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOld() {
        return old;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOld(Integer old) {
        this.old = old;
    }

    public GENDER getGender() {
        return gender;
    }

    public void setGender(GENDER gender) {
        this.gender = gender;
    }

    public SCORE getScore() {
        return score;
    }

    public void setScore(SCORE score) {
        this.score = score;
    }

    public enum GENDER {
        MALE,
        FEMALE,
        OTHER
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(getId(), client.getId()) && Objects.equals(getLogin(), client.getLogin()) && Objects.equals(getPassword(), client.getPassword()) && Objects.equals(getName(), client.getName()) && Objects.equals(getOld(), client.getOld()) && Objects.equals(getCreditCard(), client.getCreditCard()) && getGender() == client.getGender() && getScore() == client.getScore() && Objects.equals(getCreatedAt(), client.getCreatedAt()) && Objects.equals(getUpdatedAt(), client.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(), getName(), getOld(), getCreditCard(), getGender(), getScore(), getCreatedAt(), getUpdatedAt());
    }

    public enum SCORE {
        EXCELLENT(5),
        GREAT(4),
        GOOD(3),
        BAD(2),
        TERRIBLE(1),
        OWE(0);

        private final int value;

        SCORE(final int newValue) {
            this.value = newValue;
        }

        public int getValue() {
            return this.value;
        }
    }
}
