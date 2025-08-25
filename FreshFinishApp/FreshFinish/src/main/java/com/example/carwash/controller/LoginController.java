package com.example.carwash.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.carwash.model.User;
import com.example.carwash.repo.UserRepository;

import org.springframework.ui.Model;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // Show login form
    @GetMapping("/login")
    public String loginForm() {
        return "Auth/login";
    }
    
    @GetMapping("/")
    public String root() {
        return "Auth/index";
    }

    // Handle login
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpServletRequest request,
                            Model model) {

        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            // ✅ Create session
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            session.setAttribute("userEmail", user.getEmail());

            return "redirect:/dashboard"; 
        }

        model.addAttribute("error", "Invalid email or password");
        return "Auth/login";   // ✅ points to Auth/login.html
    }

    // Dashboard (protected page)
    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request,
                            HttpServletResponse response ) {
        
        // Prevent browser from caching secured pages
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
        response.setHeader("Pragma", "no-cache"); 
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            return "redirect:/login"; // Not logged in → back to login
        }

        return "Auth/index";  // ✅ change to your real dashboard template (e.g. pages/dashboard)
    }
    
    @GetMapping("/index")
    public String index(HttpServletRequest request) {
        return "Auth/index";   // ✅ points to templates/Auth/index.html
    }


    // Logout
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate(); // Destroy session
        }

        return "redirect:/index";
    }
}
