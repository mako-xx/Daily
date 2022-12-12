package com.travelthree.daily.mapper;

import com.travelthree.daily.domain.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author faust
* @description 针对表【department】的数据库操作Mapper
* @createDate 2022-11-23 16:37:33
* @Entity daily.domain.Department
*/
@Mapper
public interface DepartmentMapper {

    int deleteByPrimaryKey(String id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String id);

    Department selectByName(String name);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> selectAll();
}
