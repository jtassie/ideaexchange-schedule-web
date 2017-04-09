package org.ideaexchange;

import org.ideaexchange.dao.impl.LocationDaoImpl;
import org.ideaexchange.entity.LocationEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Main{

	@RequestMapping("/")
    @ResponseBody
    LocationEntity home() {
		
		LocationDaoImpl dao = new LocationDaoImpl();
		return dao.getLocationById(1);
    }
	
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}