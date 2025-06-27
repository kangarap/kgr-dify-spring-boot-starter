package com.kgr.dify.domain.workflow;


import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;
import com.kgr.dify.baseEntity.AbstractWorkflowRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 用于获取应用的基本信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class WorkflowInfoRequest extends AbstractWorkflowRequest<WorkflowInfoResponse> {


    private String appId;

    public WorkflowInfoRequest(){
        super(Method.GET);
    }

    @Override
    public WorkflowInfoResponse doRequest() {
        String res = getRequest(appId, "/info", "");
        return JSONUtil.toBean(res, WorkflowInfoResponse.class);
    }

}
