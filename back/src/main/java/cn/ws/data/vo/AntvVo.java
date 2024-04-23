package cn.ws.data.vo;

import io.swagger.annotations.Api;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author WhiteSprite
 */
@Api(tags = "图表VO类")
@Data
public class AntvVo {
    private String title;
    private String type;
    private BigDecimal value;
}

