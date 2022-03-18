package com.saimon.tuBank.controller;

import com.saimon.tuBank.dto.BankUserDTO;
import com.saimon.tuBank.entity.model.BankUser;
import com.saimon.tuBank.service.BankUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public BankUser saveUser(@RequestBody @Valid BankUserDTO bankUserDTO) throws Exception {
        return bankUserService.saveUser(bankUserDTO);
    }

    @PutMapping("/{id}")
    public BankUser updateUser(@PathVariable String id, @RequestBody @Valid BankUserDTO bankUserDTO) throws Exception {
        return bankUserService.updateUser(id, bankUserDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUSer(@PathVariable String id) throws Exception {
        bankUserService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
