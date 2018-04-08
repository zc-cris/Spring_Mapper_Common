package com.zc.cris.mapper.test;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zc.cris.mapper.entities.Employee;
import com.zc.cris.mapper.services.EmployeeService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

class EmployeeMapperTest {

	private ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
	private EmployeeService empService = context.getBean(EmployeeService.class);
	
	
	@Test
	void testSelectOne() {
		//1. 创建封装查询条件的实体类对象(查询id 为1 号的员工信息)
		Employee employeeQueryCondition = new Employee(2, null, null, null);
		Employee employeeQueryResult = empService.selectOne(employeeQueryCondition);
		System.out.println(employeeQueryResult);
	}

	@Test
	void testSelectAll() {
		List<Employee> emps = empService.selectAll();
		for (Employee employee : emps) {
			System.out.println(employee);
		}
		emps = empService.selectAll();
		for (Employee employee : emps) {
			System.out.println(employee);
		}
	}
}
