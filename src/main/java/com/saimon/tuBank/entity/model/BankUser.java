package com.saimon.tuBank.entity.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import java.util.Objects;


@Document
public class BankUser {

    @Id
    private String id;
    private String login;
    private String password;
    private String name;
    @Min(18)
    private Integer old;
    private GENDER gender;
    private SCORE score;

    public BankUser() {

    }

    public BankUser(String login, String password, String name, Integer old, GENDER gender) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.old = old;
        this.gender = gender;
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
        if (!(o instanceof BankUser)) return false;
        BankUser bankUser = (BankUser) o;
        return Objects.equals(getId(), bankUser.getId()) && Objects.equals(getLogin(), bankUser.getLogin()) && Objects.equals(getPassword(), bankUser.getPassword()) && Objects.equals(getName(), bankUser.getName()) && Objects.equals(getOld(), bankUser.getOld()) && getGender() == bankUser.getGender() && getScore() == bankUser.getScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(), getName(), getOld(), getGender(), getScore());
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
