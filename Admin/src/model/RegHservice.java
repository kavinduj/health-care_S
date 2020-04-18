package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegHservice {
	
	
	//A common method to connect to the DB
		public Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/paf", "root", "");
		 
		//For testing
		 System.out.print("Successfully connected"); 
		 
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		
		
		public String insertItem(String HospitalName, String Email, String Address, String PhoneNumber, String Reg_Number)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " insert into registered_hospital(`Hospital_ID`,`HospitalName`,`Email`,`Address`,`PhoneNumber`,`Reg_Number`)"
		 + " values (?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, HospitalName);
		 preparedStmt.setString(3, Email);
		 preparedStmt.setString(4, Address);
		 preparedStmt.setString(5, PhoneNumber);
		 preparedStmt.setString(6, Reg_Number);
		
		 
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		public String readItems()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border=\"1\"><tr><th>Hospital_ID</th><th> HospitalName </th><th> Email </th><th> Address </th><th> PhoneNumber </th><th> Reg_Number </th></tr>";
		 String query = "select * from registered_hospital";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String Hospital_ID = Integer.toString(rs.getInt("Hospital_ID"));
			 String HospitalName = rs.getString("HospitalName");
			 String Email = rs.getString("Email");
			 String Address = rs.getString("Address");		
			 String PhoneNumber = rs.getString("PhoneNumber");
			 String Reg_Number = rs.getString("Reg_Number");
			
		 // Add into the html table
			 output += "<tr><td>" + Hospital_ID + "</td>";
			 output += "<td>" + HospitalName + "</td>";
			 output += "<td>" + Email + "</td>";
			 output += "<td>" + Address + "</td>";
			 output += "<td>" + PhoneNumber + "</td>";
			 output += "<td>" + Reg_Number + "</td></tr>";
			 
		 // buttons
		 //output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"items.jsp\">" + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
		// + "<input name=\"AppointmentID\" type=\"hidden\" value=\"" + AppointmentID
		// + "\">" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
 		
		
		
		public String updateItem(String Hospital_ID, String HospitalName, String Email, String Address, String PhoneNumber, String Reg_Number)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE registered_hospital SET HospitalName=?,Email=?,Address=?,PhoneNumber=?,Reg_Number=?WHERE Hospital_ID=?";
		 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, HospitalName);
		 preparedStmt.setString(2, Email);
		 preparedStmt.setString(3, Address);
		 preparedStmt.setString(4, PhoneNumber);
		 preparedStmt.setString(5, Reg_Number);
		 preparedStmt.setInt(6,Integer.parseInt(Hospital_ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
				
		
		public String deleteItem(String Hospital_ID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from registered_hospital where Hospital_ID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(Hospital_ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 



}
