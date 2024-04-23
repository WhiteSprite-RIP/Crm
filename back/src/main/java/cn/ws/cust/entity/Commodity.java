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
import java.math.BigDecimal;

/**
 * @author WhiteSprite
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_commodity")
@TableName("a_commodity")
@ApiModel(value = "商品")
public class Commodity extends WsBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品名称")
    private String title;

    @ApiModelProperty(value = "商品类型")
    private String type;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "商品图片")
    private String image;

    @ApiModelProperty(value = "库存")
    private BigDecimal number;
}