package com.bah.na.asc.drools.api.resource;

import com.bah.na.asc.drools.api.init.Initialization;
import com.bah.na.asc.drools.api.pojo.Data;
import com.bah.na.asc.drools.api.service.DroolsService;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;

public class DroolsResource{
	private static final Logger logger = LoggerFactory.getLogger(DroolsResource.class);
	private KieSession ks = Initialization.getKieSession();
	private DroolsService service = new DroolsService();

	@GET
	public Response html(){
		String html = service.setHtml("index");
		return Response.ok().entity(html).build();
	}

	@GET
	@Path("/datum")
	public String data(){
		String id = service.randomID();
		Data data = new Data(id);
		if(service.map.get(id) == null){
			data.count = 1;
			ArrayList<Data> newDataList = new ArrayList<Data>();
			newDataList.add(data);
			service.map.put(id, newDataList);

		}else{
			data.count = service.map.get(id).size() + 1;
			service.map.get(id).add(data);
		}
		ks.insert(data);
		logger.info("Datum Inserted");
		ks.fireAllRules();
		logger.info("# of records: {} for id {}", data.getCount().toString(), id);
		if(service.map.get(id).size() > 5){
			try{
				service.map.get(id).clear();
				// get URL content
				logger.info("Opening url");
				return service.setHtml("success");
			}catch(Exception e){
				logger.error("Exception", e);
				return null;
			}
		}
		return null;
	}

	@GET
	@Path("/firerules")
	public void rule(){
		ks.fireAllRules();
		logger.info("Rules fired");
	}

	@GET
	@Path("/delete")
	public void delete(){
		ks.dispose();
		DroolsService.resetCount();
		Initialization.createKieSession();
		logger.info("Facts Reset");
	}

	@GET
	@Path("/showall")
	public void showall(){
		Collection<FactHandle> facts = ks.getFactHandles();
		logger.info("# of Facts: {}", facts.size());
	}
}
