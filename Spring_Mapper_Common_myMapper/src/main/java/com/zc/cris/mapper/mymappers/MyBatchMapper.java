package com.zc.cris.mapper.mymappers;

import java.util.List;

import org.apache.ibatis.annotations.UpdateProvider;

// 自定义的 Mapper 拓展接口
public interface MyBatchMapper<T> {

	@UpdateProvider(type=MyBatchUpdateProvider.class,method="dynamicSQL")
	public void batchUpdate(List<T> list);
	
}
