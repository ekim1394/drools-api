package com.bah.na.asc.drools.api.config;

import com.bah.na.asc.drools.api.resource.DroolsResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/rest")
public class ApplicationConfig extends ResourceConfig{

	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

	public ApplicationConfig(){
		register(DroolsResource.class);
		logger.info("Stateful Drools API resource has been registered...");
	}

}
