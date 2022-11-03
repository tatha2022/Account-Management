package com.barclays.accountmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.accountmanagement.entity.BankAccount;
import com.barclays.accountmanagement.entity.BankCustomerDetails;
import com.barclays.accountmanagement.entity.Users;
import com.barclays.accountmanagement.response.ResponseHandler;
import com.barclays.accountmanagement.service.BankAccountService;
import com.barclays.accountmanagement.service.BankCustomerDetailsService;
import com.barclays.accountmanagement.service.UsersService;


@RestController
public class BankCustomerDetailsController {
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	private BankCustomerDetailsService bankCustomerDetailsService;
	@Autowired
	private BankAccountService bankAccountService;
	@Autowired
	private UsersService usersService;
	
	/*
	 * Get User 
	 * by
	 * PAN Number 
	 * if present no need to create account 
	 * else create
	 */
	@GetMapping("/getdetailsbypan/{pannumber}")
	@CrossOrigin(origins="*")
	public ResponseEntity<Object> getUserDetailsByPan(@PathVariable String pannumber) {
		try {
			
			BankCustomerDetails bankCustomerDetails =bankCustomerDetailsService.getDetailsByPan(pannumber);
			if(!bankCustomerDetails.equals(null))
			return ResponseHandler.generateResponse("success",HttpStatus.OK, bankCustomerDetails);
			else 
				return ResponseHandler.generateResponse("not found",HttpStatus.OK, null);
			
		} catch (Exception e) {
			// TODO: handle exception
//			System.out.println(e);
			return ResponseHandler.generateResponse("No Data Found",HttpStatus.NOT_FOUND, null);
		}
		
	}
	
	/*
	 * Get Account details
	 */
	@GetMapping("/getcustdetails/{customerId}")
	public ResponseEntity<Object> getCustomerDetailsById(@PathVariable Long customerId) {
		try {
			BankCustomerDetails customerDetails= bankCustomerDetailsService.getCustomerDetails(customerId);
			if(customerDetails!=null)
			return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, customerDetails);
			else 
				return ResponseHandler.generateResponse("User not found", HttpStatus.NOT_FOUND, null);
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
		}
	}
	
	/*
	 * Create Account
	 * by
	 * Bank Manager- Admin
	 * 
	 */
	@PostMapping("/adduserdetails")

@CrossOrigin(origins="*")
	public ResponseEntity<Object> addUserDetails(@RequestBody BankCustomerDetails customerDetails){
		
		BankCustomerDetails bankCustomerDetailsfromDB =bankCustomerDetailsService.getDetailsByPan(customerDetails.getPanNumber());
		//if user does not exist -> create new one
		if(bankCustomerDetailsfromDB==null) {
		
			try {
				
				BankCustomerDetails bankCustomerDetails = bankCustomerDetailsService.createNewCusomer(customerDetails);
			
				Users newUser = createNewLoginCredentials(bankCustomerDetails);
				
//				sendEmail(newUser,customerDetails);
				
				return ResponseHandler.generatResponseForAccountCreation("Success", HttpStatus.CREATED, bankCustomerDetails,
						createbankAccount(bankCustomerDetails));
				
			} catch (Exception e) {
				// TODO: handle exception
				return ResponseHandler.generatResponseForAccountCreation(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null,null);
			}
		}
		else 
			//if user already exists -> create new account and add that account number to existing customerId
		{
			

			return ResponseHandler.generatResponseForAccountCreation("User added to existing customer Id", HttpStatus.CREATED, bankCustomerDetailsfromDB,createbankAccount(bankCustomerDetailsfromDB));
		}
		
		
	}
	
	// update
	
	
	//Delete 
	
	@DeleteMapping("/deleteuserdetails/{customerId}")
	public ResponseEntity<Object> deleteUserDetails(@PathVariable Integer customerId){
		try {
			bankCustomerDetailsService.deleteCustomer(customerId);
			return ResponseHandler.generateResponse("User deleted", HttpStatus.OK, null);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseHandler.generateResponse("Failed to delete", HttpStatus.BAD_REQUEST, null);
		}
	}
	
	
	public BankAccount createbankAccount(BankCustomerDetails bankCustomerDetails) {
		BankAccount bankAccount = new BankAccount();
		bankAccount.customerId = bankCustomerDetails.getCustomerId();
		
		bankAccountService.createAccount(bankAccount);
		return bankAccount;
	}
	
	/*
	 * User table populated by the details from here
	 */
	public Users createNewLoginCredentials(BankCustomerDetails bankCustomerDetails) {
		Users newUser = new Users();
		newUser.customerId=bankCustomerDetails.getCustomerId();
		newUser.password="aj12P2&^ioa";
		//newUser.roleId=2;
		usersService.createUser(newUser);
		return newUser;
	}
	public void sendEmail(Users user,BankCustomerDetails bankCustomerDetails) {
		
	        SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(bankCustomerDetails.getEmailAddress());

	        msg.setSubject("----<Important>----- Login Credentials for Internet Banking");
	        msg.setText("Dear" +bankCustomerDetails.getName()+",\nPlease find below your CustomerId and first time login Password for Internet Banking.\nYou will be forced to "
	        		+ "change your system generated password for first time login.\n"+ 
	        		" CustomerID: "+ user.getCustomerId() + " \nSystem generated Password:"+ user.getPassword()+ "\n \n"
	        				+ "Yours Truthful,\n Bank of Group A1 ");

	        javaMailSender.send(msg);

	}
	
}

