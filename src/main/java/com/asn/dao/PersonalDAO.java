package com.asn.dao;

import java.util.List;

import com.asn.model.PersonalDetails;

public interface PersonalDAO {
	
	public Long savePersonalInfo(PersonalDetails personalDetails);
	public void updatePersonalInfo(PersonalDetails personalDetails);
	public PersonalDetails getPersonalInfo(Long id);
	public PersonalDetails getPersonalInfo(String email);
	public List<PersonalDetails> getPersonalInfoByQuery(String queryHQL);	
	public void deletePersonalInfo(PersonalDetails personalDetails);
}
