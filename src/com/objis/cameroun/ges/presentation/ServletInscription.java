package com.objis.cameroun.ges.presentation;

import java.io.ByteArrayOutputStream;


import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.objis.cameroun.ges.domain.Eleve;
import com.objis.cameroun.ges.domain.Inscription;
import com.objis.cameroun.ges.service.IService;
import com.objis.cameroun.ges.service.Service;
import com.objis.cameroun.ges.connection.ConnectInscription;



import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

/**
 * Servlet implementation class ServletInscription
 */

@MultipartConfig(maxFileSize = 169999999)
@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;     
	public ServletInscription() 
	{        super();    }     
	// Show Inscription creation page.    
	
	@Override    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{         
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Inscription.jsp");        
		dispatcher.forward(request, response);    
	}     
	
	// When the user enters the Inscription information, and click Submit.    
	// This method will be called.    
	
	@Override    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{        
		//Connection conn = MyUtils.getStoredConnection(request);  
		
		Connection conn;
		try {
			conn = ConnectInscription.getMySQLConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int age = 0;
		BigDecimal frais = null;
		Date date = null;
		//byte[] fileImage;
		Inscription inscription ;
		Eleve eleve = null;
		IService iService = null; 
		
		
		String matricule = (String) request.getParameter("matricule");        
		String nom = (String) request.getParameter("nom");  
		String prenom = (String) request.getParameter("prenom");
		String genre = (String) request.getParameter("genre");
		String adresse = (String) request.getParameter("adresse");
		String ageStr = (String) request.getParameter("age");
		String classe = (String) request.getParameter("classe");
		String fraisStr = (String) request.getParameter("frais");
		String dateStr = (String) request.getParameter("date");
	
		 InputStream inputStream = null;
		 Part filePart = request.getPart("image");
         if (filePart != null) 
         {
             /*// prints out some information for debugging
             System.out.println(filePart.getName());
             System.out.println(filePart.getSize());
             System.out.println(filePart.getContentType());
			*/
             inputStream = filePart.getInputStream();
         }
		
         System.out.println(inputStream);
         byte[] contents;
         ByteArrayOutputStream output = new ByteArrayOutputStream();
         byte[] buffer = new byte[1024];
         int count;
         while ((count = inputStream.read(buffer)) != -1)
         {
         	output.write(buffer, 0, count);
         }
     
         contents = output.toByteArray();
         Blob blob = null;
         try 
         {
         	blob = new SerialBlob(contents);
         } 
         catch (SerialException e) {e.printStackTrace();}
         catch (SQLException e) {e.printStackTrace();}
         
		try 
		{            
			age = Integer.parseInt(ageStr);
			frais = convertStringToBigDecimal(fraisStr);  
			date = new SimpleDateFormat("mm/dd/yyyy").parse(dateStr);
			
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace();       
		}  
		
		eleve = new Eleve(nom, prenom, genre, adresse, age, classe);			
		inscription = new Inscription(matricule, eleve, frais, date, blob);
						
		String errorString = null;         
		
		// Inscription ID is the string literal [a-zA-Z_0-9]        
		// with at least 1 character        
		String regex = "\\w+";         
		if (matricule == null || !matricule.matches(regex)) 
		{            
			errorString = "Code d'Inscriotion invalide !!!";        
		}         
		if (errorString == null) 
		{            
			try 
			{    
				iService = new Service();
				conn = ConnectInscription.getMySQLConnection();
				iService.insertInscriptionService(conn, inscription);
			} 
			catch (SQLException | ClassNotFoundException e) 
			{                
				e.printStackTrace();                
				errorString = e.getMessage();            
			}        
		}         
		
		// Store infomation to request attribute, before forward to views.        
		request.setAttribute("errorString", errorString);        
		request.setAttribute("inscription", inscription);         
		
		// If error, forward to Edit page.        
		if (errorString != null) 
		{            
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Inscription.jsp");            
			dispatcher.forward(request, response);        
		}        
		
		// If everything nice.        
		// Redirect to the Inscription listing page.        
		else 
		{            
			response.sendRedirect(request.getContextPath() + "/ServletListeTous");        
		}    
	} 
	
	protected BigDecimal convertStringToBigDecimal(String bdStr)
	 {
		 BigDecimal result = null;
		 try
			{
				double valueDouble = Double.parseDouble(bdStr);
				result = BigDecimal.valueOf(valueDouble);
			}
			catch(Exception ex)
			{
				System.out.println("Valeur invalid. Valeur par defaut to 0.0");
				result = BigDecimal.valueOf(0.0);
			}	
		return result;	
	}
	
}