package org.ideaexchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;


@Configuration
public class RootMvcConfiguration  {

	final static Logger logger = LoggerFactory.getLogger(RootMvcConfiguration.class);
    
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