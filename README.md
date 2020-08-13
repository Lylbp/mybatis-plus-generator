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
### 执行
``` lua
1.首先配置config
2.进入generator目录根据数据库类型选择MySQLCodeGenerator或OracleCodeGenerator并进行数据配置
3.执行MySQLCodeGenerator或OracleCodeGenerator执行main方法
```

### 注意
``` lua
1.config配置项:FILE_OVERRIDE(是否覆盖已有文件)默认配置为true每次都会覆盖原有代码,请谨慎操作。
2.为了让配置可读性更强，现config中所有输出路径均不采用拼接模式
3.代码生成器模版地址/resources/templates/generate/,其中controller模版因返回值只试用于我自己的架构，若不合适需手动修改生成controller
的模版文件
4.生成的代码中使用了三方依赖-hutool工具类
5.如果你用的oracle, oracle驱动可能maven不下来, 网上解决方法一大堆请自行百度进行手动maven打包oracle驱动
6.代码生成模版使用velocity模板引擎,顾生成的java代码,请手动一键格式话(ctrl+A-> Ctrl+Alt+L)
```