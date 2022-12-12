package com.travelthree.daily.mapper;

import com.travelthree.daily.domain.Leave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
* @author faust
* @description 针对表【leave】的数据库操作Mapper
* @createDate 2022-11-27 16:45:35
* @Entity com.travelthree.daily.domain.Leave
*/
@Mapper
public interface LeaveMapper {

    int deleteByPrimaryKey(String id);

    int insert(Leave record);

    int insertSelective(Leave record);

    Leave selectByPrimaryKey(String id);

    List<Leave> queryLeave(Integer status);

    int updateByPrimaryKeySelective(Leave record);

    int updateByPrimaryKey(Leave record);

    Set<Leave> selectAllByDate(@Param("time") LocalDate time);

    Set<Leave> selectAllByStatusAndDate(@Param("status") Integer status, @Param("time") LocalDate time);

    List<Leave> selectAllByEmployeeId(String employeeId);
}
