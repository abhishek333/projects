package com.asn.dao;

import java.util.List;

import com.asn.model.Complaints;

public interface ComplaintsDAO {
	Long save(Complaints complaints);
	void delete(Complaints complaints);
	Complaints get(Long id);
	List getByStudentId(Long id);
	List list();
}
