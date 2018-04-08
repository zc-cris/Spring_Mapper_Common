package com.zc.cris.shop.entities;

import javax.persistence.*;

@Table(name = "tb_emp")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private Double salary;

	private Integer age;

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return salary
	 */
	public Double getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	/**
	 * @return age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", age=" + age + "]";
	}

	public Employee() {
		super();

	}

	public Employee(Integer id, String name, Double salary, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.age = age;
	}

}