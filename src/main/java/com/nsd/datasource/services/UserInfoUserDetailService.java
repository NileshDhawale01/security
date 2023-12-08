package com.nsd.datasource.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.nsd.datasource.config.UserInfoUserDetails;
import com.nsd.datasource.entities.UserInfo;
import com.nsd.datasource.repositoryes.UserRepo;

@Component
public class UserInfoUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = repo.findByName(username);
		return userInfo.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
