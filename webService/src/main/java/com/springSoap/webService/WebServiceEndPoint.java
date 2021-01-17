package com.springSoap.webService;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.PayloadRoots;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import localhost._9090.demoservices.GetNameRequest;
import localhost._9090.demoservices.GetNameResponse;



@Endpoint
public class WebServiceEndPoint {

	private static final String NAMESPACE_URI="http://localhost:9090/demoServices";
	
	@PayloadRoot(namespace=NAMESPACE_URI , localPart="getNameRequest")
	@ResponsePayload
	public GetNameResponse getNameRequest(@RequestPayload GetNameRequest getNameRequest) {
		System.out.println(getNameRequest.getFirstName());
		GetNameResponse getNameResponse = new GetNameResponse();
		getNameResponse.setMessage("hello " +getNameRequest.getFirstName());
		return getNameResponse;
	}
}
