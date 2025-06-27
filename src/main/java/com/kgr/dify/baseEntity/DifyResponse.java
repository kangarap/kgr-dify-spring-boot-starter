package com.kgr.dify.baseEntity;

import lombok.Data;

@Data
public abstract class DifyResponse {

    /** 结果原报文 */
    private String result;

    private String message;
    private String code;

}
