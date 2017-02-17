package com.maju.core.admin.admin.dao;

import org.apache.ibatis.annotations.Param;

import com.maju.core.admin.admin.bean.Admin;

public interface AdminDao {

	public Admin getAdminByName(@Param("adminname")String adminname);

}
