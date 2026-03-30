package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.LoginDto;
import com.app.quantitymeasurement.dto.UserDto;
import com.app.quantitymeasurement.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.quantitymeasurement.dto.SignupDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {
	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<UserDto> singup(@Valid @RequestBody SignupDto sinupDto) {
        log.info("Sign up called");
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(sinupDto));
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto, HttpServletResponse response) {
        log.info("Login called");
		String token = userService.login(loginDto);
		return ResponseEntity.accepted().body(token);
	}
}
