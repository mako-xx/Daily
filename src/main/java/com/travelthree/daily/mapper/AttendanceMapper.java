package com.travelthree.daily.mapper;

import com.travelthree.daily.domain.Attendance;
import org.apache.ibatis.annotations.Mapper;
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

}
