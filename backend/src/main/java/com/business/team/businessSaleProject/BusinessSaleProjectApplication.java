package com.business.team.businessSaleProject;

import com.business.team.businessSaleProject.core.config.AppContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BusinessSaleProjectApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(BusinessSaleProjectApplication.class, args);

		AppContext.loadApplicationContext(ctx);
	}

}