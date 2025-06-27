package com.kgr.dify.domain.workflow;


import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;
import com.kgr.dify.baseEntity.AbstractWorkflowRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 上传文件
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class WorkflowTaskStopRequest extends AbstractWorkflowRequest<WorkflowTaskStopResponse> {

    private String appId;

    private String taskId;
    private String user;


    public WorkflowTaskStopRequest(){
        super( Method.POST);
    }

    @Override
    public WorkflowTaskStopResponse doRequest() {
        String res = getRequest(appId, String.format("/workflows/tasks/%s/stop", taskId), JSONUtil.toJsonStr(this));
        return JSONUtil.toBean(res, WorkflowTaskStopResponse.class);
    }

}
