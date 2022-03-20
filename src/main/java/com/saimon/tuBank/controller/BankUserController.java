package com.saimon.tuBank.controller;

import com.saimon.tuBank.dto.BankInformationsDTO;
import com.saimon.tuBank.dto.BankUserDTO;
import com.saimon.tuBank.entity.model.BankUser;
import com.saimon.tuBank.service.BankUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/bankuser")
public class BankUserController {

    private final BankUserService bankUserService;

    public BankUserController(BankUserService bankUserService) {
        this.bankUserService = bankUserService;
    }

    @GetMapping("/{id}")
    public BankUser getUser(@PathVariable String id) throws Exception {
        return bankUserService.getUser(id);
    }

    @PostMapping("/add")
    public ResponseEntity saveUser(@RequestBody @Valid BankUserDTO bankUserDTO) throws Exception {
        BankInformationsDTO bankInformationsDTO = bankUserService.saveUser(bankUserDTO);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/bankuser/".concat(bankInformationsDTO.getId()))
                .toString());

        return ResponseEntity.created(uri).body(bankInformationsDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody @Valid BankUserDTO bankUserDTO) throws Exception {
        BankUser user = bankUserService.updateUser(id, bankUserDTO);

        return ResponseEntity.accepted().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) throws Exception {
        bankUserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUSer(@PathVariable String id) throws Exception {
        bankUserService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
