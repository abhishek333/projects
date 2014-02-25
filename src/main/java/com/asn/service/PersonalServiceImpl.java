package com.asn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asn.dao.PersonalDAO;
import com.asn.dao.PersonalService;
import com.asn.model.PersonalDetails;

@Service
@Transactional
public class PersonalServiceImpl implements PersonalService {

	@Autowired
	private PersonalDAO personalDAO;
	
	@Override
	public Long savePersonalInfo(PersonalDetails personalDetails) {
		return personalDAO.savePersonalInfo(personalDetails);
	}

	@Override
	public void updatePersonalInfo(PersonalDetails personalDetails) {
		personalDAO.updatePersonalInfo(personalDetails);
	}

	@Override
	public PersonalDetails getPersonalInfo(Long id) {
		return personalDAO.getPersonalInfo(id);
	}

	@Override
	public PersonalDetails getPersonalInfo(String email) {
		return personalDAO.getPersonalInfo(email);
	}

	@Override
	public void deletePersonalInfo(PersonalDetails personalDetails) {
		personalDAO.deletePersonalInfo(personalDetails);
	}

	@Override
	public List<PersonalDetails> getPersonalInfoByQuery(String queryHQL) {
		return personalDAO.getPersonalInfoByQuery(queryHQL);
	}

}
