package com.lylbp.generator.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.lylbp.generator.config.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weiwenbin
 */
public class GenneratorService {

    /**
     * 数据连接信息
     *
     * @param dbType   数据库类型
     * @param dbUrl    连接地址
     * @param username 用户名
     * @param password 密码
     * @param driver   驱动
     * @return DataSourceConfig
     */
    private static DataSourceConfig dataSourceConfig(DbType dbType, String dbUrl, String username, String password, String driver) {
        return new DataSourceConfig()
                .setDbType(dbType)
                .setUrl(dbUrl)
                .setUsername(username)
                .setPassword(password)
                .setDriverName(driver)
                ;
    }

    /**
     * 全局配置对象
     *
     * @return GlobalConfig
     */
    private static GlobalConfig globalConfig() {
        return new GlobalConfig()
                .setAuthor(Config.AUTHOR)
                .setOutputDir(Config.OUT_OUT_DIR)
                //是否覆盖已有文件
                .setFileOverride(Config.FILE_OVERRIDE)
                //是否打开输出目录
                .setOpen(false)
                //时间采用java 8，（操作工具类：JavaLib => DateTimeUtils）
                .setDateType(DateType.ONLY_DATE)
                //不需要ActiveRecord特性的请改为false
                .setActiveRecord(true)
                //XML 二级缓存
                .setEnableCache(false)
                //XML ResultMap
                .setBaseResultMap(true)
                //XML columList
                .setBaseColumnList(true)
                //XML enableCache
                .setEnableCache(false)
                //是否生成 kotlin 代码
                .setKotlin(false)
                //自定义文件命名，注意 %s 会自动填充表实体属性！
                .setEntityName(Config.FILE_NAME_ENTITY)
                .setMapperName(Config.FILE_NAME_MAPPER)
                .setXmlName(Config.FILE_NAME_XML)
                .setServiceName(Config.FILE_NAME_SERVICE)
                .setServiceImplName(Config.FILE_NAME_SERVICE_IMPL)
                .setControllerName(Config.FILE_NAME_CONTROLLER)
                //主键类型
                .setIdType(IdType.ASSIGN_UUID)
                //是否支持swagger2
                .setSwagger2(Config.SWAGGER_SUPPORT)
                ;
    }

    /**
     * 策略配置对象
     *
     * @param tablePrefixes 表前缀
     * @param tableNames    表前缀
     * @param fieldPrefixes 字段前缀
     * @return StrategyConfig
     */
    private static StrategyConfig strategyConfig(String[] tablePrefixes, String[] tableNames, String[] fieldPrefixes) {
        return new StrategyConfig()
                // 全局大写命名 ORACLE 注意
                .setCapitalMode(true)
                // 是否跳过视图
                .setSkipView(false)
                //.setDbColumnUnderline(true)
                // 此处可以修改为您的表前缀(数组)
                .setTablePrefix(tablePrefixes)
                // 字段前缀
                .setFieldPrefix(fieldPrefixes)
                // 表名生成策略
                .setNaming(NamingStrategy.underline_to_camel)
                //修改替换成你需要的表名，多个表名传数组
                .setInclude(tableNames)
                // 排除生成的表
                //.setExclude(new String[]{"test"})
                // entity是否支持lombok实体
                .setEntityLombokModel(Config.ENTITY_LOMBOK_SUPPORT)
                //entity是否为构建者模型
                .setChainModel(false)
                //entity是否生成字段常量（默认 false 可通过常量名获取数据库字段名  3.x支持lambda表达式
                .setEntityColumnConstant(false)
                // 逻辑删除属性名称
                .setLogicDeleteFieldName(Config.FIELD_LOGIC_DELETE_NAME)
                //是否为RestController
                .setRestControllerStyle(Config.IS_REST_CONTROLLER)
                //实体属性上添加表字段映射
                .setEntityTableFieldAnnotationEnable(true)
                ;
    }

    /**
     * 包配置对象
     *
     * @return PackageConfig
     */
    private static PackageConfig packageConfig() {
        return new PackageConfig()
                .setParent(null)
                .setController(ObjectUtil.isEmpty(Config.PACKAGE_NAME_CONTROLLER) ? null : Config.PACKAGE_NAME_CONTROLLER)
                .setEntity(ObjectUtil.isEmpty(Config.PACKAGE_NAME_ENTITY) ? null : Config.PACKAGE_NAME_ENTITY)
                .setMapper(ObjectUtil.isEmpty(Config.PACKAGE_NAME_MAPPER) ? null : Config.PACKAGE_NAME_MAPPER)
                .setService(ObjectUtil.isEmpty(Config.PACKAGE_NAME_SERVICE) ? null : Config.PACKAGE_NAME_SERVICE)
                .setServiceImpl(ObjectUtil.isEmpty(Config.PACKAGE_NAME_SERVICE_IMPL) ? null : Config.PACKAGE_NAME_SERVICE_IMPL)
                ;
    }

    /**
     * 自定义配置对象
     *
     * @param injectionConfig 表配置
     * @return InjectionConfig
     */
    private static InjectionConfig injectionConfig(InjectionConfig injectionConfig) {
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(Config.XML_PATH) || ObjectUtil.isNotEmpty(Config.XML_TEMPLATE)) {
            // 自定义xml配置
            fileOutConfigList.add(new FileOutConfig(Config.XML_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return Config.XML_PATH + "/" + tableInfo.getXmlName() + StringPool.DOT_XML;
                }
            });
        }

        if (ObjectUtil.isNotEmpty(Config.VO_TEMPLATE) || ObjectUtil.isNotEmpty(Config.VO_PATH)) {
            // 自定义输出VO配置
            fileOutConfigList.add(new FileOutConfig(Config.VO_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return Config.VO_PATH + "/" + tableInfo.getEntityName() + "VO.java";
                }
            });
        }

        if (ObjectUtil.isNotEmpty(Config.QO_TEMPLATE) || ObjectUtil.isNotEmpty(Config.QO_PATH)) {
            // 自定义输出QO配置
            fileOutConfigList.add(new FileOutConfig(Config.QO_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return Config.QO_PATH + "/" + tableInfo.getEntityName() + "QO.java";
                }
            });
        }

        if (ObjectUtil.isNotEmpty(Config.DTO_TEMPLATE) || ObjectUtil.isNotEmpty(Config.DTO_PATH)) {
            // 自定义输出DTO配置
            fileOutConfigList.add(new FileOutConfig(Config.DTO_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return Config.DTO_PATH + "/" + tableInfo.getEntityName() + "DTO.java";
                }
            });
        }


        injectionConfig.setFileOutConfigList(fileOutConfigList);

        return injectionConfig;
    }

    /**
     * 执行
     *
     * @param dbType        数据库类型
     * @param dbUrl         数据库地址
     * @param username      数据库用户名
     * @param password      数据库密码
     * @param driver        数据库驱动
     * @param tablePrefixes 表前缀
     * @param tableNames    表明
     * @param fieldPrefixes 字段前缀
     */
    public static void execute(DbType dbType, String dbUrl, String username, String password, String driver,
                               String[] tablePrefixes, String[] tableNames, String[] fieldPrefixes) {
        if (ArrayUtil.isAllEmpty(tableNames)) {
            return;
        }

        //mabatis-plus全局配置
        GlobalConfig globalConfig = globalConfig();

        //数据库配置对象
        DataSourceConfig dataSourceConfig = dataSourceConfig(dbType, dbUrl, username, password, driver);

        // 策略配置
        StrategyConfig strategyConfig = strategyConfig(tablePrefixes, tableNames, fieldPrefixes);
        // 策略配置--controller父类
        if (ObjectUtil.isNotEmpty(Config.SUPER_CONTROLLER_CLASS)) {
            strategyConfig.setSuperControllerClass(Config.SUPER_CONTROLLER_CLASS);
        }

        //包信息配置
        PackageConfig packageConfig = packageConfig();

        // 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>(10);
                map.put("VOPackage", Config.PACKAGE_NAME_VO);
                map.put("QOPackage", Config.PACKAGE_NAME_QO);
                map.put("DTOPackage", Config.PACKAGE_NAME_DTO);
                map.put("DBType", dbType.getDb());
                map.put("FieldlogicDeleteName", Config.FIELD_LOGIC_DELETE_NAME);
                this.setMap(map);
            }
        };
        injectionConfig = injectionConfig(injectionConfig);

        TemplateConfig tc = new TemplateConfig();
        tc.setMapper(Config.MAPPER_TEMPLATE);
        tc.setService(Config.SERVICE_TEMPLATE);
        tc.setServiceImpl(Config.SERVICE_IMPL_TEMPLATE);
        tc.setController(Config.CONTROLLER_TEMPLATE);
        tc.setXml(null);

        new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setCfg(injectionConfig)
                .setTemplateEngine(new VelocityTemplateEngine())
                .setTemplate(tc)
                .execute();
    }


}
