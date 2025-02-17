package com.eauts.ems.Eauts_management.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PreAuthorize("hasRole('ADMIN')") // Chỉ ADMIN mới được truy cập
    @GetMapping("/dashboard")
    public String getAdminDashboard() {
        return "Welcome to Admin Dashboard!";
    }
}
