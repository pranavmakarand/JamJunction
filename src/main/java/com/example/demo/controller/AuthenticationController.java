package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dao.ShoppingCart;
import com.example.demo.filter.PasswordEncoder;
import com.example.demo.pojo.User;
import com.example.demo.service.RolesService;
import com.example.demo.service.UserService;

@Controller
public class AuthenticationController {
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RolesService roleService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getIndexPage(Model model) {
		return "indexPage";
	}
	
	@RequestMapping(value = "/adminIndexPage", method = RequestMethod.GET)
	public String getAdminIndexPage(Model model) {
		return "adminIndexPage";
	}

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

	@PostMapping(value = "/register")
	public String submitRegistrationForm(@ModelAttribute("user") User user) {

		String userId = user.getEmail();
		String pass = user.getPassword();
		
		user.setEmail(userId);
		user.setPassword(pass);

		userService.addNewUser(user);
		
		userService.saveRole(user);
		
		System.out.println("Done!");
		
		return "redirect:/";
	}
    
	@GetMapping("/login")
	public String showForm() {
		ShoppingCart.removeAllItem();
		return "login";
	}
}