# mybatisGenerator

### 组织结构
``` lua
├── config -- 代码生成器配置文件
├── generator -- 执行类
    ├── annotation -- 自定义注解
        ├── MySQLCodeGenerator -- mysql数据库代码生成执行类
        ├── OracleCodeGenerator -- oracle数据库代码生成执行类
    ├── service -- 代码生成器代码
```

### 注意
``` lua
1.config配置项:FILE_OVERRIDE(是否覆盖已有文件)若配置为true每次都会覆盖原有代码,请谨慎操作
2.为了让配置可读性更强，现config中所有输出路径均不采用拼接模式
3.代码生成器模版地址/resources/templates/generate/,其中controller模版返回值只试用于我自己的架构，若不需要可以手动修改
4.生成的代码中使用了三方依赖-hutool工具类
```