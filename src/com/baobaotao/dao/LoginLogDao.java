package com.baobaotao.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.baobaotao.domain.LoginLog;

@Repository
public class LoginLogDao {
	
	@Autowired
	private  JdbcTemplate jdbcTempate;
	
	public void insertLoginLog(LoginLog loginLog){
		
		String sql = "insert into t_login_log(user_id,ip,login_datetime) values(?,?,?)";
		
		Object[] objs = {loginLog.getUserId(),loginLog.getIp(),loginLog.getLoginDate()};
		
		jdbcTempate.update(sql, objs);
	}
}
