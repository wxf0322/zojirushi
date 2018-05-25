/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.clover.zojirushi.entity.OperationEntity;
import org.clover.zojirushi.model.vo.OperationVO;
import org.clover.zojirushi.service.OperationService;
import org.clover.zojirushi.service.TreeService;
import org.clover.zojirushi.util.MapUtil;
import org.clover.zojirushi.util.SqlFilter;
import org.clover.zojirushi.vo.ResultStatus;
import org.clover.zojirushi.vo.TreeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017-5-23 10:32
 */
@Controller
@RequestMapping("/operation")
public class OperationController {

    /**
     * @see Logger
     */
    private static Logger logger = LoggerFactory.getLogger(OperationController.class);

    /**
     * @see OperationService
     */
    @Autowired
    private OperationService operationService;

    /**
     * @see TreeService
     */
    @Autowired
    private TreeService treeService;

    @ResponseBody
    @RequestMapping(value = "tree")
    public List<TreeData> listTreeData(HttpServletRequest request) {
        SqlFilter sqlFilter = new SqlFilter();
        String applicationId = request.getParameter("applicationId");
        if (!StringUtils.isEmpty(applicationId)) {
            sqlFilter.addFilter("QUERY_o#application_id_L_EQ", applicationId);
        }
        return treeService.listTreeData("operations o", sqlFilter);
    }

    @ResponseBody
    @RequestMapping(value = "find")
    public OperationVO findOne(Long id) {
        OperationEntity operationEntity = operationService.findOne(id);
        OperationVO operationVO = new OperationVO(operationEntity);
        return operationVO;
    }

    @ResponseBody
    @RequestMapping(value = "saveOrUpdate")
    public ResultStatus saveOrUpdate(@RequestBody OperationVO operationVO) {
        Long entityId = operationVO.getId();
        Long parentId = operationVO.getParentId();
        try {
            Map<String, Object> underlineMap = MapUtil.toUnderlineStringMap(MapUtil.toMap(operationVO));
            treeService.saveOrUpdateTreeData(entityId,
                    parentId, underlineMap,
                    "operations",
                    "operations_s");
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error(e.getMessage(), e);
            return new ResultStatus(false, "");
        }
        return new ResultStatus(true, "");
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public ResultStatus delete(Long id) {
        if (operationService.canBeDeleted(id)) {
            operationService.delete(id);
            return new ResultStatus(true, "");
        } else {
            return new ResultStatus(false, "");
        }
    }

}
