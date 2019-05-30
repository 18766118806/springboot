package com.pingan.domain;

import lombok.Data;

import java.io.Serializable;

/*
 * @Author:  Yajun_Xu
 * @Create: 2019-05-01 18:56
 **/
@Data
public class Tb_Areas implements Serializable {
   private String id ;
   private String areaid ;
   private String area;
   private String idcityid ;
}
