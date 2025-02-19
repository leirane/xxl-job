package com.xxl.job.core.biz;

import com.xxl.job.core.biz.model.HandleCallbackParam;
import com.xxl.job.core.biz.model.RegistryParam;
import com.xxl.job.core.biz.model.ReturnT;

import java.util.List;

/**
 * @author xuxueli 2017-07-27 21:52:49
 */
public interface AdminBiz {


    // ---------------------- callback ----------------------

    /**
     * callback
     *
     * @param callbackParamList
     * @return
     */
    public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList);


    // ---------------------- registry ----------------------

    /**
     * registry
     *
     * @param registryParam
     * @return
     */
    public ReturnT<String> registry(RegistryParam registryParam);

    /**
     * registry remove
     *
     * @param registryParam
     * @return
     */
    public ReturnT<String> registryRemove(RegistryParam registryParam);


    // ---------------------- biz (custome) ----------------------
    // group、job ... manage

    ReturnT<String> jobInfoAdd(Object obj);

    ReturnT<String> jobInfoUpdate(Object obj);

    ReturnT<String> jobInfoRemove(Object obj);

    ReturnT<String> jobInfoStart(Object obj);

    ReturnT<String> jobInfoStop(Object obj);

    ReturnT<String> getJobGroupById(int id);

    ReturnT<String> getJobGroupList(int start, int length, String appname, String title);

    ReturnT<String> jobGroupAdd(Object obj);

    ReturnT<String> jobGroupUpdate(Object obj);

    ReturnT<String> jobGroupRemove(Object obj);
}
