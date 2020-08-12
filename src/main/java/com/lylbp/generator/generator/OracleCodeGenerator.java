package com.lylbp.generator.generator;


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
     * 如果你配置了com.dar.app.generator.Config中的PACKAGE_NAME
     * ！！！每次生成代码会覆盖原有代码 请谨慎操作！
     * ！！！每次生成代码会覆盖原有代码 请谨慎操作！
     * ！！！每次生成代码会覆盖原有代码 请谨慎操作！
     * ！！！每次生成代码会覆盖原有代码 请谨慎操作！
     *
     * @param args
     */
    public static void main(String[] args) {
        DbType dbType = DbType.ORACLE;
        String dbUrl = "jdbc:oracle:thin:@192.168.1.151:1521/orcl";
        String username = "C##gsgkpt";
        String password = "gsgkpt";
        String driver = "oracle.jdbc.driver.OracleDriver";
        // 表前缀，生成的实体类，不含前缀
        String[] tablePrefixes = {"SJCZ_T_"};
        // 表名，为空，不生成表
        String[] tableNames = {"SJCZ_T_CJ_B"};
        // 字段前缀
        String[] fieldPrefixes = {};
        GenneratorService.execute(dbType, dbUrl, username, password, driver, tablePrefixes, tableNames, fieldPrefixes);
    }

}
