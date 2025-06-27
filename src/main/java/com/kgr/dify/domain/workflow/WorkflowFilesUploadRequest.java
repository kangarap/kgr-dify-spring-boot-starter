package com.kgr.dify.domain.workflow;

import cn.hutool.http.Method;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.kgr.dify.baseEntity.AbstractWorkflowRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class WorkflowFilesUploadRequest extends AbstractWorkflowRequest<WorkflowFilesUploadResponse> {

    private File file;
    private String user;

    private String appId;

    public WorkflowFilesUploadRequest(){
        super(Method.POST);
    }

    @Override
    public WorkflowFilesUploadResponse doRequest() {
        String res = getRequest(appId, "/files/upload", user, file);
        return JSONUtil.toBean(res, WorkflowFilesUploadResponse.class);
    }

}
