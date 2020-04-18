package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.xdevapi.Statement;

public class Hospital {
	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "");

			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertHospital(String Rnum, String Hname, String Address, String Pnum, String Email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into hospitalrreq(`RegNumber`,`Hospital_Name`,`Address`,`PhoneNumber`,`Email`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, Rnum);
			preparedStmt.setString(2, Hname);
			preparedStmt.setString(3, Address);
			preparedStmt.setString(4, Pnum);
			preparedStmt.setString(5, Email);
//execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readHospital() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Register Number</th><th>Hospital Name</th><th>Hoapital Address</th><th>Phone Number</th><th>Email</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from hospitalrreq";
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String RegNumber = rs.getString("RegNumber");
				String Hospital_Name = rs.getString("Hospital_Name");
				String Address = rs.getString("Address");
				String PhoneNumber = rs.getString("PhoneNumber");
				String Email = rs.getString("Email");
// Add into the html table
				output += "<tr><td>" + RegNumber + "</td>";
				output += "<td>" + Hospital_Name + "</td>";
				output += "<td>" + Address + "</td>";
				output += "<td>" + PhoneNumber + "</td>";
				output += "<td>" + Email + "</td>";
// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"RegNumber\" type=\"hidden\" value=\"" + RegNumber + "\">"
						+ "</form></td></tr>";
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

	public String updateHospital(String Rnum, String Hname, String Address, String Pnum, String Email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
// create a prepared statement
			String query = "UPDATE hospitalrreq SET RegNumber=?,Hospital_Name=?,Address=?,PhoneNumber=?,Email=?WHERE RegNumber=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setString(1, Rnum);
			preparedStmt.setString(2, Hname);
			preparedStmt.setString(3, Address);
			preparedStmt.setString(4, Pnum);
			preparedStmt.setString(5, Email);
			preparedStmt.setString(6, Rnum);
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteHospital(String Rnum) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
//create a prepared statement
			String query = "delete from hospitalrreq where RegNumber=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
//binding values
			preparedStmt.setString(1, Rnum);
//execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted Hospital details successfully";
		} catch (Exception e) {
			output = "Error while deleting the Hospital details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}