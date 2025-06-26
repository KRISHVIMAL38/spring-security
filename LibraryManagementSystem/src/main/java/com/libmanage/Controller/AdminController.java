package com.libmanage.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libmanage.DTO.RegisterRequestDTO;
import com.libmanage.Entity.User;
import com.libmanage.Service.AuthenticationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@Slf4j
public class AdminController {
	private static final Logger logger=LoggerFactory.getLogger(AdminController.class);
	@Autowired
	AuthenticationService authenticationService;
	
	@PostMapping("/registeradminuser")
	public ResponseEntity<User>registerAdminUser(@RequestBody RegisterRequestDTO registerRequestDTO){
		logger.info("Inside register admin controller");
		 System.out.println("Inside registeradminuser controller");
		 return ResponseEntity.ok(authenticationService.registerAdminUser(registerRequestDTO));
	}
	
}
