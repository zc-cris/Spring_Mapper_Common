package com.zc.cris.mapper.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zc.cris.mapper.entities.Employee;
import com.zc.cris.mapper.services.EmployeeService;


class MyEmployeeMapperTest {

	private ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
	private EmployeeService empService = context.getBean(EmployeeService.class);
	
	
	@Test
	void testGetAll() {
		
		List<Employee> emps = empService.queryAll();
		for (Employee employee : emps) {
			System.out.println(employee);
		}
	}
	
	@Test
	void testBatchUpdate() {
		
		List<Employee> emps = new ArrayList<>();
		emps.add(new Employee(2, "newEmp02", 20000.0, 12));
		emps.add(new Employee(3, "newEmp03", 30000.0, 13));
		emps.add(new Employee(4, "newEmp04", 40000.0, 14));
		
		empService.batchUpdateEmps(emps);
		
	}

}
