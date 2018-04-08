package com.zc.cris.mapper.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.cris.mapper.entities.Employee;
import com.zc.cris.mapper.mappers.EmployeeMapper;

import tk.mybatis.mapper.entity.Example;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;

	// 根据条件查询一个员工
	public Employee selectOne(Employee employeeQueryCondition) {
		return employeeMapper.selectOne(employeeQueryCondition);
	}

	// 根据主键查询一个员工
	public Employee electByPrimaryKey(int i) {
		return employeeMapper.selectByPrimaryKey(i);
	}

	// 查询所有员工
	public List<Employee> selectAll(){
		return employeeMapper.selectAll();
	}
	
	// 保存一个员工
	public void saveEmployee(Employee employee) {
		employeeMapper.insert(employee);
	}

	// 有选择性的保存数据到数据库(使 sql 语句更加简洁，提高执行效率)
	public void saveEmployeeSelective(Employee employee) {
		employeeMapper.insertSelective(employee);
	}

	// 有选择性的更新数据，为null 的数据就不更新，效率高，防止数据丢失
	public void updateEmpSelective(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
	}

	// 根据 Entity 实体类来删除数据
	public void removeEmployee(Employee employee) {
		employeeMapper.delete(employee);
	}

	// 根据主键进行数据的删除
	public void removeEmployeeById(int i) {
		employeeMapper.deleteByPrimaryKey(i);
	}

	// 使用 QBC 查询完成复杂条件查询
	public List<Employee> selectEmpsByExample(Example example) {
		
		return employeeMapper.selectByExample(example);
	}
	
}
