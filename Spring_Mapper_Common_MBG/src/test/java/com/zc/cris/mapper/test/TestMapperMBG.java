package com.zc.cris.mapper.test;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.zc.cris.shop.entities.Employee;
import com.zc.cris.shop.mappers.EmployeeMapper;

import tk.mybatis.mapper.mapperhelper.MapperHelper;

public class TestMapperMBG {

	public static void main(String[] args) {
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		
		InputStream inputStream = TestMapperMBG.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
		
		SqlSessionFactory factory = builder.build(inputStream);
		
		SqlSession session = factory.openSession();
		
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		
		// 按照官方文档，整合通用 Mapper 还需要进行以下设置
		// 1. 创建 MapperHelper 对象
		MapperHelper mapperHelper = new MapperHelper();
		
		// 2. 通过 mapperHelper 对象对Mybatis 的原生 Configuration 对象进行处理
		mapperHelper.processConfiguration(session.getConfiguration());
		
		Iterator<Employee> iterator = mapper.selectAll().iterator();
		while (iterator.hasNext()) {
			Employee employee = (Employee) iterator.next();
			System.out.println(employee);
		}
	}
}
