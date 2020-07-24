//console.info('加载','common.js');
/**
 * 通用请求接口
 * @param method   angular请求方式:'GET'/'get' ;'PUT'/'put' ;'POST'/'post';'DELETE'/'delete'
 * @param url      请求地址
 * @param headers  请求头的配置:如果使用默认方式,直接填写空对象 {}
 * @param param    请求参数   :如果没有参数,直接填写空对象    {}
 * @param callbacktype  回调函数
 */
//var app = angular.module('myApp',[]);

//http错误码处理
var handleFailStatus = function(code){
	if(code==403){
		// 403没权限，跳转到'无权限页面'
		alert("暂无权限访问该页面，请联系系统管理员确认权限")
		setTimeout(function(){window.top.location.href = "../403.html"},3000)
		return true
	} else if(code==401){
		alert("token已失效，请重新登录")
		setTimeout(function(){window.top.location.href = "../index.html"},3000)
		return true
	}
	return false
}
var utils = {};
var toString = Object.prototype.toString;
function encode(val) {
	  return encodeURIComponent(val).
	    replace(/%40/gi, '@').
	    replace(/%3A/gi, ':').
	    replace(/%24/g, '$').
	    replace(/%2C/gi, ',').
	    replace(/%20/g, '+').
	    replace(/%5B/gi, '[').
	    replace(/%5D/gi, ']');
	}
function isArray(val) {
	  return toString.call(val) === '[object Array]';
	}
utils.isArray = function(val) {
	  return toString.call(val) === '[object Array]';
}
utils.isString = function(val) {
	  return typeof val === 'string';
	}
utils.isObject = function(val) {
	  return val !== null && typeof val === 'object';
	}
utils.isDate = function (val) {
	  return toString.call(val) === '[object Date]';
	}
utils.forEach  = function (obj, fn) {
	  // Don't bother if no value provided
	  if (obj === null || typeof obj === 'undefined') {
	    return;
	  }
	
	  // Force an array if not already something iterable
	  if (typeof obj !== 'object' && !isArray(obj)) {
	    /*eslint no-param-reassign:0*/
	    obj = [obj];
	  }
	
	  if (isArray(obj)) {
	    // Iterate over array values
	    for (var i = 0, l = obj.length; i < l; i++) {
	      fn.call(null, obj[i], i, obj);
	    }
	  } else {
	    // Iterate over object keys
	    for (var key in obj) {
	      if (Object.prototype.hasOwnProperty.call(obj, key)) {
	        fn.call(null, obj[key], key, obj);
	      }
	    }
	  }
	}
utils.isURLSearchParams = function (val) {
	  return typeof URLSearchParams !== 'undefined' && val instanceof URLSearchParams;
}

app.factory('common',function($http){
	var SYSTEM_ID = "SC269";
	var get_data = function(_url){
		return $http.get(_url);
	}
	var get_list = function(_url){
		return $http.get(_url);
	}
	var update_list = function(_url,_data){
		return $http.put(_url, _data,{headers:{
			'Content-Type': 'application/json;charset=UTF-8'
		}});
	}
	var del_list = function(_url){
		return $http.delete(_url);
	}
	function GC(key){
		var arr, reg = new RegExp("(^| )" +key + "=([^;]*)(;|$)"),cookie;
		if(arr = document.cookie.match(reg)){
			cookie = unescape(arr[2]);
			if(typeof cookie=='string'&&cookie.length>1&&cookie[0]=="\""&&cookie[cookie.length-1]=="\"")
				cookie = cookie.slice(1,-1);
			return cookie;
		}
		else
			return null;
	}
	var getDataWithOneParam = function(typed, url, data, headers, callbacktype, param, failcallbacktype){
		if((typed==='GET'||typed==='DELETE')&&data){
			var serializedParams;
			if (utils.isURLSearchParams(data)) {
			    serializedParams = data.toString();
			  } else {
			    var parts = [];
			
			    utils.forEach(data, function serialize(val, key) {
			      if (val === null || typeof val === 'undefined') {
			        return;
			      }
			
			      if (utils.isArray(val)) {
			        key = key + '[]';
			      }
			
			      if (!utils.isArray(val)) {
			        val = [val];
			      }
			
			      utils.forEach(val, function parseValue(v) {
			        if (utils.isDate(v)) {
			          v = v.toISOString();
			        } else if (utils.isObject(v)) {
			          v = JSON.stringify(v);
			        }
			        parts.push(encode(key) + '=' + encode(v));
			      });
			    });
			
			    serializedParams = parts.join('&');
			  }
			
			  if (serializedParams) {
			    url += (url.indexOf('?') === -1 ? '?' : '&') + serializedParams;
			  }
		};
		var tokenValue = GC('token') ;
		//console.log(tokenValue);
		if(!headers)headers = {}
		if(tokenValue){
			headers.TK_AUTHEN_TOKEN = tokenValue;
			headers.Authorization = 'Bearer '+tokenValue;
			headers.bx_token = 'BX_TOKEN_HERE';
		}
		/**Cache-Control*/
		headers['Cache-Control'] ? !0 : headers['Cache-Control'] = "no-cache, no-store, max-age=0"
		url ? url.indexOf('?') > -1 ? url += '&jaraxxus=' + Date.now() : url += '?jaraxxus=' + Date.now() : !0
		return	$http({
			  method:typed,
			  url:url,
			  data:data,
			  //headers:headers
			  headers:headers
		  	}).success(function(data, status, headers, config){
		  		//console.log("后台返回的数据",data,config);
		  		if(data.status==undefined||data.status==null||data.status==='')
		  			data.status=data.msg;
		  		if(data.status=="success"||data.status=="成功"||data.code=="0"){
		  			if(param!==null)
		  			{
		  				callbacktype(data, param);
		  			}
		  			else
		  			{
		  				callbacktype(data);
		  			}
		  		}else if(data.code!=undefined&&data.code!=null&&data.code!==''){
			  			if(data.code!="0"){
			  				if(failcallbacktype!=undefined&&failcallbacktype!=null&&failcallbacktype!=''){
			  					if(param!==null){
			  						failcallbacktype(data, param);
				  				}
				  				else{
				  					failcallbacktype(data);
					  			}
				  			}
				  		}
			  			else if(data.code==0){
			  				callbacktype(data);
			  			}
		  		}
		  		else if(data.status=="failure"){
		  			if(data.error_message==null || data.error_message == undefined || data.error_message === ""){
//		  				alert("网络繁忙");
		  			}else{
		  				alert(data.error_message);
		  			}
		  		} else {
		  			failcallbacktype(data);
		  		}
		  	}).error(function(data, status, headers, config){
		  		handleFailStatus(status)
		  		//console.info("GetData function error!");
//		  		$("#warning").modal('show');
//               	$("#warning-title").text("服务器端出错了!!");
		  	})
	  	};
	var getDataByOneParam = function(typed, url, data, headers, callbacktype, param){
		if(!headers)headers = {};
//		if(!headers.bx_token)headers.bx_token = 'BX_TOKEN_HERE';
//		headers.Authorization = 'Bearer '+GC('token');
//		headers.TK_AUTHEN_TOKEN = GC('token');
		var tokenValue = GC('token') ;
		if(tokenValue){
			headers.TK_AUTHEN_TOKEN = tokenValue;
			headers.Authorization = 'Bearer '+tokenValue;
			headers.bx_token = 'BX_TOKEN_HERE';
		}
		/**Cache-Control*/
		headers['Cache-Control'] ? !0 : headers['Cache-Control'] = "no-cache, no-store, max-age=0"
		url ? url.indexOf('?') > -1 ? url += '&jaraxxus=' + Date.now() : url += '?jaraxxus=' + Date.now() : !0
		  return	$http({
			  method:typed,
			  url:url,
			  params:data,
			  headers:headers
		  	}).success(function(data, status, headers, config){
		  		if(data.status==undefined||data.status==null||data.status==='')
		  			data.status=data.msg;
		  		if(data.status=="success"||data.status=="成功"||data.code=="0"){
		  			if(param!==null)
		  			{
		  				callbacktype(data, param);
		  			}
		  			else
		  			{
		  				callbacktype(data);
		  			}
		  		}
		  		else if(data.status=="failure"){
		  			if(data.error_message==null || data.error_message == undefined || data.error_message === ""){
	//			  				alert("网络繁忙");
		  			}else{
		  				alert(data.error_message);
		  			}
		  		}
		  	}).error(function(data, status, headers, config){
		  		handleFailStatus(status)
		  		//console.info("GetData function error!");
	//			  		$("#warning").modal('show');
	//	               	$("#warning-title").text("服务器端出错了!!");
		  	})
	  	};
	var common={
	getData: function(typed, url, data, headers, callbacktype, failcallbacktype){
			/*if(GC('token')==null || GC('token')==="" || GC('token')==undefined){
				 window.location.href="../systemManage/index.html";
				 return false;
			}*/
			getDataWithOneParam(typed, url, data, headers, callbacktype, null, failcallbacktype);
		},
	getDataWithOneParam:function(typed,url,data,headers,callbacktype, param, failcallbacktype){
		return getDataWithOneParam(typed, url, data, headers, callbacktype, param, failcallbacktype);
	},
	getDataByParam: function(typed, url, data, headers, callbacktype){
		/*if(GC('token')==null || GC('token')==="" ||GC('token')==undefined){
			window.location.href="../systemManage/index.html";
			return false;
		}*/
		getDataByOneParam(typed, url, data, headers, callbacktype, null);
	},
	get_data:function(_url){ 
		   return get_data(_url);
	   },
	get_list:function(_url){
		   return get_list(_url);
		   
	   },
	update_list:function(_url,_data){
		return  update_list(_url,_data);
	},
	del_list:function(_url){
		return del_list(_url);
	},
	SYSTEM_ID:SYSTEM_ID,
	uuid:function(){
		  function S4() {
		      return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
		  }
		  return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
	}
	};
	return common;
})
//function getDate(method,url,param,headers,callbacktype){
////	var token=$.cookie('token');
////	if(token==null||token==''||token==undefined){
////        此处是需要跳转的页面(后期增加)
////        return false;
////	}else{
////		 param.token=token;
//		 $http{
//		    	method:method,
//		    	data:param,
//		    	url:url,
//		    	headers:headers,
//		    	success:function (successData){//正常交互
//		    		//响应成功,数据状态码为'success'
//		    		if(resultData.status=='success'){
//		    			callbacktype(successData.data);
//					}
//		    		//响应成功,数据状态码为'failure'
//		    		if(successData.status=='failure'){
//						alert("后台出错了");
//					}
//		    	},
//		    	error:function(errorData){//交互错误
//		    		console.log("error:" + JSON.stringify(data));
//		            $scope.result_data = JSON.stringify(data);
//		    	}
//		 }
////	}
//}