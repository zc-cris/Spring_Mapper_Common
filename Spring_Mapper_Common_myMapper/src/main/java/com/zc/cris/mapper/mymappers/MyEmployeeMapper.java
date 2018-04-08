package com.zc.cris.mapper.mymappers;

import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;

// 自定义的 mapper 接口有选择性的继承指定的 Mapper 接口（通用 mapper 接口及自定义的mapper 拓展接口）
public interface MyEmployeeMapper<T> extends 
							SelectAllMapper<T>, 
							SelectByExampleMapper<T>, 
							MyBatchMapper<T>{

}
