package org.ideaexchange.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Auth0Util {

private final static Logger logger = LoggerFactory.getLogger(Auth0Util.class);
	
	public static String getIdpToken(String endpoint, String accessToken, String userId) 
		throws UnirestException
	{
		logger.info("Requesting idp auth token from endpoint:" + endpoint + ", and accessToken: " + accessToken);
		
		HttpResponse<String> response = Unirest.get("https://" + endpoint + "/api/v2/users/" + userId)
			.header("authorization", "Bearer " + accessToken)
			.asString();
		
		return response.getBody();
	}
}
