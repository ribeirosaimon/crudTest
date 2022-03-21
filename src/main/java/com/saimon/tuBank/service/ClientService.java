package com.saimon.tuBank.service;

import com.saimon.tuBank.dto.ClientInformationsDto;
import com.saimon.tuBank.dto.ClientDto;
import com.saimon.tuBank.entity.model.Client;

public interface ClientService {
    Client getUser(String id) throws Exception;
    ClientInformationsDto saveUser(ClientDto clientDto) throws Exception;
    Client updateUser(String id, ClientDto clientDto) throws Exception;
    void deleteUser(String id) throws Exception;
}
