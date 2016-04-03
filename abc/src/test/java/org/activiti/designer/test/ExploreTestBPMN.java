package org.activiti.designer.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.db.DbSchemaCreate;
import org.activiti.engine.impl.event.logger.handler.ProcessInstanceStartedEventHandler;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import java.io.FileInputStream;

public class ExploreTestBPMN {

	private String filename = "C:/Users/windows/workspace3/abc/src/main/resources/diagrams/MyProcess.bpmn";
	
	@Test
	public void test() throws FileNotFoundException {
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activity.cfg.xml", "processEngineConfigurationMain" );
		String[] args = new String[0];
		DbSchemaCreate.main(args);
		//processEngineConfiguration.setMailServerUseSSL(true);
		processEngineConfiguration.setMailServerHost("127.0.0.1");
		processEngineConfiguration.setMailServerPort(2525);
		processEngineConfiguration.setMailServerUsername("username@xxx.com");
		processEngineConfiguration.setMailServerPassword("password");
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		
		repositoryService.createDeployment().addInputStream("myProcess.bpmn20.xml",
				new FileInputStream(filename)).deploy();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("name", "Activiti");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess", variableMap);
		assertNotNull(processInstance.getId());
		System.out.println("id " + processInstance.getId() + " "
				+ processInstance.getProcessDefinitionId());
		System.out.println(processEngineConfiguration.getClass());
		/*
		ProcessEngine p = 
		RuntimeService rs = p.getRuntimeService();
		rs.activateProcessInstanceById("default");
		fail("Not yet implemented");
		*/
	}

}
