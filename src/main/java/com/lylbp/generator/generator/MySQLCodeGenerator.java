package com.lylbp.generator.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.lylbp.generator.service.GenneratorService;

/**
 * MySQL 数据库代码生成类
 *
 * @author Erwin Feng
 * @since 2019-04-17 10:33
 */
public class MySQLCodeGenerator {

    /**
     * 若配置了是否覆盖已有文件为true每次生成代码会覆盖原有代码 请谨慎操作！
     * 若配置了是否覆盖已有文件为true每次生成代码会覆盖原有代码 请谨慎操作！
     * 若配置了是否覆盖已有文件为true每次生成代码会覆盖原有代码 请谨慎操作！
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        // url
        String dbUrl = "jdbc:mysql://localhost:3306/java_model";
        // 账户
        String username = "root";
        // 密码
        String password = "root";
        // 表前缀[生成的实体类自动不含表前缀]
        String[] tablePrefixes = {"t_dar_"};
        // 表名，为空，不生成表
        String[] tableNames = {"area"};
        // 字段前缀
        String[] fieldPrefixes = {};
        GenneratorService.execute(DbType.MYSQL, dbUrl, username, password, "com.mysql.cj.jdbc.Driver", tablePrefixes, tableNames, fieldPrefixes);
    }

}
