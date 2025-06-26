package com.libmanage.Service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.libmanage.DTO.LoginRequestDTO;
import com.libmanage.DTO.LoginResponseDTO;
import com.libmanage.DTO.RegisterRequestDTO;
import com.libmanage.Entity.User;
import com.libmanage.JWT.JwtService;
import com.libmanage.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class AuthenticationService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService; 
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private final Logger logger=LoggerFactory.getLogger(AuthenticationService.class);
	
	
	public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
			AuthenticationManager authenticationManager) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	public User registerNormalUser(RegisterRequestDTO registerRequestDTO) {
		if(userRepository.findByUsername(registerRequestDTO.getUserName()).isPresent()) {
			throw new RuntimeException("User Already Registered");
		}
		
		Set<String>roles=new HashSet<>();
		roles.add("ROLE_USER");
		
		User user=new User();
		user.setUsername(registerRequestDTO.getUserName());
		user.setEmail(registerRequestDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
		user.setRoles(roles);
		
		return userRepository.save(user);
	}
	
	public User registerAdminUser(RegisterRequestDTO registerRequestDTO) {
		System.out.println("Inside registerAdminUser");
		if(userRepository.findByUsername(registerRequestDTO.getUserName()).isPresent()) {
			throw new RuntimeException("User Already Registered");
		}
		
		Set<String>roles=new HashSet<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		System.out.println("At line 69");
		User user=new User();
		user.setUsername(registerRequestDTO.getUserName());
		user.setEmail(registerRequestDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
		user.setRoles(roles);
		System.out.println("At line 75");
		return userRepository.save(user);
		

	}
	
	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
		logger.info("Inside login");
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequestDTO.getUserName(), 
						loginRequestDTO.getPassword())
				);
		
		User user=userRepository.findByUsername(loginRequestDTO.getUserName()).
				orElseThrow(()-> new RuntimeException("Username not found"));
		
		String token=jwtService.generateToken(user);
		
		return LoginResponseDTO.builder()
				.token(token)
				.userName(user.getUsername())
				.roles(user.getRoles())
				.build();
		
		
	}
}













