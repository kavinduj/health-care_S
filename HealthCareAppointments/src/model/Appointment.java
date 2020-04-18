package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Appointment {
	
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
		
		
		
		public String insertItem(String aname, String anic, String aphone, String adoctorname, String ahosname, String adate, String atime)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " insert into appointment(`A_AppointmentID`,`A_PatientName`,`A_PatientNIC`,`A_PatientPhoneNo`,`A_DoctorName`,`A_HospitalName`,`A_Date`,`A_Time`)"
		 + " values (?, ?, ?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, aname);
		 preparedStmt.setString(3, anic);
		 preparedStmt.setString(4, aphone);
		 preparedStmt.setString(5, adoctorname);
		 preparedStmt.setString(6, ahosname);
		 preparedStmt.setString(7, adate);
		 preparedStmt.setString(8, atime);
		 
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the appointment.";
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
		 output = "<table border=\"1\"><tr>Appointment ID<th>Patient Name</th><th>Patient NIC</th><th>Patient PhoneNumber</th><th>Doctor Name</th><th>Hospital Name</th><th>Date</th><th>Time</th><th>Update</th><th>Remove</th></tr>";
		 String query = "select * from appointment";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
			 String A_AppointmentID = Integer.toString(rs.getInt("A_AppointmentID"));
			 String A_PatientName = rs.getString("A_PatientName");
			 String A_PatientNIC = rs.getString("A_PatientNIC");
			 String A_PatientPhoneNo = rs.getString("A_PatientPhoneNo");		
			 String A_DoctorName = rs.getString("A_DoctorName");
			 String A_HospitalName = rs.getString("A_HospitalName");
			 String A_Date = rs.getString("A_Date");
			 String A_Time = rs.getString("A_Time");
		 // Add into the html table
			 output += "<tr><td>" + A_PatientName + "</td>";
			 output += "<td>" + A_PatientNIC + "</td>";
			 output += "<td>" + A_PatientPhoneNo + "</td>";
			 output += "<td>" + A_DoctorName + "</td>";
			 output += "<td>" + A_HospitalName + "</td>";
			 output += "<td>" + A_Date + "</td>";
			 output += "<td>" + A_Time + "</td>";
		 // buttons
		 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"items.jsp\">" + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
		 + "<input name=\"A_AppointmentID\" type=\"hidden\" value=\"" + A_AppointmentID
		 + "\">" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the appointments";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
		
		public String updateItem(String aID, String aname, String anic, String aphone, String adoctorname, String ahosname, String adate, String atime)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE appointment SET A_PatientName=?,A_PatientNIC=?,A_PatientPhoneNo=?,A_DoctorName=?,A_HospitalName=?,A_Date=?,A_Time=?WHERE A_AppointmentID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, aname);
		 preparedStmt.setString(2, anic);
		 preparedStmt.setString(3, aphone);
		 preparedStmt.setString(4, adoctorname);
		 preparedStmt.setString(5, ahosname);
		 preparedStmt.setString(6, adate);
		 preparedStmt.setString(7, atime);
		 preparedStmt.setInt(8, Integer.parseInt(aID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the appointment.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		public String deleteItem(String A_AppointmentID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from appointment where A_AppointmentID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(A_AppointmentID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the appointment.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 


}