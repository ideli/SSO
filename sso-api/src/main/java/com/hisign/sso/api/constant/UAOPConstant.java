package com.hisign.sso.api.constant;
/**
 * @Title:
 *   统一认证平台常量定义
 * @description:
 * 
 * @author lnj 
 */
public class UAOPConstant {
	
	//key-统一认证中心唯一标识
	public static final String KEY_SYSTEMID = "systemId"; 
	
	//统一认证中心唯一标识
	public static final String SYSTEMID = "UAOP"; 
	
	/**
	 * 实体状态
	 * @author Administrator
	 *
	 */
	public static final class STATUS{ 
		public static final int NORMAL = 0;  //正常
		public static final int DELETED = 1; //已删除
	}
	
	/**
	 * 组织机构类型
	 * @author Administrator
	 *
	 */
	public static final class OrganiseType{ 
		public static final int NORMAL = 0;  //普通类型
		public static final int SUBMISSION = 1; //委托机构
		public static final int INDENTIFY = 2; //鉴定机构
	}

	/**
	 * 用户类型
	 * @author Administrator
	 */
	public static final class UserType{ 
		public static final int NORMAL = 0;  //委托用户
		public static final int EXTSUBMISSION = 1; //外部委托用户
		public static final int INDENTIFY = 2; //鉴定用户
	}
	
	/**
	 * 角色类型
	 * @author Administrator
	 */
	public static final class RoleType{ 
		public static final int NORMAL = 0;  //普通角色
		public static final int BUILDIN = 1; //内置角色
	}
	
	/**
	 * 授权点类型
	 * @author Administrator
	 */
	public static final class ResourceType{ 
		public static final int MENU = 0;  //菜单授权点
		public static final int SERVICE = 1; //角色授权点
	}
}
