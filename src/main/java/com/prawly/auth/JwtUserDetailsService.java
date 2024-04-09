package com.prawly.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	@Override
	public UserDetails loadUserByUsername(String username) {
		var user = userRepository.findByLogin(username);
		return user;
	}

	public User save(SignUpDTO userDTO) {
		if (userRepository.findByLogin(userDTO.getUsername()) != null) {
			throw new UserAlreadyExists("Username already exists");
		}
		String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());
		User user = new User(userDTO.getUsername(), encryptedPassword, UserRole.ADMIN);
		return userRepository.save(user);
	}

}
