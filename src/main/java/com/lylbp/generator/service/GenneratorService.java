package com.lylbp.generator.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.DbQueryRegistry;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.lylbp.generator.config.Config;

import java.util.*;

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
     * DTO文件名称
     */
    public static final String FILE_NAME_DTO = "%sDTO";

    /**
     * 新增Form文件名称
     */
    public static final String FILE_NAME_FORM_CREATE = "%sCreateForm";

    /**
     * 编辑Form文件名称
     */
    public static final String FILE_NAME_FORM_UPDATE = "%sUpdateForm";

    /**
     * 查询Form文件名称
     */
    public static final String FILE_NAME_FORM_QUERY = "%sQueryForm";

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
        return new DataSourceConfig.Builder(dbUrl, username, password)
                .dbQuery(new DbQueryRegistry().getDbQuery(dbType))
                .driverClassName(driver)
                .build();
    }

    /**
     * 全局配置对象
     *
     * @return GlobalConfig
     */
    private static GlobalConfig globalConfig() {
        GlobalConfig.Builder builder = new GlobalConfig.Builder()
                .author(Config.AUTHOR)
                .outputDir(Config.OUT_OUT_DIR)
                .disableOpenDir()
                .dateType(DateType.ONLY_DATE);
        if (Config.SWAGGER_SUPPORT) {
            builder.enableSpringdoc();
        }
        return builder.build();
    }

    /**
     * 策略配置对象
     *
     * @param tablePrefixes 表前缀
     * @param tableNames    表明
     * @param fieldPrefixes 字段前缀
     * @return StrategyConfig
     */
    private static StrategyConfig strategyConfig(String[] tablePrefixes, String[] tableNames, String[] fieldPrefixes) {
        List<IFill> tableFills = new ArrayList<>();
        if (StrUtil.isNotEmpty(Config.CREATE_FILL)) {
            List<String> createFillList = Arrays.asList(Config.CREATE_FILL.split(","));
            createFillList.forEach(s -> tableFills.add(new Column(s, FieldFill.INSERT)));
        }

        if (StrUtil.isNotEmpty(Config.UPDATE_FILL)) {
            List<String> updateFillList = Arrays.asList(Config.UPDATE_FILL.split(","));
            updateFillList.forEach(s -> tableFills.add(new Column(s, FieldFill.UPDATE)));
        }

        StrategyConfig.Builder strategyBuilder = new StrategyConfig.Builder()
                .enableCapitalMode()
                .addTablePrefix(tablePrefixes)
                .addFieldPrefix(fieldPrefixes)
                .addInclude(tableNames);

        StrategyConfig strategyConfig = strategyBuilder.build();

        //自定义entity
        strategyConfig.entityBuilder()
                .javaTemplate(Config.ENTITY_TEMPLATE)
                .naming(NamingStrategy.underline_to_camel)
                .formatFileName(FILE_NAME_ENTITY)
                .idType(Config.ENTITY_ID_TYPE)
                .enableActiveRecord()
                .logicDeleteColumnName(Config.FIELD_LOGIC_DELETE_NAME)
                .addTableFills(tableFills)
                .enableFileOverride()
                .get();
        if (!Config.ENTITY_SERIAL_VERSION_UID) {
            strategyConfig.entityBuilder().disableSerialVersionUID().get();
        }else {
            strategyConfig.entityBuilder().enableSerialAnnotation().get();
        }

        if (Config.ENTITY_BOOLEAN_COLUMN_REMOVE_IS_PREFIX) {
            strategyConfig.entityBuilder().enableRemoveIsPrefix().get();
        }

        if (Config.ENTITY_TABLE_FIELD_ANNOTATION_ENABLE) {
            strategyConfig.entityBuilder().enableTableFieldAnnotation().get();
        }

        if (Config.ENTITY_LOMBOK_SUPPORT) {
            strategyConfig.entityBuilder().enableLombok().get();
        }

        //自定义controller
        strategyConfig.controllerBuilder()
                .template(Config.CONTROLLER_TEMPLATE)
                .formatFileName(FILE_NAME_CONTROLLER)
                .enableFileOverride()
                .enableRestStyle()
                .build();
        if (ObjectUtil.isNotEmpty(Config.SUPER_CONTROLLER_CLASS)) {
            strategyConfig.controllerBuilder().superClass(Config.SUPER_CONTROLLER_CLASS).build();
        }


        //自定义service
        strategyConfig.serviceBuilder()
                .serviceTemplate(Config.SERVICE_TEMPLATE)
                .serviceImplTemplate(Config.SERVICE_IMPL_TEMPLATE)
                .formatServiceFileName(FILE_NAME_SERVICE)
                .formatServiceImplFileName(FILE_NAME_SERVICE_IMPL)
                .enableFileOverride()
                .build();

        //自定义mapper
        strategyConfig.mapperBuilder()
                .mapperTemplate(Config.MAPPER_TEMPLATE)
//                .mapperXmlTemplate(Config.XML_TEMPLATE)
                .disableMapperXml()
                .formatMapperFileName(FILE_NAME_MAPPER)
                .formatXmlFileName(FILE_NAME_XML)
                .enableBaseResultMap()
                .enableBaseColumnList()
                .enableFileOverride()
                .build();

        return strategyConfig;
    }

    /**
     * 包配置对象
     *
     * @return PackageConfig
     */
    private static PackageConfig packageConfig() {
        PackageConfig.Builder builder = new PackageConfig.Builder();
        builder.parent("");
        if (ObjectUtil.isNotEmpty(Config.PACKAGE_NAME_CONTROLLER)) {
            builder.controller(Config.PACKAGE_NAME_CONTROLLER);
        }
        if (ObjectUtil.isNotEmpty(Config.PACKAGE_NAME_ENTITY)) {
            builder.entity(Config.PACKAGE_NAME_ENTITY);
        }
        if (ObjectUtil.isNotEmpty(Config.PACKAGE_NAME_MAPPER)) {
            builder.mapper(Config.PACKAGE_NAME_MAPPER);
        }
        if (ObjectUtil.isNotEmpty(Config.PACKAGE_NAME_SERVICE)) {
            builder.service(Config.PACKAGE_NAME_SERVICE);
        }
        if (ObjectUtil.isNotEmpty(Config.PACKAGE_NAME_SERVICE_IMPL)) {
            builder.serviceImpl(Config.PACKAGE_NAME_SERVICE_IMPL);
        }
        return builder.build();
    }

    /**
     * 自定义配置对象
     *
     * @param customMap   自定义参数
     * @return InjectionConfig
     */
    private static InjectionConfig injectionConfig(Map<String, Object> customMap) {
        List<CustomFile> customFiles = new ArrayList<>();

//        //自定义controller
//        if (ObjectUtil.isNotEmpty(Config.CONTROLLER_PATH) && ObjectUtil.isNotEmpty(Config.CONTROLLER_TEMPLATE)) {
//            customFiles.add(new CustomFile.Builder()
//                    .templatePath(Config.CONTROLLER_TEMPLATE)
//                    .filePath(Config.CONTROLLER_PATH)
//                    .formatNameFunction(tableInfo -> String.format(FILE_NAME_CONTROLLER, tableInfo.getEntityName()))
//                    .fileName(StringPool.DOT_JAVA)
//                    .enableFileOverride()
//                    .build());
//        }

//        //自定义entity
//        if (ObjectUtil.isNotEmpty(Config.ENTITY_PATH) && ObjectUtil.isNotEmpty(Config.ENTITY_TEMPLATE)) {
//            customFiles.add(new CustomFile.Builder()
//                    .templatePath(Config.ENTITY_TEMPLATE)
//                    .filePath(Config.ENTITY_PATH)
//                    .formatNameFunction(tableInfo -> String.format(FILE_NAME_ENTITY, tableInfo.getEntityName()))
//                    .fileName(StringPool.DOT_JAVA)
//                    .enableFileOverride()
//                    .build());
//        }

//        //自定义Service
//        if (ObjectUtil.isNotEmpty(Config.SERVICE_PATH) && ObjectUtil.isNotEmpty(Config.SERVICE_TEMPLATE)) {
//            customFiles.add(new CustomFile.Builder()
//                    .templatePath(Config.SERVICE_TEMPLATE)
//                    .filePath(Config.SERVICE_PATH)
//                    .formatNameFunction(tableInfo -> String.format(FILE_NAME_SERVICE, tableInfo.getEntityName()))
//                    .fileName(StringPool.DOT_JAVA)
//                    .enableFileOverride()
//                    .build());
//        }

//        //自定义ServiceImpl
//        if (ObjectUtil.isNotEmpty(Config.SERVICE_IMPL_PATH) && ObjectUtil.isNotEmpty(Config.SERVICE_IMPL_TEMPLATE)) {
//            customFiles.add(new CustomFile.Builder()
//                    .templatePath(Config.SERVICE_IMPL_TEMPLATE)
//                    .filePath(Config.SERVICE_IMPL_PATH)
//                    .formatNameFunction(tableInfo -> String.format(FILE_NAME_SERVICE_IMPL, tableInfo.getEntityName()))
//                    .fileName(StringPool.DOT_JAVA)
//                    .enableFileOverride()
//                    .build());
//        }

//        //自定义Mapper
//        if (ObjectUtil.isNotEmpty(Config.MAPPER_PATH) && ObjectUtil.isNotEmpty(Config.MAPPER_TEMPLATE)) {
//            customFiles.add(new CustomFile.Builder()
//                    .templatePath(Config.MAPPER_TEMPLATE)
//                    .filePath(Config.MAPPER_PATH)
//                    .formatNameFunction(tableInfo -> String.format(FILE_NAME_MAPPER, tableInfo.getEntityName()))
//                    .fileName(StringPool.DOT_JAVA)
//                    .enableFileOverride()
//                    .build());
//        }

        // 自定义xml配置
        if (ObjectUtil.isNotEmpty(Config.XML_PATH) && ObjectUtil.isNotEmpty(Config.XML_TEMPLATE)) {
            customFiles.add(new CustomFile.Builder()
                    .templatePath(Config.XML_TEMPLATE)
                    .filePath(Config.XML_PATH)
                    .formatNameFunction(TableInfo::getXmlName)
                    .fileName(StringPool.DOT_XML)
                    .enableFileOverride()
                    .build());
        }

        // 自定义输出VO配置
        if (ObjectUtil.isNotEmpty(Config.VO_TEMPLATE) && ObjectUtil.isNotEmpty(Config.VO_PATH)) {
            customFiles.add(new CustomFile.Builder()
                    .templatePath(Config.VO_TEMPLATE)
                    .filePath(Config.VO_PATH)
                    .formatNameFunction(tableInfo -> String.format(FILE_NAME_VO, tableInfo.getEntityName()))
                    .fileName(StringPool.DOT_JAVA)
                    .enableFileOverride()
                    .build());
        }

        // 自定义输出查询DTO配置
        if (ObjectUtil.isNotEmpty(Config.DTO_QUERY_TEMPLATE) && ObjectUtil.isNotEmpty(Config.DTO_PATH)) {
            customFiles.add(new CustomFile.Builder()
                    .templatePath(Config.DTO_QUERY_TEMPLATE)
                    .filePath(Config.DTO_PATH)
                    .formatNameFunction(tableInfo -> String.format(FILE_NAME_DTO_QUERY, tableInfo.getEntityName()))
                    .fileName(StringPool.DOT_JAVA)
                    .enableFileOverride()
                    .build());
        }

        // 自定义输出新增DTO配置
        if (ObjectUtil.isNotEmpty(Config.DTO_CREATE_TEMPLATE) && ObjectUtil.isNotEmpty(Config.DTO_PATH)) {
            customFiles.add(new CustomFile.Builder()
                    .templatePath(Config.DTO_CREATE_TEMPLATE)
                    .filePath(Config.DTO_PATH)
                    .formatNameFunction(tableInfo -> String.format(FILE_NAME_DTO_CREATE, tableInfo.getEntityName()))
                    .fileName(StringPool.DOT_JAVA)
                    .enableFileOverride()
                    .build());
        }

        // 自定义输出编辑DTO配置
        if (ObjectUtil.isNotEmpty(Config.DTO_UPDATE_TEMPLATE) && ObjectUtil.isNotEmpty(Config.DTO_PATH)) {
            customFiles.add(new CustomFile.Builder()
                    .templatePath(Config.DTO_UPDATE_TEMPLATE)
                    .filePath(Config.DTO_PATH)
                    .formatNameFunction(tableInfo -> String.format(FILE_NAME_DTO_UPDATE, tableInfo.getEntityName()))
                    .fileName(StringPool.DOT_JAVA)
                    .enableFileOverride()
                    .build());
        }

        // 自定义输出DTO配置
        if (ObjectUtil.isNotEmpty(Config.DTO_TEMPLATE) && ObjectUtil.isNotEmpty(Config.DTO_PATH)) {
            customFiles.add(new CustomFile.Builder()
                    .templatePath(Config.DTO_TEMPLATE)
                    .filePath(Config.DTO_PATH)
                    .formatNameFunction(tableInfo -> String.format(FILE_NAME_DTO, tableInfo.getEntityName()))
                    .fileName(StringPool.DOT_JAVA)
                    .enableFileOverride()
                    .build());
        }

        // 自定义输出查询FORM配置
        if (ObjectUtil.isNotEmpty(Config.FORM_QUERY_TEMPLATE) && ObjectUtil.isNotEmpty(Config.FORM_PATH)) {
            customFiles.add(new CustomFile.Builder()
                    .templatePath(Config.FORM_QUERY_TEMPLATE)
                    .filePath(Config.FORM_PATH)
                    .formatNameFunction(tableInfo -> String.format(FILE_NAME_FORM_QUERY, tableInfo.getEntityName()))
                    .fileName(StringPool.DOT_JAVA)
                    .enableFileOverride()
                    .build());
        }

        // 自定义输出新增Form配置
        if (ObjectUtil.isNotEmpty(Config.FORM_CREATE_TEMPLATE) && ObjectUtil.isNotEmpty(Config.FORM_PATH)) {
            customFiles.add(new CustomFile.Builder()
                    .templatePath(Config.FORM_CREATE_TEMPLATE)
                    .filePath(Config.FORM_PATH)
                    .formatNameFunction(tableInfo -> String.format(FILE_NAME_FORM_CREATE, tableInfo.getEntityName()))
                    .fileName(StringPool.DOT_JAVA)
                    .enableFileOverride()
                    .build());
        }

        // 自定义输出编辑Form配置
        if (ObjectUtil.isNotEmpty(Config.FORM_UPDATE_TEMPLATE) && ObjectUtil.isNotEmpty(Config.FORM_PATH)) {
            customFiles.add(new CustomFile.Builder()
                    .templatePath(Config.FORM_UPDATE_TEMPLATE)
                    .filePath(Config.FORM_PATH)
                    .formatNameFunction(tableInfo -> String.format(FILE_NAME_FORM_UPDATE, tableInfo.getEntityName()))
                    .fileName(StringPool.DOT_JAVA)
                    .enableFileOverride()
                    .build());
        }

        // 自定义输出convertMapper配置
        if (ObjectUtil.isNotEmpty(Config.CONVERT_MAPPER_TEMPLATE) && ObjectUtil.isNotEmpty(Config.CONVERT_PATH)) {
            customFiles.add(new CustomFile.Builder()
                    .templatePath(Config.CONVERT_MAPPER_TEMPLATE)
                    .filePath(Config.CONVERT_PATH)
                    .formatNameFunction(tableInfo -> String.format(FILE_NAME_CONVERT_MAPPER, tableInfo.getEntityName()))
                    .fileName(StringPool.DOT_JAVA)
                    .enableFileOverride()
                    .build());
        }

        return new InjectionConfig.Builder()
                .customMap(customMap)
                .customFile(customFiles)
                .build();
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

        DataSourceConfig dataSourceConfig = dataSourceConfig(dbType, dbUrl, username, password, driver);

        GlobalConfig globalConfig = globalConfig();

        StrategyConfig strategyConfig = strategyConfig(tablePrefixes, tableNames, fieldPrefixes);

        PackageConfig packageConfig = packageConfig();

        Map<String, Object> customMap = new HashMap<>(10);
        customMap.put("VOPackage", Config.PACKAGE_NAME_VO);
        customMap.put("ConvertMapperPackage", Config.PACKAGE_NAME_CONVERT);
        customMap.put("DTOPackage", Config.PACKAGE_NAME_DTO);
        customMap.put("FormPackage", Config.PACKAGE_NAME_FORM);
        customMap.put("DBType", dbType.getDb());
        customMap.put("FieldlogicDeleteName", Config.FIELD_LOGIC_DELETE_NAME);
        customMap.put("FieldlogicDeleteDefaultValue", Config.FIELD_LOGIC_DELETE_DEFAULT_VALUE);
        customMap.put("BasePack", Config.BASE_PACK);
        customMap.put("BaseCoreUtilPack", Config.BASE_CORE_UTIL_PACK);
        customMap.put("BaseCoreEntityPack", Config.BASE_CORE_ENTITY_PACK);
        customMap.put("MybatisUtilPack", Config.MYBATIS_UTIL_PACK);
        customMap.put("VoNotReturnField", Config.VO_NOT_RETURN_FIELD);
        customMap.put("QueryFormSupport", StrUtil.isNotEmpty(Config.FORM_QUERY_TEMPLATE));
        customMap.put("AddFormSupport", StrUtil.isNotEmpty(Config.FORM_CREATE_TEMPLATE));
        customMap.put("UpdateFormSupport", StrUtil.isNotEmpty(Config.FORM_UPDATE_TEMPLATE));

        InjectionConfig injectionConfig = injectionConfig(customMap);

        new AutoGenerator(dataSourceConfig)
                .global(globalConfig)
                .strategy(strategyConfig)
                .packageInfo(packageConfig)
                .injection(injectionConfig)
                .execute(new VelocityTemplateEngine());
    }


}