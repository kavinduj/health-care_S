package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctor {
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

	public String insertDoctor(String Dnic, String Dname, String DphoneNumber, String Dfee, String Demail,
			String Dspecial, String DregisterNumber, String Dhospitals) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into doctor_reg_req(`D_NIC`,`D_Name`,`D_PhoneNumber`,`D_Fee`,`D_Email`,`D_Specialization`,`D_RegisterNumber`,`D_Hospitals`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, Dnic);
			preparedStmt.setString(2, Dname);
			preparedStmt.setString(3, DphoneNumber);
			preparedStmt.setDouble(4, Double.parseDouble(Dfee));
			preparedStmt.setString(5, Demail);
			preparedStmt.setString(6, Dspecial);
			preparedStmt.setString(7, DregisterNumber);
			preparedStmt.setString(8, Dhospitals);

//execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted doctor details successfully";
		} catch (Exception e) {
			output = "Error while inserting the doctor details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readRegDetails() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Doctor NIC</th><th>Doctor Name</th><th>DoctorPhoneNumber</th><th>DoctorFee</th><th>DoctorEmail</th><th>Specialization</th><th>RegisterNumber</th><th>Hospitals</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from doctor_reg_req";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String D_NIC = rs.getString("D_NIC");
				String D_Name = rs.getString("D_Name");
				String D_PhoneNumber = rs.getString("D_PhoneNumber");
				String D_Fee = Double.toString(rs.getDouble("D_Fee"));
				String D_Email = rs.getString("D_Email");
				String D_Specialization = rs.getString("D_Specialization");
				String D_RegisterNumber = rs.getString("D_RegisterNumber");
				String D_Hospitals = rs.getString("D_Hospitals");
// Add into the html table
				output += "<tr><td>" + D_NIC + "</td>";
				output += "<td>" + D_Name + "</td>";
				output += "<td>" + D_PhoneNumber + "</td>";
				output += "<td>" + D_Fee + "</td>";
				output += "<td>" + D_Email + "</td>";
				output += "<td>" + D_Specialization + "</td>";
				output += "<td>" + D_RegisterNumber + "</td>";
				output += "<td>" + D_Hospitals + "</td>";
// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"Doctors.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"D_NIC\" type=\"hidden\" value=\"" + D_NIC + "\">" + "</form></td></tr>";
			}
			con.close();
// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Doctor details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

public String updateDoctor(String Dnic, String Dname, String DphoneNumber, String Dfee, String Demail,
			String Dspecial, String DregisterNumber, String Dhospitals) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
// create a prepared statement
			String query = "UPDATE doctor_reg_req SET D_NIC=?,D_Name=?,D_PhoneNumber=?,D_Fee=?,D_Email=?,D_Specialization=?,D_RegisterNumber=?,D_Hospitals=?WHERE D_NIC=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setString(1, Dnic);
			preparedStmt.setString(2, Dname);
			preparedStmt.setString(3, DphoneNumber);
			preparedStmt.setDouble(4, Double.parseDouble(Dfee));
			preparedStmt.setString(5, Demail);
			preparedStmt.setString(6, Dspecial);
			preparedStmt.setString(7, DregisterNumber);
			preparedStmt.setString(8, Dhospitals);
			preparedStmt.setString(9,Dnic);
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated doctor details successfully";
		} catch (Exception e) {
			output = "Error while updating the doctor details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteDoctor(String Dnic) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
// create a prepared statement
			String query = "delete from doctor_reg_req where D_NIC=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setString(1,Dnic);
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted doctor details successfully";
		} catch (Exception e) {
			output = "Error while deleting the doctor details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readRegisterdHospitals(String hospitalname)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>Hospital_ID</th><th> HospitalName </th><th> Email </th><th> Address </th><th> PhoneNumber </th><th> Reg_Number </th></tr>";
	 String query = "select * from registered_hospital where HospitalName="+hospitalname ;
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
	
	
	
}
