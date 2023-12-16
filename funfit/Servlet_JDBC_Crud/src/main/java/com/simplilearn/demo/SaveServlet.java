package com.simplilearn.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/save")
public class SaveServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=resp.getWriter();
		
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String username=req.getParameter("username");
		
		Connection conn= DBConfig.getConnection();
		
		if(conn!=null) {
			try {
				PreparedStatement stmt=conn.prepareStatement("insert into student (name,email,username) value(?,?,?)");
				stmt.setString(1, name);
				stmt.setString(2, email);
				stmt.setString(3, username);
				
				int x=stmt.executeUpdate();
				if(x>0) {
					out.println("Record inserted successfully");
					
					resp.sendRedirect("list");
				}
					
				else {
					out.println("Error while inserting record");
				}
					
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			out.println("Error while connecting");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	

}
