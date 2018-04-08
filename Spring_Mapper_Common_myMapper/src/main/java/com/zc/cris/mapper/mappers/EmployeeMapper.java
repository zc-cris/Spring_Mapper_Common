package com.zc.cris.mapper.mappers;

import com.zc.cris.mapper.entities.Employee;
import com.zc.cris.mapper.mymappers.MyEmployeeMapper;


// 继承自定义的 mapper 接口(实际使用的 mapper 接口其实是个空接口)
public interface EmployeeMapper extends MyEmployeeMapper<Employee> {

}
