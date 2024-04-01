package com.rays.service;

import com.rays.dto.AttechamentDTO;

public interface AttechamentServiceInt {

	public long add(AttechamentDTO dto);

	public void update(AttechamentDTO dto);

	public void delete(long pk);

	public AttechamentDTO findbyid(long pk);
}
