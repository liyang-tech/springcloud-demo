console.log('加载','interfacesConfig.js');
//控制地址
//var besUrl = "http://" + window.location.host;
var besUrl = "http://localhost:10000";

//请求地址包装对象
var interfaces = {
	/**
	 * 客户条件列表查询
	 */
	customerSearch : besUrl + "/tkrsbes/cust/v1/customers",
	/**
	 * 客户新增
	 */
	saveCustomer : besUrl + "/tkrsbes/cust/v1/customer",
	/**
	 * 客户单记录查询
	 */
	searchOneCust : besUrl + "/tkrsbes/cust/v1/customer/{code}",
	/**
	 * 客户编辑
	 */
	editCustomer : besUrl + "/tkrsbes/cust/v1/customer/{code}",
	/**
	 * 客户删除
	 */
	delCustomer : besUrl + "/tkrsbes/cust/v1/customer/{code}",
	/**
	 * 根据对象编码获取性别主数据列表查询
	 */
	searchSexs : besUrl + "/tkrsbes/master/1/masters",
	/**
	 * 根据对象编码获取证件类型主数据列表查询
	 */
	searchCerTypeCds : besUrl + "/tkrsbes/master/2/masters",
	/**
	 * 根据对象编码获取客户类型主数据列表查询
	 */
	searchCustTypeCds : besUrl + "/tkrsbes/master/3/masters",
	/**
	 * 根据对象编码获取婚姻状态主数据列表查询
	 */
	searchMarrieds : besUrl + "/tkrsbes/master/4/masters",
	/**
	 * 用户条件列表查询
	 */
	userSearch : besUrl + "/tkrsbes/users",
	/**
	 * 用户新增
	 */
	saveUser : besUrl + "/tkrsbes/user",
	/**
	 * 用户单记录查询
	 */
	searchOneUser : besUrl + "/tkrsbes/user/{code}",
	/**
	 * 用户编辑
	 */
	editUser : besUrl + "/tkrsbes/user/{code}",
	/**
	 * 用户删除
	 */
	delUser : besUrl + "/tkrsbes/user/{code}",
	/**
	 * 角色条件列表查询
	 */
	searchRoles : besUrl + "/tkrsbes/roles",
	/**
	 * 角色新增
	 */
	saveRole : besUrl + "/tkrsbes/role",
	/**
	 * 角色单记录查询
	 */
	searchOneRole : besUrl + "/tkrsbes/role/{code}",
	/**
	 * 角色编辑
	 */
	editRole : besUrl + "/tkrsbes/role/{code}",
	/**
	 * 角色删除
	 */
	delRole : besUrl + "/tkrsbes/role/{code}",
	/**
	 * 权限条件列表查询
	 */
	searchPermissions : besUrl + "/tkrsbes/permissions",
	/**
	 * 权限菜单新增
	 */
	savePermission : besUrl + "/tkrsbes/permission",
	/**
	 * 权限菜单单记录查询
	 */
	searchOnePermission : besUrl + "/tkrsbes/permission/{code}",
	/**
	 * 权限菜单编辑
	 */
	editPermission : besUrl + "/tkrsbes/permission/{code}",
	/**
	 * 权限菜单删除
	 */
	delPermission : besUrl + "/tkrsbes/permission/{code}",
	/**
	 * 用户登录
	 */
	userLogin : besUrl + "/tkrsbes/user/login",
	/**
	 * 根据用户编码获取所拥有的权限
	 */
	userPermissions: besUrl + "/tkrsbes/user/{userCd}/permissions",
	/**
	 * 主数据列表查询
	 */
	masterSearch: besUrl + "/tkrsbes/master/masters",
	/**
	 * 主数据单记录查询
	 */
	searchOneMst: besUrl + "/tkrsbes/{objectCd}/master/{code}",
	/**
	 * 主数据新增
	 */
	saveMaster: besUrl + "/tkrsbes/master",
	/*
	 * 主数据删除
	 */
	delMaster: besUrl + "/tkrsbes/{objectCd}/master/{code}",
	/**
	 * 主数据编辑
	 */
	editMaster: besUrl + "/tkrsbes/{objectCd}/master/{code}",
	
	/**
	 * 数据对象列表查询
	 */
	searchObjects: besUrl + "/tkrsbes/object/objects",
};