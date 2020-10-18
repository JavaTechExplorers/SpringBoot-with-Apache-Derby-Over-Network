package com.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myapp.entity.EmployeeEntity;
import com.myapp.repository.EmployeeRepository;
import com.myapp.service.so.EmployeeSo;

@Component
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeSo> getData(EmployeeSo employeeSo) throws Exception {

	List<EmployeeSo> target = new ArrayList<>();

	Iterable<EmployeeEntity> empIterable = employeeRepository.findAll();
	System.out.println("empIterable= " + empIterable);
	for (EmployeeEntity employeeEntity : empIterable) {

	    if (employeeEntity != null) {
		
		EmployeeSo so = new EmployeeSo();
		so.setEmpName(employeeEntity.getEmpName());
		so.setEmpDesc(employeeEntity.getEmpDesc());
		so.setEmpGender(employeeEntity.getEmpGender());

		target.add(so);
	    }
	}

	return target;
    }

    public EmployeeSo save(EmployeeSo employeeSo) throws Exception {

	EmployeeEntity entity = new EmployeeEntity();

	BeanUtils.copyProperties(entity, employeeSo);

	employeeRepository.save(entity);

	return employeeSo;
    }

}
