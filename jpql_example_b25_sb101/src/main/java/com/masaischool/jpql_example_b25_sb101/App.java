package com.masaischool.jpql_example_b25_sb101;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class App {
	static EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("employeeConnect");
	}
	
	static void printEmployeeNameInUpperCase() {
		EntityManager em = emf.createEntityManager();
		//jpql statement
		String upperQuery= "SELECT UPPER(e.ename) FROM Employee e";
		//call the createQuery method using the em
		Query query = em.createQuery(upperQuery);
		List<String> list = query.getResultList();
		list.forEach(System.out::println);
		em.close();
	}
	
	static void printMaximumSalary() {
		EntityManager em = emf.createEntityManager();
		//jpql statement
		String maxSalaryQuery= "SELECT MAX(e.salary) FROM Employee e";
		//call the createQuery method using the em
		Query query = em.createQuery(maxSalaryQuery);
		Integer salary = (Integer)query.getSingleResult();
		System.out.println("The maximum salary is " + salary);
		em.close();
	}
	
	static void printEmployeesForSalaryRange(int startSalary, int endSalary) {
		EntityManager em = emf.createEntityManager();
		//jpql statement
		String betweenSalaryQuery= "SELECT e FROM Employee e WHERE e.salary BETWEEN :paraOne AND :paraTwo";
		//call the createQuery method using the em
		Query query = em.createQuery(betweenSalaryQuery);
		query.setParameter("paraOne", startSalary);
		query.setParameter("paraTwo", endSalary);
		List<Employee> list = query.getResultList();
		list.forEach(System.out::println);
		em.close();
	}
	
	static void printDesignationWiseCounting() {
		EntityManager em = emf.createEntityManager();
		//jpql statement
		String groupByQuery= "SELECT e.designation, COUNT(e) FROM Employee e GROUP BY e.designation HAVING COUNT(e) >= 2 ORDER BY COUNT(e) DESC";
		//call the createQuery method using the em
		Query query = em.createQuery(groupByQuery);
		List<Object[]> list = query.getResultList();
		Consumer<Object[]> con = obj -> System.out.println(obj[0] + " : " + obj[1]);
		list.forEach(con);
		em.close();
	}
	
	static void printEmployeeByName(String name) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("FindEmployeeByName");
		query.setParameter("name", "%" + name + "%");
		List<Employee> list = query.getResultList();
		list.forEach(System.out::println);
		em.close();
	}
	
	static void printEmployeeByDesignation() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("FindEmployeeByDesignations");
		List<String> designations = Arrays.asList("Technical Manager", "Proof Reader");
		query.setParameter("designation", designations);
		List<Employee> list = query.getResultList();
		list.forEach(System.out::println);
		em.close();
	}
	
	static void printAllEmployees() {
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("FindAllEmployee");
		List<Employee> list = query.getResultList();
		list.forEach(System.out::println);
		em.close();
	}
	
    public static void main( String[] args ){
    	printEmployeeNameInUpperCase();
    	System.out.println();
    	printMaximumSalary();
    	System.out.println();
    	printEmployeesForSalaryRange(35000, 40000);
    	System.out.println();
    	printDesignationWiseCounting();
    	System.out.println();
    	printEmployeeByName("n");
    	System.out.println();
    	printEmployeeByDesignation();
    	System.out.println();
    	printAllEmployees();
    }
}
