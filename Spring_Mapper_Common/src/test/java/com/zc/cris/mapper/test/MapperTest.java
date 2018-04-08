package com.zc.cris.mapper.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MapperTest {

	private ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

	@Test
	public void test() throws SQLException {
		DataSource dataSource = context.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());

	}

}
