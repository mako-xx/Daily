CREATE DATABASE IF NOT EXISTS daily;
USE daily;
/*
 * 部门：部门id 部门名称 上级部门id
 */
DROP TABLE IF EXISTS department;
CREATE TABLE department(
                           id VARCHAR(36) NOT NULL comment '部门id',
    `name` VARCHAR(255) NOT NULL comment '部门名称',
    superior_id VARCHAR(255) comment '上级部门id',
    PRIMARY KEY (id)
    );

/*
 * 员工：员工id 员工姓名 角色 联系方式 所属部门id
 *
 * 角色：普通员工，管理员
 */
DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
                          id VARCHAR(36) NOT NULL comment '员工id',
    `username` VARCHAR(64) NOT NULL UNIQUE comment '用户名',
    `password` VARCHAR(255) NOT NULL comment '密码',
    `name` VARCHAR(255) NOT NULL comment '员工姓名',
    `role` TINYINT NOT NULL comment '角色：普通员工(0)，管理员(1)',
    telephone VARCHAR(255) comment '联系方式',
    department_id VARCHAR(36) NOT NULL comment '所属部门',
    PRIMARY KEY (id),
    FOREIGN KEY (department_id) REFERENCES department(id)
    );

/*
 * 考勤记录：考勤id 考勤日期 员工id 考勤状态
 *
 * 员工在某一天的考勤记录
 * 考勤状态：出勤，缺勤，请假
 */
DROP TABLE IF EXISTS attendance;
CREATE TABLE attendance(
                           id VARCHAR(36) NOT NULL comment '考勤id',
    `date` DATE NOT NULL comment '考勤日期',
                           employee_id VARCHAR(36) NOT NULL comment '员工id',
    `status` TINYINT NOT NULL comment '考勤状态：出勤(0)，缺勤(1)，请假(2)',
                           PRIMARY KEY (id),
                           FOREIGN KEY (employee_id) REFERENCES employee(id)
);

/*
 * 请假类型：请假类型id 请假类型名称
 * 
 * 类型：病假，事假，婚假，丧假，产假，其他
 */
DROP TABLE IF EXISTS leave_type;
CREATE TABLE leave_type(
                           id TINYINT NOT NULL AUTO_INCREMENT comment '请假类型id',
    `name` VARCHAR(64) NOT NULL comment '请假类型名称',
    PRIMARY KEY(id)
    );

/*
 * 假条：假条id 员工id 请假开始时间 结束时间 假条审核状态 请假类型 假条内容 审核人id
 *
 * 假条审核状态：等待审核，审核不通过，审核通过
 * 请假类型：病假，事假，婚假，丧假，产假，其他
 * 假条由管理员审核
 */
DROP TABLE IF EXISTS `leave`;
CREATE TABLE `leave`(
    id VARCHAR(36) NOT NULL comment '假条id',
    employee_id VARCHAR(36) NOT NULL comment '员工id',
    startDate DATE NOT NULL comment '请假起始时间',
    endDate DATE NOT NULL comment '请假结束时间',
    `status` TINYINT NOT NULL comment '请假审核状态：等待审核(0)，审核不通过(1)，审核通过(2)',
    type_id TINYINT NOT NULL comment '请假类型：病假(0)，事假(1)，婚假(2)，丧假(3)，产假(4)，其他(5)',
    reason VARCHAR(255) NOT NULL comment '请假理由',
    auditorId VARCHAR(36) comment '审核人id',
    PRIMARY KEY (id),
    FOREIGN KEY (employee_id) REFERENCES employee(id),
    FOREIGN KEY (type_id) REFERENCES leave_type(id)
    );

# 插入测试部门
INSERT INTO department values ('1', 'test', null);
# 插入一个员工和一个管理员
INSERT INTO employee values ('0', 'staff', '$2a$10$7YpYgwyjaK6UW5kCInK4Yuv5hcDPNng6gutMKt4u7ZPM3FQWVwt5S', 'staff', 0, '123', 1);
INSERT INTO employee values ('1', 'admin', '$2a$10$7YpYgwyjaK6UW5kCInK4Yuv5hcDPNng6gutMKt4u7ZPM3FQWVwt5S', 'admin', 1, '123', 1);