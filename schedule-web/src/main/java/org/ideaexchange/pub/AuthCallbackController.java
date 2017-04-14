package org.ideaexchange.pub;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.auth0.web.Auth0CallbackHandler;

@Controller
public class AuthCallbackController extends Auth0CallbackHandler 
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    protected Auth0CallbackHandler callback;
	
	@RequestMapping(value = "${auth0.loginCallback}", method = RequestMethod.GET)
	protected void callback(final HttpServletRequest req, final HttpServletResponse res) 
		throws ServletException, IOException
	{
		logger.info("auth0 callback occurred");
		
		super.handle(req, res);
	}
}