package com.asn.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asn.dao.RoleDAO;
import com.asn.dao.UserDAO;
import com.asn.model.User;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private RoleDAO roleDAO;
	
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {		
		User user = userDAO.findByEmail(email);		
		if (user == null)
		{
			logger.info("user not found.");
		      throw new UsernameNotFoundException("user not found");
		}
		user.setRole(new Long(roleDAO.getRoleName(user.getId())));
		//System.out.println("user: "+user);
		return (UserDetails)new UserDetailsAdapter(user);
	}

	

}
