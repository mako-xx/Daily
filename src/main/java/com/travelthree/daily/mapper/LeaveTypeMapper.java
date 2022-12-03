package com.travelthree.daily.mapper;

import com.travelthree.daily.domain.LeaveType;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
* @author faust
* @description 针对表【leave_type】的数据库操作Mapper
* @createDate 2022-11-27 16:45:35
* @Entity com.travelthree.daily.domain.LeaveType
*/
@Mapper
public interface LeaveTypeMapper {

    int deleteByPrimaryKey(String id);

    int insert(LeaveType record);

    int insertSelective(LeaveType record);

    LeaveType selectByPrimaryKey(String id);

    List<LeaveType> selectAll();

    int updateByPrimaryKeySelective(LeaveType record);

    int updateByPrimaryKey(LeaveType record);

}
