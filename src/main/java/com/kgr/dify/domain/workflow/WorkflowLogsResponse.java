package com.kgr.dify.domain.workflow;


import com.kgr.dify.baseEntity.DifyResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class WorkflowLogsResponse extends DifyResponse {
    private Integer page;
    private Integer limit;
    private Integer total;
    private Boolean has_more;
    private List<Object> data;

}