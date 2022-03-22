package com.saimon.tuBank.service.impl;

import com.saimon.tuBank.entity.model.Client;
import com.saimon.tuBank.entity.model.CreditCard;
import com.saimon.tuBank.entity.model.Manager;
import com.saimon.tuBank.entity.repository.ClientRepository;
import com.saimon.tuBank.entity.repository.ManagerRepository;
import com.saimon.tuBank.service.ClientService;
import com.saimon.tuBank.service.ManagerService;
import org.springframework.stereotype.Service;

@Service
public class ManageServiceImpl implements ManagerService {
    private final ClientService clientService;
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;

    public ManageServiceImpl(ClientService clientService,
                             ClientRepository clientRepository,
                             ManagerRepository managerRepository) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
    }

    @Override
    public Manager getManager(String id) throws Exception {
        return managerRepository.findById(id).orElseThrow(() -> new Exception("Manager not found"));

    }

    @Override
    public void giveCard(String managerId, String clientId) throws Exception {
        Manager manager = this.getManager(managerId);
        Client client = clientService.getUser(clientId);
        CreditCard creditCard = new CreditCard(manager.getAgency(), client);
        creditCard.set
    }
}
