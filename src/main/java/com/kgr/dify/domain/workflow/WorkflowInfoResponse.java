package com.kgr.dify.domain.workflow;


import com.kgr.dify.baseEntity.DifyResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class WorkflowInfoResponse extends DifyResponse {
    private String name;
    private String description;
    private List<String> tags;

}