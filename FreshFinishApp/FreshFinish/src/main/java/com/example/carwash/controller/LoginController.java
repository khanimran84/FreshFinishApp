package com.example.carwash.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.carwash.model.User;
import com.example.carwash.repo.UserRepository;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // Show login page
    @GetMapping("/login")
    public String loginForm() {
        return "Auth/login";
    }

    // Handle login POST
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpServletRequest request,
                            Model model) {

        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {

            String role = user.getRole();

            // Only CUSTOMER and SELLER allowed
            if (!role.equalsIgnoreCase("CUSTOMER") && !role.equalsIgnoreCase("SELLER")) {
                model.addAttribute("error", "Access denied for this role!");
                return "Auth/login";
            }

            // Create session
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            session.setAttribute("userEmail", user.getEmail());
            session.setAttribute("userRole", user.getRole());

            return "redirect:/dashboard"; // login success
        }

        model.addAttribute("error", "Invalid email or password");
        return "Auth/login"; // login failed
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login?logout";
    }

    // Redirect root to login or index
    @GetMapping("/")
    public String root() {
        return "Auth/index";
    }

}
