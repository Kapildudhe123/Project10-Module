package com.rays.dao;

import com.rays.dto.AttechamentDTO;

public interface AttechamentDAOInt {

	public long add(AttechamentDTO dto);

	public void update(AttechamentDTO dto);

	public void delete(AttechamentDTO dto);

	public AttechamentDTO findbyPk(long pk);

}
