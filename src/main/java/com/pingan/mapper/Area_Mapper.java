package com.pingan.mapper;

import com.pingan.domain.Tb_Areas;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/*
 * @Author:  Yajun_Xu
 * @Create: 2019-05-01 19:02
 **/
@Mapper
public interface Area_Mapper {
    List<Tb_Areas> queryAreaList(@Param ( value = "id") String id);
}
