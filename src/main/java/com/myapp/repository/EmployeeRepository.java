package com.myapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.myapp.entity.EmployeeEntity;

@RepositoryRestResource
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {

}