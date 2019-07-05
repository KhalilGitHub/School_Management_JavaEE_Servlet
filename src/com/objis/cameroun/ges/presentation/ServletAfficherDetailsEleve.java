package com.objis.cameroun.ges.presentation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.objis.cameroun.ges.domain.Inscription;
import com.objis.cameroun.ges.service.IService;
import com.objis.cameroun.ges.service.Service;
import com.objis.cameroun.ges.connection.ConnectInscription;



@WebServlet("/ServletAfficherDetailsEleve")
public class ServletAfficherDetailsEleve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public ServletAfficherDetailsEleve() {      super();  }   
	
	  
		@Override    
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{        
			IService iService = null; 
			Connection conn = null;
			
			
			try {
				conn = ConnectInscription.getMySQLConnection();
			} catch (ClassNotFoundException e1) {
				
				e1.printStackTrace();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			String matricule = (String) request.getParameter("matricule");         
			Inscription inscription = null;         
			String errorString = null;         
			try 
			{   
				iService = new Service(); 
				conn = ConnectInscription.getMySQLConnection();
				inscription = iService.findInscriptionService(conn, matricule);
				
				
			} 
			catch (SQLException e) 
			{            
				e.printStackTrace();            
				errorString = e.getMessage();        
			} catch (ClassNotFoundException e) {
		
				e.printStackTrace();
			}         
			
			
			
		
			
			// If no error.        
			// The product does not exist to edit.        
			// Redirect to Inscription List page.        
			if (errorString != null && inscription == null) 
			{            
				response.sendRedirect(request.getServletPath() + "/ServletListeTous");            
				return;        
			}         
			
			// Store errorString in request attribute, before forward to views.        
			request.setAttribute("errorString", errorString);        
			request.setAttribute("inscription", inscription);   
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/afficherDetailsEleve.jsp");        
			dispatcher.forward(request, response);     
		}     
		
		// After the user modifies the Inscription information, and click Submit.    
		// This method will be executed.    
		@Override    
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{        

			doGet(request, response);
			
		}
			
	}