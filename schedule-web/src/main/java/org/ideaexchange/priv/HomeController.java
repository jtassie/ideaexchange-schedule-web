package org.ideaexchange.priv;

import java.security.Principal;
import java.util.Map;

import org.ideaexchange.AppConfig;
import org.ideaexchange.util.Auth0Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.auth0.Auth0ClientImpl;
import com.auth0.Auth0User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.exceptions.UnirestException;

@Controller
public class HomeController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AppConfig appConfig;

    @Autowired
    public HomeController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @RequestMapping(value="/portal/home", method = RequestMethod.GET)
    protected String home(final Map<String, Object> model, final Principal principal) {
        logger.info("Home page");
        final Auth0User user = (Auth0User) principal;
        logger.info("Principal name: " + user.getName());
        
        try
        {
        	String userIdpToken = Auth0Util.getIdpToken( 
    			appConfig.getDomain() ,
    			appConfig.getAccessToken(),
    			user.getUserId()
			);
        	logger.info("IDP json results: " + userIdpToken);
        	
        	Gson gson = new GsonBuilder().create();
        	Auth0User idpUser = gson.fromJson(userIdpToken, Auth0User.class);
        	logger.info("IDP token: " + idpUser.getIdentities().get(0).getAccessToken());
        }
        catch(UnirestException ex)
        {
        	logger.warn("Failed to get IDP token",ex);
        }
        
        model.put("user", user);
        return "app.portal.home";
    }
}