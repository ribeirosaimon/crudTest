package com.saimon.tuBank.controller;

import com.saimon.tuBank.entity.model.Manager;
import com.saimon.tuBank.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getManager(@PathVariable String id) throws Exception {
        Manager manager = managerService.getManager(id);
        return ResponseEntity.ok(manager);
    }
}
