package com.still.bos.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.still.bos.dao.base.StandardRepository;
import com.still.bos.domain.base.Standard;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StandarRepositoryTest {

	@Autowired
	private StandardRepository standardRepository;
	
	
	
	@Test
	public void test() {
		
		List<Standard> all = standardRepository.findAll();
		for (Standard standard : all) {
			System.out.println(standard);
		}
	}
	
	
	@Test
	public void test02(){
	    
	   Standard standard = new Standard();
	   standard.setName("李四三");
	   standard.setMaxLength(233);
	   standardRepository.save(standard);
	    
	}
	
	@Test
    public void testName()  {
        
	    
	    
	    
    }
	
	
	
}
