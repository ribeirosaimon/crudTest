package com.saimon.tuBank.util;

import com.saimon.tuBank.dto.ClientInformationsDto;
import com.saimon.tuBank.dto.ClientDto;
import com.saimon.tuBank.entity.model.Client;
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
    public static Client.GENDER BANKUSER_GENDER = Client.GENDER.MALE;
    public static Client.SCORE BANKUSER_SCORE = Client.SCORE.EXCELLENT;

    //API URLS
    public static String BANKUSER_API = "/api/client";

    //MENSSAGES ERRORS
    public static String OLD_ERROR_MESSAGE = "You are not old enough to open an account";


    public static Client user() {
        Client client = new Client(BANKUSER_LOGIN, BANKUSER_PASSWORD, BANKUSER_NAME, BANKUSER_OLD, BANKUSER_GENDER);
        client.setId(BANKUSER_ID);
        client.setGender(BANKUSER_GENDER);
        client.setScore(BANKUSER_SCORE);
        return client;
    }

    public static ClientDto userDto() {
        return new ClientDto(BANKUSER_ID,
                BANKUSER_NAME,
                BANKUSER_LOGIN,
                BANKUSER_PASSWORD,
                BANKUSER_OLD,
                BANKUSER_GENDER);
    }

    public static ClientInformationsDto InfoUserDto() {
        return new ClientInformationsDto(BANKUSER_ID,
                BANKUSER_NAME,
                BANKUSER_OLD,
                BANKUSER_GENDER);
    }

    public static ClientDto changeToDto(Client client){
        return new ClientDto(client.getId(),
                client.getName(),
                client.getLogin(),
                client.getPassword(),
                client.getOld(),
                client.getGender());
    }
}
