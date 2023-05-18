package com.xxl.job.core.biz.client;

import com.google.gson.JsonObject;
import com.xxl.job.core.biz.AdminBiz;
import com.xxl.job.core.biz.model.HandleCallbackParam;
import com.xxl.job.core.biz.model.RegistryParam;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.util.XxlJobRemotingUtil;

import java.util.List;

/**
 * admin api test
 *
 * @author xuxueli 2017-07-28 22:14:52
 */
public class AdminBizClient implements AdminBiz {

    public AdminBizClient() {
    }

    public AdminBizClient(String addressUrl, String accessToken) {
        this.addressUrl = addressUrl;
        this.accessToken = accessToken;

        // valid
        if (!this.addressUrl.endsWith("/")) {
            this.addressUrl = this.addressUrl + "/";
        }
    }

    private String addressUrl;
    private String accessToken;
    private int timeout = 3;


    @Override
    public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList) {
        return XxlJobRemotingUtil.postBody(addressUrl + "api/callback", accessToken, timeout, callbackParamList, String.class);
    }

    @Override
    public ReturnT<String> registry(RegistryParam registryParam) {
        return XxlJobRemotingUtil.postBody(addressUrl + "api/registry", accessToken, timeout, registryParam, String.class);
    }

    @Override
    public ReturnT<String> registryRemove(RegistryParam registryParam) {
        return XxlJobRemotingUtil.postBody(addressUrl + "api/registryRemove", accessToken, timeout, registryParam, String.class);
    }

    @Override
    public ReturnT<String> jobInfoAdd(Object obj) {
        return XxlJobRemotingUtil.postBody(addressUrl + "api/jobInfoAdd", accessToken, timeout, obj, String.class);
    }

    @Override
    public ReturnT<String> jobInfoUpdate(Object obj) {
        return XxlJobRemotingUtil.postBody(addressUrl + "api/jobInfoUpdate", accessToken, timeout, obj, String.class);
    }

    @Override
    public ReturnT<String> jobInfoRemove(Object obj) {
        return XxlJobRemotingUtil.postBody(addressUrl + "api/jobInfoRemove", accessToken, timeout, obj, String.class);
    }

    @Override
    public ReturnT<String> jobInfoStart(Object obj) {
        return XxlJobRemotingUtil.postBody(addressUrl + "api/jobInfoStart", accessToken, timeout, obj, String.class);
    }

    @Override
    public ReturnT<String> jobInfoStop(Object obj) {
        return XxlJobRemotingUtil.postBody(addressUrl + "api/jobInfoStop", accessToken, timeout, obj, String.class);
    }


    @Override
    public ReturnT<String> getJobGroupList(int start, int length, String appname, String title) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("start", start);
        jsonObject.addProperty("length", length);
        jsonObject.addProperty("appname", appname);
        jsonObject.addProperty("title", title);
        return XxlJobRemotingUtil.postBody(addressUrl + "api/getJobGroupList", accessToken, timeout, jsonObject, String.class);
    }

    @Override
    public ReturnT<String> jobGroupAdd(Object obj) {
        return XxlJobRemotingUtil.postBody(addressUrl + "api/jobGroupAdd", accessToken, timeout, obj, String.class);
    }

    @Override
    public ReturnT<String> jobGroupUpdate(Object obj) {
        return XxlJobRemotingUtil.postBody(addressUrl + "api/jobGroupUpdate", accessToken, timeout, obj, String.class);
    }

    @Override
    public ReturnT<String> jobGroupRemove(Object obj) {
        return XxlJobRemotingUtil.postBody(addressUrl + "api/jobGroupRemove", accessToken, timeout, obj, String.class);
    }

}
