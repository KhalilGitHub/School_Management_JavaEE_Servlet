package com.objis.cameroun.ges.presentation;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.objis.cameroun.ges.domain.Inscription;
import com.objis.cameroun.ges.service.IService;
import com.objis.cameroun.ges.service.Service;


/**
 * Servlet implementation class Inscription List Servlet
 */
@WebServlet("/ServletListeTous")
public class ServletListeTous extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListeTous() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IService iService = null; 
		
		 RequestDispatcher view = request.getRequestDispatcher("/listerTousEleves.jsp");
		 List<Inscription> lproduct;
		try {
			iService = new Service();
			lproduct = iService.getAllInscriptionsService();
			request.setAttribute("listProducts", lproduct);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		 view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);   
	}

}
