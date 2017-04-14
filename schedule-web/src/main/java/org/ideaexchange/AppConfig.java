package org.ideaexchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.auth0.web.Auth0Config;
import com.auth0.web.Auth0Filter;

@Component
@Configuration
public class AppConfig extends Auth0Config {

	final static Logger logger = LoggerFactory.getLogger(AppConfig.class);
    
	@Value(value = "${auth0.accessToken}")
    private String accessToken;
	public String getAccessToken(){
		return accessToken;
	}
	
    @Bean
    public FilterRegistrationBean filterRegistration() {
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new Auth0Filter(this));
        registration.addUrlPatterns(securedRoute);
        registration.addInitParameter("redirectOnAuthError", loginRedirectOnFail);
        registration.setName("Auth0Filter");
        return registration;
    }

    @Bean
    public TilesConfigurer tilesConfigurer(){ 

       String[] definitions = new String[] {
               "WEB-INF/layouts/layouts.xml",
               "WEB-INF/views/**/views.xml" /*Scans directories for Tiles configurations */
           };
       logger.info("interesting4");
       TilesConfigurer tilesConfigurer = new TilesConfigurer();
       tilesConfigurer.setDefinitions(definitions);
       tilesConfigurer.setCheckRefresh(true);
       return tilesConfigurer;
    }
    
    /**
     * Introduce a Tiles view resolver, this is a convenience implementation that extends URLBasedViewResolver.
     * 
     * @return tiles view resolver
     */
    @Bean
    public TilesViewResolver tilesViewResolver() {
        final TilesViewResolver resolver = new TilesViewResolver();
        resolver.setViewClass(TilesView.class);
        return resolver;
    }
}