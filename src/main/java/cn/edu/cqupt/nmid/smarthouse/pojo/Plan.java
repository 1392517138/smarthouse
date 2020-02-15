package cn.edu.cqupt.nmid.smarthouse.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/15 9:26 AM
 */
@Data
@ApiModel("计划")
@AllArgsConstructor
@NoArgsConstructor
public class Plan {

    int id;
    @ApiModelProperty("时间")
    String time;
    @ApiModelProperty("事件描述")
    String thing;
}
