package com.okey.bookstore.resource;

import com.okey.bookstore.domain.ResponseString;
import com.okey.bookstore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@RestController
@Slf4j
public class LoginResource {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/token")
	public Map<String, String> token(HttpSession session, HttpServletRequest request) {
		String remoteHost = request.getRemoteHost();
		int portNumber = request.getRemotePort();
		log.info("Here is the server information {} {}", remoteHost, portNumber);
		return Collections.singletonMap("token", session.getId());
	}

	@GetMapping(value="/checkSession", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseString> checkSession() {
		return new ResponseEntity<>(new ResponseString("session is active!"), HttpStatus.OK);
	}

	@PostMapping("/user/logout")
	public ResponseEntity<ResponseString> logout() {
		SecurityContextHolder.clearContext();
		return new ResponseEntity<>(new ResponseString("Logout Successful!"), HttpStatus.OK);
	}
}
