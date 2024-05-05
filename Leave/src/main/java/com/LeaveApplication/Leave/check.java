//package com.LeaveApplication.Leave;
//import org.flowable.engine.ProcessEngine;
//import org.flowable.engine.ProcessEngines;
//import org.springframework.boot.actuate.health.Health;
//import org.springframework.boot.actuate.health.HealthIndicator;
//import org.springframework.stereotype.Component;
//
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class check implements HealthIndica {
//
//
//    @Override
//    public Health health() {
//        try {
//            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//            if (processEngine != null) {
//                // Perform some simple operation to check health
//                processEngine.getRepositoryService().createProcessDefinitionQuery().listPage(0, 1);
//                return Health.up().build();
//            }
//        } catch (Exception e) {
//            return Health.down(e).build();
//        }
//        return Health.down().build();
//    }
//}
