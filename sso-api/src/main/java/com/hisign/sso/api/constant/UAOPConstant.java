package com.hisign.sso.api.constant;
/**
 * @Title:
 *   统一认证平台常量定义
 * @description:
 * 
 * @author lnj 
 */
public class UAOPConstant {
	
	/**
	 * key-统一认证中心唯一标识
	 */
	public static final String KEY_SYSTEMID = "systemId"; 
	
	/**
	 * 统一认证中心唯一标识
	 */
	public static final String SYSTEMID = "UAOP"; 
	
	/**
	 * 用户开放标记
	 */
	public static final String USER_OPEN = "1";

	/**
	 * 客户端系统功能JSP目录
	 */
	public static final String JSP_SYS_DIR = "WEB-INF/jsp/sys/";

	/**
	 * 分页起始行
	 */
	public static final String SQL_PAGE_START_KEY = "start";

	/**
	 * 每页数据条数
	 */
	public static final String SQL_PAGE_SIZE_KEY = "size";

	/**
	 * 系统字符集编码
	 */
	public static final String CHARSET_NAME = "UTF-8";
	
	/**
	 * 实体状态
	 *
	 */
	public static final class STATUS{ 
		public static final int NORMAL = 0;  //正常
		public static final int DELETED = 1; //已删除
	}
	
	/**
	 * 组织机构类型
	 *
	 */
	public static final class OrganiseType{ 
		public static final int NORMAL = 0;  //普通类型
		public static final int SUBMISSION = 1; //委托机构
		public static final int INDENTIFY = 2; //鉴定机构
	}

	/**
	 * 用户类型
	 */
	public static final class UserType{ 
		public static final int NORMAL = 0;  //委托用户
		public static final int EXTSUBMISSION = 1; //外部委托用户
		public static final int INDENTIFY = 2; //鉴定用户
		
		public static final int XCKY = 10; //现勘用户
		
		public static final int YTHPT = 20; //一体化平台用户
	}
	
	/**
	 * 角色类型
	 */
	public static final class RoleType{ 
		public static final int NORMAL = 0;  //普通角色
		public static final int BUILDIN = 1; //内置角色
	}
	
	/**
	 * 授权点类型
	 */
	public static final class ResourceType{ 
		public static final int MENU = 0;  //菜单授权点
		public static final int SERVICE = 1; //角色授权点
	}
	
	/**
	 * 是否启用状态
	 */
	public static final class ActiveStatus{ 
		public static final int UNACTIVE = 0;  //不启用
		public static final int ACTIVE = 1; //启用
	}
	
	/**
	 * 菜单类型
	 */
	public static final class MenuType{ 
		public static final int MENU = 0;  //菜单 
		public static final int BUTTON = 1; //按钮
	}
}
