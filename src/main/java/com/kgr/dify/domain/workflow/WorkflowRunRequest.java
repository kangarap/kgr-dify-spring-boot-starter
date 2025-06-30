package com.kgr.dify.domain.workflow;


import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;
import com.kgr.dify.baseEntity.AbstractWorkflowRequest;
import com.kgr.dify.config.KgrDifyProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class WorkflowRunRequest extends AbstractWorkflowRequest<WorkflowRunResponse> {

    // 请求参数（允许空对象）
    private Map<String, Object> inputs = new HashMap<>();
    // 响应模式（streaming 或 blocking）
    private String response_mode = "streaming";
    // 用户标识符
    private String user;

    private String appId;

    // 由内部派生
    private String api;
    private String authorizationHeader;


    public void setAppId(String appId) {
        this.appId = appId;
        this.api = KGR_DIFY_PROPERTIES.getWorkflow().getApi();
        this.authorizationHeader = "Bearer " + KGR_DIFY_PROPERTIES.getWorkflow().getAppInfo(appId).getAppKey();
    }

    @Override
    public WorkflowRunResponse doRequest() throws Exception {
        throw new Exception("workflow run not allow the function doRequest, please use execute instead");
    }

    public Flux<String> execute() {
        return WebClient.create().post()
                .uri(this.api + "/workflows/run")
                .header("Content-Type", "application/json")
                .header("Authorization",  this.authorizationHeader)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .bodyValue(JSONUtil.toJsonStr(this))
                .retrieve()
                .bodyToFlux(String.class);
    }
}
