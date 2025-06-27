package com.kgr.dify.domain.workflow;


import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;
import com.kgr.dify.baseEntity.AbstractWorkflowRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class WorkflowRunInfoRequest extends AbstractWorkflowRequest<WorkflowRunInfoResponse> {

    private String appId;

    public WorkflowRunInfoRequest(){
        super(Method.GET);
    }

    @Override
    public WorkflowRunInfoResponse doRequest() {
        String res = getRequest(appId, String.format("/workflows/run/%s", appId ), JSONUtil.toJsonStr(this));
        return JSONUtil.toBean(res, WorkflowRunInfoResponse.class);
    }

}
