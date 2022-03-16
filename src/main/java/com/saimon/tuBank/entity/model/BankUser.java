package com.saimon.tuBank.entity.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


@Document
public class BankUser {

    @Id
    private String id;
    private String name;
    private Integer old;
    private GENDER gender;
    private SCORE score;

    public BankUser() {

    }

    public BankUser(String name, Integer old) {
        this.name = name;
        this.old = old;
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
        return Objects.equals(getId(), bankUser.getId()) &&
                Objects.equals(getName(), bankUser.getName()) &&
                Objects.equals(getOld(), bankUser.getOld()) &&
                getGender() == bankUser.getGender() &&
                getScore() == bankUser.getScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getOld(), getGender(), getScore());
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
