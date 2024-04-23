package cn.ws.data.entity;

import cn.ws.basics.baseClass.WsBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
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
@Table(name = "a_dict")
@TableName("a_dict")
@ApiModel(value = "数据字典")
public class Dict extends WsBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据字典标题")
    private String title;

    @ApiModelProperty(value = "数据字典排序值")
    @Column(precision = 10, scale = 2)
    private BigDecimal sortOrder;

    @ApiModelProperty(value = "数据字典备注")
    private String description;

    @ApiModelProperty(value = "数据字典类型")
    private String type;
}