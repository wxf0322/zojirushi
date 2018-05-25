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

import org.clover.zojirushi.entity.InstitutionEntity;
import org.clover.zojirushi.model.vo.InstitutionVO;
import org.clover.zojirushi.service.InstitutionService;
import org.clover.zojirushi.service.TreeService;
import org.clover.zojirushi.util.MapUtil;
import org.clover.zojirushi.vo.ResultStatus;
import org.clover.zojirushi.vo.TreeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述 组织机构管理Controller层
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/5/17 11:03
 */
@Controller
@RequestMapping("/institution")
public class InstitutionController {

    /**
     * 注入日志管理器
     */
    private static Logger logger = LoggerFactory.getLogger(InstitutionController.class);

    /**
     * 注入InstitutionService
     */
    @Autowired
    private InstitutionService institutionService;

    /**
     * 注入TreeService
     */
    @Autowired
    private TreeService treeService;

    @ResponseBody
    @RequestMapping(value = "tree")
    public List<TreeData> listTreeData(HttpServletRequest request) {
        return treeService.listTreeData("institutions");
    }

    @ResponseBody
    @RequestMapping(value = "find")
    public InstitutionVO findOne(Long id) {
        InstitutionEntity institutionEntity = institutionService.findOne(id);
        InstitutionVO institutionVO = new InstitutionVO(institutionEntity);
        return institutionVO;
    }

    @ResponseBody
    @RequestMapping(value = "saveOrUpdate")
    public ResultStatus saveOrUpdate(@RequestBody InstitutionVO institutionVO) {
        Long entityId = institutionVO.getId();
        Long parentId = institutionVO.getParentId();
        try {
            Map<String, Object> underlineMap = MapUtil.toUnderlineStringMap(MapUtil.toMap(institutionVO));
            this.treeService.saveOrUpdateTreeData(entityId,
                    parentId, underlineMap,
                    "institutions",
                    "institutions_s");
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error(e.getMessage(), e);
            return new ResultStatus(false, "");
        }
        return new ResultStatus(true, "");
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public ResultStatus delete(Long id) {
        if (institutionService.canBeDeleted(id)) {
            institutionService.delete(id);
            return new ResultStatus(true, "");
        } else {
            return new ResultStatus(false, "");
        }
    }

}
