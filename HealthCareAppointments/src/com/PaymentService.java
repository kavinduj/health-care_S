package com;
import model.Payment;

import java.sql.Date;

//For REST Service 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser; 

@Path("/Pay")
public class PaymentService {
	
	//Show details
	
	Payment payObj = new Payment(); 
	 
	 @GET  
	 @Path("/")  
	 @Produces(MediaType.TEXT_HTML)  
	 public String readPayments()  
	 {   
		 return payObj.readPayments();  
	} 
	 
	 //Insert Payment Details
	 
		@POST 
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String insertPayment(@FormParam("P_Amount") String P_Amount,
				@FormParam("P_NameOnCard") String P_NameOnCard,    
				@FormParam("P_CVC") String P_CVC,       
				@FormParam("P_ExpiringDate") String P_ExpiringDate,
				@FormParam("P_CardNumber") String P_CardNumber,
				@FormParam("P_AppointmentID") int P_AppointmentID)
		{  
			String output = payObj.insertPayment(P_Amount, P_NameOnCard, P_CVC, P_ExpiringDate, P_CardNumber,P_AppointmentID);  
			return output; 
		}
		
		//Update Payment
		
		@PUT @Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updatePayment(String payData) 
		{  
			//Convert the input string to a JSON object  
			JsonObject payObject = new JsonParser().parse(payData).getAsJsonObject(); 
			 
			 //Read the values from the JSON object  
			String P_PaymentID = payObject.get("P_PaymentID").getAsString();  
			String P_Amount = payObject.get("P_Amount").getAsString();  
			String P_NameOnCard = payObject.get("P_NameOnCard").getAsString();  
			String P_CVC = payObject.get("P_CVC").getAsString();  
			String P_ExpiringDate = payObject.get("P_ExpiringDate").getAsString();
			String P_CardNumber = payObject.get("P_CardNumber").getAsString();
			String P_AppointmentID = payObject.get("P_AppointmentID").getAsString();
			 
			       String output = payObj.updatePayment(P_PaymentID, P_Amount, P_NameOnCard, P_CVC, P_ExpiringDate, P_CardNumber, P_AppointmentID); 
			 
			 return output; 
		}
		
		//Delete Payment
		
		@DELETE 
		@Path("/") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deletePayment(String payData) 
		{  
			//Convert the input string to an XML document  
			Document doc = Jsoup.parse(payData, "", Parser.xmlParser());     
			//Read the value from the element <itemID>  
			String P_PaymentID = doc.select("P_PaymentID").text(); 
			 
			 String output = payObj.deletePayment(P_PaymentID); 
			 
			 return output; }

}

