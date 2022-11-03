package com.barclays.accountmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.accountmanagement.entity.Users;
import com.barclays.accountmanagement.response.ResponseHandler;
import com.barclays.accountmanagement.service.UsersService;

@RestController
public class UserController {

	@Autowired
	private UsersService usersService;
	
	@GetMapping("/getuser/{customerId}")
	public ResponseEntity<Object> getUser(@PathVariable Integer customerId){
		try {
		
			Users extUser =usersService.getUserById(customerId);
			return ResponseHandler.generateResponse("success", HttpStatus.OK, extUser);
		
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
		}
	}
	
	@PostMapping("/createuser")
	public ResponseEntity<Object> createUser(Users user){
		try {
			Users newUser = usersService.createUser(user);
			return ResponseHandler.generateResponse("created", HttpStatus.CREATED, newUser);
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	@PostMapping("/login")
	public boolean userLogin(@RequestParam("customer_id") long customer_id, @RequestParam("password") String password) {
		System.out.println(customer_id + password);
		
		 Users users = usersService.findByCustomerId(customer_id);
		 
		 if(users==null) {
			 System.out.println("User doesn't exist, contact Bank Manager");
			 return false;
		 }
		 else {
			 if(users.getPassword().equals(password)) {
				 System.out.println(users.getCustomerId()+users.getPassword());
				 System.out.println("User logged in");
				 return true;
			 }
		 }
		 return false;
		
  
	}

}

