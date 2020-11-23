package com.github.test;

import com.alibaba.fastjson.JSON;
import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author: <a href=mailto:guanjian@jd.com>guanjian23</a>
 * @date: 2020/07/06 9:24
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class ActivitiDemoTest {

    @Test
    public void test() {
        // 1 创建流程引擎，使用内存数据库
        ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration().buildProcessEngine();

        // 2 部署流程定义文件
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //        String bpmnFileName = "leave2.bpmn";
        String bpmnFileName = "bpmn/demo.bpmn";
        repositoryService.createDeployment()
                .addInputStream("bpmn/demo.bpmn", this.getClass().getClassLoader()
                        .getResourceAsStream(bpmnFileName))
                .disableSchemaValidation()
                .deploy();

        // 3 验证已部署的流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
        assertEquals("studentApply", processDefinition.getKey());

        // 4 启动流程并返回流程实例
        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String, Object> variables = new HashMap<>();
        variables.put("applyUser", "employee1"); //申请人名称
        variables.put("days", 3); //请假天数

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("studentApply", variables);
        assertNotNull(processInstance);
        System.out.println("pid=" + processInstance.getId() + ", pdid=" + processInstance.getProcessDefinitionId());

        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService
                .createTaskQuery()
                .list();
        assertNotNull(tasks);

        tasks.forEach(task->{
            System.out.println("流程实例ID:"+task.getProcessInstanceId());
            System.out.println("任务ID:"+task.getId());
            System.out.println("任务负责人:"+task.getAssignee());
            System.out.println("任务名称:"+task.getName());
        });

    }
}