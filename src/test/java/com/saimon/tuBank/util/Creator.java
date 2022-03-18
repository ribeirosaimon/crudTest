package com.saimon.tuBank.util;

import com.saimon.tuBank.dto.BankInformationsDTO;
import com.saimon.tuBank.dto.BankUserDTO;
import com.saimon.tuBank.entity.model.BankUser;
import org.bson.types.ObjectId;

public class Creator {

    //BANK USER INFORMATIONS
    public static String BANKUSER_ID = new ObjectId().toString();
    public static String BANKUSER_NAME = "Teste";
    public static String BANKUSER_LOGIN = "testeLogin";
    public static String BANKUSER_PASSWORD = "0123456";
    public static Integer BANKUSER_OLD = 18;
    public static Integer UPDATED_OLD = 20;
    public static Integer EXCEPTION_OLD = 15;
    public static BankUser.GENDER BANKUSER_GENDER = BankUser.GENDER.MALE;
    public static BankUser.SCORE BANKUSER_SCORE = BankUser.SCORE.EXCELLENT;

    //API URLS
    public static String BANKUSER_API = "/api/bankuser";

    //MENSSAGES ERRORS
    public static String OLD_ERROR_MESSAGE = "You are not old enough to open an account";


    public static BankUser user() {
        BankUser bankUser = new BankUser(BANKUSER_LOGIN, BANKUSER_PASSWORD, BANKUSER_NAME, BANKUSER_OLD, BANKUSER_GENDER);
        bankUser.setId(BANKUSER_ID);
        bankUser.setGender(BANKUSER_GENDER);
        bankUser.setScore(BANKUSER_SCORE);
        return bankUser;
    }

    public static BankUserDTO userDto() {
        return new BankUserDTO(BANKUSER_ID,
                BANKUSER_NAME,
                BANKUSER_LOGIN,
                BANKUSER_PASSWORD,
                BANKUSER_OLD,
                BANKUSER_GENDER);
    }

    public static BankInformationsDTO InfoUserDto() {
        return new BankInformationsDTO(BANKUSER_ID,
                BANKUSER_NAME,
                BANKUSER_OLD,
                BANKUSER_GENDER);
    }
}
