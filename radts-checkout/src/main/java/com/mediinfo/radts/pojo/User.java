package com.mediinfo.radts.pojo;


import lombok.Data;

import javax.persistence.*;

/**
 * @description Vue登录页面demo信息对象实体
 * @author WANGJIHONG
 * @date 2018年4月5日 下午10:57:53
 * @Copyright 版权所有 (c) www.javalsj.com
 * @memo 备注信息
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String username;

    @Column
    private String password;
}
