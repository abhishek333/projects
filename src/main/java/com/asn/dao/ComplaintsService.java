package com.asn.dao;

import java.util.List;

import com.asn.model.Complaints;

public interface ComplaintsService {
	Long save(Complaints complaints);
	void delete(Complaints complaints);
	Complaints get(Long id);
	List list();
	List getByStudentId(Long id);
}
