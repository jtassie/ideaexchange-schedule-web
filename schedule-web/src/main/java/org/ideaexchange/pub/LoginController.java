package org.ideaexchange.pub;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ideaexchange.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.auth0.NonceUtils;
import com.auth0.SessionUtils;

@Controller
public class LoginController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AppConfig appConfig;

    @Autowired
    public LoginController(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @RequestMapping(value="login", method = RequestMethod.GET)
    protected String login(final Map<String, Object> model, final HttpServletRequest req) {
        logger.info("Performing login");
        detectError(model);
        // add a Nonce value to session storage
        NonceUtils.addNonceToStorage(req);
        model.put("clientId", appConfig.getClientId());
        model.put("clientDomain", appConfig.getDomain());
        model.put("loginCallback", appConfig.getLoginCallback());
        model.put("state", SessionUtils.getState(req));
        return "app.login";
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    protected String logout(final HttpServletRequest request) {
        logger.info("Performing logout");
        invalidateSession(request);
        final String logoutPath = appConfig.getOnLogoutRedirectTo();
        return "redirect:" + logoutPath;
    }

    private void invalidateSession(HttpServletRequest request) {
	    if (request.getSession() != null) {
	        request.getSession().invalidate();
	    }
    }

    private void detectError(final Map<String, Object> model) {
        if (model.get("error") != null) {
            model.put("error", true);
        } else {
            model.put("error", false);
        }
    }
}