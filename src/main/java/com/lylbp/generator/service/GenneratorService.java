package com.lylbp.generator.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.lylbp.generator.config.Config;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author weiwenbin
 */
public class GenneratorService {

    /////////////////////////////////////////////类名相关///////////////////////////////////////////////////////////
    /**
     * Entity文件名称
     */
    public static final String FILE_NAME_ENTITY = "%s";

    /**
     * mapper文件名称
     */
    public static final String FILE_NAME_MAPPER = "%sMapper";

    /**
     * 数据库xml文件名称
     */
    public static final String FILE_NAME_XML = "%sMapper";

    /**
     * Service文件名称
     */
    public static final String FILE_NAME_SERVICE = "%sService";

    /**
     * Service实现类文件名称
     */
    public static final String FILE_NAME_SERVICE_IMPL = "%sServiceImpl";

    /**
     * Controller文件名称
     */
    public static final String FILE_NAME_CONTROLLER = "%sController";

    /**
     * vo文件名称
     */
    public static final String FILE_NAME_VO = "%sVO";

    /**
     * 新增DTO文件名称
     */
    public static final String FILE_NAME_DTO_CREATE = "%sCreateDTO";

    /**
     * 编辑DTO文件名称
     */
    public static final String FILE_NAME_DTO_UPDATE = "%sUpdateDTO";

    /**
     * 查询DTO文件名称
     */
    public static final String FILE_NAME_DTO_QUERY = "%sQueryDTO";

    /**
     * convertMapper文件名称
     */
    public static final String FILE_NAME_CONVERT_MAPPER = "%sConvertMapper";

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
                //作者
                .setAuthor(Config.AUTHOR)
                //controller,service,mapper,serviceImpl输出路径
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
                //XML是否生成ResultMap
                .setBaseResultMap(true)
                //XML是否生成columList
                .setBaseColumnList(true)
                //XML enableCache
                .setEnableCache(false)
                //是否生成 kotlin 代码
                .setKotlin(false)
                //自定义文件命名，注意 %s 会自动填充表名
                .setEntityName(FILE_NAME_ENTITY)
                .setMapperName(FILE_NAME_MAPPER)
                .setXmlName(FILE_NAME_XML)
                .setServiceName(FILE_NAME_SERVICE)
                .setServiceImplName(FILE_NAME_SERVICE_IMPL)
                .setControllerName(FILE_NAME_CONTROLLER)
                //主键类型
                .setIdType(Config.ENTITY_ID_TYPE)
                //是否支持swagger2
                .setSwagger2(Config.SWAGGER_SUPPORT);
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
        List<TableFill> tableFills = new ArrayList<>();
        // 表的自动填充字段
        if (StrUtil.isNotEmpty(Config.CREATE_FILL)){
            List<String> createFillList = Arrays.asList(Config.CREATE_FILL.split(","));
            createFillList.forEach(s -> tableFills.add(new TableFill(s, FieldFill.INSERT)));
        }

        if (StrUtil.isNotEmpty(Config.UPDATE_FILL)){
            List<String> updateFillList = Arrays.asList(Config.UPDATE_FILL.split(","));
            updateFillList.forEach(s -> tableFills.add(new TableFill(s, FieldFill.UPDATE)));
        }

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
                // 表的自动填充字段
                .setTableFillList(tableFills)
                //是否为RestController
                .setRestControllerStyle(Config.IS_REST_CONTROLLER)
                //实体属性上添加表字段映射
                .setEntityTableFieldAnnotationEnable(Config.ENTITY_TABLE_FIELD_ANNOTATION_ENABLE)
                //entity是否生成序列化id
                .setEntitySerialVersionUID(Config.ENTITY_SERIAL_VERSION_UID)
                //Boolean 类型字段是否移除 is 前缀（默认 false）
                .setEntityBooleanColumnRemoveIsPrefix(Config.ENTITY_BOOLEAN_COLUMN_REMOVE_IS_PREFIX)
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
        //自定义controller
        if (ObjectUtil.isNotEmpty(Config.CONTROLLER_PATH) && ObjectUtil.isNotEmpty(Config.CONTROLLER_TEMPLATE)) {
            fileOutConfigList.add(new FileOutConfig(Config.CONTROLLER_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    String fileName = String.format(FILE_NAME_CONTROLLER, tableInfo.getEntityName());
                    return Config.CONTROLLER_PATH + "/" + fileName + StringPool.DOT_JAVA;
                }
            });
        }

        //自定义entity
        if (ObjectUtil.isNotEmpty(Config.ENTITY_PATH) && ObjectUtil.isNotEmpty(Config.ENTITY_TEMPLATE)) {
            fileOutConfigList.add(new FileOutConfig(Config.ENTITY_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    String fileName = String.format(FILE_NAME_ENTITY, tableInfo.getEntityName());
                    return Config.ENTITY_PATH + "/" + fileName + StringPool.DOT_JAVA;
                }
            });
        }

        //自定义Service
        if (ObjectUtil.isNotEmpty(Config.SERVICE_PATH) && ObjectUtil.isNotEmpty(Config.SERVICE_TEMPLATE)) {
            fileOutConfigList.add(new FileOutConfig(Config.SERVICE_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    String fileName = String.format(FILE_NAME_SERVICE, tableInfo.getEntityName());
                    return Config.SERVICE_PATH + "/" + fileName + StringPool.DOT_JAVA;
                }
            });
        }

        //自定义ServiceImpl
        if (ObjectUtil.isNotEmpty(Config.SERVICE_IMPL_PATH) && ObjectUtil.isNotEmpty(Config.SERVICE_IMPL_TEMPLATE)) {
            fileOutConfigList.add(new FileOutConfig(Config.SERVICE_IMPL_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    String fileName = String.format(FILE_NAME_SERVICE_IMPL, tableInfo.getEntityName());
                    return Config.SERVICE_IMPL_PATH + "/" + fileName + StringPool.DOT_JAVA;
                }
            });
        }


        //自定义Mapper
        if (ObjectUtil.isNotEmpty(Config.MAPPER_PATH) && ObjectUtil.isNotEmpty(Config.MAPPER_TEMPLATE)) {
            fileOutConfigList.add(new FileOutConfig(Config.MAPPER_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    String fileName = String.format(FILE_NAME_MAPPER, tableInfo.getEntityName());
                    return Config.MAPPER_PATH + "/" + fileName + StringPool.DOT_JAVA;
                }
            });
        }

        // 自定义xml配置
        if (ObjectUtil.isNotEmpty(Config.XML_PATH) && ObjectUtil.isNotEmpty(Config.XML_TEMPLATE)) {
            fileOutConfigList.add(new FileOutConfig(Config.XML_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    String fileName = tableInfo.getXmlName();
                    return Config.XML_PATH + "/" + fileName + StringPool.DOT_XML;
                }
            });
        }

        // 自定义输出VO配置
        if (ObjectUtil.isNotEmpty(Config.VO_TEMPLATE) && ObjectUtil.isNotEmpty(Config.VO_PATH)) {
            fileOutConfigList.add(new FileOutConfig(Config.VO_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    String fileName = String.format(FILE_NAME_VO, tableInfo.getEntityName());
                    return Config.VO_PATH + "/" + fileName + StringPool.DOT_JAVA;
                }
            });
        }

        // 自定义输出查询DTO配置
        if (ObjectUtil.isNotEmpty(Config.DTO_QUERY_TEMPLATE) && ObjectUtil.isNotEmpty(Config.DTO_PATH)) {
            fileOutConfigList.add(new FileOutConfig(Config.DTO_QUERY_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    String fileName = String.format(FILE_NAME_DTO_QUERY, tableInfo.getEntityName());
                    return Config.DTO_PATH + "/" + fileName + StringPool.DOT_JAVA;
                }
            });
        }

        // 自定义输出新增DTO配置
        if (ObjectUtil.isNotEmpty(Config.DTO_CREATE_TEMPLATE) && ObjectUtil.isNotEmpty(Config.DTO_PATH)) {
            fileOutConfigList.add(new FileOutConfig(Config.DTO_CREATE_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    String fileName = String.format(FILE_NAME_DTO_CREATE, tableInfo.getEntityName());
                    return Config.DTO_PATH + "/" + fileName + StringPool.DOT_JAVA;
                }
            });
        }


        // 自定义输出编辑DTO配置
        if (ObjectUtil.isNotEmpty(Config.DTO_UPDATE_TEMPLATE) && ObjectUtil.isNotEmpty(Config.DTO_PATH)) {
            fileOutConfigList.add(new FileOutConfig(Config.DTO_UPDATE_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    String fileName = String.format(FILE_NAME_DTO_UPDATE, tableInfo.getEntityName());
                    return Config.DTO_PATH + "/" + fileName + StringPool.DOT_JAVA;
                }
            });
        }

        // 自定义输出convertMapper配置
        if (ObjectUtil.isNotEmpty(Config.CONVERT_MAPPER_TEMPLATE) && ObjectUtil.isNotEmpty(Config.CONVERT_PATH)) {
            fileOutConfigList.add(new FileOutConfig(Config.CONVERT_MAPPER_TEMPLATE) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    String fileName = String.format(FILE_NAME_CONVERT_MAPPER, tableInfo.getEntityName());
                    return Config.CONVERT_PATH + "/" + fileName + StringPool.DOT_JAVA;
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
        //要生成的表数组为空直接中断
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
                map.put("ConvertMapperPackage", Config.PACKAGE_NAME_CONVERT);
                map.put("DTOPackage", Config.PACKAGE_NAME_DTO);
                map.put("DBType", dbType.getDb());
                map.put("FieldlogicDeleteName", Config.FIELD_LOGIC_DELETE_NAME);
                map.put("FieldlogicDeleteDefaultValue", Config.FIELD_LOGIC_DELETE_DEFAULT_VALUE);
                map.put("BasePack", Config.BASE_PACK);
                map.put("BaseCoreUtilPack", Config.BASE_CORE_UTIL_PACK);
                map.put("BaseCoreEntityPack", Config.BASE_CORE_ENTITY_PACK);
                map.put("MybatisUtilPack", Config.MYBATIS_UTIL_PACK);
                map.put("VoNotReturnField", Config.VO_NOT_RETURN_FIELD);
                this.setMap(map);
            }
        };
        injectionConfig = injectionConfig(injectionConfig);

        //设置模版
        TemplateConfig tc = new TemplateConfig();
        tc.setMapper(Config.MAPPER_TEMPLATE);
        tc.setService(Config.SERVICE_TEMPLATE);
        tc.setServiceImpl(Config.SERVICE_IMPL_TEMPLATE);
        tc.setController(Config.CONTROLLER_TEMPLATE);
        tc.setEntity(Config.ENTITY_TEMPLATE);
        //已自定义xml模版以及输出路径顾这里写null,不然默认会在mapper目录下生成一个xml
        tc.setXml(null);

        //代码生成
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
