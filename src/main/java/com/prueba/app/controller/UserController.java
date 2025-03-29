package com.prueba.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prueba.app.DTO.UserDTO;
import com.prueba.app.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {

	//Por simplicidad, Se podria inyectar por constructor
	@Autowired
	private UserService service;

	
	@GetMapping("/users")
	public String getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(required = false) Boolean activeFilter, Model model) {

		Pageable pageable = PageRequest.of(page, size);

		model.addAttribute("activeFilter", activeFilter);
		model.addAttribute("usersPage", service.getAllUsersPage(pageable, activeFilter));

		return "users";
	}
	
	@GetMapping("/users/new_user")
	public String showCreateUserForm(Model model) {
		model.addAttribute("user", new UserDTO());
		return "new_user";
	}
	
	@GetMapping("users/edit_user/{id}")
	public String showEditUserForm(@PathVariable Long id, Model model) {
		model.addAttribute("user", service.getUser(id));
		return "edit_user";
	}

	
	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		service.deleteUser(id);
		return "redirect:/users";
	}

	@PostMapping("/users/new_user")
	public String saveUser(@ModelAttribute("user") @Valid UserDTO newUser, BindingResult result, Model model) {
		service.availableEmailForUser(newUser, result);
		if (result.hasErrors()) {
			return "new_user";
		}
		this.service.saveUser(newUser);

		return "redirect:/users";
	}

	@GetMapping("users/check/{id}")
	public String checkUser(@PathVariable Long id) {
		service.checkUser(id);
		return "redirect:/users";
	}

	@PostMapping("users/edit_user/{id}")
	public String modifyUser(@ModelAttribute("user") @Valid UserDTO modifiedUser, BindingResult result) {
		service.availableEmailForUser(modifiedUser, result);

		if (result.hasErrors()) {
			return "edit_user";
		}
		service.editUser(modifiedUser);
		return "redirect:/users";
	}
}
