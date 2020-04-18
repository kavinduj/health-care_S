package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
	//A common method to connect to the DB
		public Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "");
		 
		//For testing
		 System.out.print("Successfully connected"); 
		 
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		
		
		
		public String insertPayment(String p_Amount,String NameOnCard, String Cvc, String Expdate,String CardNumber,int AppointmentID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " insert into payment(`P_PaymentID`,`P_Amount`,`P_NameOnCard`,`P_CVC`,`P_ExpiringDate`,`P_CardNumber`,`P_AppointmentID`)"
		 + " values (?, ?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setDouble(2, Double.parseDouble(p_Amount));
		 preparedStmt.setString(3, NameOnCard);
		 preparedStmt.setString(4, Cvc);
		 preparedStmt.setString(5, Expdate);
		 preparedStmt.setString(6, CardNumber);
		 preparedStmt.setInt(7, AppointmentID);
		 
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the payment Details.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		public String readPayments()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border=\"1\"><tr><th>Amount</th><th>Name of Card</th><th>CVC</th><th>Exp Date</th><th>Card Number</th><th>Appointment ID</th><th>Update</th><th>Remove</th></tr>";
		 String query = "select * from payment";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String P_PaymentID = Integer.toString(rs.getInt("P_PaymentID"));
		 String P_Amount = Double.toString(rs.getDouble("P_Amount"));
		 String P_NameOnCard = rs.getString("P_NameOnCard");
		 String P_CVC = rs.getString("P_CVC");
		 String P_ExpiringDate = rs.getString("P_ExpiringDate");
		 String P_CardNumber = Integer.toString(rs.getInt("P_CardNumber"));
		 String P_AppointmentID = rs.getString("P_AppointmentID");
		 
		 // Add into the html table
		 output += "<td>" + P_Amount + "</td>";
		 output += "<td>" + P_NameOnCard + "</td>";
		 output += "<td>" + P_CVC + "</td>";
		 output += "<td>" + P_ExpiringDate + "</td>";
		 output += "<td>" + P_CardNumber + "</td>";
		 output += "<td>" + P_AppointmentID + "</td>";
		 // buttons
		 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"items.jsp\">" + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
		 + "<input name=\"P_PaymentID\" type=\"hidden\" value=\"" + P_PaymentID
		 + "\">" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the Payment Details.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
		
		public String updatePayment(String pID,String Pamount, String NOnCard, String cv, String Exdate,String Cnumber,String ApID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE payment SET P_Amount=?,P_NameOnCard=?,P_CVC=?,P_ExpiringDate=?,P_CardNumber=?,P_AppointmentID=?WHERE P_PaymentID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setDouble(1, Double.parseDouble(Pamount));
		 preparedStmt.setString(2, NOnCard);
		 preparedStmt.setString(3, cv);
		 preparedStmt.setString(4, Exdate);
		 preparedStmt.setString(5, Cnumber);
		 preparedStmt.setString(6, ApID);
		 preparedStmt.setInt(7, Integer.parseInt(pID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated Payment successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the Payament.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		public String deletePayment(String P_PaymentID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from payment where P_PaymentID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(P_PaymentID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted Payment successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the Payment Details.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 

}

