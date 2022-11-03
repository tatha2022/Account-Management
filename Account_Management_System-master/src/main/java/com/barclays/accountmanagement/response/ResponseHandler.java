package com.barclays.accountmanagement.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	 public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
	        Map<String, Object> map = new HashMap<String, Object>();
	            map.put("message", message);
	            map.put("status", status.value());
	            map.put("user details", responseObj);
	            

	            return new ResponseEntity<Object>(map,status);
	            
	 }
	 
	 public static ResponseEntity<Object> generatResponseForAccountCreation(String message, HttpStatus status, Object responseObj,Object accountDetailsObject) {
	        Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", message);
            map.put("status", status.value());
            map.put("account details", accountDetailsObject);
            map.put("user details", responseObj);
            

            return new ResponseEntity<Object>(map,status);
		 
	 }
}
