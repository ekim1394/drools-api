package com.bah.na.asc.drools.api.service;

import com.bah.na.asc.drools.api.pojo.Data;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DroolsService{
	private static final Logger logger = LoggerFactory.getLogger(DroolsService.class);
	private static Integer count = 0;
	public static HashMap<String, ArrayList<Data>> map = new HashMap<String, ArrayList<Data>>();

	public String setHtml(String file){
		String response = "";
		String htmlPath = file + ".html";
		ClassLoader classLoader = getClass().getClassLoader();

		try{
			response = IOUtils.toString(classLoader.getResourceAsStream(htmlPath));
		}catch(IOException e){
			logger.error("No file found.", e);
		}
		return response;
	}

	public static void resetCount(){
		count = 0;
	}

	public void success(){
		logger.info("Desktop Support?");
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		logger.info("Yes");
		if(desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
			try{
				logger.info("Opening Success Page");
				desktop.browse(URI.create("heyyeyaaeyaaaeyaeyaa.com"));
			}catch(Exception e){
				logger.error("Exception:", e);
			}
		}
	}

	public String randomID(){
		Random rand = new Random();
		Integer id = rand.nextInt(5);
		return id.toString();
	}

}
