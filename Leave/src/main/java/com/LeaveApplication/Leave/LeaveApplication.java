package com.LeaveApplication.Leave;

import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class LeaveApplication {
	@Autowired

	private ProcessEngineConfiguration processEngineConfiguration;

	@Bean
	public RuntimeService runtimeService() {
		return processEngineConfiguration.buildProcessEngine().getRuntimeService();
	}


	public static void main(String[] args) {
		SpringApplication.run(LeaveApplication.class, args);
	}




}
		