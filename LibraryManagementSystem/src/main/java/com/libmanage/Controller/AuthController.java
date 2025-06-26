package com.libmanage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libmanage.DTO.LoginRequestDTO;
import com.libmanage.DTO.LoginResponseDTO;
import com.libmanage.DTO.RegisterRequestDTO;
import com.libmanage.Entity.User;
import com.libmanage.Service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthenticationService authenticationService;
	
	@PostMapping("/registernormaluser")
	public ResponseEntity<User>registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO){
		 return ResponseEntity.ok(authenticationService.registerNormalUser(registerRequestDTO));
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO>login(@RequestBody LoginRequestDTO loginRequestDTO){
		 return ResponseEntity.ok(authenticationService.login(loginRequestDTO));
	}
}
