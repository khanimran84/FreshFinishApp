package com.example.carwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.carwash.model.User;
import com.example.carwash.repo.UserRepository;



@Controller
public class SignUpController {
	
	@Autowired
	private UserRepository userRepository;

    @GetMapping("/signup")
    public String sign_up() {
        return "Auth/signup";
    }
    
    @PostMapping("/signup")
    public String save_user(@ModelAttribute User user) {

        // Only allow CUSTOMER or SELLER roles
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("CUSTOMER"); // default
        } else if (!user.getRole().equalsIgnoreCase("CUSTOMER") &&
                   !user.getRole().equalsIgnoreCase("SELLER")) {
            user.setRole("CUSTOMER"); // fallback
        }

        // Save user
        userRepository.save(user);

        return "redirect:/signup?success";
    }

    
    
}
