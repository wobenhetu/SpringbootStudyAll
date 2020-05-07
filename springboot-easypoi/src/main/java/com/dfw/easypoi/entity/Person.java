package com.dfw.easypoi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * 类的功能及详细方法说明
 * All rights Reserved, Designed By www.jettech.com
 * @Title:  Person.java
 * @Package com.dfw.easypoi.entity
 * @author: 北京捷科智诚科技有限公司
 * @date:    16:03
 * @version V1.0
 * @Copyright: 2020 www.jettech.com Inc. All rights reserved.
 * 注意：本内容仅限于北京捷科智诚科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class Person {

    @Excel(name = "姓名")
    private String name;
    @Excel(name = "手机")
    private String tel;
    @Excel(name = "Email")
    private String email;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}