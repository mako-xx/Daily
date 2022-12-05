package com.travelthree.daily.utils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travelthree.daily.dto.PageParam;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Jiayi Zhu
 * @description change sql page to view page
 * @createDate 2022/12/5
 */
public class PageTransformUtil {

    /**
     * change entity page with page-helper to view page
     *
     * @param pageParam page param
     * @param query the selection statement
     * @param mapper mapping from T to R
     * @return PageInfo with view object
     * @param <T> entity object
     * @param <R> view object
     */
    public static <T, R> PageInfo<R> toViewPage(PageParam pageParam,
                                           Supplier<List<T>> query,
                                           Function<T, R> mapper) {

        PageHelper.startPage(pageParam.getPage(), pageParam.getPageSize());
        List<T> tList = query.get();
        PageInfo<T> tPageInfo = new PageInfo<>(tList);
        PageInfo<R> rPageInfo =new PageInfo<>();
        BeanUtils.copyProperties(tPageInfo, rPageInfo);
        rPageInfo.setList(tList.stream().map(mapper).toList());
        return rPageInfo;
    }
}
