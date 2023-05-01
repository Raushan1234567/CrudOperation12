package com.masaischool.jpql_example_b25_sb101;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "FindEmployeeByName", query = "SELECT e FROM Employee e WHERE e.ename LIKE :name"),
		@NamedQuery(name = "FindEmployeeByDesignations", query = "SELECT e FROM Employee e WHERE e.designation IN(:designation)")	
	}
)
@NamedNativeQuery(name = "FindAllEmployee", query = "SELECT * FROM employee", resultClass = Employee.class)
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eid;
	private String ename;
	private int salary;
	private String designation;
	public Employee() {
		super();
	}
	
	public Employee(String ename, int salary, String designation) {
		super();
		this.ename = ename;
		this.salary = salary;
		this.designation = designation;
	}
	
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", salary=" + salary + ", designation=" + designation
				+ "]";
	}
}
