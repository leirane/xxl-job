package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.thread.JobCompleteHelper;
import com.xxl.job.admin.core.thread.JobRegistryHelper;
import com.xxl.job.core.biz.AdminBiz;
import com.xxl.job.core.biz.model.HandleCallbackParam;
import com.xxl.job.core.biz.model.RegistryParam;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuxueli 2017-07-27 21:54:20
 */
@Service
public class AdminBizImpl implements AdminBiz {


    @Override
    public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList) {
        return JobCompleteHelper.getInstance().callback(callbackParamList);
    }

    @Override
    public ReturnT<String> registry(RegistryParam registryParam) {
        return JobRegistryHelper.getInstance().registry(registryParam);
    }

    @Override
    public ReturnT<String> registryRemove(RegistryParam registryParam) {
        return JobRegistryHelper.getInstance().registryRemove(registryParam);
    }

    @Override
    public ReturnT<String> jobInfoAdd(Object obj) {
        return null;
    }

    @Override
    public ReturnT<String> jobInfoUpdate(Object obj) {
        return null;
    }

    @Override
    public ReturnT<String> jobInfoRemove(Object obj) {
        return null;
    }

    @Override
    public ReturnT<String> jobInfoStart(Object obj) {
        return null;
    }

    @Override
    public ReturnT<String> jobInfoStop(Object obj) {
        return null;
    }

    @Override
    public ReturnT<String> getJobGroupById(int id) {
        return null;
    }

    @Override
    public ReturnT<String> getJobGroupList(int start, int length, String appname, String title) {
        return null;
    }

    @Override
    public ReturnT<String> jobGroupAdd(Object obj) {
        return null;
    }

    @Override
    public ReturnT<String> jobGroupUpdate(Object obj) {
        return null;
    }

    @Override
    public ReturnT<String> jobGroupRemove(Object obj) {
        return null;
    }

}
