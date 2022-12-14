package com.travelthree.daily.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.travelthree.daily.domain.Employee;
import org.apache.ibatis.annotations.Mapper;
/**
* @author faust
* @description 针对表【employee】的数据库操作Mapper
* @createDate 2022-11-23 16:37:33
* @Entity daily.domain.Employee
*/
@Mapper
public interface EmployeeMapper {

    int deleteByPrimaryKey(String id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    Employee selectOneByUsername(@Param("username") String username);

    List<Employee> selectAllByRole(@Param("role") Integer role);

    List<Employee> selectAllByDepartmentId(@Param("department_id") String id);

    List<Employee> queryEmployeeByPage();
}
