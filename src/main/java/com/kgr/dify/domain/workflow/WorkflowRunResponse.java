package com.kgr.dify.domain.workflow;


import com.kgr.dify.baseEntity.DifyResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WorkflowRunResponse extends DifyResponse {
    private String id;
    private String workflow_id;
    private String status;
    private Object inputs;
    private Object outputs;
    private String error;
    private Integer total_steps;
    private Integer total_tokens;
    private String created_at;
    private String finished_at;
    private String elapsed_time;
}