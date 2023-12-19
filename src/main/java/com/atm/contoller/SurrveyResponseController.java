package com.atm.contoller;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.atm.config.DBConfig;
import com.atm.dao.User;
import com.atm.dao.UserResponseDao;
import com.atm.pojo.Patient;
import com.atm.pojo.UserResponse;
import com.atm.service.TwoStepVerificationService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/userData") 
public class SurrveyResponseController extends HttpServlet {
	
	final String AUTHENTICATION_PAGE="/Authenticate.jsp";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String email1 = (String) session.getAttribute("email1");
		Patient patient =new Patient(); 
		ArrayList<UserResponse> urs = new ArrayList<UserResponse>();
		try {
			patient = new User().getPatiest(email);
			patient = new User().getPatiest(email1);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		Map<String, String[]> a = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : a.entrySet()) {
            String paramName = entry.getKey();
            String[] paramValues = entry.getValue();

            // Process each parameter and its values
            for (String paramValue : paramValues) {
            	UserResponse ur = new UserResponse();
            	ur.setAnswerId(Integer.parseInt(paramValue));
				ur.setPatientId(patient.getPatientID());
                ur.setQuestionId(Integer.parseInt(paramName));
                urs.add(ur);
            }
        }
		try {
			boolean isInsert = new UserResponseDao().setUserResponse(urs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
