package cn.edu.cqupt.nmid.smarthouse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author piwenjing
 * @description
 * @date 2020/2/15 9:26 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan {
    int id;
    String time;
    String thing;
}
