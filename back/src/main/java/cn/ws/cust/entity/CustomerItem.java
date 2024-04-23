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
@Table(name = "a_customer_item")
@TableName("a_customer_item")
@ApiModel(value = "客户跟踪")
public class CustomerItem extends WsBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户ID")
    private String customerId;

    @ApiModelProperty(value = "客户姓名")
    private String customerName;

    @ApiModelProperty(value = "跟进日期")
    private String date;

    @ApiModelProperty(value = "跟进内容")
    private String content;

    @ApiModelProperty(value = "跟进人ID")
    private String userId;

    @ApiModelProperty(value = "跟进人")
    private String userName;
}