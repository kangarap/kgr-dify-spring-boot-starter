package com.kgr.dify.domain.workflow;


import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;
import com.kgr.dify.baseEntity.AbstractWorkflowRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取应用参数
 * 用于进入页面一开始，获取功能开关、输入参数名称、类型及默认值等使用。
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class WorkflowParametersRequest extends AbstractWorkflowRequest<WorkflowParametersResponse> {

    private String appId;

    public WorkflowParametersRequest(){
        super(Method.GET);
    }

    @Override
    public WorkflowParametersResponse doRequest() {
        String res = getRequest(appId, "/parameters", JSONUtil.toJsonStr(this));
        return JSONUtil.toBean(res, WorkflowParametersResponse.class);
    }


}
