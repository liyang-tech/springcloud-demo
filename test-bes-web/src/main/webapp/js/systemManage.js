var makeUrl = function(url, data) {
	if(typeof url !== 'string') console.error('URL(param1) must be a string');
	if(typeof data !== 'object') console.error('Data(param2) must be a object');
	for(var key in data) {
		url = url.replace('{' + key + '}', data[key]);
	}
	return url;
};

//屏幕适配
$(function() {
	var fir = $(window).height(),
		sec = $(window).height() - 37,
		thi = sec - 98,
		fou = sec - 28,
		fiv = sec - 25 - 110,
		six = sec - 38;
	$(".content-left").css({
		'height': sec + 'px',
		'overflow': 'hidden'
	});
	$("#con-right").css({
		'height': sec + 'px',
		'overflow': 'hidden'
	});

	$(".navDiv").css({
		'height': six + 'px',
		'overflow-y': 'auto',
		'overflow-x': 'hidden'
	});
	//我的待办点击效果
	$(".myFunction").on('click', function() {
		if($(this).children(".myAgent").css('display') == 'none') {
			$(this).children(".myAgent").show().end().siblings(".myFunction").children(".myAgent").hide();
		} else {
			$(this).children(".myAgent").hide();
		}
	});
	//导航点击效果
	$(".ulTitle").on("click", function() {
		if($(this).next("ul").css('display') == 'none') {
			$(this).children(".navmiddleImg").hide();
			$(this).children(".navmiddleImg1").show();
			$(this).next("ul").slideDown(400);
			$(this).children(".imgdroDown").css({
				"-webkit-transform": "rotate(180deg)"
			});
		} else {
			$(this).children(".navmiddleImg").show();
			$(this).children(".navmiddleImg1").hide();
			$(this).next("ul").slideUp(400);
			$(this).children(".imgdroDown").css({
				"-webkit-transform": "rotate(0deg)",
				"-moz-transform": "rotate(0deg)",
				"-o-transform": "rotate(0deg)"
			});
		}
	});
	$(".inputOrder").focus(function() {
		$(this).parent(".orderDiv1").css({
			"background": "rgba(0,0,0,0.2)"
		});
	});
	$(".inputOrder").blur(function() {
		$(this).parent(".orderDiv1").css({
			"background": "rgba(255,255,255,0)"
		});
	});
	$("[data-toggle='tooltip']").tooltip();
	$("[data-toggle='tooltip']").tooltip("destroy");
	$(".sf-s").on("click", function() {
		//   		$scope.navSref = true;
		$("[data-toggle='tooltip']").tooltip();
		$(this).hide();
		$(".sf-f").show();
		$(".specialSpan").hide();
		$(".navTitle").hide();
		$(".navTitle").hide();
		$(".tipNumber").hide();
		$(".imgdroDown").show();
		$(".inputOrder").css({
			"width": "36px"
		});
		$(".inputOrder").attr({
			"placeholder": ""
		});
		$(".navimgImport").css({
			"padding-left": "3px"
		});
		var footW = $(window).width() - 36 - 197;
		$(".res_footer").css({
			"width": footW + "px",
			"margin-left": "-114px"
		});
		$(".navmiddleImg").css({
			"margin-bottom": "2px"
		});
		$(".navmiddleImg1").css({
			"margin-bottom": "2px"
		});
		$(".navmiddleImg").eq(0).attr({
			"src": "img/resident-icon0.svg"
		});
		$(".navmiddleImg").eq(1).attr({
			"src": "img/yz-icon0.svg"
		});
		$(".navmiddleImg").eq(2).attr({
			"src": "img/nursing-icon0.svg"
		});
		$(".navmiddleImg1").eq(0).attr({
			"src": "img/resident-icon0.svg"
		});
		$(".navmiddleImg1").eq(1).attr({
			"src": "img/yz-icon0.svg"
		});
		$(".navmiddleImg1").eq(2).attr({
			"src": "img/nursing-icon0.svg"
		});
		$(".contentleft-tab1 ul li a").css({
			"padding-left": "10px"
		});
		$(".voiceImg").css({
			"background": "url(img/u122.png) no-repeat center center",
			"background-size": "12px 12px",
			"background-position": "11px"
		});
		$(".voiceImg").click(function() {
			$(".orderDiv2").show();
		});
		$(".returnImg").click(function() {
			$(".orderDiv2").hide();
		});
		$(".specialDiv").css({
			"background": "rgba(0,0,0,0.1)"
		});
		$(".historyL").hide();
		$(".historyR").hide();
		$(".navImg").css({
			'position': 'absolute',
			'left': '10px',
			'top': '14px'
		});
		$(".content-left").css({
			"width": "36px",
			'left': '-36px'
		});
		$(".content").css({
			'padding-left': '36px'
		});
	});
	$(".sf-f").on("click", function() {
		//   		$scope.navSref = false;
		$("[data-toggle='tooltip']").tooltip("destroy");
		$(this).hide();
		$(".sf-s").show();
		$(".specialSpan").show();
		$(".navTitle").show();
		$(".navTitle").show();
		$(".tipNumber").show();
		$(".imgdroDown").hide();
		var footW = $(window).width() - 150 - 197;
		$(".res_footer").css({
			"width": footW + "px",
			"margin-left": "0"
		});
		$(".inputOrder").css({
			"width": "150px"
		});
		$(".inputOrder").attr({
			"placeholder": "请输入指令"
		});
		$(".navimgImport").css({
			"padding-left": "1px"
		});
		$(".navmiddleImg").css({
			"margin-bottom": "32px"
		});
		$(".navmiddleImg1").css({
			"margin-bottom": "30px"
		});
		$(".navmiddleImg").attr({
			"src": "img/droDownW-mark03-S.svg"
		});
		$(".navmiddleImg1").attr({
			"src": "img/droDownW-mark03-K.svg"
		});
		$(".contentleft-tab1 ul li a").css({
			"padding-left": "30px"
		});
		$(".absoluteImg").hide();
		$(".voiceImg").css({
			"background": "url(img/voice-icon0.svg) no-repeat center center",
			"background-size": "16px 16px"
		});
		$(".voiceImg").click(function() {
			$(".orderDiv2").hide();
		});
		$(".returnImg").click(function() {
			$(".orderDiv2").hide();
		});
		$(".specialDiv").css({
			"background": "url(img/nav-fg-mark.png) no-repeat left top rgba(0,0,0,0.1)",
			"background-size": "11px 7px"
		});
		$(".historyL").show();
		$(".historyR").show();
		$(".navImg").css({
			'position': 'relative',
			'left': '0px',
			'top': '0px'
		});
		$(".content-left").css({
			"width": "150px",
			'left': '-150px'
		});
		$(".content").css({
			'padding-left': '150px'
		});
	});

});
//屏幕改变
window.onresize = function() {
		var fir = $(window).height(),
			sec = $(window).height() - 37,
			thi = sec - 98,
			fou = sec - 28,
			fiv = sec - 25 - 110,
			six = sec - 38;
		$(".content-left").css({
			'height': sec + 'px',
			'overflow': 'hidden'
		});
		$("#con-right").css({
			'height': sec + 'px',
			'overflow': 'hidden'
		});

		$(".navDiv").css({
			'height': six + 'px',
			'overflow-y': 'auto',
			'overflow-x': 'hidden'
		});
		var fir = $(window).height() - 30;
		$("#iframeId").css({
			'height': fir + 'px',
			'overflow': 'hidden'
		});
	}
	//cookieHandler
function CH(cookie) {
	if(typeof cookie == 'string' && cookie.length > 1 && cookie[0] == "\"" && cookie[cookie.length - 1] == "\"")
		cookie = cookie.slice(1, -1);
	return cookie;
}

function GC(key) {
	var arr, reg = new RegExp("(^| )" +key + "=([^;]*)(;|$)");
	if(arr = document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}

function BC(name, value, options) {
	var path, expires;
	options = options || {};
	expires = options.expires;
	path = typeof options.path !== 'undefined' ? options.path : '/';
	if(typeof value === 'undefined') {
		expires = 'Thu, 01 Jan 1970 00:00:00 GMT';
		value = '';
	}
	if(typeof expires === 'string') {
		expires = new Date(expires);
	}

	var str = encodeURIComponent(name) + '=' + encodeURIComponent(value);
	str += path ? ';path=' + path : '';
	str += options.domain ? ';domain=' + options.domain : '';
	str += expires ? ';expires=' + expires.toUTCString() : '';
	str += options.secure ? ';secure' : '';
	return str;
}

function SC(key, value, options) {
	document.cookie = BC(key, value, options);
}

function DC(key) {
	var date = new Date();
	date.setTime(date.getTime() - 10000);
	document.cookie = key + "=v; expire=" + date.toGMTString();
}
//处理路径
function GetRequest() {
	var url = location.search; //获取url中"?"符后的字串    
	var theRequest = new Object();
	if(url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for(var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
	}
	return theRequest;
}
var tkrsReq = new Object();
tkrsReq = GetRequest();
var tkrsToken = tkrsReq['token'];
//console.log(tkrsReq);
if(tkrsToken){
		//SC('token','\"' + tkrsToken + '\"');
	}

var listHideFlag = false,
	userCd = "",
	orgCd = "",
	orgnizations = [],
	orgName = "",
	ajaxToken = CH(GC('token')),
	//	ajaxPortalCd = CH(GC('portalCd')),
	careAreas = [],
	slides = null,
	userName = '',
	sessionData = null;
//console.log(ajaxToken)
	//  ajax请求
	//获取会话信息（包括护理区域）
/*$.ajax({
		url: interfaces.getSession + '?jaraxxus=' + Date.now(),
		type: 'get',
		dataType: 'json',
		data: null,
		async: false,
		headers: {
			TK_AUTHEN_TOKEN: ajaxToken,
			Authorization: 'Bearer ' + ajaxToken,
			bx_token: 'BX_TOKEN_HERE',
			'Cache-Control': "no-cache, no-store, max-age=0"
		}
	})*/
	console.log('1111111------'+document.cookie)
	userCd = document.cookie.substring(7)
	console.log(userCd)
	var dataIn = {
		userCd: userCd,
	}
	//获取菜单信息
	$.ajax({
		url: makeUrl(interfaces.userPermissions, dataIn) + '?jaraxxus=' + Date.now(),
		type: 'get',
		dataType: 'json',
		async: false
		/*headers: {
			TK_AUTHEN_TOKEN: "AAAA",
			Authorization: 'Bearer AAAA',
			bx_token: 'BX_TOKEN_HERE',
			'Cache-Control': "no-cache, no-store, max-age=0"
		}*/
	}).then(function(data) {
		//console.log(makeUrl(interfaces.updateMeunByPortolId,dataInformate))
		//console.log(data);
		if(data.code == 0) {
			userName = data.data.name;
			slides = data.data.permissionList; //左侧菜单数据
			console.log(JSON.stringify(slides))
		} else {
			alert(data.msg);
		}
	}, function(data) {
		alert(data.msg);
		//						setTimeout("location.href='../systemManage/index.html'",0);
	})
	/*.then(function(data) {
		//处理会话信息
		if(data.data) {
			if(data.code == 0) {
				sessionData = data.data;
				userCd = data.data.userCd;
				userName = data.data.showName;
				orgnizations = data.data.orgnizations;
				for(var i = 0; i < data.data.orgnizations.length; i++) {
					if(data.data.orgnizations[i].currentFlag == true) {
						orgCd = data.data.orgnizations[i].orgCd;
						orgName = data.data.orgnizations[i].orgName;
						careAreas = data.data.orgnizations[i].careAreas;
					}
				}
				if(careAreas.length == 0) {
					orgCd = data.data.orgnizations[0].orgCd;
					orgName = data.data.orgnizations[0].orgName;
					careAreas = data.data.orgnizations[0].careAreas;
				}
			}
			//token失效
			else if(data.data.code == 2) {
				alert(data.msg);
				//				setTimeout("location.href='../systemManage/index.html'",0);
			}
		} else {
			alert(data.msg);
			//			setTimeout("location.href='../systemManage/index.html'",0);
		}
	}, function(data) {
		alert(data.msg);
		//		setTimeout("location.href='../systemManage/index.html'",0);
	})*/
	//初始化Angular
var app = angular.module('myApp', ['ui.router']);
app.run(['$rootScope', '$state', '$stateParams',
	function($rootScope, $state, $stateParams) {
		$rootScope.$state = $state;
		$rootScope.$stateParams = $stateParams;
	}
]);
//17-1-11 重新配置路由
app.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
	//根据请求到的数据动态加载路由
	if(slides) {
		//console.log(slides);
		try {
			if(slides[0].children && slides[0].children.length > 0)
				$urlRouterProvider.when('', slides[0].children[0].enName);
			else $urlRouterProvider.when('', slides[0].enName);
		} catch(e) {
			console.log(e)
		}
		//		$urlRouterProvider.when('', '/makePrescription');
		function bindRoute(cont) {
			/*if(cont.menuTreeLeafFlag == 0) {
				if(cont.children && cont.children.length > 0) {
					cont.children.forEach(function(e) {
						bindRoute(e);
					})
				}
			} else if(cont.menuTreeLeafFlag == 1) {*/
				//获取路由页面的英文名
				cont.enName = cont.enName ? cont.enName :
					cont.uri ? cont.uri.split('/').pop().split('.')[0] : false;
				if(!cont.enName) return console.error('“' + cont.name + '”没有配置相应的function', cont);
				$stateProvider.state(cont.enName, {
					url: '/' + cont.enName,
					template: '<iframe name="myFrame" id="iframeId" src=' + cont.uri + ' style="width:100%;border:none;" class="ngCloak"></iframe>',
					//		           	templateUrl: cont.uri,
					controller: function() {
						window.frames[0].focus();
						var token = GC('token');
						var scope = angular.element(document.querySelector('[ng-controller=superController]')).scope();
						//SC('nursingAreaId', scope.nursingAreaCd);
						window.frames[0].addEventListener('load', function() {
							//console.log("Frames addEventListener.");
							EventBus.post({
								topic: 'env',
								eventCode: 'init',
								data: {
									token: token,
									nursingAreaId: scope.nursingAreaCd,
									listHideFlag: listHideFlag,
									orgCd: scope.orgCd,
									userName: scope.userName,
									userCd: scope.userCd,
									orgName: scope.orgName,
									careAreaName: scope.nursingArea,
									//orgnizations: sessionData.orgnizations
								}
							});
							$("#iframeId").removeClass("ngCloak");
						});
						var fir = $(window).height() - 30;
						$("#iframeId").css({
							'height': fir + 'px',
							'overflow': 'hidden'
						});
					},
					onExit: function() {
							//console.log("onexit");
						}
						//templateUrl: cont.uri,
						//controller:cont.enName.split("/")[0]+'Controller'
				});
			/*}*/
		}
		slides.forEach(function(cont) {
			bindRoute(cont);
		})
	}
}]);
/**
 * 顶层controller(最外层)
 * */
app.controller('superController', ['$scope', '$http', 'common', '$location', '$rootScope', '$state', function($scope, $http, common, $location, $rootScope, $state) {
	$scope.slides = slides; //左侧列表--信息
	setTimeout(function() {
		var index = GC('originnalIndex');
		if(index && index != '0') {
			var num = parseInt(index),
				dom = $('.listItems').eq(num);
			//SC('originnalIndex', num);
			dom.show().parent().siblings().find('.listItems').hide();
			dom.parent().find('.navmiddleImg1').show();
			dom.parent().find('.navmiddleImg').hide();
			dom.parent().find(".imgdroDown").css({
				"-webkit-transform": "rotate(180deg)",
				"-moz-transform": "rotate(180deg)",
				"-o-transform": "rotate(180deg)"
			});
		} else {
			var dom = $('.listItems').eq(0);
			//SC('originnalIndex', 0);
			dom.show().parent().siblings().find('.listItems').hide();
			dom.parent().find('.navmiddleImg1').show();
			dom.parent().find('.navmiddleImg').hide();
			dom.parent().find(".imgdroDown").css({
				"-webkit-transform": "rotate(0deg)",
				"-moz-transform": "rotate(0deg)",
				"-o-transform": "rotate(0deg)"
			});
		}
	}, 0);
	//设置cookie
	function setCookie(name, value) {
		var exp = new Date();
		//10小时过期
		exp.setTime(exp.getTime() + 10 * 60 * 60 * 1000);
		document.cookie = name + "=" + encodeURIComponent(value) + ";expires=" + exp.toGMTString() + ";path=/";
		return true;
	}
	//	$scope.blink = function(each){
	//		setCookie('portalCd',each.code)
	//		window.location.href = each.uri
	//	}
	$scope.nurseClick = function(num, $event) {
		$event.stopPropagation();
		//SC('originnalIndex', num);
	}

	$scope.click = function(num, $event) {
			$event.stopPropagation();
			//SC('originnalIndex', num);
		}
		//退出登录
	$scope.logOut = function() {
		confirm('是否退出登录', function(flag){
			if(flag){
				window.location = 'index.html';
			}else{
				return false;
			}
		});
		
		/*
			common.getData('DELETE', interfaces.userLogout, null, null,
				function(data) {
					//console.log(data);
					if(data.code == '0') {
						window.localStorage.clear();
						window.location = 'index.html';
					} else {
						alert('退出登录失败' + data.data);
					}
				},
				null,
				function(data) {
					alert(data.msg);
				}
			)
		*/}
		//点击之后导航收缩
	$scope.navSref = false;
	/*滚动条事件*/
	$(".tableQuery").scroll(function() {
		if($(this).scrollTop() >= 4) {
			$(this).addClass("tableWrapShadow");
		} else {
			$(this).removeClass("tableWrapShadow");
		}
	});

	//列表切换
	$scope.show = {
		value: false
	};
	$scope.nurdeListToggle = function() {
		$scope.show.value = !$scope.show.value;
		if($scope.show.value) {
			$('.specialSpan').text('分级显示');
			$('.navimgImport').attr('src', 'img/listS-icon.svg');
			$('.listItems').css('display', 'block');
			$('.mainToggle').hide();
			if($scope.navSref == true) {
				$(".specialSpan").hide();
				$(".ulTitle").hide();
				$(".navTitle").hide();
			}
		} else {
			$('.specialSpan').text('列表显示');
			$('.navimgImport').attr('src', 'img/list-icon.svg');
			$('.listItems').css('display', 'none');
			$('.mainToggle').show();
			if($scope.navSref == true) {
				$(".specialSpan").hide();
				$(".ulTitle").hide();
				$(".navTitle").hide();
			}
		}
	}

	/**获取会话信息————————————————Start*/
	//属性：
	//护理区域
	$scope.careAreas = careAreas;
	//用户名和Code
	$scope.userName = userName;
	$scope.userCd = userCd;
	//组织名称和Code
	$scope.orgName = orgName;
	$scope.orgCd = orgCd;
	//用户的所有机构信息
	$scope.orgnizations = orgnizations;
	//当前显示的机构名称及Code
	$scope.nursingArea = '';
	$scope.nursingAreaCd = '';
	for(var i = 0; i < careAreas.length; i++) {
		if(careAreas[i].currentFlag == true) {
			$scope.nursingArea = careAreas[i].name;
			$scope.nursingAreaCd = careAreas[i].code;
		}
	}
	if($scope.nursingArea == '' && $scope.nursingAreaCd == '' && $scope.careAreas.length > 0) {
		$scope.nursingArea = $scope.careAreas[0].name;
		$scope.nursingAreaCd = $scope.careAreas[0].code;
	}
	//当前部门
	for(var i = 0; i < $scope.orgnizations.length; i++) {
		if($scope.orgnizations[i].currentFlag) {
			$scope.dept = $scope.orgnizations[i]
		}
	}
	if(!$scope.dept) $scope.dept = $scope.orgnizations[0]
		//方法：
	$scope.changeOrg = function(org) {
			//		1.修改展示层
			$scope.orgCd = org.orgCd;
			$scope.orgName = org.orgName;

			//		2.向EventBus发送信息
			$scope.changeOrgEV(org);

			//		3.通知服务器修改session
			var postData = {
				"orgCd": $scope.orgCd,
				"careAreaCd": $scope.nursingAreaCd
			}
			common.getData("PUT", interfaces.userSession, postData, null,
				function(res) {
					//console.log(res)
					if(res.data == null) {
						alert(res.msg);
						return
					}
					// 更新cookie中careAreaCd和careAreaName
					//SC('orgCd', org.orgCd);
					//SC('orgName', org.orgName);
					//更新当前护理区代办
					//				$scope.loadMyPlan();
					window.location.reload()

				}, null,
				function(err) {
					//console.log(err)
					alert(err.msg)
				}
			)
		}
		//切换部门
	$scope.changeDept = function(dept) {
			$scope.dept = dept
				//		var postData = {
				//				"orgCd":$scope.orgCd
				//		}
				//		common.getData("PUT",interfaces.userSession,postData,null,
				//				function(res){
				//					console.log(res)
				//					if(res.data == null){
				//						alert(res.msg);
				//						return
				//					}
				//					// 更新cookie中careAreaCd和careAreaName
				//					SC('careAreaCd',takeArea.code);
				//					SC('careAreaName',takeArea.name);
				//					//更新当前护理区代办
				////					$scope.loadMyPlan();
				//					window.location.reload()
				//		
				//				},null,
				//				function(err){
				//					//console.log(err)
				//					alert(err.msg)
				//				}
				//			)
				//		EventBus.post({
				//			topic: 'env',
				//			eventCode: 'areaChange',
				//			data: {
				//				token:GC('token'),
				//				nursingAreaId:nursingAreaCd,
				//				listHideFlag:listHideFlag,
				//				orgCd:$scope.orgCd,
				//				userName:$scope.userName,
				//				userCd:$scope.userCd,
				//				orgName:$scope.orgName,
				//				careAreaName:$scope.nursingArea,
				//				orgnizations:sessionData.orgnizations
				//			}
				//		});
		}
		//点击社区列表，切换为其他的护理区域
	$scope.changeArea = function(takeArea) {
			//变更区域名称，并向服务器提交会话信息变更
			$scope.nursingArea = takeArea.name;
			$scope.nursingAreaCd = takeArea.code;
			//!!!!!!!!!调用滨哥的更改护理区域接口
			//向EventBus发送信息
			$scope.changeNursingArea($scope.nursingAreaCd);

			// 2017-5-5 修改：向服务器提交改变护理区(wangweilong)
			//console.log(takeArea)
			var postData = {
				"orgCd": $scope.orgCd,
				"careAreaCd": takeArea.code
			}
			common.getData("PUT", interfaces.userSession, postData, null,
					function(res) {
						//console.log(res)
						if(res.data == null) {
							alert(res.msg);
							return
						}
						// 更新cookie中careAreaCd和careAreaName
						//SC('careAreaCd', takeArea.code);
						//SC('careAreaName', takeArea.name);
						//更新当前护理区代办
						//				$scope.loadMyPlan();
						window.location.reload()

					}, null,
					function(err) {
						//console.log(err)
						alert(err.msg)
					}
				)
				// 2017-5-5 修改结束
		}
		/**获取门户信息——start*/
		//	$scope.portals = []
		//	$scope.getPortalsCB = function(data){
		//		data.data.unshift({code:"0000",
		//			des:"",
		//			eName:"main",
		//			name:"首页",
		//			sortNo:1,
		//			uri:"/index/main.html"})
		//		$scope.portals = data.data
		//	}
		//	console.log($scope)
		//	common.getData("get",makeUrl(interfaces.getPortals,{orgCd:$scope.orgCd,userCd:$scope.userCd}),
		//			null,null,
		//			$scope.getPortalsCB,null,
		//			$scope.getPortalsFailCB);
		//	$scope.location = window.location
		/**获取门户信息——end*/
		//	/**获取为分派医嘱信息——start*/
		//	$scope.recoverOrderNum = 0;
		//	$scope.getRecoverOrderNum = function(){
		//		common.getData('get',interfaces.getUnassignedOrdersNum + '?orgCd=' + $scope.orgCd,null,null,
		//				$scope.getRecoverOrderNumCB,
		//				null,
		//				$scope.getRecoverOrderNumFailCB)
		//	}
		//	$scope.getRecoverOrderNumCB = function(datax){
		//		$scope.recoverOrderNum = datax.data
		//	}
		//	$scope.getRecoverOrderNumFailCB = function(datax){
		//		console.error('获取未分派医嘱条数失败！')
		//	}
		//	$scope.getRecoverOrderNum()
		//	/**获取为分派医嘱信息——end*/
		//17-1-6 增加changeNursingArea方法(护理区变化)
	$scope.changeNursingArea = function(nursingAreaCd) {
		EventBus.post({
			topic: 'env',
			eventCode: 'areaChange',
			data: {
				token: GC('token'),
				nursingAreaId: nursingAreaCd,
				listHideFlag: listHideFlag,
				orgCd: $scope.orgCd,
				userName: $scope.userName,
				userCd: $scope.userCd,
				orgName: $scope.orgName,
				careAreaName: $scope.nursingArea,
				orgnizations: sessionData.orgnizations
			}
		});
	}
	$scope.changeOrgEV = function(org) {
			EventBus.post({
				topic: 'env',
				eventCode: 'areaChange',
				data: {
					token: GC('token'),
					nursingAreaId: $scope.nursingAreaCd,
					listHideFlag: listHideFlag,
					orgCd: org.orgCd,
					userName: $scope.userName,
					userCd: $scope.userCd,
					orgName: org.orgName,
					careAreaName: $scope.nursingArea,
					orgnizations: sessionData.orgnizations
				}
			});
		}
		/**获取会话信息————————————————END*/
		//点击左上logo
		//	$scope.logoClick = function(){
		//		window.location.href=interfaces.besSystem;
		//	}

	//增加子页面切换时的门户处理逻辑
	EventBus.registerReceiver(function(e) {
			switch(e.eventCode) {
				case 'toggle':
					var targetUrl = e.data.url;
					var event = {
						eventCode: 'toggle',
						data: e.data.message
					}
					if(e.data.encounterId != undefined)
						location.hash = targetUrl + "?encounterId=" + e.data.encounterId;
					else location.hash = targetUrl;
					window.frames[0].addEventListener('load', function() {
						EventBus.send(window.frames[0], event);
					});
					break;
				case 'alert':
					alert(e.data);
					break;
				case 'listHideFlag':
					console.log(listHideFlag);
					listHideFlag = e.data.listHideFlag;
					break;
			}
		})
		/**获取待办信息(author:anran)————————————————Start*/
	$scope.onloadFlag = true
	if($scope.onloadFlag == true) {
		$scope.$watch('$viewContentLoaded', function() {
			//			$scope.loadMyPlan();
			$scope.onloadFlag = false
		})
	}
	//	$scope.loadMyPlan = function(){
	//		$scope.myPlanWaitNo = 0
	//		$scope.myPlanExcutingNo = 0
	//		$scope.myPlanNo = 0
	//		common.getData('GET',interfaces.subTaskByRoleAndUser+'?number=1&size=5',null,null,
	//			function  (responseData) {
	//				console.log(responseData)
	//				$scope.myPlanWaitData = responseData.data.tskSubTaskWaitExcuteList
	//				$scope.myPlanExcutingData = responseData.data.tskSubTaskExcutingList
	//				$scope.myPlanWaitNo = responseData.data.waitCount?responseData.data.waitCount:0
	//				$scope.myPlanExcutingNo = responseData.data.tskSubTaskExcutingList.length?responseData.data.tskSubTaskExcutingList.length:0
	//				$scope.myPlanNo = $scope.myPlanWaitNo + $scope.myPlanExcutingNo
	//			}, null,
	//			function  (errorData) {
	//				console.log(errorData)
	//			}
	//		);
	//	}
	$scope.setAddress = function(obj) {
		if(obj == null || obj == '' || obj == undefined) {
			return ''
		}
		var province = obj.province ? obj.province : '';
		var city = obj.city ? obj.city : '';
		var county = obj.county ? obj.county : '';
		var streetName = obj.streetName ? obj.streetName : '';
		var buildingNo = obj.buildingNo ? obj.buildingNo : '';

		var unitNo = obj.unitNo ? '-' + obj.unitNo : '';
		var roomNo = obj.roomNo1 ? '-' + obj.roomNo1 : '';
		var sa = county + streetName + buildingNo + unitNo + roomNo;
		return sa
	}
	$scope.setCookie = function(name, value) {
			var exp = new Date();
			//3天过期
			exp.setTime(exp.getTime() + 3 * 24 * 60 * 60 * 1000);
			document.cookie = name + "=" + encodeURIComponent(value) + ";expires=" + exp.toGMTString() + ";path=/";
			return true;
		}
		//	$scope.localPlan = function(encounterId,typeCd){
		//		if(typeCd =='20'){
		//			var portalCd = '2';
		//			$scope.setCookie('portalCd',portalCd);
		//			var loadPlanUrl = location.hash.split('/')[1].split('?')[0]
		//			if (loadPlanUrl=='executionRecord') {
		//				EventBus.post(
		//					{
		//						topic:'reId',
		//						eventCode:'init',
		//						data:{
		//							reloadEncounterId:encounterId
		//						}
		//					}
		//				)
		//			} else{
		//				window.location = '/insurance/insurance.html#/executionRecord?encounterId='+encounterId
		//			}
		//		}
		//		if(typeCd =='30'){
		//			alert('请在移动端进行评估')
		//		}
		//	}
		/**获取待办信息————————————————End*/
}]);

//兼容滚动条样式（左侧列表导航）
//$(function(){
//		$.mCustomScrollbar.defaults.scrollButtons.enable = true; //启用默认的滚动按钮
//		$(".navDiv").mCustomScrollbar( {
//			theme : "dark"
//		});
//		
//})