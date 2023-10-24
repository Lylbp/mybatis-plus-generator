package com.lylbp.generator.config;


import com.baomidou.mybatisplus.annotation.IdType;

public class Config {
    /**
     * 作者
     */
    public static final String AUTHOR = "weiwenbin";

    /////////////////////////////////////////////路径相关///////////////////////////////////////////////////////////
    /**
     * controller,service,mapper,serviceImpl输出路径
     */
    public static final String OUT_OUT_DIR = "/home/alex/code/java/lylbp/java_model_simple/src/main/java/";

    /**
     * controller文件输出路径
     */
    public static final String CONTROLLER_PATH = "/home/alex/code/java/lylbp/java_model_simple/src/main/java/com/lylbp/project/controller";

    /**
     * entity输出路径
     */
    public static final String ENTITY_PATH = "/home/alex/code/java/lylbp/java_model_simple/src/main/java/com/lylbp/project/entity";

    /**
     * service文件输出路径
     */
    public static final String SERVICE_PATH = "/home/alex/code/java/lylbp/java_model_simple/src/main/java/com/lylbp/project/service";

    /**
     * serviceImpl文件输出路径
     */
    public static final String SERVICE_IMPL_PATH = "/home/alex/code/java/lylbp/java_model_simple/src/main/java/com/lylbp/project/service/impl";

    /**
     * Mapper文件输出路径
     */
    public static final String MAPPER_PATH = "/home/alex/code/java/lylbp/java_model_simple/src/main/java/com/lylbp/project/mapper";

    /**
     * xml文件输出路径
     */
    public static final String XML_PATH = "/home/alex/code/java/lylbp/java_model_simple/src/main/resources/mapper";

    /**
     * dto输出路径
     */
    public static final String DTO_PATH = "/home/alex/code/java/lylbp/java_model_simple/src/main/java/com/lylbp/project/dto";

    /**
     * vo输出路径
     */
    public static final String VO_PATH = "/home/alex/code/java/lylbp/java_model_simple/src/main/java/com/lylbp/project/vo";

    /**
     * convert输出路径
     */
    public static final String CONVERT_PATH = "/home/alex/code/java/lylbp/java_model_simple/src/main/java/com/lylbp/project/convert";

    /////////////////////////////////////////////包名相关///////////////////////////////////////////////////////////
    public static final String BASE_PACK = "com.lylbp";

    public static final String BASE_CORE_UTIL_PACK = "com.lylbp.common.utils.*";
    public static final String BASE_CORE_ENTITY_PACK = "com.lylbp.common.entity.*";
    public static final String MYBATIS_UTIL_PACK = "com.lylbp.common.utils.MybatisUtil";

    /**
     * controller包名
     */
    public static final String PACKAGE_NAME_CONTROLLER = "com.lylbp.project.controller";

    /**
     * 包名：entity
     */
    public static final String PACKAGE_NAME_ENTITY = "com.lylbp.project.entity";


    /**
     * 包名：mapper
     */
    public static final String PACKAGE_NAME_MAPPER = "com.lylbp.project.mapper";

    /**
     * 包名：service
     */
    public static final String PACKAGE_NAME_SERVICE = "com.lylbp.project.service";

    /**
     * 包名：service.impl
     */
    public static final String PACKAGE_NAME_SERVICE_IMPL = "com.lylbp.project.service.impl";

    /**
     * 包名：dto
     */
    public static final String PACKAGE_NAME_DTO = "com.lylbp.project.dto";


    /**
     * 包名：vo
     */
    public static final String PACKAGE_NAME_VO = "com.lylbp.project.vo";

    /**
     * 包名：convert
     */
    public static final String PACKAGE_NAME_CONVERT = "com.lylbp.project.convert";

    /////////////////////////////////////////////模版文件配置///////////////////////////////////////////////////////////
    /**
     * controller模版地址
     */
    public static final String CONTROLLER_TEMPLATE = "/templates/lylbp/myController.java.vm";


    /**
     * service模版地址
     */
    public static final String SERVICE_TEMPLATE = "/templates/lylbp/myService.java.vm";

    /**
     * serviceImpl模版地址
     */
    public static final String SERVICE_IMPL_TEMPLATE = "/templates/lylbp/myServiceImpl.java.vm";

    /**
     * mapper模版地址
     */
    public static final String MAPPER_TEMPLATE = "/templates/lylbp/myMapper.java.vm";


    /**
     * xml模版地址
     */
    public static final String XML_TEMPLATE = "/templates/lylbp/myMapper.xml.vm";

    /**
     * entity模版地址
     */
    public static final String ENTITY_TEMPLATE = "/templates/lylbp/entity.java.vm";

    /**
     * VO模版地址
     */
    public static final String VO_TEMPLATE = "/templates/lylbp/entity.vo.java.vm";


    /**
     * DTO查询模版地址
     */
    public static final String DTO_QUERY_TEMPLATE = "/templates/lylbp/entity.dtoQuery.java.vm";

    /**
     * DTO新增模版地址
     */
    public static final String DTO_CREATE_TEMPLATE = "/templates/lylbp/entity.dtoCreate.java.vm";

    /**
     * DTO新增模版地址
     */
    public static final String DTO_UPDATE_TEMPLATE = "/templates/lylbp/entity.dtoUpdate.java.vm";


    /**
     * convertMapper模版地址
     */
    public static final String CONVERT_MAPPER_TEMPLATE = "/templates/lylbp/entity.convertMapper.java.vm";
    /////////////////////////////////////////////其他配置///////////////////////////////////////////////////////////
    /**
     * controller公共父类[空串无效]
     */
    public static final String SUPER_CONTROLLER_CLASS = "";

    /**
     * 逻辑删除字段[空串无效]
     */
    public static final String FIELD_LOGIC_DELETE_NAME = "is_valid";

    /**
     * 逻辑删除字段默认值
     */
    public static final String FIELD_LOGIC_DELETE_DEFAULT_VALUE = "\"1\"";

    /**
     * 添加自动填充[空串无效]
     */
    public static final String CREATE_FILL = "create_time,create_by,is_valid";

    /**
     * 编辑自动填充[空串无效]
     */
    public static final String UPDATE_FILL = "update_by,update_time";

    /**
     * 是否支持Swagger
     */
    public static final Boolean SWAGGER_SUPPORT = true;


    /**
     * 是否覆盖已有文件
     */
    public static final Boolean FILE_OVERRIDE = true;

    /**
     * entity是否支持lombok
     */
    public static final Boolean ENTITY_LOMBOK_SUPPORT = true;

    /**
     * 是否为RestController
     */
    public static final Boolean IS_REST_CONTROLLER = true;

    /**
     * entity属性上添加表字段映射
     */
    public static final Boolean ENTITY_TABLE_FIELD_ANNOTATION_ENABLE = true;

    /**
     * entity是否生成序列化id
     */
    public static final Boolean ENTITY_SERIAL_VERSION_UID = true;

    /**
     * Boolean 类型字段是否移除 is 前缀（默认 false）
     */
    public static final Boolean ENTITY_BOOLEAN_COLUMN_REMOVE_IS_PREFIX = false;

    /**
     * entity主键类型
     */
    public static final IdType ENTITY_ID_TYPE = IdType.ASSIGN_ID;

    /**
     * VO中不生成的字段[空串无效]
     */
    public static final String VO_NOT_RETURN_FIELD = "is_valid,create_by,update_by";
}
