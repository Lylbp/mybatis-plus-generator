package com.lylbp.generator.tem.javaModel;


import com.baomidou.mybatisplus.annotation.DbType;
import com.lylbp.generator.service.GenneratorService;

/**
 * Oracle 数据库代码生成类
 *
 * @author Erwin Feng[xfsy_2015@163.com]
 * @since 2019-04-17 10:33
 */
public class OracleCodeGenerator {
    /**
     * 若配置了是否覆盖已有文件为true每次生成代码会覆盖原有代码 请谨慎操作！
     * 若配置了是否覆盖已有文件为true每次生成代码会覆盖原有代码 请谨慎操作！
     * 若配置了是否覆盖已有文件为true每次生成代码会覆盖原有代码 请谨慎操作！
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        // URL
        String dbUrl = "jdbc:oracle:thin:@192.168.1.151:1521/orcl";
        // 用户名
        String username = "C##gsgkpt";
        // 密码
        String password = "gsgkpt";
        // 表前缀[生成的实体类自动不含表前缀]
        String[] tablePrefixes = {""};
        // 表名，为空，不生成表
        String[] tableNames = {"PUSH_MSG_USER"};
        // 字段前缀
        String[] fieldPrefixes = {};

        GenneratorService.execute(DbType.ORACLE, dbUrl, username, password, "oracle.jdbc.driver.OracleDriver", tablePrefixes, tableNames, fieldPrefixes);
    }

}
