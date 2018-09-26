package xyz.leefly.project.bo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "product", resultMap = "ProductResultMap")
public class Product {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("product_category")
    private String productCategory;
    @TableField("product_name")
    private String productName;
    @TableField("executive_standard")
    private String executiveStandard;

    private String output;

    private String unit;
    @TableField("company_id")
    private Long companyId;

    private Integer deleted;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;

}
