package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.dto.CarDTO;

@Repository
public class CarDAOImpl implements CarDAOInt {

	@PersistenceContext
	EntityManager entitymanager;

	public long add(CarDTO dto) {
		entitymanager.persist(dto);
		return dto.getId();
	}

	@Override
	public void update(CarDTO dto) {

		entitymanager.merge(dto);
	}

	@Override
	public void delete(CarDTO dto) {
		entitymanager.remove(dto);
	}

	@Override
	public CarDTO findbyPk(long pk) {
		CarDTO dto = entitymanager.find(CarDTO.class, pk);
		return dto;
	}

	@Override
	public List search(CarDTO dto, int pageNo, int pageSize) {
		CriteriaBuilder builder = entitymanager.getCriteriaBuilder();
		CriteriaQuery<CarDTO> cq = builder.createQuery(CarDTO.class);

		Root<CarDTO> qRoot = cq.from(CarDTO.class);

		List<Predicate> predicatelist = new ArrayList<Predicate>();

		if (dto != null) {
			if (dto.getCarName() != null && dto.getCarName().length() > 0) {
				predicatelist.add(builder.like(qRoot.get("carName"), dto.getCarName() + "%"));
			}
			if (dto.getId() != null && dto.getId() > 0) {
				predicatelist.add(builder.equal(qRoot.get("id"), dto.getId()));
			}
		}
		cq.where(predicatelist.toArray(new Predicate[predicatelist.size()]));

		TypedQuery<CarDTO> tq = entitymanager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);
		}

		List<CarDTO> list = tq.getResultList(); 

		return list;
	}

}
