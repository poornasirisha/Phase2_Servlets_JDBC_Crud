package com.simplilearn.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/list")
public class FetchServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=resp.getWriter();
		out.println("List of Students Available");
		
		Connection conn=DBConfig.getConnection();
		
		if(conn!=null) {
			out.println("Connection Established");
			Statement stmt;
			try {
				stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery("select * from student");
				resp.setContentType("text/html");
				out.println("<table border=1 cellpadding=10 cellspacing=0><thead><tr>");
				out.print("<th>ID</th><th>NAME</th><th>EMAIL</th><th>USERNAME</th>");
				out.print("</tr></thead>");
				out.print("<tbody>");
				while(rs.next()) {
					out.print("<tr>");
					out.print("<td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td>");
					out.print("</tr>");
					//out.println("Id: "+rs.getInt(1)+", Name: "+rs.getString(2)+", Email: "+rs.getString(3)+", Username: "+rs.getString(4));
				}
				
				out.print("</tbody></table>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}else {
			out.println("Error While Connecting");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}