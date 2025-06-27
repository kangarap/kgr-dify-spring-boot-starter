package com.kgr.dify.domain.workflow;

import com.kgr.dify.baseEntity.DifyResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WorkflowFilesUploadResponse extends DifyResponse {
    /**
     * 文件唯一标识符，通常为UUID
     */
    private String id;
    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件大小，单位为字节
     */
    private int size;
    /**
     * 文件扩展名
     */
    private String extension;
    /**
     * MIME类型
     */
    private String mime_type;
    /**
     * 创建者ID
     */
    private String created_by;
    /**
     * 创建时间，Unix时间戳（秒）
     */
    private long created_at;
}
