package com.bah.na.asc.drools.api.init;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initialization implements ServletContextListener{
	private static final Logger logger = LoggerFactory.getLogger(Initialization.class);
	private static KieSession kSession;

	public void contextInitialized(ServletContextEvent servletContextEvent){
		logger.info("Starting up Stateful Drools Engine");
		createKieSession();
		logger.info("Stateful Drools Engine has been started.");
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent){
		kSession.destroy();
		logger.info("Drools Engine has been shut down");
	}

	public static KieSession getKieSession(){
		return kSession;
	}

	public static void createKieSession(){
		KieServices ks = KieServices.Factory.get();
		/* File System Handler */
		KieFileSystem kfs = ks.newKieFileSystem();
		logger.info("loading rules");
		kfs.write(ResourceFactory.newClassPathResource("drl/api.drl"));
		KieBuilder kieBuilder = ks.newKieBuilder(kfs).buildAll();
		Results results = kieBuilder.getResults();

		if(results.hasMessages(Message.Level.ERROR)){
			logger.info(results.getMessages().toString());
			throw new IllegalStateException("### errors ###");
		}

		KieContainer kContainer = ks.newKieContainer(ks.getRepository().getDefaultReleaseId());
		kSession = kContainer.newKieSession();
	}
}
