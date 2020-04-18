package com;

import model.RegDoc;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Rdoctor")

public class RegDservice {

	RegDoc Ndoc = new RegDoc();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return Ndoc.readItems();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	// RD_ID RD_Name RD_Specialization RD_NIC RD_Hospital RD_RegisterNumber
	// RD_PhoneNumber RD_Email
	// RD_Fee RD_Monday RD_Tuesday RD_Wednesday RD_Thursday RD_Friday RD_Saturday
	// RD_Sunday
	public String insertItem(@FormParam("RD_Name") String RD_Name,
			@FormParam("RD_Specialization") String RD_Specialization, @FormParam("RD_NIC") String RD_NIC,
			@FormParam("RD_Hospital") String RD_Hospital, @FormParam("RD_RegisterNumber") String RD_RegisterNumber,
			@FormParam("RD_PhoneNumber") String RD_PhoneNumber, @FormParam("RD_Email") String RD_Email,
			@FormParam("RD_Fee") String RD_Fee, @FormParam("RD_Monday") String RD_Monday,
			@FormParam("RD_Tuesday") String RD_Tuesday, @FormParam("RD_Wednesday") String RD_Wednesday,
			@FormParam("RD_Thursday") String RD_Thursday, @FormParam("RD_Friday") String RD_Friday,
			@FormParam("RD_Saturday") String RD_Saturday, @FormParam("RD_Sunday") String RD_sunday)

	{
		String output = Ndoc.insertItem(RD_Name, RD_Specialization, RD_NIC, RD_Hospital, RD_RegisterNumber,
				RD_PhoneNumber, RD_Email, RD_Fee, RD_Monday, RD_Tuesday, RD_Wednesday, RD_Thursday, RD_Friday,
				RD_Saturday, RD_sunday);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject docno = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 
		// RD_ID RD_Name RD_Specialization RD_NIC RD_Hospital RD_RegisterNumber
		// RD_PhoneNumber RD_Email
		// RD_Fee RD_Monday RD_Tuesday RD_Wednesday RD_Thursday RD_Friday RD_Saturday
		// RD_Sunday
	 
	 String RD_ID = docno.get("RD_ID").getAsString();
	 String RD_Name = docno.get("RD_Name").getAsString();
	 String RD_Specialization = docno.get("RD_Specialization").getAsString();
	 String RD_NIC = docno.get("RD_NIC").getAsString();
	 String RD_Hospital = docno.get("RD_Hospital").getAsString();
	 String RD_RegisterNumber = docno.get("RD_RegisterNumber").getAsString();
	 String RD_PhoneNumber = docno.get("RD_PhoneNumber").getAsString();
	 String RD_Email = docno.get("RD_Email").getAsString();
	 String RD_Fee = docno.get("RD_Fee").getAsString();
	 String RD_Monday = docno.get("RD_Monday").getAsString();
	 String RD_Tuesday = docno.get("RD_Tuesday").getAsString();
	 String RD_Wednesday = docno.get("RD_Wednesday").getAsString();
	 String RD_Thursday = docno.get("RD_Thursday").getAsString();
	 String RD_Friday = docno.get("RD_Friday").getAsString();
	 String RD_Saturday = docno.get("RD_Saturday").getAsString();
	 String RD_Sunday = docno.get("RD_Sunday").getAsString();

	 
	 

	
	 
	 String output = Ndoc.updateItem(RD_ID, RD_Name, RD_Specialization, RD_NIC, RD_Hospital, RD_RegisterNumber, RD_PhoneNumber, RD_Email, RD_Fee, RD_Monday, RD_Tuesday, RD_Wednesday, RD_Thursday, RD_Friday, RD_Saturday, RD_Sunday);
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
	 String RD_ID = doc.select("RD_ID").text();
	 String output = Ndoc.deleteItem(RD_ID);
	return output;
	}

	
}
