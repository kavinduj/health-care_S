package com;

import model.Appointment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Appointments")
public class AppointmentService {
	
	Appointment appointmentObj = new Appointment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointments()
	 {
	 return appointmentObj.readAppointments();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("A_PatientName") String A_PatientName,
	 @FormParam("A_PatientNIC") String A_PatientNIC,
	 @FormParam("A_PatientPhoneNo") String A_PatientPhoneNo,
	 @FormParam("A_DoctorName") String A_DoctorName,
	 @FormParam("A_HospitalName") String A_HospitalName,
	 @FormParam("A_Date") String A_Date,
	 @FormParam("A_Time") String A_Time)
	
	{
	 String output = appointmentObj.insertAppointment(A_PatientName, A_PatientNIC, A_PatientPhoneNo, A_DoctorName, A_HospitalName, A_Date, A_Time);
	return output;
	}
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject appointmentObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String A_AppointmentID = appointmentObject.get("A_AppointmentID").getAsString();
	 String A_PatientName = appointmentObject.get("A_PatientName").getAsString();
	 String A_PatientNIC = appointmentObject.get("A_PatientNIC").getAsString();
	 String A_PatientPhoneNo = appointmentObject.get("A_PatientPhoneNo").getAsString();
	 String A_DoctorName = appointmentObject.get("A_DoctorName").getAsString();
	 String A_HospitalName = appointmentObject.get("A_HospitalName").getAsString();
	 String A_Date = appointmentObject.get("A_Date").getAsString();
	 String A_Time = appointmentObject.get("A_Time").getAsString();
	 
	 String output = appointmentObj.updateAppointment(A_AppointmentID, A_PatientName, A_PatientNIC, A_PatientPhoneNo, A_DoctorName, A_HospitalName, A_Date, A_Time);
	return output;
	}
	
	
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String  DeleteAppointment(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String A_AppointmentID = doc.select(",m/").text();
	 String output = appointmentObj.DeleteAppointment(A_AppointmentID);
	return output;
	}
	//comment
	
	
	@GET
	@Path("/AppointmentDetails/{RD_Specialization}")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointmentDetails(@PathParam("RD_Specialization") String RD_Specialization)
	 {
	 return appointmentObj.readAppointmentDetails(RD_Specialization);
	 }
	
	@GET
	@Path("/DoctorDeatails/{RD_Name}")
	@Produces(MediaType.TEXT_HTML)
	public String readDoctorDetails(@PathParam("RD_Name") String RD_Name)
	 {
	 return appointmentObj.readDoctorDetails(RD_Name);
	 }
	
	
	
}