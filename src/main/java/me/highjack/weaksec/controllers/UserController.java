package me.highjack.weaksec.controllers;

import me.highjack.weaksec.models.User;
import me.highjack.weaksec.repositories.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    UserRepo userRepo;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/register")
    public String register(Model model){
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        return "user/register";
    }

    @PostMapping("/save")
    public String saveUser(Model model, User newUser){
        logger.info("New User Created: ");
        logger.info("Username: {}", newUser.getUsername());
        logger.info("Email: {}", newUser.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        userRepo.save(newUser);
        return "redirect:/";
    }
}
