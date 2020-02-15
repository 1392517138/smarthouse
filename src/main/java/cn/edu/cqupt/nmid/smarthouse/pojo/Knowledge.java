package cn.edu.cqupt.nmid.smarthouse.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author piwenjing
 * @description
 * @date 2020/1/28 8:51 PM
 */
@ApiModel("题库")
@Data
@AllArgsConstructor
public class Knowledge {
    @ApiModelProperty("题目id")
    private int id;
    @ApiModelProperty("题目内容")
    private String content;
    @ApiModelProperty("题目答案")
    private String answer;
}
