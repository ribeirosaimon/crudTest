package com.saimon.tuBank.controller;

import com.saimon.tuBank.dto.ClientDto;
import com.saimon.tuBank.dto.ClientInformationsDto;
import com.saimon.tuBank.entity.model.Client;
import com.saimon.tuBank.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable String id) throws Exception {
        Client user = clientService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/add")
    public ResponseEntity saveUser(@RequestBody @Valid ClientDto clientDto) throws Exception {
        ClientInformationsDto clientInformationsDto = clientService.saveUser(clientDto);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/bankuser/".concat(clientInformationsDto.getId()))
                .toString());

        return ResponseEntity.created(uri).body(clientInformationsDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody @Valid ClientDto clientDto) throws Exception {
        Client user = clientService.updateUser(id, clientDto);

        return ResponseEntity.accepted().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) throws Exception {
        clientService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
