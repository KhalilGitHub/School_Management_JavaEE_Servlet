package com.objis.cameroun.ges.presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;

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




/**
 * Servlet implementation class ModifDeleteProduct
 */
@WebServlet("/ServletModifierSupprimer")
public class ServletModifierSupprimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 
	public ServletModifierSupprimer() {        super();    }     
	
	@Override    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	{        
		//Connection conn = MyUtils.getStoredConnection(request);
		IService iService = null; 
		Connection conn;
		
		
		String errorString = null;        
		List<Inscription> list = null;        
		try 
		{            
			iService = new Service();
			conn = ConnectInscription.getMySQLConnection();
			list = iService.queryInscriptionService(conn);
			     
		} 
		catch (SQLException | ClassNotFoundException e) 
		{            
			e.printStackTrace();            
			errorString = e.getMessage();        
		}        
		// Store info in request attribute, before forward to views        
		request.setAttribute("errorString", errorString); 
		
		request.setAttribute("listInscription", list);  
		
		// Forward to /WEB-INF/views/InscriptionListView.jsp        
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/modifierOuSupprimerInscription.jsp");        
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	}     
	
	@Override    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{        
		doGet(request, response);    
	} 
} 

