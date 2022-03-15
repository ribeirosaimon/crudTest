package com.saimon.tuBank.entity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class BankUser {

    @Id
    private String id;
    private String name;
    private Integer old;
    private GENDER gender;
    private SCORE score;

    public BankUser(){

    }
    public BankUser(String name, Integer old){

    }

    public enum GENDER {
        MALE,
        FEMALE,
        OTHER
    }

    public enum SCORE {
        EXCELLENT(5),
        GREAT(4),
        GOOD(3),
        BAD(2),
        TERRIBLE(1),
        OWE(0);

        private final int value;

        SCORE(final int newValue){
            this.value = newValue;
        }

        public int getValue(){
            return this.value;
        }
    }
}
