package com.atm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.atm.config.DBConfig;
import com.atm.pojo.UserResponse;

public class UserResponseDao {
	
	static DBConfig dbc = new DBConfig();
	
	public boolean setUserResponse(ArrayList<UserResponse> usrReses) throws SQLException {
		Connection con = dbc.GetMysqlCon();
		int inserted=0;
		try {
			
			String sql = "insert into  ayurveda.userresponse (patient_patient_id,Answer_id,question_id,creationtime) values (?,?,?,sysdate());";
			PreparedStatement statement = con.prepareStatement(sql);
			for(UserResponse usrRes: usrReses) {
			statement.setInt(1, usrRes.getPatientId());
			statement.setInt(2, usrRes.getAnswerId());
			statement.setInt(3, usrRes.getQuestionId());
			inserted = statement.executeUpdate();
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			con.close();
		}
		if(inserted == usrReses.size()) {
			return true;
			}
		return false;
	}
	
	public UserResponse getUserResponse(int id) throws SQLException {
		Connection con = dbc.GetMysqlCon();
		UserResponse usrRes = new UserResponse();
		try {
			String sql = "select * from ayurveda.userresponse where id = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			usrRes.setId(rs.getInt("id"));
			usrRes.setPatientId(rs.getInt("patient_patient_id"));
			usrRes.setAnswerId(rs.getInt("Answer_id"));
			usrRes.setAnswerId(rs.getInt("question_id"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			con.close();
		}
		
		return usrRes;
	}

}
