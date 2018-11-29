package com.admin.constant;

/**
 * 
 * @author fengxiang
 * @date 2018-10-23
 */
public class Constant {
	
	//权限类型
	public final static String PERMISSION_ADMIN = "ADMIN"; //管理员权限
	public final static String PERMISSION_READ = "GET"; //查看权限
	public final static String PERMISSION_POST = "POST"; //编辑权限
//	管理员权限代替删除权限   删除权限暂时没用
//	public final static String PERMISSION_DELETE = "DELETE"; //删除权限
	
	//供应商常量管理
	public final static String CONSTANT_TYPE_SETTLEMENT_METHOD = "settlementMethod"; //结算方式
	public final static String CONSTANT_TYPE_ADD_SERVICE = "addService";//附加服务
	public final static String CONSTANT_TYPE_TRADE_TYPE = "tradeType";//行业
	public final static String CONSTANT_TYPE_BRAND = "brand";//品牌
	
	//基础模块  新建供应商 ，供应商列表，附件列表，新建项目，进行中项目（更新项目），已归档项目，已删除项目，员工管理，权限管理，供应商管理
	public final static String MODULE_SUPPLIER_CREATE = "/supplierCreate";
	public final static String MODULE_SUPPLIER_LIST = "/supplierList";
	public final static String MODULE_ADD_FILES = "/addFileList";
	public final static String MODULE_PROJECT_CREATE = "/projectCreate";
	public final static String MODULE_PROJECT_UPDATE = "/projectUpdate";
	public final static String MODULE_FINISH_PROJECTS = "/finishProjects";
	public final static String MODULE_DELETE_PROJECTS = "/deleteProjects";
	public final static String MODULE_USER_MANAGER = "/userManager";
	public final static String MODULE_PERMISSION_MANAGER = "/permissionManager";
	public final static String MODULE_CONSTANT_MANAGER = "/constantManager";
	
	//两种基础资源权限
	public final static String RESOURCE_SUPPLIER = "/supplier";
	public final static String RESOURCE_PROJECT = "/project";
	
	//redis key 前缀
	public final static String REDIS_PREFIX_COOKIE = "office_cookie_";
	public final static String REDIS_PREFIX_LOGINMSG = "office_loginmsg_";
	
	//三种附件类型
	public final static String FILE_TYPE_PROJECT = "1";
	public final static String FILE_TYPE_SUPPLIER = "2";
	public final static String FILE_TYPE_SUPPLIER_OTHER = "3";
	
	//两种附件状态
	public final static String FILE_STATE_NORMAL = "1";
	public final static String FILE_STATE_DELETE = "2";
	
	//三种项目状态
	public final static String PROJECT_STATE_NEW = "1";
	public final static String PROJECT_STATE_FINISH = "2";
	public final static String PROJECT_STATE_DELETE = "3";
	
	//两种节点状态
	public final static String NODE_STATE_NORMAL = "1";
	public final static String NODE_STATE_DELETE = "2";
}
