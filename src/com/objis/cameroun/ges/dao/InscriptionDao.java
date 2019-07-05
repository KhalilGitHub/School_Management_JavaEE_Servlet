package com.objis.cameroun.ges.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;

import com.objis.cameroun.ges.domain.Eleve;
import com.objis.cameroun.ges.domain.Inscription;
import com.objis.cameroun.ges.connection.ConnectInscription;



public class InscriptionDao implements InscriptionIDao{

	
	public  List<Inscription> queryInscription(Connection conn) throws SQLException 
	{     
		
		Inscription inscription = null;
		Eleve eleve = null;
		String matricule = null;
		String nom = null;
		String prenom = null;
		String genre = null;
		String adresse = null;
		int age = 0;
		String classe = null;
		BigDecimal frais = null;
		Date date = null; 
		
		
			String sql = "Select a.Matricule, a.Nom, a.Prenom, a.Genre, a.Adresse, a.Age, a.Classe, a.Frais, a.Date from tbl_inscription a ";         
			PreparedStatement pstm = conn.prepareStatement(sql);         
			ResultSet rs = pstm.executeQuery();        
			List<Inscription> list = new ArrayList<Inscription>();  
			
			try
			{
				while (rs.next()) 
				{      
						
					matricule = rs.getString("Matricule");
					nom = rs.getString("Nom");
					prenom = rs.getString("Prenom");
					genre = rs.getString("Genre");
					adresse = rs.getString("Adresse");
					age = rs.getInt("Age");
					classe = rs.getString("Classe");
					frais = rs.getBigDecimal("Frais");
					date = rs.getDate("Date");
				
					inscription = new Inscription();
					eleve = new Eleve();
					inscription.setMatricule(matricule);
					eleve.setNom(nom);
					eleve.setPrenom(prenom);
					eleve.setGenre(genre);
					eleve.setAdresse(adresse);
					eleve.setAge(age);
					eleve.setClasse(classe);
					inscription.setEleve(eleve);            
					inscription.setFrais(frais); 
					inscription.setDate(date);
						
					list.add(inscription); 					
				}        
		}
		catch (SQLException e) {
		      throw new SQLException(e);
		  } finally {
		      close(conn, pstm, rs);
		  }
		
		return list;    
	}     
	
	public  Inscription findInscription(Connection conn, String matricule) throws SQLException 
	{        
	
		Inscription inscription = null;
		Eleve eleve = null;
		String nom = null;
		String prenom = null;
		String genre = null;
		String adresse = null;
		int age = 0;
		String classe = null;
		BigDecimal frais = null;
		Date date = null; 
		
		String sql = "Select a.Nom, a.Prenom, a.Genre, a.Adresse, a.Age, a.Classe, a.Frais, a.Date, a.Image from tbl_inscription a where Matricule=?";         
		PreparedStatement pstm = conn.prepareStatement(sql);        
				
		pstm.setString(1, matricule);         
		ResultSet rs = pstm.executeQuery();   
		
		try
		{
			while (rs.next()) 
			{       
			
				nom = rs.getString("Nom");
				prenom = rs.getString("Prenom");
				genre = rs.getString("Genre");
				adresse = rs.getString("Adresse");
				age = rs.getInt("Age");
				classe = rs.getString("Classe");
				frais = rs.getBigDecimal("Frais");
				date = rs.getDate("Date");
			
				inscription = new Inscription();
				eleve = new Eleve();
				
				eleve.setNom(nom);
				eleve.setPrenom(prenom);
				eleve.setGenre(genre);
				eleve.setAdresse(adresse);
				eleve.setAge(age);
				eleve.setClasse(classe);
				inscription.setEleve(eleve);            
				inscription.setFrais(frais); 
				inscription.setDate(date);
				
			
				Blob photo = rs.getBlob("Image");                                 
	   			InputStream inputStream = photo.getBinaryStream();                
	   			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();                
	   			byte[] buffer = new byte[4096];                
	   			int bytesRead = -1;   
	   			
	   			try {
					while ((bytesRead = inputStream.read(buffer)) != -1) 
					{                    
						outputStream.write(buffer, 0, bytesRead);                                   
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}                                 
	   			byte[] imageBytes = outputStream.toByteArray();                
	   			String base64Image = Base64.getEncoder().encodeToString(imageBytes);                                                  
	   			try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}                
	   			try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}                                 

				inscription = new Inscription(matricule, eleve, frais, date, base64Image);            
				       
			}       
		}
		catch (SQLException e) {
		      throw new SQLException(e);
		  } finally {
		      close(conn, pstm, rs);
		  }
		
		
		return inscription;
	}     
	
	public  void updateInscription(Connection conn, Inscription Inscription) throws SQLException 
	{        
		String sql = "UPDATE tbl_inscription SET Nom = ?, Prenom=?, Genre = ?, Adresse = ?, Age = ? , Classe = ?, Frais = ?, Date = ?, Image = ? WHERE Matricule = ? ";         
		
		PreparedStatement pstm = conn.prepareStatement(sql);         
		
		try
		{
			pstm.setString(1, Inscription.getEleve().getNom());
			pstm.setString(2, Inscription.getEleve().getPrenom());
			pstm.setString(3, Inscription.getEleve().getGenre());
			pstm.setString(4, Inscription.getEleve().getAdresse());
			pstm.setInt(5, Inscription.getEleve().getAge());
			pstm.setString(6, Inscription.getEleve().getClasse());
			pstm.setBigDecimal(7, Inscription.getFrais());
			pstm.setDate(8, new Date(Inscription.getDate().getTime()));
			pstm.setBlob(9, Inscription.getImage());

			pstm.setString(10, Inscription.getMatricule());
			
			pstm.executeUpdate();
			
		}
		catch (SQLException e) {
		      throw new SQLException(e);
		  } finally {
		      close(conn, pstm);
		  }
		    
	}     
	
	public  void insertInscription(Connection conn, Inscription inscription) throws SQLException 
	{        
		String sql = "Insert into tbl_inscription(Matricule, Nom, Prenom, Genre, Adresse, Age, Classe, Frais, Date, Image) values (?,?,?,?,?,?,?,?,?,?)";         
		
		PreparedStatement pstm = conn.prepareStatement(sql);    
		
		try
		{
			
			pstm.setString(1, inscription.getMatricule());
			pstm.setString(2, inscription.getEleve().getNom());
			pstm.setString(3, inscription.getEleve().getPrenom());
			pstm.setString(4, inscription.getEleve().getGenre());
			pstm.setString(5, inscription.getEleve().getAdresse());
			pstm.setInt(6, inscription.getEleve().getAge());
			pstm.setString(7, inscription.getEleve().getClasse());
			pstm.setBigDecimal(8, inscription.getFrais());
			pstm.setDate(9, new Date(inscription.getDate().getTime()));
			pstm.setBlob(10, inscription.getImage());
		
			pstm.executeUpdate();
			
		}
		catch (SQLException e) {
		      e.printStackTrace();
		  } finally {
		      close(conn, pstm);
		  }
		    
	}     
	
	public  void deleteInscription(Connection conn, String mat) throws SQLException 
	{    
		try
		{
			
			String sql = "Delete From tbl_inscription where Matricule= ?";         
			PreparedStatement pstm = conn.prepareStatement(sql);         
			
			pstm.setString(1, mat);         
			pstm.executeUpdate();
			
		}
		catch (SQLException e) {
		      throw new SQLException(e);
		  } finally {
		      close(conn);
		  }
		    
	} 
	
	
	public  List<Inscription> getAllInscriptions() throws ClassNotFoundException {
		
		 List<Inscription> lInscription = new ArrayList<Inscription>();
		 Connection con;
		 
		 try{
		 con = ConnectInscription.getMySQLConnection();
		 String sql="SELECT * FROM tbl_inscription ";
		 
		 PreparedStatement pst = con.prepareStatement(sql);
		 
		 ResultSet rs = pst.executeQuery();
		 
		 while(rs.next()){
		 
			    Inscription inscription = new Inscription();			 	
			 	Eleve eleve = new Eleve();
				inscription.setMatricule(rs.getString(1));
				eleve.setNom(rs.getString(2));
				eleve.setPrenom(rs.getString(3));
				eleve.setGenre(rs.getString(4));
				eleve.setAdresse(rs.getString(5));
				eleve.setAge(rs.getInt(6));
				eleve.setClasse(rs.getString(7));
				inscription.setEleve(eleve);            
				inscription.setFrais(rs.getBigDecimal(8)); 
				inscription.setDate(rs.getDate(9));	
			 
				 lInscription.add(inscription);	
			 }
			 
			 }
			 catch(SQLException e){
			 e.printStackTrace();
			 }
		 return lInscription;
	 }	 
	
	
	
	private static void close(Connection conn, PreparedStatement preparedStatement, ResultSet resultset) throws SQLException {
		
			conn.close();
			preparedStatement.close();
			resultset.close();
	}	
	
	private static void close(Connection conn, PreparedStatement preparedStatement) throws SQLException {
		
		conn.close();
		preparedStatement.close();
	}	
	
	private static void close(Connection conn) throws SQLException {
		
		conn.close();
		
	}	
} 