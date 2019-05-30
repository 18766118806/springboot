package com.pingan.controller;

import com.pingan.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("all")
@RestController
public class MybatisController {

    @Autowired
    private IAreaService iAreaService;

    @RequestMapping("/queryAreas")
    public String queryUserList(
            @RequestParam(defaultValue = "", required = false) String id,
            @RequestParam(defaultValue = "1") String currentPage,
            @RequestParam(defaultValue = "10") String pageSize) {
        return iAreaService.queryAreaList (id, currentPage, pageSize);
    }

}
