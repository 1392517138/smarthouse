package cn.edu.cqupt.nmid.smarthouse.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author piwenjing
 * @description
 * @date 2020/1/10 2:33 PM
 */
@ApiModel(description = "用户测量的信息")
@Data
@AllArgsConstructor
public class UserInfo {
    @ApiModelProperty("温度")
    private Double temperature;
    @ApiModelProperty("湿度")
    private Double moisture;
    @ApiModelProperty("身高")
    private String height;
    @ApiModelProperty("体重")
    private String weight;
    @ApiModelProperty("体温")
    private String bTemperature;
    @ApiModelProperty("血糖")
    private String bSugar;
    @ApiModelProperty("血氧")
    private String bOxygen;
    @ApiModelProperty("心率")
    private String hRate;
    @ApiModelProperty("测量次数")
    private String nMeasure;

}
