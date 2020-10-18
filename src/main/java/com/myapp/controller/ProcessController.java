package com.myapp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.service.EmployeeService;
import com.myapp.service.so.EmployeeSo;

@RestController
public class ProcessController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/user")
    public Principal user(Principal user) {
	return user;
    }

    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    public ResponseEntity<List<EmployeeSo>> getAllData(@RequestBody EmployeeSo employeeSo) throws Exception {

	List<EmployeeSo> empList = employeeService.getData(employeeSo);
	
	return new ResponseEntity<List<EmployeeSo>>(empList, HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<EmployeeSo> saveData(@RequestBody EmployeeSo employeeSo) throws Exception {

	employeeService.save(employeeSo);

	return new ResponseEntity<EmployeeSo>(employeeSo, HttpStatus.OK);
    }

}