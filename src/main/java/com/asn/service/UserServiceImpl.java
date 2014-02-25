package com.asn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asn.dao.PersonalDAO;
import com.asn.dao.RoleDAO;
import com.asn.dao.UserDAO;
import com.asn.dao.UserService;
import com.asn.model.PersonalDetails;
import com.asn.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private PersonalDAO personalDAO;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	@Transactional
	public User findByEmail(String email) {
		User user = userDAO.findByEmail(email);
		if(user!=null)
		{
			if(user.getRole()==null)
				user.setRole(new Long(roleDAO.getRole(user.getId()).getId()));
			if(user.getPersonalDetails()==null)
			{
				PersonalDetails pd = personalDAO.getPersonalInfo(user.getId());
				if(pd!=null)
					user.setPersonalDetails(new Long(pd.getId()));
			}
		}
		return user;
	}

	@Override
	@Transactional
	public Long createUser(User user) {
		Long id = userDAO.createUser(user);
		
		UserDetailsAdapter userDetailsAdapter = new UserDetailsAdapter(user);		
		String password = userDetailsAdapter.getPassword();		
		user.setPassword(encoder.encode(password));
		userDAO.updateUser(user);
		return id;
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		UserDetailsAdapter userDetailsAdapter = new UserDetailsAdapter(user);		
		String password = userDetailsAdapter.getPassword();		
		user.setPassword(encoder.encode(password));		
		userDAO.updateUser(user);
	}

	@Override
	@Transactional
	public void updateUserPassword(Long id, String password) {
		userDAO.updateUserPassword(id, password);
		
	}

}
