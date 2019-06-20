package com.okey.bookstore.service.impl;

import com.okey.bookstore.domain.User;
import com.okey.bookstore.domain.security.UserRole;
import com.okey.bookstore.repository.RoleRepository;
import com.okey.bookstore.repository.UserRepository;
import com.okey.bookstore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional
	public User createUser(User user, Set<UserRole> roles) {
		User currentUser = userRepository.findByUsername(user.getUsername());

		if(currentUser != null) {
			LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
		} else {
			for (UserRole ur : roles) {
				roleRepository.save(ur.getRole());
			}

			user.getUserRoles().addAll(roles);

			currentUser = userRepository.save(user);
		}

		return currentUser;
	}
}
