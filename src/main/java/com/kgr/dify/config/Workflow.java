package com.kgr.dify.config;


import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kangarap
 * @date 2025-06-12 11:27
 */
@Data
public class Workflow {
    private String api;
    private List<AppInfo> appInfos;
    private String apps;

    @Data
    public static class AppInfo {
        private String appName;
        private String appId;
        private String appKey;
    }

    public void setApps(String apps) {
        this.apps = apps;
        initAppInfos();
    }

    private synchronized void initAppInfos() {

        if (apps != null) {
            this.appInfos = Arrays.stream(apps.split(";"))
                    .map(entry -> {
                        AppInfo appInfo = new AppInfo();
                        Arrays.stream(entry.split(","))  // 拆分每个对象的属性
                                .forEach(kv -> {
                                    String[] parts = kv.split("=");
                                    if ("appName".equals(parts[0])) appInfo.setAppName(parts[1]);
                                    if ("appId".equals(parts[0])) appInfo.setAppId(parts[1]);
                                    if ("appKey".equals(parts[0])) appInfo.setAppKey(parts[1]);
                                });
                        return appInfo;
                    })
                    .collect(Collectors.toList());
        }
    }

    public AppInfo getAppInfo(String appId) {

        return appInfos.stream().filter(
                app ->  StringUtils.isNotBlank(app.getAppId()) && app.getAppId().equals(appId))
                .findFirst().orElseThrow(() -> new RuntimeException("app not found"));
    }
}