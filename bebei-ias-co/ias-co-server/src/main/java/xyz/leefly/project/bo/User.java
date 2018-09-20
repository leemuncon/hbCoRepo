package xyz.leefly.project.bo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "opt_user", resultMap = "UserResultMap")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String nickname;

    @TableField("password_md5")
    private String password;

    private Integer status;

    private Integer deleted;

    private Date created;

    private Date updated;

}
