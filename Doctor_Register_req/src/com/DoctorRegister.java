package com;

//For  REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Doctor;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Doctors")
public class DoctorRegister{
	Doctor docobj = new Doctor();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
public String readRegDetails() {
		return docobj.readRegDetails();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
public String insertDoctor(@FormParam("D_NIC") String D_NIC, @FormParam("D_Name") String D_Name,
			@FormParam("D_PhoneNumber") String D_PhoneNumber, @FormParam("D_Fee") String D_Fee,
			@FormParam("D_Email") String D_Email, @FormParam("D_Specialization") String D_Specialization,
			@FormParam("D_RegisterNumber") String D_RegisterNumber, @FormParam("D_Hospitals") String D_Hospitals) {
		String output = docobj.insertDoctor(D_NIC, D_Name, D_PhoneNumber, D_Fee, D_Email, D_Specialization,
				D_RegisterNumber, D_Hospitals);
		return output;
	}
	
	

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
public String updateDoctor(String itemData) {
		
//Convert the input string to a JSON object
		
		JsonObject doctorObject = new JsonParser().parse(itemData).getAsJsonObject();
		
//Read the values from the JSON object
		
		String D_NIC = doctorObject.get("D_NIC").getAsString();
		String D_Name = doctorObject.get("D_Name").getAsString();
		String D_PhoneNumber = doctorObject.get("D_PhoneNumber").getAsString();
		String D_Fee = doctorObject.get("D_Fee").getAsString();
		String D_Email = doctorObject.get("D_Email").getAsString();
		String D_Specialization = doctorObject.get("D_Specialization").getAsString();
		String D_RegisterNumber = doctorObject.get("D_RegisterNumber").getAsString();
		String D_Hospitals = doctorObject.get("D_Hospitals").getAsString();
		String output = docobj.updateDoctor(D_NIC, D_Name, D_PhoneNumber, D_Fee, D_Email, D_Specialization,
				D_RegisterNumber, D_Hospitals);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
public String deleteDoctor(String itemData)
	{
		
	//Convert the input string to an XML document
		
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 
	 String D_NIC = doc.select("D_NIC").text();
	 String output = docobj.deleteDoctor(D_NIC);
	return output;
	}
}
