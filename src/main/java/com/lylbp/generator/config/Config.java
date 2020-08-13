package com.lylbp.generator.config;


public class Config {
    /**
     * 作者
     */
    public static final String AUTHOR = "weiwenbin";

    /////////////////////////////////////////////路径相关///////////////////////////////////////////////////////////
    /**
     * controller,service,mapper,serviceImpl路径
     */
    public static final String OUT_OUT_DIR = "/Library/WebServer/java_project/backend/dar-modules/dar-biz/src/main/java/";

    /**
     * xml文件路径
     */
    public static final String XML_PATH = "/Library/WebServer/java_project/backend/dar-modules/dar-biz/src/main/resources/mapper/dispatch";

    /**
     * dto路径
     */
    public static final String DTO_PATH = "/Library/WebServer/java_project/backend/dar-modules/dar-biz/src/main/java/com/dar/app/biz/dispatch/dto";

    /**
     * qo路径
     */
    public static final String QO_PATH = "/Library/WebServer/java_project/backend/dar-modules/dar-biz/src/main/java/com/dar/app/biz/dispatch/qo";

    /**
     * vo路径
     */
    public static final String VO_PATH = "/Library/WebServer/java_project/backend/dar-modules/dar-biz/src/main/java/com/dar/app/biz/dispatch/vo";

    /////////////////////////////////////////////包名相关///////////////////////////////////////////////////////////
    /**
     * controller包名
     */
    public static final String PACKAGE_NAME_CONTROLLER = "com.dar.app.biz.dispatch.controller";

    /**
     * 包名：entity
     */
    public static final String PACKAGE_NAME_ENTITY = "com.dar.app.biz.dispatch.entity";


    /**
     * 包名：mapper
     */
    public static final String PACKAGE_NAME_MAPPER = "com.dar.app.biz.dispatch.mapper";

    /**
     * 包名：service
     */
    public static final String PACKAGE_NAME_SERVICE = "com.dar.app.biz.dispatch.service";

    /**
     * 包名：service.impl
     */
    public static final String PACKAGE_NAME_SERVICE_IMPL = "com.dar.app.biz.dispatch.service.impl";

    /**
     * 包名：dto
     */
    public static final String PACKAGE_NAME_DTO = "com.dar.app.biz.dispatch.dto";


    /**
     * 包名：qo
     */
    public static final String PACKAGE_NAME_QO = "com.dar.app.biz.dispatch.qo";

    /**
     * 包名：vo
     */
    public static final String PACKAGE_NAME_VO = "com.dar.app.biz.dispatch.vo";

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
    public static final String CONTROLLER_TEMPLATE = "/templates/generate/myController.java.vm";

    /**
     * service模版地址
     */
    public static final String SERVICE_TEMPLATE = "/templates/generate/myService.java.vm";

    /**
     * serviceImpl模版地址
     */
    public static final String SERVICE_IMPL_TEMPLATE = "/templates/generate/myServiceImpl.java.vm";

    /**
     * mapper模版地址
     */
    public static final String MAPPER_TEMPLATE = "/templates/generate/myMapper.java.vm";


    /**
     * xml模版地址
     */
    public static final String XML_TEMPLATE = "/templates/generate/myMapper.xml.vm";


    /**
     * VO模版地址
     */
    public static final String VO_TEMPLATE = "/templates/generate/entity.vo.java.vm";


    /**
     * QO模版地址
     */
    public static final String QO_TEMPLATE = "/templates/generate/entity.qo.java.vm";


    /**
     * DTO模版地址
     */
    public static final String DTO_TEMPLATE = "/templates/generate/entity.dto.java.vm";
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
}
