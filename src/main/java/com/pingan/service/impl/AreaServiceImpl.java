package com.pingan.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pingan.domain.Tb_Areas;
import com.pingan.mapper.Area_Mapper;
import com.pingan.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2019-05-01 19:10
 **/
@Service
public class AreaServiceImpl implements IAreaService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private HashOperations hashOperations;
    @Autowired
    private Area_Mapper area_mapper;

    /**
     * @param id
     * @return
     */
    @Override
    public String queryAreaList(String id, String currentPage, String pageSize) {
        Page<Tb_Areas> page = PageHelper.startPage (Integer.parseInt (currentPage), Integer.parseInt (pageSize));
        List<Tb_Areas> tb_areasList;
        PageInfo<Tb_Areas> pageInfo ;
        if (hashOperations.get ("1","1") != null) {
           // String s = stringRedisTemplate.opsForValue ().get ("tb_areasList1");
            Object s =  hashOperations.get ("1", "1");
            return JSON.toJSONString (s);
        } else {
            tb_areasList = area_mapper.queryAreaList (id);
            pageInfo = new PageInfo<> (tb_areasList);
           // stringRedisTemplate.opsForValue().set ("tb_areasList1",pageInfo.toString ());
            hashOperations.put ("1","1",pageInfo);
        }

        if (tb_areasList == null)
            return null;
        String s1 = pageInfo.toString ();
        String s = JSON.toJSONString (pageInfo);
        return s;
    }
}
