package com.travelthree.daily.controller.admin;

import com.github.pagehelper.PageInfo;
import com.travelthree.daily.constant.RoleEnum;
import com.travelthree.daily.domain.Department;
import com.travelthree.daily.domain.Employee;
import com.travelthree.daily.dto.RegisterParam;
import com.travelthree.daily.service.DepartmentService;
import com.travelthree.daily.service.EmployeeService;

import com.travelthree.daily.vo.EmployeeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/register")
    @ResponseBody
    public String register(@Valid @RequestBody RegisterParam registerParam) {
        return employeeService.register(registerParam);
    }

    @RequestMapping("/employee")
    @ResponseBody
    public PageInfo queryEmployee(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "5") Integer pageSize) {
        //为了程序的严谨性，判断非空：
        if(pageNum == null || pageNum <= 0){
            pageNum = 1;   //设置默认当前页为1
        }
        if(pageSize == null){ //若页面大小为空
            pageSize = 5;    //设置默认每页显示的数据数
        }
        List<Employee> employees = employeeService.queryEmployeeByPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(employees);
        pageInfo.setPageSize(pageSize);

        //接下来将查询来的部门id转化为部门名称，联表查询彻底失败，我是菜逼
        //取出pageInfo里面的List<Employee>
        List<Employee> employees1 = pageInfo.getList();
        //初始化接口要求的视图对象集合
        List<EmployeeVo> employeeVos = new LinkedList<>();
        //菜逼的for循环赋值环节
        for(int tmp = 0; tmp < employees1.size(); tmp++) {
            //每次都创建新的视图对象
            EmployeeVo employeeVo = new EmployeeVo();
            //迭代赋值
            employeeVo.setId(employees1.get(tmp).getId());
            employeeVo.setName(employees1.get(tmp).getName());
            employeeVo.setUsername(employees1.get(tmp).getUsername());
            employeeVo.setPassword(employees1.get(tmp).getPassword());
            employeeVo.setRole(RoleEnum.getRoleFromOrdinal(employees1.get(tmp).getRole()));
            employeeVo.setTelephone(employees1.get(tmp).getTelephone());
            //通过employee的部门ID调用部门服务类查询部门名称并赋值
            String tmpDepartmentId = employees1.get(tmp).getDepartmentId();
            Department department = departmentService.selectByPrimaryKey(tmpDepartmentId);
            employeeVo.setDepartment(department.getName());
            //在集合中添加
            employeeVos.add(employeeVo);
        }
        //将集合赋回pageInfo
        pageInfo.setList(employeeVos);
        return pageInfo;
    }
}
