package com.travelthree.daily.mapper;

import com.travelthree.daily.domain.Department;
import org.apache.ibatis.annotations.Mapper;
/**
* @author faust
* @description 针对表【department】的数据库操作Mapper
* @createDate 2022-11-23 16:37:33
* @Entity daily.domain.Department
*/
@Mapper
public interface DepartmentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

}
