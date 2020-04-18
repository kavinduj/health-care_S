package com;

import model.RegHservice;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Rhos")

public class RegHospital {
	
	RegHservice adminobj = new RegHservice();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
	 return adminobj.readItems();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("HospitalName") String HospitalName,
	 @FormParam("Email") String Email,
	 @FormParam("Address") String Address,
	 @FormParam("PhoneNumber") String PhoneNumber,
	 @FormParam("Reg_Number") String Reg_Number)

	
	{
	 String output = adminobj.insertItem(HospitalName, Email, Address, PhoneNumber, Reg_Number);
	return output;
	}
 	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject appointmentObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String Hospital_ID = appointmentObject.get("Hospital_ID").getAsString();
	 String HospitalName = appointmentObject.get("HospitalName").getAsString();
	 String Email = appointmentObject.get("Email").getAsString();
	 String Address = appointmentObject.get("Address").getAsString();
	 String PhoneNumber = appointmentObject.get("PhoneNumber").getAsString();
	 String Reg_Number = appointmentObject.get("Reg_Number").getAsString();
	
	 
	 String output = adminobj.updateItem(Hospital_ID, HospitalName, Email, Address, PhoneNumber, Reg_Number);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, " ", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String Hospital_ID = doc.select("Hospital_ID").text();
	 String output = adminobj.deleteItem(Hospital_ID);
	return output;
	}
	
	
	
	
	
	
	

}
