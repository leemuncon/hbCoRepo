package xyz.leefly.project.bo;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "company", resultMap = "CompanyResultMap")
public class Company {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String nature;
    @TableField("legal_person")
    private String legalPerson;
    @TableField("business_license")
    private String businessLicense;

    private String contact;

    private String phone;

    private String province;
    private String city;
    private String district;

    private String address;
    @TableField("stuff_number")
    private String stuffNumber;
    @TableField("last_year_production_value")
    private String lastYearProductionValue;
    @TableField("production_value_unit")
    private String productionValueUnit;
    @TableField("laboratory_level")
    private String laboratoryLevel;

    private String authentication;
    @TableField("production_license")
    private String productionLicense;

    private Integer status;
    @TableField("check_year")
    private String checkYear;

    private Integer deleted;

    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;

}
