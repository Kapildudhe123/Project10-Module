package com.rays.service;

import java.util.List;

import com.rays.dto.CarDTO;

public interface CarServiceInt {

public long add(CarDTO dto);
	
	public void update(CarDTO dto);
	
	public void delete(long pk);
	
	public CarDTO findbyid(long pk);
	
	public List search(CarDTO dto, int pageNo,int pageSize);
}
