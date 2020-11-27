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
    public static final String OUT_OUT_DIR = "/Library/WebServer/java_project/czywxt/src/main/java/";

    /**
     * xml文件输出路径
     */
    public static final String XML_PATH = "/Library/WebServer/java_project/czywxt/src/main/resources/mapper";

    /**
     * dto输出路径
     */
    public static final String DTO_PATH = "/Library/WebServer/java_project/czywxt/src/main/java/com/dar/project/dto";

    /**
     * qo输出路径
     */
    public static final String QO_PATH = "/Library/WebServer/java_project/czywxt/src/main/java/com/dar/project/qo";

    /**
     * vo输出路径
     */
    public static final String VO_PATH = "/Library/WebServer/java_project/czywxt/src/main/java/com/dar/project/vo";

    /////////////////////////////////////////////包名相关///////////////////////////////////////////////////////////
    /**
     * controller包名
     */
    public static final String PACKAGE_NAME_CONTROLLER = "com.dar.project.controller";

    /**
     * 包名：entity
     */
    public static final String PACKAGE_NAME_ENTITY = "com.dar.project.entity";


    /**
     * 包名：mapper
     */
    public static final String PACKAGE_NAME_MAPPER = "com.dar.project.mapper";

    /**
     * 包名：service
     */
    public static final String PACKAGE_NAME_SERVICE = "com.dar.project.service";

    /**
     * 包名：service.impl
     */
    public static final String PACKAGE_NAME_SERVICE_IMPL = "com.dar.project.service.impl";

    /**
     * 包名：dto
     */
    public static final String PACKAGE_NAME_DTO = "com.dar.project.dto";


    /**
     * 包名：qo
     */
    public static final String PACKAGE_NAME_QO = "com.dar.project.qo";

    /**
     * 包名：vo
     */
    public static final String PACKAGE_NAME_VO = "com.dar.project.vo";

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
     * VO模版地址
     */
    public static final String VO_TEMPLATE = "/templates/lylbp/entity.vo.java.vm";


    /**
     * QO模版地址
     */
    public static final String QO_TEMPLATE = "/templates/lylbp/entity.qo.java.vm";


    /**
     * DTO模版地址
     */
    public static final String DTO_TEMPLATE = "/templates/lylbp/entity.dto.java.vm";
    /////////////////////////////////////////////其他配置///////////////////////////////////////////////////////////
    /**
     * controller公共父类[空串无效]
     */
    public static final String SUPER_CONTROLLER_CLASS = "";

    /**
     * 逻辑删除字段[空串无效]
     */
    public static final String FIELD_LOGIC_DELETE_NAME = "";

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
     * entity主键类型
     */
    public static final IdType ENTITY_ID_TYPE = IdType.ASSIGN_UUID;
}
