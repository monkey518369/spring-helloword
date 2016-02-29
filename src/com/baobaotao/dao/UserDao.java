package com.baobaotao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.baobaotao.domain.User;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int getMatchCount(String userName,String password){
		
		String sql = "select count(user_id) from t_user where user_name=? and password=?";
		return jdbcTemplate.queryForInt(sql,new Object[]{userName, password});
	}
	
	public User findUserByUserName(String userName){
		
		final User user = new User();
		String sql = "select * from t_user where user_name=?";
		jdbcTemplate.query(sql, new Object[]{userName},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet result) throws SQLException {
				user.setUserId(result.getInt("user_id"));
				user.setUserName(result.getString("user_name"));
				user.setCredits(result.getInt("credits"));
			}
			
		});
		return user;

	}
	
	public void updateLoginInfo(User user){
		String sql = "update t_user set last_vist=?,last_ip=?,credits=? where user_id=?";
		jdbcTemplate.update(sql, new Object[]{user.getLastVisit(),user.getLastIp(),user.getCredits(),user.getUserId()});
	}
}
