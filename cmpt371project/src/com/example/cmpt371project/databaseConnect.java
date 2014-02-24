package com.example.cmpt371project;


import java.sql.*;


public class databaseConnect {
	public Connection conn;
	public final String url="jdbc:postgresql://localhost:5432/postgres";
	public final String user="postgres";
	public final String password="";
	
	public void getConnection()
	{
		try {
		

			conn = DriverManager.getConnection(url,user,password);

		}
		catch(Exception e){
			System.out.println(e.toString()); 
		}
	}
	public void closeConnection() 
	{
		try{
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	public static void main(String[] args)
	{
		databaseConnect db = new databaseConnect();
		db.getConnection();
	}
}
