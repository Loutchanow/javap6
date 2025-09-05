package com.mddApi.service.interfaces;

import org.springframework.security.core.Authentication;


public interface JWTService {

	
	String generateToken(Authentication authentication);	
}