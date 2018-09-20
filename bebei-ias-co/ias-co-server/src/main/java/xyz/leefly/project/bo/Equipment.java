package xyz.leefly.project.bo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "equipment", resultMap = "EquipmentResultMap")
public class Equipment {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String type;

    private Integer capacity;

    private Integer output;

    private String unit;
    @TableField("company_id")
    private Long companyId;

    private Integer deleted;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;

}
