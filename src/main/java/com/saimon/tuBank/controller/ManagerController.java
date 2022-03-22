package com.saimon.tuBank.controller;

import com.saimon.tuBank.entity.model.Manager;
import com.saimon.tuBank.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("{managerId}/givecard/{clientId}")
    public ResponseEntity giveCard(@PathVariable String managerId, @PathVariable String clientId) throws Exception {
        managerService.giveCard(managerId, clientId);
        return null;
    }
}
