package com.okey.bookstore.service;

import com.okey.bookstore.domain.User;
import com.okey.bookstore.domain.security.UserRole;

import java.util.Set;

public interface UserService {
	
	User createUser(User user, Set<UserRole> userRoles);

}
