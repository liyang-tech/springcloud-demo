if(app == undefined)
$('.selectpicker').selectpicker({
	'noneSelectedText': '<span style="color:#ccc>请选择权限菜单</span>'
});
	var app = angular.module('myApp',['ngCookies', 'smartTable.table']);
app.controller('roleController', ['$scope', '$http', 'common',  function($scope, $http, common){
	
	//页面适配
	$(function (){
		var warp = $(window).height();
		var sec = $(window).height();
		$(".Warpper").css({
			'height': sec + 'px',
			'overflow-y': 'scroll'
		});
	})
/***=====华丽丽分割线==========默认加载 主数据(性别) 列表查询 start========华丽丽分割线=====***/
	//性别主数据列表查询回调
	/*$scope.searchSexsCB = function(data){
		$scope.mstSexList = data.data;
	}
	//婚姻状态主数据列表查询回调
	$scope.searchMarriedsCB = function(data){
		$scope.mstMarryList = data.data;
	}
	//证件类型主数据列表查询回调
	$scope.searchCerTypeCdsCB = function(data){
		$scope.mstCertificationTypeCdList = data.data;
	}
	//角色类型主数据列表查询回调
	$scope.searchCustTypeCdsCB = function(data){
		$scope.mstCustTypeCdList = data.data;
	}*/
	//性别主数据列表查询
	/*function searchSexs (){
		common.getData('GET',
				interfaces.searchSexs,
				null,
				null,
				$scope.searchSexsCB);
	};*/
	//婚姻状态主数据列表查询
	/*function searchMarrieds (){
		common.getData('GET',
				interfaces.searchMarrieds,
				null,
				null,
				$scope.searchMarriedsCB);
	};*/
	//证件类型主数据列表查询
	/*function searchCerTypeCds (){
		common.getData('GET',
				interfaces.searchCerTypeCds,
				null,
				null,
				$scope.searchCerTypeCdsCB);
	};*/
	//角色类型主数据列表查询
	/*function searchCustTypeCds (){
		common.getData('GET',
				interfaces.searchCustTypeCds,
				null,
				null,
				$scope.searchCustTypeCdsCB);
	};*/
	/*searchSexs();//性别主数据列表查询
	searchMarrieds();//婚姻状态主数据列表查询
	searchCerTypeCds();//证件类型主数据列表查询
	searchCustTypeCds();*///证件类型主数据列表查询
/***=====华丽丽分割线==========默认加载 主数据(性别) 列表查询 end========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========角色信息列表查询 start========华丽丽分割线=====***/
	//监听角色列表查询是否翻页
	$scope.$watch('stepNumber + size', function() {
		$scope.searchRole(); //查询角色信息列表
	});
	$scope.maxSize = 5; // 显示的页数
	// 是否显示跳页
	$scope.goPageFlag = false;
	// 角色查询每页条数
	$scope.stepSize = 10;
	// 角色查询当前页
	$scope.stepNumber = 1;
	// 角色查询数据总条数
	$scope.stepTotalElements = 1;
	// 角色查询总页数
	$scope.stepTotalPages = Math.ceil($scope.stepTotalElements / $scope.stepSize);
	
	//角色条件列表查询回调
	$scope.permissionList = [];
	$scope.searchRoleCB = function(data){
		$scope.roleList = data.data;
		$scope.stepTotalElements = data.page.totalElements;
		$scope.stepTotalPages = data.page.totalPages;
	}
	
	//角色条件列表查询
	$scope.searchRole = function(){
		//查询条件
		$scope.name = $scope.name || '';
		/*$scope.loginName = $scope.loginName || '';
		$scope.mobile = $scope.mobile || '';*/
		var searchUrl = '';
		var pageUrl = "?number=" + $scope.stepNumber + "&size=" + $scope.stepSize + "&name=" + $scope.name;/* + 
		"&loginName=" + $scope.loginName + "&mobile=" + $scope.mobile*///query入参的拼接
		searchUrl = interfaces.searchRoles + pageUrl;//请求路径的拼接
		//请求方法
		common.getData('GET',
				searchUrl,
				null,
				null,
				$scope.searchRoleCB);
	};
	
	
/***=====华丽丽分割线==========角色信息列表查询 end========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  角色新增 start  ========华丽丽分割线=====***/
	//保存角色信息成功回调
	$scope.addRoleCB = function(data){
		if(data.code == 0){
			$("#add_Modal").modal('hide');//模态框隐藏
			$scope.searchRole();//查询列表
			alert('角色新增成功');
			$scope.addRoleVo = {};//角色保存vo信息清空
			$("#addSelectPermissions").selectpicker("deselectAll");
		}else{
			alertER('<span style="color:red;">保存失败</span></br>' + data.msg);
		}
	}
	//保存角色信息失败回调
	$scope.addRoleERCB = function(data){
		alertER('<span style="color:red;">保存失败</span></br>' + data.msg);
	}
	//保存按钮，保存角色信息，触发事件
	$scope.addRoleVo = {}
	$scope.addRole = function (){
		if(!$scope.addRoleVo.name){
			alertER("名称不能为空");
			return;
		}
		/*if(!$scope.addRoleVo.loginName){
			alertER("登录名不能为空");
			return;
		}
		if(!$scope.addUserVo.mobile){
			alertER("手机号不能为空");
			return;
		}
		if(!$scope.addUserVo.password){
			alertER("密码不能为空");
			return;
		}*/
		$scope.addRoleVo.permissionCds = $scope.addRoleVo.permissionCds.join(",");
		//保存角色信息
		common.getData('POST',
				interfaces.saveRole,
				$scope.addRoleVo,
				null,
				$scope.addRoleCB,
				$scope.addRoleERCB);
	}
	//取消新增
	$scope.cancelRole = function (){
		$scope.addRoleVo = {};
		$("#addSelectPermissions").selectpicker("deselectAll");
	}
/***=====华丽丽分割线==========  角色新增 end  ========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  角色单记录查询 satrt  ========华丽丽分割线=====***/
	var custCode = '';
	$scope.alterRoleVo = {}
	//角色单记录查询成功回调
	$scope.searchOneRoleCB = function (data){
		$scope.alterRoleVo = data.data;
		var permissionLists = data.data.permissionList;
		var len = permissionLists.length;
		var optionAddString = "";
		for(var i = 0; i < len; i++){
			if(permissionLists[i].checkFlag){
				optionAddString += '<option  selected="' + permissionLists[i].checkFlag + '"  value="'  + permissionLists[i].code + '">' + permissionLists[i].name +'</option>';
			}else{
				optionAddString += '<option value="' + permissionLists[i].code + '">' + permissionLists[i].name +'</option>';
			}
		}
		$("#editSelectPermissions").html(optionAddString);
		$("#editSelectPermissions").selectpicker("refresh");
	}
	//角色单记录查询失败回调
	$scope.searchOneRoleERCB = function (data){
		alertER(data.msg);
	}
	//角色单记录查询
	$scope.searchOneRole = function(code) {
		var url = interfaces.searchOneRole.replace('{code}', code);
		common.getData('GET',
				url,
				null,
				null,
				$scope.searchOneRoleCB,
				$scope.searchOneRoleERCB);
	}
/***=====华丽丽分割线==========  角色单记录查询 end  ========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  角色编辑 start  ========华丽丽分割线=====***/
	//角色编辑成功回调
	$scope.editRoleCB = function(data){
		if(data.code == 0){
			$scope.searchRole();//查询列表
			$("#edit_Modal").modal('hide');//模态框隐藏
			alert('角色编辑成功');
		}else{
			alertER('<span style="color:red;">角色编辑失败</span></br>' + data.msg);
		}
	}
	//角色编辑失败回调
	$scope.editRoleERCB = function(data){
		alertER('<span style="color:red;">角色编辑失败</span></br>' + data.msg);
	}
	//角色编辑
	$scope.editRole = function() {
		if(!$scope.alterRoleVo.name){
			alertER("名称不能为空");
			return;
		}
		/*if(!$scope.alterRoleVo.loginName){
			alertER("登录名不能为空");
			return;
		}
		if(!$scope.alterRoleVo.mobile){
			alertER("手机号不能为空");
			return;
		}*/
		$scope.alterRoleVo.permissionCds = $scope.alterRoleVo.permissionCds.join(",");
		console.log(JSON.stringify($scope.alterRoleVo));
		var url = interfaces.editRole.replace('{code}', $scope.alterRoleVo.code);
		common.getData('PUT',
				url,
				$scope.alterRoleVo,
				null,
				$scope.editRoleCB,
				$scope.editRoleERCB);
	}
/***=====华丽丽分割线==========  角色编辑 end  ========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  角色删除 start  ========华丽丽分割线=====***/
	//角色删除成功回调
	$scope.delRoleCB = function(data){
		if(data.code == 0){
			$scope.searchRole();//查询列表
			alert('删除角色成功');
		}else{
			alertER('<span style="color:red;">角色删除失败</span></br>' + data.msg);
		}
	}
	//角色删除失败回调
	$scope.delRoleERCB = function(data){
		alertER('<span style="color:red;">角色删除失败</span></br>' + data.msg);
	}
	//角色删除
	$scope.delRole = function (code){
		var url = interfaces.delRole.replace('{code}', code);
		confirm('是否确定删除?', function(flag){
			if(flag){
				common.getData('DELETE',
						url,
						null,
						null,
						$scope.delRoleCB,
						$scope.delRoleERCB);
			}else{
				return false;
			}
		});
	}
/***=====华丽丽分割线==========  角色删除 end  ========华丽丽分割线=====***/
	
	
	//日期控件
	$scope.showAddDate = function(targetid, certification, certificationName){
		var that = this;
		jeDate({
			dateCell: "#" + targetid,
			format: "YYYY-MM-DD",
			isinitVal: false,
			isTime: true,
			isClear: true,
			minDate: "1990-01-01",
			maxDate: "2099-12-31",
			zIndex: 100000,
			okfun: function(value) {
				if(certification) {
					certification[certificationName] = dateShaper(value, "YYYY-MM-DD ");
				} else {
					that.applicationInfo[targetid] = dateShaper(value, "YYYY-MM-DD ")
				}
			}
		})
	}
	//日期控件
/*======= 华丽丽的分割线 =====查询所有角色列表==== start ==== 华丽丽的分割线 ====*/
	//查询所有角色列表 回调函数
	$scope.permissionList = [];
	$scope.searchPermissionCB = function(data){
		$scope.permissionList = data.data;
		var permissionList = data.data;
		var optionAddString = '';
		var len = permissionList.length;
		for(var i = 0; i < len; i++){
			optionAddString += '<option value="' + permissionList[i].code + '">' + permissionList[i].name +'</option>';
		}
		$("#addSelectPermissions").html(optionAddString);
		$("#addSelectPermissions").selectpicker("refresh");
	}
	//查询所有角色列表
	common.getData(
		'GET',
		interfaces.searchPermissions,
		null,
		null,
		$scope.searchPermissionCB
	);
	/*//角色checked处理
	var orderTypeCdArr = [];
	$scope.orderSelect = function($event, code){
		$event.stopPropagation();
		var checkbox = $event.target;
		if(checkbox.checked){
			orderTypeCdArr.push(code);
		}else{
			var codeIndex = orderTypeCdArr.indexOf(code);
			if(codeIndex > -1){
				orderTypeCdArr.splice(codeIndex, 1);
			}
		}
	}*/
/*======= 华丽丽的分割线 =====查询所有角色列表==== end ==== 华丽丽的分割线 ====*/
	
			
	
}]);
