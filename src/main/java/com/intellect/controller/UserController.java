package com.intellect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intellect.model.User;
import com.intellect.response.IntelectResponse;
import com.intellect.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;


	@RequestMapping(value="/create", method = RequestMethod.POST)
	public IntelectResponse createUser(@RequestBody User user){
		return userService.createUser(user);
	}

	@RequestMapping(value="/update", method = RequestMethod.PUT)
	public IntelectResponse updateUser(@RequestBody User user){
		return userService.updateUser(user);
	}

	@RequestMapping(value="/delete/{userId}", method = RequestMethod.DELETE)
	public IntelectResponse deleteUser(@PathVariable("userId") Long userId){
		return userService.deleteUser(userId);
	}
	
}
