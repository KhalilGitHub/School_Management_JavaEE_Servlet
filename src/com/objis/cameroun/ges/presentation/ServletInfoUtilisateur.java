package com.objis.cameroun.ges.presentation;

import java.io.IOException; 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 
import com.objis.cameroun.ges.domain.UserAccount;
import com.objis.cameroun.ges.dao.MyUtils;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet("/ServletInfoUtilisateur")
public class ServletInfoUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;     
	public ServletInfoUtilisateur() 
	{        
		super();    
	}     
	
	@Override    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{        
		HttpSession session = request.getSession();         
		// Check User has logged on        
		UserAccount loginedUser = MyUtils.getLoginedUser(session);         
		// Not logged in        
		if (loginedUser == null) 
		{            
			// Redirect to login page.            
			response.sendRedirect(request.getContextPath() + "/ServletLogin");            
			return;        
		}        
		// Store info to the request attribute before forwarding.        
		request.setAttribute("user", loginedUser);         
		
		// If the user has logged in, then forward to the page        
		// /WEB-INF/views/userInfoView.jsp        
		RequestDispatcher dispatcher //                
		= this.getServletContext().getRequestDispatcher("/userInfoView.jsp");        
		dispatcher.forward(request, response);     
	}     
	
	@Override    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{        
		doGet(request, response);    
	} 
}