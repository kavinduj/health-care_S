package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegDoc {

	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/paf", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	// RD_ID RD_Name RD_Specialization RD_NIC RD_Hospital RD_RegisterNumber
	// RD_PhoneNumber RD_Email
	// RD_Fee RD_Monday RD_Tuesday RD_Wednesday RD_Thursday RD_Friday RD_Saturday
	// RD_Sunday

	public String readItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr> <th>RD_ID</th> <th>RD_Name</th> <th>RD_Specialization</th> <th>RD_NIC</th> <th>RD_Hospital</th> <th>RD_RegisterNumber</th> <th>RD_PhoneNumber</th> <th>RD_Email</th> <th>RD_Fee</th> <th>RD_Monday</th> <th>RD_Tuesday</th> <th>RD_Wednesday</th> <th>RD_Thursday</th> <th>RD_Friday</th> <th>RD_Saturday</th> <th>RD_Sunday</th> </tr>";
			String query = "select * from registered_doctor";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String RD_ID = Integer.toString(rs.getInt("RD_ID"));
				String RD_Name = rs.getString("RD_Name");
				String RD_Specialization = rs.getString("RD_Specialization");
				String RD_NIC = rs.getString("RD_NIC");
				String RD_Hospital = rs.getString("RD_Hospital");
				String RD_RegisterNumber = rs.getString("RD_RegisterNumber");
				String RD_PhoneNumber = rs.getString("RD_PhoneNumber");
				String RD_Email = rs.getString("RD_Email");
				String RD_Fee = rs.getString("RD_Fee");
				String RD_Monday = rs.getString("RD_Monday");
				String RD_Tuesday = rs.getString("RD_Tuesday");
				String RD_Wednesday = rs.getString("RD_Wednesday");
				String RD_Thursday = rs.getString("RD_Thursday");
				String RD_Friday = rs.getString("RD_Friday");
				String RD_Saturday = rs.getString("RD_Saturday");
				String RD_Sunday = rs.getString("RD_Sunday");

				// Add into the html table

				output += "<tr><td>" + RD_ID + "</td>";
				output += "<td>" + RD_Name + "</td>";
				output += "<td>" + RD_Specialization + "</td>";
				output += "<td>" + RD_NIC + "</td>";
				output += "<td>" + RD_Hospital + "</td>";
				output += "<td>" + RD_RegisterNumber + "</td>";
				output += "<td>" + RD_PhoneNumber + "</td>";
				output += "<td>" + RD_Email + "</td>";
				output += "<td>" + RD_Fee + "</td>";
				output += "<td>" + RD_Monday + "</td>";
				output += "<td>" + RD_Tuesday + "</td>";
				output += "<td>" + RD_Wednesday + "</td>";
				output += "<td>" + RD_Thursday + "</td>";
				output += "<td>" + RD_Friday + "</td>";
				output += "<td>" + RD_Saturday + "</td>";
				output += "<td>" + RD_Sunday + "</td></tr>";

				// buttons
				// output += "<td><input name=\"btnUpdate\" type=\"button\"
				// value=\"Update\" class=\"btn btn-secondary\"></td>"
				// + "<td><form method=\"post\" action=\"items.jsp\">"
				// + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"
				// class=\"btn btn-danger\">"
				// + "<input name=\"itemID\" type=\"hidden\" value=\"" + itemID
				// + "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertItem(String RD_Name, String RD_Specialization, String RD_NIC, String RD_Hospital,
			String RD_RegisterNumber, String RD_PhoneNumber, String RD_Email, String RD_Fee, String RD_Monday,
			String RD_Tuesday, String RD_Wednesday, String RD_Thursday, String RD_Friday, String RD_Saturday,
			String RD_Sunday) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement

			String query = " insert into registered_doctor(`RD_ID`,`RD_Name`,`RD_Specialization`,`RD_NIC`,`RD_Hospital`,`RD_RegisterNumber`,`RD_PhoneNumber`,`RD_Email`,`RD_Fee`,`RD_Monday`,`RD_Tuesday`,`RD_Wednesday`,`RD_Thursday`,`RD_Friday`,`RD_Saturday`,`RD_Sunday`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, RD_Name);
			preparedStmt.setString(3, RD_Specialization);
			preparedStmt.setString(4, RD_NIC);
			preparedStmt.setString(5, RD_Hospital);
			preparedStmt.setString(6, RD_RegisterNumber);
			preparedStmt.setString(7, RD_PhoneNumber);
			preparedStmt.setString(8, RD_Email);
			preparedStmt.setString(9, RD_Fee);
			preparedStmt.setString(10, RD_Monday);
			preparedStmt.setString(11, RD_Tuesday);
			preparedStmt.setString(12, RD_Wednesday);
			preparedStmt.setString(13, RD_Thursday);
			preparedStmt.setString(14, RD_Friday);
			preparedStmt.setString(15, RD_Saturday);
			preparedStmt.setString(16, RD_Sunday);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	
	
	
	
	public String updateItem(String RD_ID, String RD_Name, String RD_Specialization, String RD_NIC, String RD_Hospital,
			String RD_RegisterNumber, String RD_PhoneNumber, String RD_Email, String RD_Fee, String RD_Monday,
			String RD_Tuesday, String RD_Wednesday, String RD_Thursday, String RD_Friday, String RD_Saturday,
			String RD_Sunday)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE registered_doctor SET RD_Name=?,RD_Specialization=?,RD_NIC=?,RD_Hospital=?,RD_RegisterNumber=?,RD_PhoneNumber=?,RD_Email=?,RD_Fee=?,RD_Monday=?,RD_Tuesday=?,RD_Wednesday=?,RD_Thursday=?,RD_Friday=?,RD_Saturday=?,RD_Sunday=?WHERE RD_ID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, RD_Name);
	 preparedStmt.setString(2, RD_Specialization);
	 preparedStmt.setString(3, RD_NIC);
	 preparedStmt.setString(4, RD_Hospital);
	 preparedStmt.setString(5, RD_RegisterNumber);
	 preparedStmt.setString(6, RD_PhoneNumber);
	 preparedStmt.setString(7, RD_Email);
	 preparedStmt.setString(8, RD_Fee);
	 preparedStmt.setString(9, RD_Monday);
	 preparedStmt.setString(10, RD_Tuesday);
	 preparedStmt.setString(11, RD_Wednesday);
	 preparedStmt.setString(12, RD_Thursday);
	 preparedStmt.setString(13, RD_Friday);
	 preparedStmt.setString(14, RD_Saturday);
	 preparedStmt.setString(15, RD_Sunday);
	 preparedStmt.setString(16,RD_ID);
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
	

	public String deleteItem(String RD_ID)
	 {
	 String output = "";
	 try
	 { 
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from registered_doctor where RD_ID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(RD_ID));
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
