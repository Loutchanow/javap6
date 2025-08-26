package com.mddApi.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mddApi.dto.LoginRequestDTO;
import com.mddApi.dto.RegisterDTO;
import com.mddApi.dto.TokenResponseDTO;
import com.mddApi.dto.UsersDTO;
import com.mddApi.service.JWTService;
import com.mddApi.service.UsersService;
import com.mddApi.service.mapper.UsersMapper;





@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private JWTService jwtService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UsersMapper usersMapper;
	
	public AuthController(JWTService jwtService) {
		this.jwtService = jwtService;
	}
	@PostMapping("/register")
	public TokenResponseDTO register(@RequestBody RegisterDTO registerDTO) {
	    String rawPassword = registerDTO.getPassword();
	    String encoded = passwordEncoder.encode(rawPassword);
	    UsersDTO userDTO = usersMapper.fromRegisterDto(registerDTO);
	    userDTO.setPassword(encoded);
	    userDTO.setCreated_at(LocalDateTime.now());

	    usersService.save(userDTO);

	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(userDTO.getEmail(), rawPassword)
	    );
	    String token = jwtService.generateToken(authentication);
	    return new TokenResponseDTO(token);
	}

    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody LoginRequestDTO request) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email, request.password)
        );
        String token = jwtService.generateToken(auth);
        return new TokenResponseDTO(token);
    }

}

