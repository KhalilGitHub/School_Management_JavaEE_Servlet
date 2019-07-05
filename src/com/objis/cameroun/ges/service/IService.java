package com.objis.cameroun.ges.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.objis.cameroun.ges.domain.Inscription;
import com.objis.cameroun.ges.domain.UserAccount;

public interface IService {
	

    public  UserAccount findUserService(Connection conn, String userName, String password) throws SQLException;
	public  UserAccount findUserService(Connection conn, String userName) throws SQLException;
	public  List<Inscription> queryInscriptionService(Connection conn) throws SQLException;
	public  Inscription findInscriptionService(Connection conn, String matricule) throws SQLException;
	public  void updateInscriptionService(Connection conn, Inscription Inscription) throws SQLException;
	public  void insertInscriptionService(Connection conn, Inscription inscription) throws SQLException;
	public  void deleteInscriptionService(Connection conn, String mat) throws SQLException;
	public  List<Inscription> getAllInscriptionsService() throws ClassNotFoundException;
}
