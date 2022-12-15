package com.travelthree.daily.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 分页查询参数
 * */
@Data
public class PageParam {

    /** 页码 */
    @NotNull
    @Min(value = 1)
    private Integer page = 1;

    /** 每页数据量 */
    @NotNull
    @Min(value = 1)
    private Integer pageSize = 5;
}
