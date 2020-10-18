package com.myapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class EmployeeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMP_ID")
    private int id;

    @Column(name = "NAME")
    private String empName;

    @Column(name = "REMARKS")
    private String empDesc;

    @Column(name = "GENDER")
    private String empGender;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getEmpName() {
	return empName;
    }

    public void setEmpName(String empName) {
	this.empName = empName;
    }

    public String getEmpDesc() {
	return empDesc;
    }

    public void setEmpDesc(String empDesc) {
	this.empDesc = empDesc;
    }

    public String getEmpGender() {
	return empGender;
    }

    public void setEmpGender(String empGender) {
	this.empGender = empGender;
    }

}
