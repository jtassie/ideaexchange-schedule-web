package org.ideaexchange.priv;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.ideaexchange.AppConfig;
import org.ideaexchange.ms.CalendarOperations;
import org.ideaexchange.ms.OrganizationOperations;
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
import com.microsoft.services.graph.User;
import com.microsoft.services.graph.fetchers.CalendarFetcher;
import com.microsoft.services.graph.fetchers.GraphServiceClient;
import com.microsoft.services.orc.log.LogLevel;
import com.microsoft.services.orc.resolvers.JavaDependencyResolver;
import com.microsoft.services.outlook.Event;
import com.microsoft.services.outlook.fetchers.OutlookClient;

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
        	String accessToken = idpUser.getIdentities().get(0).getAccessToken();
        	logger.info("IDP token: " + idpUser.getIdentities().get(0).getAccessToken());
        	
            try {
            	//List<User> users = OrganizationOperations.GetOrganizationUsers(accessToken).get();
				List<Event> events = CalendarOperations.getUserEvents(accessToken).get();
				for(Event ev : events){
					logger.info("Event: " + ev.getSubject());
				}
			} 
            catch (Exception e) {
				
            	logger.warn("Couldn't retrieve calendar events",e);
			}
            
        }
        catch(UnirestException ex)
        {
        	logger.warn("Failed to get IDP token",ex);
        }
        
        model.put("user", user);
        return "app.portal.home";
    }
}