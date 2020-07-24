if(app == undefined)
	var app = angular.module('myApp',['ngCookies', 'smartTable.table']);
app.controller('masterController', ['$scope', '$http', 'common',  function($scope, $http, common){
	
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
	//数据对象列表查询
	$scope.searchObjectsCB = function(data){
		$scope.objectList = data.data;
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
	//数据对象列表查询
	function searchObjects(){
		common.getData('GET',
				interfaces.searchObjects,
				null,
				null,
				$scope.searchObjectsCB);
	}
	searchSexs();//性别主数据列表查询
	searchMarrieds();//婚姻状态主数据列表查询
	searchCerTypeCds();//证件类型主数据列表查询
	searchCustTypeCds();//证件类型主数据列表查询
	searchObjects();//数据对象列表查询
/***=====华丽丽分割线==========默认加载 主数据(性别) 列表查询 end========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========主数据信息列表查询 start========华丽丽分割线=====***/
	$scope.maxSize = 5; // 显示的页数
	// 是否显示跳页
	$scope.goPageFlag = false;
	// 主数据查询每页条数
	$scope.stepSize = 10;
	// 主数据查询当前页
	$scope.stepNumber = 1;
	// 主数据查询数据总条数
	$scope.stepTotalElements = 1;
	// 主数据查询总页数
	$scope.stepTotalPages = Math.ceil($scope.stepTotalElements / $scope.stepSize);
	
	//主数据条件列表查询回调
	$scope.masterList = [];
	$scope.searchMasterCB = function(data){
		$scope.masterList = data.data;
		$scope.stepTotalElements = data.page.totalElements;
		$scope.stepTotalPages = data.page.totalPages;
	}
	
	//主数据条件列表查询
	$scope.searchMaster = function(){
		//查询条件
		$scope.objectCd = $scope.objectCd || '';
		$scope.code = $scope.code || '';
		$scope.keyword = $scope.keyword || '';
		var searchUrl = '';
		var pageUrl = "?number=" + $scope.stepNumber + "&size=" + $scope.stepSize + "&objectCd=" + $scope.objectCd + 
		"&code=" + $scope.code + "&keyword=" + $scope.keyword;//query入参的拼接
		searchUrl = interfaces.masterSearch + pageUrl;//请求路径的拼接
		//请求方法
		common.getData('GET',
				searchUrl,
				null,
				null,
				$scope.searchMasterCB);
	};
	
	//监听主数据列表查询是否翻页
	$scope.$watch('stepNumber + size', function() {
		$scope.searchMaster(); //查询主数据信息列表
	});
/***=====华丽丽分割线==========主数据信息列表查询 end========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  主数据新增 start  ========华丽丽分割线=====***/
	//保存主数据信息成功回调
	$scope.addMasterCB = function(data){
		if(data.code == 0){
			$("#add_Modal").modal('hide');//模态框隐藏
			$scope.searchMaster();//查询列表
			alert('主数据新增成功');
			//$("#addBirthday").val("");//生日清空
			$scope.addMasterVo = {};//主数据保存vo信息清空
		}else{
			alertER('<span style="color:red;">保存失败</span></br>' + data.msg);
		}
	}
	//保存主数据信息失败回调
	$scope.addMasterERCB = function(data){
		alertER('<span style="color:red;">保存失败</span></br>' + data.msg);
	}
	//保存按钮，保存主数据信息，触发事件
	$scope.addMasterVo = {}
	$scope.addMaster = function (){
		if(!$scope.addMasterVo.code){
			alertER("编码不能为空");
			return;
		}
		if(!$scope.addMasterVo.name){
			alertER("名称不能为空");
			return;
		}
		if(!$scope.addMasterVo.objectCd){
			alertER("请选择数据对象");
			return;
		}
		//保存主数据信息
		common.getData('POST',
				interfaces.saveMaster,
				$scope.addMasterVo,
				null,
				$scope.addMasterCB,
				$scope.addMasterERCB);
	}
	//取消新增
	$scope.cancelMaster = function (){
		//$("#addBirthday").val("");
		$scope.addMasterVo = {};
	}
/***=====华丽丽分割线==========  主数据新增 end  ========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  主数据单记录查询 satrt  ========华丽丽分割线=====***/
	var custCode = '';
	$scope.alterMstVo = {}
	//主数据单记录查询成功回调
	$scope.searchOneMstCB = function (data){
		console.log(data.data);
		$scope.alterMstVo = data.data;
		//$("#editBirthday").val($scope.alterMstVo.birthday);
	}
	//主数据单记录查询失败回调
	$scope.searchOneMstERCB = function (data){
		alertER(data.msg);
	}
	//主数据单记录查询
	$scope.searchOneMst = function(objectCd, code) {
		var url = interfaces.searchOneMst.replace('{objectCd}', objectCd).replace('{code}', code);
		common.getData('GET',
				url,
				null,
				null,
				$scope.searchOneMstCB,
				$scope.searchOneMstERCB);
	}
/***=====华丽丽分割线==========  主数据单记录查询 end  ========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  主数据编辑 start  ========华丽丽分割线=====***/
	//主数据编辑成功回调
	$scope.editMasterCB = function(data){
		if(data.code == 0){
			$scope.searchMaster();//查询列表
			$("#edit_Modal").modal('hide');//模态框隐藏
			alert('主数据编辑成功');
		}else{
			alertER('<span style="color:red;">主数据编辑失败</span></br>' + data.msg);
		}
	}
	//主数据编辑失败回调
	$scope.editMasterERCB = function(data){
		alertER('<span style="color:red;">主数据编辑失败</span></br>' + data.msg);
	}
	//主数据编辑
	$scope.editMaster = function() {
		if(!$scope.alterMstVo.name){
			alertER("名称不能为空");
			return;
		}
		console.log(JSON.stringify($scope.alterMstVo))
		var url = interfaces.editMaster.replace('{objectCd}', $scope.alterMstVo.objectCd).replace('{code}', $scope.alterMstVo.code);
		common.getData('PUT',
				url,
				$scope.alterMstVo,
				null,
				$scope.editMasterCB,
				$scope.editMasterERCB);
	}
/***=====华丽丽分割线==========  主数据编辑 end  ========华丽丽分割线=====***/
	
/***=====华丽丽分割线==========  主数据删除 start  ========华丽丽分割线=====***/
	//主数据删除成功回调
	$scope.delMasterCB = function(data){
		if(data.code == 0){
			$scope.searchMaster();//查询列表
			alert('删除主数据成功');
		}else{
			alertER('<span style="color:red;">主数据删除失败</span></br>' + data.msg);
		}
	}
	//主数据删除失败回调
	$scope.delMasterERCB = function(data){
		alertER('<span style="color:red;">主数据删除失败</span></br>' + data.msg);
	}
	//主数据删除
	$scope.delMaster = function (objectCd, code){
		var url = interfaces.delMaster.replace('{objectCd}', objectCd).replace('{code}', code);
		confirm('是否确定删除?', function(flag){
			if(flag){
				common.getData('DELETE',
						url,
						null,
						null,
						$scope.delMasterCB,
						$scope.delMasterERCB);
			}else{
				return false;
			}
		});
	}
/***=====华丽丽分割线==========  主数据删除 end  ========华丽丽分割线=====***/
	
	
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
	
	//新增主数据照片的上传
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
