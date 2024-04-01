package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.AttechamentDAOInt;
import com.rays.dto.AttechamentDTO;

@Service
public class AttechamentServiceImpl implements AttechamentServiceInt {
	@Autowired
	AttechamentDAOInt dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(AttechamentDTO dto) {
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AttechamentDTO dto) {
		dao.update(dto);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long pk) {
	AttechamentDTO dto=	findbyid(pk);
		dao.delete(dto);
	}

	@Transactional(readOnly = true)
	public AttechamentDTO findbyid(long pk) {
		AttechamentDTO dto=dao.findbyPk(pk);
		return dto;
	}

}
