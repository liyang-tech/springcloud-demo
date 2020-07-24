if(app == undefined)
$('.selectpicker').selectpicker({
	'noneSelectedText': '<span style="color:#ccc>请选择权限菜单</span>'
});
	var app = angular.module('myApp',['ngCookies', 'smartTable.table']);
app.controller('permissionController', ['$scope', '$http', 'common',  function($scope, $http, common){
	
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
	//权限菜单类型主数据列表查询回调
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
	//权限菜单类型主数据列表查询
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
	
/***=====华丽丽分割线==========权限菜单信息列表查询 start========华丽丽分割线=====***/
	//监听权限菜单列表查询是否翻页
	$scope.$watch('stepNumber + size', function() {
		$scope.searchPermission(); //查询权限菜单信息列表
	});
	$scope.maxSize = 5; // 显示的页数
	// 是否显示跳页
	$scope.goPageFlag = false;
	// 权限菜单查询每页条数
	$scope.stepSize = 10;
	// 权限菜单查询当前页
	$scope.stepNumber = 1;
	// 权限菜单查询数据总条数
	$scope.stepTotalElements = 1;
	// 权限菜单查询总页数
	$scope.stepTotalPages = Math.ceil($scope.stepTotalElements / $scope.stepSize);
	
	//权限菜单条件列表查询回调
	$scope.permissionList = [];
	$scope.searchPermissionCB = function(data){
		$scope.permissionList = data.data;
		$scope.stepTotalElements = data.page.totalElements;
		$scope.stepTotalPages = data.page.totalPages;
	}
	
	//权限菜单条件列表查询
	$scope.searchPermission = function(){
		//查询条件
		$scope.name = $scope.name || '';
		/*$scope.loginName = $scope.loginName || '';
		$scope.mobile = $scope.mobile || '';*/
		var searchUrl = '';
		var pageUrl = "?number=" + $scope.stepNumber + "&size=" + $scope.stepSize + "&name=" + $scope.name;/* + 
		"&loginName=" + $scope.loginName + "&mobile=" + $scope.mobile*///query入参的拼接
		searchUrl = interfaces.searchPermissions + pageUrl;//请求路径的拼接
		//请求方法
		common.getData('GET',
				searchUrl,
				null,
				null,
				$scope.searchPermissionCB);
	};
	
	
/***=====华丽丽分割线==========权限菜单信息列表查询 end========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  权限菜单新增 start  ========华丽丽分割线=====***/
	//保存权限菜单信息成功回调
	$scope.addPermissionCB = function(data){
		if(data.code == 0){
			$("#add_Modal").modal('hide');//模态框隐藏
			$scope.searchPermission();//查询列表
			alert('权限菜单新增成功');
			$scope.addPermissionVo = {};//权限菜单保存vo信息清空
			/*$("#addSelectPermissions").selectpicker("deselectAll");*/
		}else{
			alertER('<span style="color:red;">保存失败</span></br>' + data.msg);
		}
	}
	//保存权限菜单信息失败回调
	$scope.addPermissionERCB = function(data){
		alertER('<span style="color:red;">保存失败</span></br>' + data.msg);
	}
	//保存按钮，保存权限菜单信息，触发事件
	$scope.addPermissionVo = {}
	$scope.addPermission = function (){
		if(!$scope.addPermissionVo.name){
			alertER("名称不能为空");
			return;
		}
		if(!$scope.addPermissionVo.uri){
			alertER("路径不能为空");
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
		/*$scope.addRoleVo.permissionCds = $scope.addRoleVo.permissionCds.join(",");*/
		//保存权限菜单信息
		common.getData('POST',
				interfaces.savePermission,
				$scope.addPermissionVo,
				null,
				$scope.addPermissionCB,
				$scope.addPermissionERCB);
	}
	//取消新增
	$scope.cancelPermission = function (){
		$scope.addPermissionVo = {};
		/*$("#addSelectPermissions").selectpicker("deselectAll");*/
	}
/***=====华丽丽分割线==========  权限菜单新增 end  ========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  权限菜单单记录查询 satrt  ========华丽丽分割线=====***/
	var custCode = '';
	$scope.alterPermissionVo = {}
	//权限菜单单记录查询成功回调
	$scope.searchOnePermissionCB = function (data){
		$scope.alterPermissionVo = data.data;
		console.log(JSON.stringify($scope.alterPermissionVo));
		/*var permissionLists = data.data.permissionList;
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
		$("#editSelectPermissions").selectpicker("refresh");*/
	}
	//权限菜单单记录查询失败回调
	$scope.searchOnePermissionERCB = function (data){
		alertER(data.msg);
	}
	//权限菜单单记录查询
	$scope.searchOnePermission = function(code) {
		var url = interfaces.searchOnePermission.replace('{code}', code);
		common.getData('GET',
				url,
				null,
				null,
				$scope.searchOnePermissionCB,
				$scope.searchOnePermissionERCB);
	}
/***=====华丽丽分割线==========  权限菜单单记录查询 end  ========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  权限菜单编辑 start  ========华丽丽分割线=====***/
	//权限菜单编辑成功回调
	$scope.editPermissionCB = function(data){
		if(data.code == 0){
			$scope.searchPermission();//查询列表
			$("#edit_Modal").modal('hide');//模态框隐藏
			alert('权限菜单编辑成功');
		}else{
			alertER('<span style="color:red;">权限菜单编辑失败</span></br>' + data.msg);
		}
	}
	//权限菜单编辑失败回调
	$scope.editPermissionERCB = function(data){
		alertER('<span style="color:red;">权限菜单编辑失败</span></br>' + data.msg);
	}
	//权限菜单编辑
	$scope.editPermission = function() {
		if(!$scope.alterPermissionVo.name){
			alertER("名称不能为空");
			return;
		}
		if(!$scope.alterPermissionVo.uri){
			alertER("路径不能为空");
			return;
		}
		if(!$scope.alterPermissionVo.enName){
			alertER("英文名称不能为空");
			return;
		}
		/*if(!$scope.alterPermissionVo.loginName){
			alertER("登录名不能为空");
			return;
		}
		if(!$scope.alterPermissionVo.mobile){
			alertER("手机号不能为空");
			return;
		}*/
		/*$scope.alterPermissionVo.permissionCds = $scope.alterPermissionVo.permissionCds.join(",");
		console.log(JSON.stringify($scope.alterPermissionVo));*/
		var url = interfaces.editPermission.replace('{code}', $scope.alterPermissionVo.code);
		common.getData('PUT',
				url,
				$scope.alterPermissionVo,
				null,
				$scope.editPermissionCB,
				$scope.editPermissionERCB);
	}
/***=====华丽丽分割线==========  权限菜单编辑 end  ========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  权限菜单删除 start  ========华丽丽分割线=====***/
	//权限菜单删除成功回调
	$scope.delPermissionCB = function(data){
		if(data.code == 0){
			$scope.searchPermission();//查询列表
			alert('删除权限菜单成功');
		}else{
			alertER('<span style="color:red;">权限菜单删除失败</span></br>' + data.msg);
		}
	}
	//权限菜单删除失败回调
	$scope.delPermissionERCB = function(data){
		alertER('<span style="color:red;">权限菜单删除失败</span></br>' + data.msg);
	}
	//权限菜单删除
	$scope.delPermission = function (code){
		var url = interfaces.delPermission.replace('{code}', code);
		confirm('是否确定删除?', function(flag){
			if(flag){
				common.getData('DELETE',
						url,
						null,
						null,
						$scope.delPermissionCB,
						$scope.delPermissionERCB);
			}else{
				return false;
			}
		});
	}
/***=====华丽丽分割线==========  权限菜单删除 end  ========华丽丽分割线=====***/
	
	
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
/*======= 华丽丽的分割线 =====查询所有权限菜单列表==== start ==== 华丽丽的分割线 ====*/
	//查询所有权限菜单列表 回调函数
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
	//查询所有权限菜单列表
	common.getData(
		'GET',
		interfaces.searchPermissions,
		null,
		null,
		$scope.searchPermissionCB
	);
	/*//权限菜单checked处理
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
/*======= 华丽丽的分割线 =====查询所有权限菜单列表==== end ==== 华丽丽的分割线 ====*/
	
			
	
}]);
