package com.example.cmpt371project;

import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.postgresql.copy.CopyManager;
//import org.postgresql.core.BaseConnection;

public class UpdateDatabase {
	public Connection conn;
	public final String url="jdbc:postgresql://localhost:5432/postgres";
	public final String user="postgres";
	public final String password="";

	public UpdateDatabase()
	{
		try {

			conn = DriverManager.getConnection(url, user, password);
		}
		catch (Exception e) {

		}
	}

	public void copyExternalDatabase()
	{

		PreparedStatement pst = null;
		ResultSet rs = null;
		FileWriter fw = null;

		try {


//			CopyManager cm = new CopyManager((BaseConnection) conn);

			fw = new FileWriter("test.csv");
//			cm.copyOut("COPY ok TO STDOUT WITH DELIMITER AS ','", fw);

		} catch (Exception ex) {
			Logger lgr = Logger.getLogger(UpdateDatabase.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (fw != null) {
					fw.close();
				}

			} catch (Exception ex) {
				Logger lgr = Logger.getLogger(UpdateDatabase.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
	}
	public void debug() throws IOException
	{
		String line = "";
		String tableName ="userData";
		String columns = "user_ID, user_Password";
		String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
		String str2 = ");";
		FileReader file;
		try {
			file = new FileReader("test.csv");
			BufferedReader buffer = new BufferedReader(file);

			while ((line = buffer.readLine()) != null) {
				StringBuilder sb = new StringBuilder(str1);
				String[] str = line.split(",");
				sb.append("'" + str[0] + "',");
				sb.append(str[1] + "'");
				sb.append(str2);
				System.out.println(sb.toString());
			}
		}
		catch(Exception e)
		{

		}
	}



	public static void main(String[] args) {

		UpdateDatabase test = new UpdateDatabase();
		test.copyExternalDatabase();
		try {
			test.debug();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//    	try {
		//			test.importFromFile();
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
	}
}