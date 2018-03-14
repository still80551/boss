package com.still.bos.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.still.bos.domain.base.Standard;


//泛型1 : 封装数据的对象的类型
//泛型2 : 对象的主键的类型
// SpringDataJPA提供了一套命名规范,遵循这一套规范定义查询类方法
// 必须以findBy开头,后面跟属性的名字,首字母必须大写
// 如果有多个条件,使用对应的SQL关键字
public interface StandardRepository extends JpaRepository<Standard, Long>{
	
	//List<Standard> findByName(String name);
    
	
	
	
	
}
