package com.joy.auth;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class PoweredByResponseFilter implements ContainerResponseFilter{

	@Override
	public void filter(ContainerRequestContext arg0, ContainerResponseContext responseContexet) throws IOException {
		// TODO Auto-generated method stub
		responseContexet.getHeaders().add("X-Powered-by", "Nikhil Joy");
		
	}

}	
