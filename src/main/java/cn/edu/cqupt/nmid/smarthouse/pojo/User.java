package cn.edu.cqupt.nmid.smarthouse.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * @author piwenjing
 * @description 用户基本信息
 * @date 2020/1/10 2:10 PM
 */
@ApiModel("用户实体")
@Data
@AllArgsConstructor
public class User {
    @ApiModelProperty("电话号码")
    private String phone;
    @ApiModelProperty("密码")
    private String pwd;
    @ApiModelProperty(value = "别名", example = "小明")
    private String nickname;
    @ApiModelProperty("出生年月")
    private String borth;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("头像地址")
    private String photo;
    @ApiModelProperty("家庭成员")
    private String family;


}
