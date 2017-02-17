package com.maju.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.maju.common.junit.SpringJunitTest;
import com.maju.test.bean.TestTB;
import com.maju.test.service.TestTBService;

/**
 * 测试
 * @author chenli
 *
 */

public class TestTBTest extends SpringJunitTest{

	@Autowired
	private TestTBService testTBService;
	@Test
	public void testAdd() throws Exception {
		TestTB testTb = new TestTB();
		testTb.setName("金乐乐");
		testTBService.addTestTB(testTb);
	}
}
