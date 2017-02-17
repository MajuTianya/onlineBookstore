package com.maju.test.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maju.test.bean.TestTB;
import com.maju.test.dao.TestTBDao;
import com.maju.test.service.TestTBService;

/**
 * 
 * @author chenli
 *
 */
@Service
@Transactional
public class TestTBServiceImpl implements TestTBService{

	@Resource
	private TestTBDao testTBDao;
	
	public void addTestTB(TestTB testTB) {
		try {
			testTBDao.addTestTB(testTB);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}