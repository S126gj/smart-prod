# smart-prod(无租户版本)

### SpringBoot 3.x + Satoken + JDK 17 + MybatisPlus 3.5.x + Redis 3.x + Minio 8.5.x + Elasticsearch 7.x + Xxl-job + Sharding-jdbc 5.4.x

> 模块解析

* smart-prod-common: 公共模块
* smart-prod-core: 系统代码模块
* smart-prod-es: elasticserach 模块
* smart-prod-mbg: 权限 + mybatis 模块
* smart-prod-file: 文件上传模块
* smart-prod-forest: 三方对接模块
* smart-prod-sms: 短信发送模块
* smart-prod-xxljob: 动态定时任务模块

执行 doc/sql 下的 smart-prod.sql 文件，初始租户为测试，账号为 admin， 密码为 123456

doc/layout 为布局，不需要可以自行删除 smart-prod-core/src/main/java/com/device/core/column 下的所有代码

> 需要注意，存入redis的实体类，其中LocalDate，LocalDateTime必须添加以下注解，否则会出现序列化报错

LocalDate
```
@JsonFormat(pattern = "yyyy-MM-dd")
@JsonDeserialize(using = LocalDateDeserializer.class)
@JsonSerialize(using = LocalDateSerializer.class)
```

LocalDateTime
```
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
@JsonDeserialize(using = LocalDateTimeDeserializer.class)
@JsonSerialize(using = LocalDateTimeSerializer.class)
```
