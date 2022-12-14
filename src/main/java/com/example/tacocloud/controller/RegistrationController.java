package com.example.tacocloud.controller;

import com.example.tacocloud.dto.RegistrationForm;
import com.example.tacocloud.dto.User;
import com.example.tacocloud.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(
            UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }


    @PostMapping
    public String processRegistration(@ModelAttribute RegistrationForm registrationForm) {
        System.out.println(registrationForm);

        User user = new User();
        user.setFullname(registrationForm.getFullname());
        user.setUsername(registrationForm.getUsername());
        user.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
        user.setCity(registrationForm.getCity());
        user.setState(registrationForm.getState());
        user.setStreet(registrationForm.getStreet());
        user.setPhoneNumber(registrationForm.getPhone());
        user.setZip(registrationForm.getZip());
//        userRepo.save(registrationForm.toUser(passwordEncoder));

        userRepo.save(user);
        return "redirect:/login";
    }

}
