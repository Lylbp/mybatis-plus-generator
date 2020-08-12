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
     * 如果你配置了com.dar.app.generator.Config中的PACKAGE_NAME
     * ！！！每次生成代码会覆盖原有代码 请谨慎操作！
     * ！！！每次生成代码会覆盖原有代码 请谨慎操作！
     * ！！！每次生成代码会覆盖原有代码 请谨慎操作！
     * ！！！每次生成代码会覆盖原有代码 请谨慎操作！
     *
     * @param args
     */
    public static void main(String[] args) {
        DbType dbType = DbType.MYSQL;
        String dbUrl = "jdbc:mysql://localhost:3306/java_model";
        String username = "root";
        String password = "root";
        String driver = "com.mysql.cj.jdbc.Driver";
        // 表前缀，生成的实体类，不含前缀
        String[] tablePrefixes = {"t_dar_"};
        // 表名，为空，不生成表
        String[] tableNames = {"area"};
        // 字段前缀
        String[] fieldPrefixes = {};
        GenneratorService.execute(dbType, dbUrl, username, password, driver, tablePrefixes, tableNames, fieldPrefixes);
    }

}
