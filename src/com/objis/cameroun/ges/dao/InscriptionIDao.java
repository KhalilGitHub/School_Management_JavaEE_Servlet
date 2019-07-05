package com.objis.cameroun.ges.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

import com.objis.cameroun.ges.domain.Inscription;

public interface InscriptionIDao {
	
	
	public  List<Inscription> queryInscription(Connection conn) throws SQLException;
	public  Inscription findInscription(Connection conn, String matricule) throws SQLException;
	public  void updateInscription(Connection conn, Inscription Inscription) throws SQLException;
	public  void insertInscription(Connection conn, Inscription inscription) throws SQLException;
	public  void deleteInscription(Connection conn, String mat) throws SQLException;
	public  List<Inscription> getAllInscriptions() throws ClassNotFoundException;
	
}
