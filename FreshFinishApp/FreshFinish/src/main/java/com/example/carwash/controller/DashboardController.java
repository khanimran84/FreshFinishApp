package com.example.carwash.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    // Dashboard page
    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        // Check if user is logged in
        if (session == null || session.getAttribute("userId") == null) {
            return "redirect:/login"; // redirect if not logged in
        }

        // Optional: you can check role here if needed
        String role = (String) session.getAttribute("userRole");
        if (!role.equalsIgnoreCase("CUSTOMER") && !role.equalsIgnoreCase("SELLER")) {
            return "redirect:/login?error=role";
        }

        return "Dashboard/customer_dashboard";
    }

}
