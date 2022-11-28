package com.travelthree.daily.mapper;

import com.travelthree.daily.domain.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author faust
* @description 针对表【attendance】的数据库操作Mapper
* @createDate 2022-11-23 16:37:33
* @Entity daily.domain.Attendance
*/
@Mapper
public interface AttendanceMapper {

    int deleteByPrimaryKey(String id);

    int insert(Attendance record);

    int insertSelective(Attendance record);

    Attendance selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Attendance record);

    int updateByPrimaryKey(Attendance record);

    /** 更新最新一次的考勤记录 */
    void updateRecentStatusByTime(@Param("id") String id, @Param("status") Integer status);
}
