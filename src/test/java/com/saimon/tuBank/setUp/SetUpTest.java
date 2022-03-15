package com.saimon.tuBank.setUp;

import com.saimon.tuBank.entity.model.BankUser;

public class SetUpTest {

    static String BANKUSER_ID = "ID";
    static String BANKUSER_NAME = "Teste";
    static Integer BANKUSER_OLD = 18;
    static BankUser.GENDER BANKUSER_GENDER = BankUser.GENDER.MALE;
    static BankUser.SCORE BANKUSER_SCORE = BankUser.SCORE.EXCELLENT;

    public static BankUser createUser() {
        BankUser bankUser = new BankUser(BANKUSER_NAME, BANKUSER_OLD);
        bankUser.setId(BANKUSER_ID);
        bankUser.setGender(BANKUSER_GENDER);
        bankUser.setScore(BANKUSER_SCORE);
        return bankUser;
    }

}
