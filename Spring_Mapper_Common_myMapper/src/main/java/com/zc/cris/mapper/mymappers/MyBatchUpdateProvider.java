package com.zc.cris.mapper.mymappers;

import java.util.Set;

import org.apache.ibatis.mapping.MappedStatement;

import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

// 专门为自定义的 mapper 拓展接口的方法提供实现的类
public class MyBatchUpdateProvider extends MapperTemplate{

	public MyBatchUpdateProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}

	/*
	 * 目标：通过动态的sql 语句拼接完成批量更新的通用操作
	 * <foreach collection="list" item="record" separator=";">
	 * 	UPDATE tb_emp 
	 * 	<set>
	 * 		name=#{record.name},
	 * 		age=#{record.age},
	 * 		salary =#{record.salary},
	 * 	</set>
	 * 	WHERE id = #{record.id}
	 * </foreach>
	 */
	public String batchUpdate(MappedStatement statement) {
		
		//1. 创建 StringBuilder 对象，用于拼接 sql 语句
		StringBuilder sb = new StringBuilder();
		
		//2. 拼接 foreach 标签
		sb.append("<foreach collection=\"list\" item=\"record\" separator=\";\">");
		
		//3.1 获取到实体类
		Class<?> entityClass = super.getEntityClass(statement);
		//3.2 获取到数据表名
		String tableName = super.tableName(entityClass);
		//3.3 生成update 语句
		String updateClause = SqlHelper.updateTable(entityClass, tableName);
		//3.4 拼接 update 语句
		sb.append(updateClause);
		
		sb.append("<set>");
		
		String idColumn = null;
		String idColumnHolder = null;
		// 4. 获取所有字段的信息
		Set<EntityColumn> columns = EntityHelper.getColumns(entityClass);
		for (EntityColumn entityColumn : columns) {
	
			// 5. 如果是主键字段，就用于拼接 where 字句
			if(entityColumn.isId()) {
				idColumn = entityColumn.getColumn();
				idColumnHolder = entityColumn.getColumnHolder("record");
			}else {
				// 6. 如果是非主键字段，就用于拼接 set 子句
				String column = entityColumn.getColumn();
				String columnHolder = entityColumn.getColumnHolder("record");
				sb.append(column).append("=").append(columnHolder).append(",");
			}
		}
		
		sb.append("</set>");
		
		sb.append("where ").append(idColumn).append("=").append(idColumnHolder);
		
		sb.append("</foreach>");
		return sb.toString();
	}
}
