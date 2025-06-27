package com.kgr.dify.baseEntity;


import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import com.kgr.dify.config.KgrDifyProperties;
import com.kgr.dify.config.Workflow;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 对接方式改动
 */
@Slf4j
public abstract class AbstractWorkflowRequest<T extends DifyResponse>  {

    private static final KgrDifyProperties KGR_DIFY_PROPERTIES = SpringUtil.getBean("kgrDifyProperties");

    public String appId;
    String url;
    Method method;

    public AbstractWorkflowRequest() {
    }

    public AbstractWorkflowRequest(Method method) {
        this.method = method;
    }


    protected AbstractWorkflowRequest(String url, Method method) {
        this.url = url;
        this.method = method;
    }


    public Map<String, String> getWorkflowConfig(String appId) {


        Workflow.AppInfo appInfo = KGR_DIFY_PROPERTIES.getWorkflow().getAppInfo(appId);

        String token = appInfo.getAppKey();
        String host = KGR_DIFY_PROPERTIES.getWorkflow().getApi();

        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("host", host);
        return map;
    }



    public String getRequest(String appId, String url, String body) {

        Map<String, String> difyConfig = getWorkflowConfig(appId);
        String token = difyConfig.get("token");
        String host = difyConfig.get("host");

        url = host + (StringUtils.isBlank(url) ? this.url : url);

        HttpRequest httpRequest = HttpRequest.post(url).body(body);

        if (method.equals(Method.GET)) {

            if(StringUtils.isNotBlank(body)) {
                Map<String, Object> map = new HashMap<>(JSONUtil.parseObj(body));
                httpRequest = HttpRequest.get(url + "?" + HttpUtil.toParams(map));
            }else
            {
                httpRequest = HttpRequest.get(url);
            }
        } else if (method.equals(Method.DELETE)) {

            httpRequest = HttpRequest.delete(url).body(body);
        } else if (method.equals(Method.PATCH)) {

            httpRequest = HttpRequest.patch(url).body(body);
        }

        log.info("===> [method]: {}, [url]: {}", method, url);
        log.info("===> [body]: {}", body);
        String res = httpRequest.bearerAuth(token).execute().body();
        log.info("===> [resp]: {}", res);
        if (StringUtils.isNotBlank(res)) {
            JSONObject jsonObject = JSONUtil.parseObj(res);
            String errMsg = jsonObject.getStr("message");
            if (StringUtils.isNotBlank(errMsg)) {
                throw new RuntimeException(errMsg);
            }
        }
        return res;
    }


    public String getRequest(String appId, String url, File file) {

        Map<String, String> difyConfig = getWorkflowConfig(appId);
        String token = difyConfig.get("token");
        String host = difyConfig.get("host");

        url = host + (StringUtils.isBlank(url) ? this.url : url);

        HttpRequest httpRequest = HttpRequest.post(url).form("file", file);

        log.info("请求url => {}", url);
        log.info("请求method => {}", method);
        log.info("请求file => {}", file.getName());
        String res = httpRequest.bearerAuth(token).execute().body();

        JSONObject jsonObject = JSONUtil.parseObj(res);

        String errMsg = jsonObject.getStr("message");
        if (StringUtils.isNotBlank(errMsg)) {
            throw new RuntimeException(errMsg);
        }
        return res;
    }

    public String getRequest(String appId, String url, String user, File file) {

        Map<String, String> difyConfig = getWorkflowConfig(appId);
        String token = difyConfig.get("token");
        String host = difyConfig.get("host");

        url = host + (StringUtils.isBlank(url) ? this.url : url);

        HttpRequest httpRequest = HttpRequest.post(url).form("user", user).form("file", file);

        log.info("[AbstractWorkflowRequest]请求url => {}", url);
        log.info("[AbstractWorkflowRequest]请求method => {}", method);
        log.info("[AbstractWorkflowRequest]请求file => {}", file.getName());
        String res = httpRequest.bearerAuth(token).execute().body();

        JSONObject jsonObject = JSONUtil.parseObj(res);

        String errMsg = jsonObject.getStr("message");
        if (StringUtils.isNotBlank(errMsg)) {
            throw new RuntimeException(errMsg);
        }
        return res;
    }

    public byte[] download(String appId, String url, String body) {

        Map<String, String> difyConfig = getWorkflowConfig(appId);
        String token = difyConfig.get("token");
        String host = difyConfig.get("host");

        url = host + (StringUtils.isBlank(url) ? this.url : url);

        HttpRequest httpRequest = HttpRequest.post(url).body(body);

        return httpRequest.bearerAuth(token).execute().bodyBytes();

    }


    public abstract T doRequest() throws Exception;


}
