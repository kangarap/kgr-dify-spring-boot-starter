package com.kgr.dify.domain.workflow;


import com.kgr.dify.baseEntity.DifyResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class WorkflowParametersResponse extends DifyResponse {
    private List<Object> user_input_form ;
    private Object file_upload ;
    private Object system_parameters ;
}