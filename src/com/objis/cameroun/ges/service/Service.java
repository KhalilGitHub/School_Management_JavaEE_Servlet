package com.objis.cameroun.ges.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.objis.cameroun.ges.dao.IloginDao;
import com.objis.cameroun.ges.dao.InscriptionDao;
import com.objis.cameroun.ges.dao.InscriptionIDao;
import com.objis.cameroun.ges.dao.LoginDao;
import com.objis.cameroun.ges.domain.Inscription;
import com.objis.cameroun.ges.domain.UserAccount;


public  class Service implements IService {

	private InscriptionIDao iIDao;
	private IloginDao iloginDao;
	
		public Service() {
		
		this.iIDao = new InscriptionDao();
		this.iloginDao = new LoginDao();
	}
	
		public  UserAccount findUserService(Connection conn, String userName, String password) throws SQLException
		{
			
			return iloginDao.findUser(conn, userName, password);
		}
		
		public  UserAccount findUserService(Connection conn, String userName) throws SQLException
		{
			return iloginDao.findUser(conn, userName);
		}
		
		public  List<Inscription> queryInscriptionService(Connection conn) throws SQLException {
			
			return iIDao.queryInscription(conn);
		}
		
		public  Inscription findInscriptionService(Connection conn, String matricule) throws SQLException {
			
			return iIDao.findInscription(conn, matricule);
		}
		
		public  void updateInscriptionService(Connection conn, Inscription Inscription) throws SQLException {
			
			iIDao.updateInscription(conn, Inscription);
			
		}
		
		public  void insertInscriptionService(Connection conn, Inscription inscription) throws SQLException {
			
			iIDao.insertInscription(conn, inscription);
			
		}
		
		public  void deleteInscriptionService(Connection conn, String mat) throws SQLException {
			
			iIDao.deleteInscription(conn, mat);
		}
		
		public  List<Inscription> getAllInscriptionsService() throws ClassNotFoundException {
			
			return iIDao.getAllInscriptions();
			
		}
}
