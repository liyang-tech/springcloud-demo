if(app == undefined)
$('.selectpicker').selectpicker({
	'noneSelectedText': '<span style="color:#ccc>请选择岗位</span>'
});
	var app = angular.module('myApp',['ngCookies', 'smartTable.table']);
app.controller('userController', ['$scope', '$http', 'common',  function($scope, $http, common){
	
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
	//用户类型主数据列表查询回调
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
	//用户类型主数据列表查询
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
	
/***=====华丽丽分割线==========用户信息列表查询 start========华丽丽分割线=====***/
	//监听用户列表查询是否翻页
	$scope.$watch('stepNumber + size', function() {
		$scope.searchUser(); //查询用户信息列表
	});
	$scope.maxSize = 5; // 显示的页数
	// 是否显示跳页
	$scope.goPageFlag = false;
	// 用户查询每页条数
	$scope.stepSize = 10;
	// 用户查询当前页
	$scope.stepNumber = 1;
	// 用户查询数据总条数
	$scope.stepTotalElements = 1;
	// 用户查询总页数
	$scope.stepTotalPages = Math.ceil($scope.stepTotalElements / $scope.stepSize);
	
	//用户条件列表查询回调
	$scope.userList = [];
	$scope.searchUserCB = function(data){
		$scope.userList = data.data;
		$scope.stepTotalElements = data.page.totalElements;
		$scope.stepTotalPages = data.page.totalPages;
	}
	
	//用户条件列表查询
	$scope.searchUser = function(){
		//查询条件
		$scope.name = $scope.name || '';
		$scope.loginName = $scope.loginName || '';
		$scope.mobile = $scope.mobile || '';
		var searchUrl = '';
		var pageUrl = "?number=" + $scope.stepNumber + "&size=" + $scope.stepSize + "&name=" + $scope.name + 
		"&loginName=" + $scope.loginName + "&mobile=" + $scope.mobile;//query入参的拼接
		searchUrl = interfaces.userSearch + pageUrl;//请求路径的拼接
		//请求方法
		common.getData('GET',
				searchUrl,
				null,
				null,
				$scope.searchUserCB);
	};
	
	
/***=====华丽丽分割线==========用户信息列表查询 end========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  用户新增 start  ========华丽丽分割线=====***/
	//保存用户信息成功回调
	$scope.addUserCB = function(data){
		if(data.code == 0){
			$("#add_Modal").modal('hide');//模态框隐藏
			$scope.searchUser();//查询列表
			alert('用户新增成功');
			$scope.addUserVo = {};//用户保存vo信息清空
			$("#addSelectRoles").selectpicker("deselectAll");
		}else{
			alertER('<span style="color:red;">保存失败</span></br>' + data.msg);
		}
	}
	//保存用户信息失败回调
	$scope.addUserERCB = function(data){
		alertER('<span style="color:red;">保存失败</span></br>' + data.msg);
	}
	//保存按钮，保存用户信息，触发事件
	$scope.addUserVo = {}
	$scope.addUser = function (){
		if(!$scope.addUserVo.name){
			alertER("姓名不能为空");
			return;
		}
		if(!$scope.addUserVo.loginName){
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
		}
		$scope.addUserVo.roleCds = $scope.addUserVo.roleCds.join(",");
		//保存用户信息
		
		debugger
		common.getData('POST',
				interfaces.saveUser,
				$scope.addUserVo,
				null,
				$scope.addUserCB,
				$scope.addUserERCB);
	}
	//取消新增
	$scope.cancelUser = function (){
		$scope.addUserVo = {};
		$("#addSelectRoles").selectpicker("deselectAll");
	}
/***=====华丽丽分割线==========  用户新增 end  ========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  用户单记录查询 satrt  ========华丽丽分割线=====***/
	var custCode = '';
	$scope.alterUserVo = {}
	//用户单记录查询成功回调
	$scope.searchOneUserCB = function (data){
		
		$scope.alterUserVo = data.data;
		$scope.alterUserVo.password = "******";
		var roleLists = data.data.roleList;
		var len = roleLists.length;
		var optionAddString = "";
		for(var i = 0; i < len; i++){
			if(roleLists[i].checkFlag){
				optionAddString += '<option  selected="' + roleLists[i].checkFlag + '"  value="'  + roleLists[i].code + '">' + roleLists[i].name +'</option>';
			}else{
				optionAddString += '<option value="' + roleLists[i].code + '">' + roleLists[i].name +'</option>';
			}
		}
		$("#editSelectRoles").html(optionAddString);
		$("#editSelectRoles").selectpicker("refresh");
	}
	//用户单记录查询失败回调
	$scope.searchOneUserERCB = function (data){
		alertER(data.msg);
	}
	//用户单记录查询
	$scope.searchOneUser = function(code) {
		var url = interfaces.searchOneUser.replace('{code}', code);
		common.getData('GET',
				url,
				null,
				null,
				$scope.searchOneUserCB,
				$scope.searchOneUserERCB);
	}
/***=====华丽丽分割线==========  用户单记录查询 end  ========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  用户编辑 start  ========华丽丽分割线=====***/
	//用户编辑成功回调
	$scope.editUserCB = function(data){
		if(data.code == 0){
			$scope.searchUser();//查询列表
			$("#edit_Modal").modal('hide');//模态框隐藏
			alert('用户编辑成功');
		}else{
			alertER('<span style="color:red;">用户编辑失败</span></br>' + data.msg);
		}
	}
	//用户编辑失败回调
	$scope.editUserERCB = function(data){
		alertER('<span style="color:red;">用户编辑失败</span></br>' + data.msg);
	}
	//用户编辑
	$scope.editUser = function() {
		if(!$scope.alterUserVo.name){
			alertER("姓名不能为空");
			return;
		}
		if(!$scope.alterUserVo.loginName){
			alertER("登录名不能为空");
			return;
		}
		if(!$scope.alterUserVo.mobile){
			alertER("手机号不能为空");
			return;
		}
		$scope.alterUserVo.password = '';
		$scope.alterUserVo.roleCds = $scope.alterUserVo.roleCds.join(",");
		console.log(JSON.stringify($scope.alterUserVo));
		var url = interfaces.editUser.replace('{code}', $scope.alterUserVo.code);
		common.getData('PUT',
				url,
				$scope.alterUserVo,
				null,
				$scope.editUserCB,
				$scope.editUserERCB);
	}
/***=====华丽丽分割线==========  用户编辑 end  ========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  用户删除 start  ========华丽丽分割线=====***/
	//用户删除成功回调
	$scope.delUserCB = function(data){
		if(data.code == 0){
			$scope.searchUser();//查询列表
			alert('删除用户成功');
		}else{
			alertER('<span style="color:red;">用户删除失败</span></br>' + data.msg);
		}
	}
	//用户删除失败回调
	$scope.delUserERCB = function(data){
		alertER('<span style="color:red;">用户删除失败</span></br>' + data.msg);
	}
	//用户删除
	$scope.delUser = function (code){
		var url = interfaces.delUser.replace('{code}', code);
		confirm('是否确定删除?', function(flag){
			if(flag){
				common.getData('DELETE',
						url,
						null,
						null,
						$scope.delUserCB,
						$scope.delUserERCB);
			}else{
				return false;
			}
		});
	}
/***=====华丽丽分割线==========  用户删除 end  ========华丽丽分割线=====***/
	
	
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
	$scope.roleList = [];
	$scope.searchRolesCB = function(data){
		$scope.roleList = data.data;
		var roleList = data.data;
		var optionAddString = '';
		var len = roleList.length;
		for(var i = 0; i < len; i++){
			optionAddString += '<option value="' + roleList[i].code + '">' + roleList[i].name +'</option>';
		}
		$("#addSelectRoles").html(optionAddString);
		$("#addSelectRoles").selectpicker("refresh");
	}
	//查询所有角色列表
	common.getData(
		'GET',
		interfaces.searchRoles,
		null,
		null,
		$scope.searchRolesCB
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
