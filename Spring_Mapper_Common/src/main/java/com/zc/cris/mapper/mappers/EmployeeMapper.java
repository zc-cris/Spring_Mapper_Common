package com.zc.cris.mapper.mappers;

import org.apache.ibatis.annotations.CacheNamespace;

import com.zc.cris.mapper.entities.Employee;

import tk.mybatis.mapper.common.Mapper;

// 具体操作数据库的mapper 接口，需要继承通过 Mapper<T> 接口，
// 而具体的泛型就是实体类的类型
@CacheNamespace			// 开启二级缓存
public interface EmployeeMapper extends Mapper<Employee> {

}
