package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.CarDAOInt;
import com.rays.dto.CarDTO;

@Service
public class CarServiceImpl implements CarServiceInt {

	@Autowired
	CarDAOInt dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(CarDTO dto) {
		long pk = dao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(CarDTO dto) {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long pk) {
		CarDTO dto = findbyid(pk);
		dao.delete(dto);
	}

	@Transactional(readOnly = true)
	public CarDTO findbyid(long pk) {

		CarDTO dto = dao.findbyPk(pk);
		return dto;
	}

	@Transactional(readOnly = true)
	public List search(CarDTO dto, int pageNo, int pageSize) {
		List list = dao.search(dto, pageNo, pageSize);
		return list;
	}

}
