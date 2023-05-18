package com.xxl.job.admin.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xxl.job.admin.controller.annotation.PermissionLimit;
import com.xxl.job.admin.core.conf.XxlJobAdminConfig;
import com.xxl.job.admin.core.model.XxlJobGroup;
import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.dao.XxlJobGroupDao;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.core.biz.AdminBiz;
import com.xxl.job.core.biz.model.HandleCallbackParam;
import com.xxl.job.core.biz.model.RegistryParam;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.util.GsonTool;
import com.xxl.job.core.util.XxlJobRemotingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xuxueli on 17/5/10.
 */
@Controller
@RequestMapping("/api")
public class JobApiController {

    private final Logger logger = LoggerFactory.getLogger(JobApiController.class);

    @Resource
    private AdminBiz adminBiz;
    @Resource
    private XxlJobService xxlJobService;

    @Resource
    public XxlJobGroupDao xxlJobGroupDao;

    /**
     * api
     *
     * @param uri
     * @param data
     * @return
     */
    @RequestMapping("/{uri}")
    @ResponseBody
    @PermissionLimit(limit = false)
    public ReturnT<String> api(HttpServletRequest request, @PathVariable("uri") String uri, @RequestBody(required = false) String data) {
        try {
            // valid
            if (!"POST".equalsIgnoreCase(request.getMethod())) {
                return new ReturnT<String>(ReturnT.FAIL_CODE, "invalid request, HttpMethod not support.");
            }
            if (uri == null || uri.trim().length() == 0) {
                return new ReturnT<String>(ReturnT.FAIL_CODE, "invalid request, uri-mapping empty.");
            }
            if (XxlJobAdminConfig.getAdminConfig().getAccessToken() != null
                    && XxlJobAdminConfig.getAdminConfig().getAccessToken().trim().length() > 0
                    && !XxlJobAdminConfig.getAdminConfig().getAccessToken().equals(request.getHeader(XxlJobRemotingUtil.XXL_JOB_ACCESS_TOKEN))) {
                return new ReturnT<String>(ReturnT.FAIL_CODE, "The access token is wrong.");
            }

            // services mapping
            switch (uri) {
                case "callback":
                    List<HandleCallbackParam> callbackParamList = GsonTool.fromJson(data, List.class, HandleCallbackParam.class);
                    return adminBiz.callback(callbackParamList);
                case "registry": {
                    RegistryParam registryParam = GsonTool.fromJson(data, RegistryParam.class);
                    return adminBiz.registry(registryParam);
                }
                case "registryRemove": {
                    RegistryParam registryParam = GsonTool.fromJson(data, RegistryParam.class);
                    return adminBiz.registryRemove(registryParam);
                }
                case "jobInfoAdd": {
                    XxlJobInfo jobInfo = GsonTool.fromJson(data, XxlJobInfo.class);
                    return xxlJobService.add(jobInfo);
                }
                case "jobInfoUpdate": {
                    XxlJobInfo jobInfo = GsonTool.fromJson(data, XxlJobInfo.class);
                    return xxlJobService.update(jobInfo);
                }
                case "jobInfoRemove": {
                    XxlJobInfo jobInfo = GsonTool.fromJson(data, XxlJobInfo.class);
                    int id = jobInfo.getId();
                    return xxlJobService.remove(id);
                }
                case "jobInfoStart": {
                    XxlJobInfo jobInfo = GsonTool.fromJson(data, XxlJobInfo.class);
                    int id = jobInfo.getId();
                    return xxlJobService.start(id);
                }
                case "jobInfoStop": {
                    XxlJobInfo jobInfo = GsonTool.fromJson(data, XxlJobInfo.class);
                    int id = jobInfo.getId();
                    return xxlJobService.stop(id);
                }
                case "getJobGroupList": {
                    JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
                    int offset = jsonObject.get("start").getAsInt();
                    int length = jsonObject.get("length").getAsInt();
                    String appname = jsonObject.get("appname").getAsString();
                    String title = jsonObject.get("title").getAsString();
                    return new ReturnT<String>(ReturnT.SUCCESS_CODE, GsonTool.toJson(xxlJobGroupDao.pageList(offset, length, appname, title)));
                }
                case "jobGroupAdd": {
                    XxlJobGroup xxlJobGroup = GsonTool.fromJson(data, XxlJobGroup.class);
                    xxlJobGroupDao.save(xxlJobGroup);
                    if (xxlJobGroup.getId() < 1) {
                        return ReturnT.FAIL;
                    } else {
                        return new ReturnT<String>(String.valueOf(xxlJobGroup.getId()));
                    }
                }
                case "jobGroupUpdate": {
                    XxlJobGroup xxlJobGroup = GsonTool.fromJson(data, XxlJobGroup.class);
                    xxlJobGroupDao.update(xxlJobGroup);
                    return ReturnT.SUCCESS;
                }
                case "jobGroupRemove": {
                    XxlJobGroup xxlJobGroup = GsonTool.fromJson(data, XxlJobGroup.class);
                    xxlJobGroupDao.remove(xxlJobGroup.getId());
                    return ReturnT.SUCCESS;
                }
                default:
                    return new ReturnT<String>(ReturnT.FAIL_CODE, "invalid request, uri-mapping(" + uri + ") not found.");
            }
        } catch (Exception ex) {
            logger.error("操作失败!", ex);
            return ReturnT.FAIL;
        }

    }

}
