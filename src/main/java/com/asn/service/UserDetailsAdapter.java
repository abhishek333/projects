package com.asn.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.asn.model.User;

public class UserDetailsAdapter extends org.springframework.security.core.userdetails.User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Long id;	
	
	public UserDetailsAdapter(User userEntity){
		super(userEntity.getUsername(), userEntity.getPassword(), userEntity.getActive(), true, true, true, toAuthorities(userEntity.getRole()));
		this.id = userEntity.getId();
	}
	
	private static Collection<? extends GrantedAuthority> toAuthorities(Long securityRole) {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if(securityRole==null)
		{
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		else{
		for(String s: getRoles(securityRole.intValue()))
			authorities.add(new SimpleGrantedAuthority(s));
		}
		return authorities;
	}

	/**
	 * Converts a numerical role to an equivalent list of roles
	 * @param role the numerical role
	 * @return list of roles as as a list of {@link String}
	 */
	private static List<String> getRoles(Integer role) {
		
		List<String> roles = new ArrayList<String>();
		
		if (role.intValue() == 0) {
			roles.add("ROLE_USER");
			roles.add("ROLE_ADMIN");
			
		} else if (role.intValue() == 1) {
			roles.add("ROLE_USER");
		}
		
		return roles;
	}
	
	public Long getId() {
		return id;
	}

}
