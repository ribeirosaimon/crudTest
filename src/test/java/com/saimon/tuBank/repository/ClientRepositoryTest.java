package com.saimon.tuBank.repository;

import com.saimon.tuBank.entity.model.Client;
import com.saimon.tuBank.entity.repository.ClientRepository;
import com.saimon.tuBank.util.Creator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataMongoTest(properties = {"spring.mongodb.embedded.version=4.0.2"})
public class ClientRepositoryTest {

    private Client client;

    @Autowired
    ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        this.client = Creator.user();
    }

    @AfterEach
    public void tearDow() {
        clientRepository.deleteById(Creator.BANKUSER_ID);
    }

    @Test
    @DisplayName("Get BankUser by Id")
    public void getUser_Successful() {
        clientRepository.insert(client);
        Assertions.assertTrue(clientRepository.existsById(client.getId()));
    }

    @Test
    @DisplayName("Save BankUser")
    public void saveUser_Successful() {
        clientRepository.save(client);
        Assertions.assertTrue(clientRepository.existsById(client.getId()));
    }

    @Test
    @DisplayName("Update BankUser")
    public void updateUser_Successful() {
        client.setOld(20);
        Client save = clientRepository.save(client);
        Assertions.assertTrue(clientRepository.existsById(client.getId()));
        Assertions.assertEquals(save.getOld(), Creator.UPDATED_OLD);
    }

    @Test
    @DisplayName("Delete BankUser")
    public void deleteUser_ExceptionOldlessThen() {
        clientRepository.delete(client);
        Assertions.assertTrue(!clientRepository.existsById(client.getId()));
    }
}