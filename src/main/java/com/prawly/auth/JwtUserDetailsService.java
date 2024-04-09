package com.prawly.auth;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prawly.exceptions.UserAlreadyExists;
import com.prawly.user.User;
import com.prawly.user.SignUpDTO;
import com.prawly.user.UserRepository;
import com.prawly.user.UserRole;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	// @Autowired
	// private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) {
		var user = userRepository.findByLogin(username);
		return user;
	}

	// @Override
	// public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	// 	if ("javainuse".equals(username)) {
	// 		// return new User("javainuse",
	// 		// "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
	// 		// new ArrayList<>());
	// 	} else {
	// 		throw new UsernameNotFoundException("User not found with username: " + username);
	// 	}
	//}

	public User save(SignUpDTO userDTO) {
		if (userRepository.findByLogin(userDTO.getUsername()) != null) {
			throw new UserAlreadyExists("Username already exists");
		}
		String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());
		User user = new User(userDTO.getUsername(), encryptedPassword, UserRole.ADMIN);
		return userRepository.save(user);
	}

}
