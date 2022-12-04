# Web开发课大作业

网上考勤系统

使用方式：

启动MYSQL，进入MYSQL的控制台，输入
```bash
source schema.sql
```

项目结构：
```plain text
|—— sql 建表文件
|—— src/...
    |—— com
        |—— travelforthree
            |—— daily
                |—— aspect AOP切面及其处理器
                |—— componet 配置类需要的组件
                |—— config 应用配置
                |—— constant 应用所需常量
                |—— controller 控制器
                |—— cron 定时任务
                |—— domain 数据库实体
                |—— dto 数据传输对象
                |—— exception 自定义异常类
                |—— mapper 数据库访问层
                |—— service 服务层
                |—— utils 工具类
                |—— vo 视图对象
        |—— resources
            |—— static 静态文件
        |—— webapp
            |—— WEB-INF
                |—— views jsp视图
```

部署应用：
```bash
docker-compose up -d
```