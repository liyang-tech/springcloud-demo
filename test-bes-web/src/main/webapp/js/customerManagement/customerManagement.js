if(app == undefined)
	var app = angular.module('myApp',['ngCookies', 'smartTable.table']);
app.controller('customerController', ['$scope', '$http', 'common',  function($scope, $http, common){
	
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
	$scope.searchSexsCB = function(data){
		console.log(data.data);
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
	//客户类型主数据列表查询回调
	$scope.searchCustTypeCdsCB = function(data){
		$scope.mstCustTypeCdList = data.data;
	}
	//性别主数据列表查询
	function searchSexs (){
		common.getData('GET',
				interfaces.searchSexs,
				null,
				null,
				$scope.searchSexsCB);
	};
	//婚姻状态主数据列表查询
	function searchMarrieds (){
		common.getData('GET',
				interfaces.searchMarrieds,
				null,
				null,
				$scope.searchMarriedsCB);
	};
	//证件类型主数据列表查询
	function searchCerTypeCds (){
		common.getData('GET',
				interfaces.searchCerTypeCds,
				null,
				null,
				$scope.searchCerTypeCdsCB);
	};
	//客户类型主数据列表查询
	function searchCustTypeCds (){
		common.getData('GET',
				interfaces.searchCustTypeCds,
				null,
				null,
				$scope.searchCustTypeCdsCB);
	};
	searchSexs();//性别主数据列表查询
	searchMarrieds();//婚姻状态主数据列表查询
	searchCerTypeCds();//证件类型主数据列表查询
	searchCustTypeCds();//证件类型主数据列表查询
/***=====华丽丽分割线==========默认加载 主数据(性别) 列表查询 end========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========客户信息列表查询 start========华丽丽分割线=====***/
	$scope.maxSize = 5; // 显示的页数
	// 是否显示跳页
	$scope.goPageFlag = false;
	// 客户查询每页条数
	$scope.stepSize = 10;
	// 客户查询当前页
	$scope.stepNumber = 1;
	// 客户查询数据总条数
	$scope.stepTotalElements = 1;
	// 客户查询总页数
	$scope.stepTotalPages = Math.ceil($scope.stepTotalElements / $scope.stepSize);
	
	//客户条件列表查询回调
	$scope.customerList = [];
	$scope.searchCustomerCB = function(data){
		$scope.customerList = data.data;
		$scope.stepTotalElements = data.page.totalElements;
		$scope.stepTotalPages = data.page.totalPages;
	}
	
	//客户条件列表查询
	$scope.searchCustomer = function(){
		//查询条件
		$scope.name = $scope.name || '';
		$scope.sexCd = $scope.sexCd || '';
		$scope.mobile = $scope.mobile || '';
		var searchUrl = '';
		var pageUrl = "?number=" + $scope.stepNumber + "&size=" + $scope.stepSize + "&name=" + $scope.name + 
		"&sexCd=" + $scope.sexCd + "&mobile=" + $scope.mobile;//query入参的拼接
		searchUrl = interfaces.customerSearch + pageUrl;//请求路径的拼接
		//请求方法
		common.getData('GET',
				searchUrl,
				null,
				null,
				$scope.searchCustomerCB);
	};
	
	//监听客户列表查询是否翻页
	$scope.$watch('stepNumber + size', function() {
		$scope.searchCustomer(); //查询客户信息列表
	});
/***=====华丽丽分割线==========客户信息列表查询 end========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  客户新增 start  ========华丽丽分割线=====***/
	//保存客户信息成功回调
	$scope.addCustomerCB = function(data){
		if(data.code == 0){
			$("#add_Modal").modal('hide');//模态框隐藏
			$scope.searchCustomer();//查询列表
			alert('客户新增成功');
			$("#addBirthday").val("");//生日清空
			$scope.addCustomerVo = {};//客户保存vo信息清空
		}else{
			alertER('<span style="color:red;">保存失败</span></br>' + data.msg);
		}
	}
	//保存客户信息失败回调
	$scope.addCustomerERCB = function(data){
		alertER('<span style="color:red;">保存失败</span></br>' + data.msg);
	}
	//保存按钮，保存客户信息，触发事件
	$scope.addCustomerVo = {}
	$scope.addCustomer = function (){
		if(!$scope.addCustomerVo.name){
			alertER("姓名不能为空");
			return;
		}
		if(!$scope.addCustomerVo.email){
			alertER("邮箱不能为空");
			return;
		}
		if(!$scope.addCustomerVo.mobile){
			alertER("联系电话不能为空");
			return;
		}
		$scope.addCustomerVo.birthday = $("#addBirthday").val() ? ($("#addBirthday").val() == "" ? null : $("#addBirthday").val()) : null;
		//保存客户信息
		common.getData('POST',
				interfaces.saveCustomer,
				$scope.addCustomerVo,
				null,
				$scope.addCustomerCB,
				$scope.addCustomerERCB);
	}
	//取消新增
	$scope.cancelCustomer = function (){
		$("#addBirthday").val("");
		$scope.addCustomerVo = {};
	}
/***=====华丽丽分割线==========  客户新增 end  ========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  客户单记录查询 satrt  ========华丽丽分割线=====***/
	var custCode = '';
	$scope.alterCustmerVo = {}
	//客户单记录查询成功回调
	$scope.searchOneCustCB = function (data){
		console.log(data.data);
		$scope.alterCustmerVo = data.data;
		$("#editBirthday").val($scope.alterCustmerVo.birthday);
	}
	//客户单记录查询失败回调
	$scope.searchOneCustERCB = function (data){
		alertER(data.msg);
	}
	//客户单记录查询
	$scope.searchOneCust = function(code) {
		custCode = code;
		var url = interfaces.searchOneCust.replace('{code}', custCode);
		common.getData('GET',
				url,
				null,
				null,
				$scope.searchOneCustCB,
				$scope.searchOneCustERCB);
	}
/***=====华丽丽分割线==========  客户单记录查询 end  ========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  客户编辑 start  ========华丽丽分割线=====***/
	//客户编辑成功回调
	$scope.editCustomerCB = function(data){
		if(data.code == 0){
			$scope.searchCustomer();//查询列表
			$("#edit_Modal").modal('hide');//模态框隐藏
			alert('客户编辑成功');
		}else{
			alertER('<span style="color:red;">客户编辑失败</span></br>' + data.msg);
		}
	}
	//客户编辑失败回调
	$scope.editCustomerERCB = function(data){
		alertER('<span style="color:red;">客户编辑失败</span></br>' + data.msg);
	}
	//客户编辑
	$scope.editCustomer = function() {
		if(!$scope.alterCustmerVo.name){
			alertER("姓名不能为空");
			return;
		}
		if(!$scope.alterCustmerVo.email){
			alertER("邮箱不能为空");
			return;
		}
		if(!$scope.alterCustmerVo.mobile){
			alertER("联系电话不能为空");
			return;
		}
		$scope.alterCustmerVo.birthday = $("#editBirthday").val() ? ($("#editBirthday").val() == "" ? null : $("#editBirthday").val()) : null;
		console.log(JSON.stringify($scope.alterCustmerVo))
		var url = interfaces.editCustomer.replace('{code}', custCode);
		common.getData('PUT',
				url,
				$scope.alterCustmerVo,
				null,
				$scope.editCustomerCB,
				$scope.editCustomerERCB);
	}
/***=====华丽丽分割线==========  客户编辑 end  ========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  客户删除 start  ========华丽丽分割线=====***/
	//客户删除成功回调
	$scope.delCustomerCB = function(data){
		if(data.code == 0){
			$scope.searchCustomer();//查询列表
			alert('删除客户成功');
		}else{
			alertER('<span style="color:red;">客户删除失败</span></br>' + data.msg);
		}
	}
	//客户删除失败回调
	$scope.delCustomerERCB = function(data){
		alertER('<span style="color:red;">客户删除失败</span></br>' + data.msg);
	}
	//客户删除
	$scope.delCustomer = function (code){
		var url = interfaces.delCustomer.replace('{code}', code);
		confirm('是否确定删除?', function(flag){
			if(flag){
				common.getData('DELETE',
						url,
						null,
						null,
						$scope.delCustomerCB,
						$scope.delCustomerERCB);
			}else{
				return false;
			}
		});
	}
/***=====华丽丽分割线==========  客户删除 end  ========华丽丽分割线=====***/
	
	
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
	
	//新增客户照片的上传
	$scope.setImg = function (obj){
		
		/*}
	function setImg(obj){*/
		var file = obj.files[0];
		var picPath = file.name;
		var type = picPath.substring(picPath.lastIndexOf(".") + 1, picPath.length).toLowerCase();
		if(type == ""){
			return false;
		}else if(type != "bmp" && type != "png" && type != "gif" && type != "jpg" && type != "jpeg"){
			alert("图片格式不对");
			return false;
		}
		if(file.size/1024/1024 > 2){
			alert("上传的图片文件不能超过2M");
		}
		var reader = new FileReader();
		reader.readAsDataURL(file);
		reader.onload = function(e){
			photoBef = this.result;
			photoBef = photoBef.substring(photoBef.indexOf(",") + 1, photoBef.length);
			$("#imgdiv img").attr("src", this.result);
			$("#photo").val(photoBef);
		}
		$("#popupContact").modal("hide");
	}
	
	var x = $("#add_Modal").offset();
	$("#popupContact").modal().css({
		width: 1500,
		top: x.top - window.pageYOffset + 5,
	});
			
	
}]);
