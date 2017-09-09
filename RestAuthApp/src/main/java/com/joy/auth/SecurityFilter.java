package com.joy.auth;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class SecurityFilter implements ContainerRequestFilter{

	public static final String AUTHORIZATION_HEADER_KEY="Authorization";
	public static final String AUTHORIZATION_HEADER_PREFIX="Basic ";
	public static final String SECURED_URL_PREFIX="secured";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		// This will ensure that this filter works only for secured resources
		System.out.println(requestContext.getUriInfo().getPath());
		if(requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX))
		{
			
			List<String> val=	requestContext.getHeaders().get("Authorization");
			if(val.size()>0)
			{
				String authToken=val.get(0);
				
				if(authToken!=null && !"".equalsIgnoreCase(authToken))
				{
					authToken=authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
					//Apache commons have a utility class base64
					String decodedString= Base64.decodeAsString(authToken);
					StringTokenizer tokenizer= new StringTokenizer(decodedString, ":");
					String uname=tokenizer.nextToken();
					System.out.println(uname);
					String pwd=tokenizer.nextToken();
					System.out.println(pwd);
					if("Nikhil".equalsIgnoreCase(uname) && "5184".equalsIgnoreCase(pwd))
						return;
					
					
				}
				
				
			}
			Response unAuthres=Response.status(Status.UNAUTHORIZED).entity("Unable to authorize").build();
			// This will not allow the request to go to resource. It will abort the request and send the response from here
			requestContext.abortWith(unAuthres);
		}
		// TODO Auto-generated method stub
	
	}

}
