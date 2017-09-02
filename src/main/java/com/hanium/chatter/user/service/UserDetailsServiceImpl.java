package com.hanium.chatter.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanium.chatter.user.domain.Person;
import com.hanium.chatter.user.domain.PersonRepository;
import com.hanium.chatter.user.domain.PersonRole;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PersonRepository personRepository;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person person = personRepository.findByUserId(username);
		List<GrantedAuthority> authorities = buildUserAuthority(person.getPersonRoles());

		return buildUserForAuthentication(person, authorities);
	}

	private User buildUserForAuthentication(Person person, List<GrantedAuthority> authorities) {
		return new User(person.getUserId(), person.getPassword(), true, true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(Set<PersonRole> personRoles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
		for (PersonRole personRole : personRoles) {
			authorities.add(new SimpleGrantedAuthority(personRole.getRoleName()));
		}
		
		return authorities;
	}
}
