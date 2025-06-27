## 食用方法
**每个封装方法中都需要传参appId**

1. maven添加依赖
```yaml
        <dependency>
            <groupId>com.kgr.dify</groupId>
            <artifactId>kgr-dify-spring-boot-starter</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
```

2. 添加配置
```yaml
kgr:
  dify:
    workflow:
      api: ${DIFY_WORKFLOW_API:https://api.dify.com/v1}
      apps: ${DIFY_WORKFLOW_APPS:"appName=xxx,appId=xxx,appKey=xxx;"}
```

3. restful中使用
```java
    @GetMapping("/test")
    public Object test() {

        String appId = "2ab8f452-76e7-4292-b908-d7a90e743478";
    
        WorkflowParametersRequest workflowParametersRequest = new WorkflowParametersRequest();
        workflowParametersRequest.setAppId(appId);
        System.out.println(workflowParametersRequest.doRequest());
    
        WorkflowInfoRequest workflowInfoRequest = new WorkflowInfoRequest();
        workflowInfoRequest.setAppId(appId);
        System.out.println(workflowInfoRequest.doRequest());
    
        WorkflowLogsRequest workflowLogsRequest = new WorkflowLogsRequest();
        workflowLogsRequest.setAppId(appId);
        System.out.println(workflowLogsRequest.doRequest());
    
        WorkflowRunInfoRequest workflowRunInfoRequest = new WorkflowRunInfoRequest();
        workflowRunInfoRequest.setAppId(appId);
        System.out.println(workflowRunInfoRequest.doRequest());
        
        
        
        // 执行工作流方式一
        String api = kgrDifyProperties.getWorkflow().getApi();
        String authorizationHeader = "Bearer " + kgrDifyProperties.getWorkflow().getAppInfo(appId).getAppKey();
        WorkflowRunRequest request = new WorkflowRunRequest(api, authorizationHeader);
        Map<String, Object> inputs = new HashMap<>();
        inputs.put("自定义参数1", "自定义参数1的值");
        request.setInputs(inputs);
        // 填项目名称或者其他必填
        request.setUser("test"); 
        // execute 替代传统方案
        request.execute(); 
        
        // 执行工作流方式二, 直接提供appId
        WorkflowRunRequest workflowRunRequest = new WorkflowRunRequest(appId);
        workflowRunRequest.setUser("trest");
        workflowRunRequest.setInputs(inputs);
        workflowRunRequest.execute();

        return "execute success";
    }
```
