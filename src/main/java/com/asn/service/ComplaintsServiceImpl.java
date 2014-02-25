package com.asn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asn.dao.ComplaintsDAO;
import com.asn.dao.ComplaintsService;
import com.asn.model.Complaints;
@Service
@Transactional
public class ComplaintsServiceImpl implements ComplaintsService {
	@Autowired private ComplaintsDAO complaintsDAO;
	
	@Override
	public Long save(Complaints complaints) {
		return complaintsDAO.save(complaints);
	}

	@Override
	public void delete(Complaints complaints) {
		complaintsDAO.delete(complaints);
	}

	@Override
	public Complaints get(Long id) {
		return complaintsDAO.get(id);
	}

	@Override
	public List list() {
		return complaintsDAO.list();
	}

	@Override
	public List getByStudentId(Long studId) {		
		return complaintsDAO.getByStudentId(studId);
	}

}
