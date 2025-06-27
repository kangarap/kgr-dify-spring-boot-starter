package com.kgr.dify.domain.workflow;


import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;
import com.kgr.dify.baseEntity.AbstractWorkflowRequest;
import com.kgr.dify.config.KgrDifyProperties;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class WorkflowRunRequest extends AbstractWorkflowRequest<WorkflowRunResponse> {

    // 请求参数（允许空对象）
    private Map<String, Object> inputs = new HashMap<>();
    // 响应模式（streaming 或 blocking）
    private String response_mode = "streaming";
    // 用户标识符
    private String user;

    private String appId;
    private String api;
    private String authorizationHeader;


    private static final KgrDifyProperties kgrDifyProperties = SpringUtil.getBean("kgrDifyProperties");


    public WorkflowRunRequest(String appId){
        this.api = kgrDifyProperties.getWorkflow().getApi();
        this.authorizationHeader = "Bearer " + kgrDifyProperties.getWorkflow().getAppInfo(appId).getAppKey();
    }

    public WorkflowRunRequest(String api, String authorizationHeader){
        this.api = api;
        this.authorizationHeader = authorizationHeader;
    }

    @Override
    public WorkflowRunResponse doRequest() throws Exception {
        throw new Exception("workflow run not allow the function doRequest, please use execute instead");
    }

    public Flux<String> execute() {
        return WebClient.create().post()
                .uri(api + "/workflows/run")
                .header("Content-Type", "application/json")
                .header("Authorization", authorizationHeader)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .bodyValue(JSONUtil.toJsonStr(this))
                .retrieve()
                .bodyToFlux(String.class);
    }
}
