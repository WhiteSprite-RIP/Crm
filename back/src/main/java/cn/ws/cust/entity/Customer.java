package cn.ws.cust.entity;

import cn.ws.basics.baseClass.WsBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * @author WhiteSprite
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_customer")
@TableName("a_customer")
@ApiModel(value = "客户")
public class Customer extends WsBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户姓名")
    private String title;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "客户类型")
    private String type;

    @ApiModelProperty(value = "备注")
    private String remark;
}