package com.example.demo.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * 演示实体父类
 */
public class SuperEntity<T extends Model> extends Model<T> {

    /**
     * 主键ID , 这里故意演示注解可以无
     */
//    @TableId("test_id")
    public int id;
//    private Long tenantId;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public Long getTenantId() {
//        return tenantId;
//    }
//
//    public SuperEntity setTenantId(Long tenantId) {
//        this.tenantId = tenantId;
//        return this;
//    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
