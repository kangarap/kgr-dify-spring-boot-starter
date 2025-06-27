package com.kgr.dify.domain.workflow;


import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;
import com.kgr.dify.baseEntity.AbstractWorkflowRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取 workflow 日志
 * 倒序返回workflow日志
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class WorkflowLogsRequest extends AbstractWorkflowRequest<WorkflowLogsResponse> {

    private String keyword;
    /**
     * 执行状态 succeeded/failed/stopped
     */
    private String status;
    private int page = 1;
    private int limit = 20;


    private String appId;

    public WorkflowLogsRequest(){
        super(Method.GET);
    }

    @Override
    public WorkflowLogsResponse doRequest() {
        String res = getRequest(appId, "/workflows/logs", JSONUtil.toJsonStr(this));
        return JSONUtil.toBean(res, WorkflowLogsResponse.class);
    }

}
