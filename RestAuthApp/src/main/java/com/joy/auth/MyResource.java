package com.joy.auth;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("Resource")
public class MyResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String testMethod()
	{
		return "Auth";
	}
}
