package com;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Hospital;


//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Hospitals")
public class HospitalService
{
	Hospital hospitalObj = new Hospital();

@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readHospital()
{
return hospitalObj.readHospital();
}
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertHospital(@FormParam("RegNumber") String RegNumber,
 @FormParam("Hospital_Name") String Hospital_Name,
 @FormParam("Address") String Address,@FormParam("PhoneNumber") String PhoneNumber,
 @FormParam("Email") String Email)
{
 String output = hospitalObj.insertHospital(RegNumber, Hospital_Name, Address, PhoneNumber,Email);
return output;
}
@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateHospital(String itemData)
{
//Convert the input string to a JSON object
 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
//Read the values from the JSON object
 String RegNumber = itemObject.get("RegNumber").getAsString();
 String Hospital_Name = itemObject.get("Hospital_Name").getAsString();
 String Address = itemObject.get("Address").getAsString();
 String PhoneNumber = itemObject.get("PhoneNumber").getAsString();
 String Email = itemObject.get("Email").getAsString();
 String output = hospitalObj.updateHospital(RegNumber, Hospital_Name, Address, PhoneNumber, Email);
return output;
}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteHospital(String itemData)
{
	
//Convert the input string to an XML document
	
 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 
 String RegNumber = doc.select("RegNumber").text();
 String output = hospitalObj.deleteHospital(RegNumber);
return output;
}
}

