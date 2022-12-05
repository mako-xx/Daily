package com.travelthree.daily.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jiayi Zhu
 * @description
 * @createDate 2022/12/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentVo {

    private String id;

    private String name;

    private String superior;
}
