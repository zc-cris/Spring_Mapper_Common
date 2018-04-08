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


	// 使用 QBC 查询完成复杂条件查询
	public List<Employee> selectEmpsByExample(Example example) {
		return employeeMapper.selectByExample(example);
	}


	// 通过继承自定义的 Mapper 接口来实现查询全部
	public List<Employee> queryAll() {
		return employeeMapper.selectAll();
	}
	
	// 批量更新数据（通过自定义的拓展接口实现）
	public void batchUpdateEmps(List<Employee> emps) {
		employeeMapper.batchUpdate(emps);
	}
	
}
