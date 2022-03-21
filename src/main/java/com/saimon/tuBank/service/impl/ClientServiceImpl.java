package com.saimon.tuBank.service.impl;

import com.saimon.tuBank.dto.ClientInformationsDto;
import com.saimon.tuBank.dto.ClientDto;
import com.saimon.tuBank.entity.model.Client;
import com.saimon.tuBank.entity.repository.ClientRepository;
import com.saimon.tuBank.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getUser(String id) throws Exception {
        return clientRepository.findById(id).orElseThrow(() -> new Exception("not found"));
    }

    @Override
    public ClientInformationsDto saveUser(ClientDto clientDto) throws Exception {
        Client client = new Client(clientDto.getLogin(),
                clientDto.getPassword(),
                clientDto.getName(),
                clientDto.getOld(),
                clientDto.getGender());
        client.setGender(clientDto.getGender());
        client.setCreatedAt(new Date());

        if (clientDto.getOld() > 50){
            client.setScore(Client.SCORE.GOOD);
        } else {
            client.setScore(Client.SCORE.GREAT);
        }

        Client savedUser = clientRepository.save(client);
        return new ClientInformationsDto(savedUser.getId(), savedUser.getName(), savedUser.getOld(), savedUser.getGender());
    }

    @Override
    public Client updateUser(String id, ClientDto clientDto) throws Exception {
        Client user = this.getUser(id);
        user.setName(clientDto.getName());
        user.setGender(clientDto.getGender());
        user.setOld(clientDto.getOld());
        user.setUpdatedAt(new Date());

        return clientRepository.save(user);
    }

    @Override
    public void deleteUser(String id) throws Exception {
        Client user = this.getUser(id);
        clientRepository.delete(user);
    }
}
