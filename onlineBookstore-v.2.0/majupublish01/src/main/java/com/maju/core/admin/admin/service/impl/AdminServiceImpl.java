package com.maju.core.admin.admin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maju.core.admin.admin.bean.Admin;
import com.maju.core.admin.admin.dao.AdminDao;
import com.maju.core.admin.admin.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	
	@Resource
	private AdminDao adminDao;

	@Override
	public Admin getAdminByName(String adminname) {
		return adminDao.getAdminByName(adminname);
	}

}
