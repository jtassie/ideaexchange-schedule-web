package org.ideaexchange;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ComponentScan(basePackages = {"com.auth0.web", "org.ideaexchange"})
@Configuration
@EnableAutoConfiguration
public class Main {

	final static Logger logger = LoggerFactory.getLogger(Main.class);
	
	@RequestMapping("/test")
    public String home(Map<String, Object> model) {
		
		//LocationDaoImpl dao = new LocationDaoImpl();
		//return dao.getLocationById(1);
		return "index";
    }
	
	/*
	@Bean
    public ServletRegistrationBean dispatcherServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet());
        Map<String,String> params = new HashMap<String,String>();
        params.put("org.atmosphere.servlet","org.springframework.web.servlet.DispatcherServlet");
        params.put("contextClass","org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
        params.put("contextConfigLocation","net.org.selector.animals.config.ComponentConfiguration");
        registration.setInitParameters(params);
        return registration;
    }
	
	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("defaultHtmlEscape", "true");
        super.onStartup(servletContext);
    }
	*/

    public static void main(String[] args) {
    	
    	logger.info("Starting spring application...");
    	
        SpringApplication.run(Main.class, args);
    }
}