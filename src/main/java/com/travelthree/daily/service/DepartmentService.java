package com.travelthree.daily.service;

import com.travelthree.daily.domain.Department;
import com.travelthree.daily.domain.LeaveType;

import java.util.List;

/**
* @author faust
* @description 针对表【department】的数据库操作Service
* @createDate 2022-11-23 15:56:31
*/
public interface DepartmentService {
    /**
     * 根据id获得部门名
     * @param id
     * @return
     */
    Department selectByPrimaryKey(String id);

    void addDepartment(Department department);

    void updateDepartment(Department department, String id);

    void deleteInfo(String id);

    List<Department> selectAllDepartments();

    String getSuperiorName(Department department);
}
