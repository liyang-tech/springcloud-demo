//const dontNeedTokenUrls = [interfaces.organizationapplyinfo,
//                           interfaces.categoryCdUrl,
//                           interfaces.aplOrgDesignatedTypes,
//                           interfaces.ownershipCdUrl,
//                           interfaces.applicantStatus,
//                           interfaces.roleType,
//                           interfaces.titleUrl,
//                           interfaces.address,
//                           ]
//var dontNeedToken = function(url){
//	for(var noUrl in dontNeedTokenUrls){
//		if(noUrl.indexOf(url)>-1){
//			return true;
//		}
//	}
//	return false;
//}

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
//http错误码处理
var handleFailStatus = function(code){
	if(code==403){
		// 403没权限，跳转到'无权限页面'
		alert("暂无权限访问该页面，请联系系统管理员确认权限")
		setTimeout(function(){window.top.location.href = "../../403.html"},3000)
		return true
	} else if(code==401){
		alert("token已失效，请重新登录")
		setTimeout(function(){window.top.location.href = "../../systemManage/index.html"},3000)
		return true
	}
	return false
}
//请求拦截器
axios.interceptors.request.use(function(config){
	//console.log(config.headers)
	//1.先判断该接口是否需要加token
//	if(dontNeedToken(config.url))return config;
	//请求前在header中添加Token
//	var TK_AUTHEN_TOKEN = $.cookie('token');
	/**TK_AUTHEN_TOKEN*/
	var TK_AUTHEN_TOKEN = document.cookie.split("token=");
	if(TK_AUTHEN_TOKEN.length>1){
		TK_AUTHEN_TOKEN = TK_AUTHEN_TOKEN[1].split(';')[0];
		TK_AUTHEN_TOKEN = TK_AUTHEN_TOKEN.split("%22")[1];
	}
	else TK_AUTHEN_TOKEN = ""
	if(!config.headers)config.headers={};
//	if(config.headers.TK_AUTHEN_TOKEN==undefined||config.headers.TK_AUTHEN_TOKEN==null||config.headers.TK_AUTHEN_TOKEN===''){
	//如果没有Token或者是在首页，就不加token
	if(TK_AUTHEN_TOKEN&&window.location.href.indexOf("/systemManage/index.html")<0){
//		config.headers.TK_AUTHEN_TOKEN = TK_AUTHEN_TOKEN;
		config.headers.Authorization='Bearer '+TK_AUTHEN_TOKEN;
	}
//	}
	
	/**bx_token*/
	var bx_token = 'BX_TOKEN_HERE';
	if(!config.headers.bx_token)config.headers.bx_token = bx_token;
	else console.warn('不推荐在headers中添加bx_token属性！相关信息请咨询管理员！');
	
	/**Cache-Control*/
	config.headers['Cache-Control'] ? !0 : config.headers['Cache-Control'] = "no-cache, no-store, max-age=0"
	return config;
},function(error){
//	alert("请求失败！");
	//console.log("请求失败！");
	return Promise.reject(error);
})
//回调拦截器
axios.interceptors.response.use(function(response){
	//调试时，在控制台展示返回数据
	// dosome(response);
	//console.info("本次请求为：",response.config);
	//console.log("后台返回数据：",response);
	return response;
},function(error){
//	alert("请求失败！");
	if(error instanceof axios.Cancel)console.log("请求已被取消！");
	else console.log("请求失败！");
	return Promise.reject(error);
})


var getData = function(type,url,data,headers,callback,callbackparam,failcallback,param,config){
			var index = url.indexOf('?')
			// 中文转换为编码
			if(index>-1){
				url += '&jaraxxus=' + Date.now()
				url = encodeURI(url)
			} else {
				url += '?jaraxxus=' + Date.now()
			}
			type = type.toUpperCase();
			if((type==='GET'||type==='DELETE')&&data){
				var serializedParams ,paramsSerializer = config && config.paramsSerializer || false;
				  if (paramsSerializer) {
				    serializedParams = paramsSerializer(data);
				  } else if (utils.isURLSearchParams(data)) {
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
			var conf = {
				method: type,
				url: url,
				data: data,
				headers:headers,
				params:param
			};
			if(!!config){
//				if(!!config.async)
				for(var key in config){
					conf[key] = config[key];
				}
			}
			axios(conf).then(function(response){
				//console.log(response)
				//如果是调用平台SDK的返回格式，返回信息包含:code,data,msg,page,throwable
				if(response.data.code!=undefined&&response.data.code!=null&&response.data.code!==""){
//				if(typeof response.data.code == "string"){
					//成功回调
					if(response.data.code==0)
						callback(response,callbackparam);
					//失败回调
					else {
						var msg = '';
						if(response.data.msg)
							msg = response.data.msg;
//						alert('服务器繁忙:\r\n' + msg);
					//	console.log('服务器繁忙:\r\n' + msg);
						failcallback && failcallback(response,callbackparam);
					}
				}
				//如果收到其他格式的返回格式
				//例如：后台返回格式是：data,errorCode,errorMessage,status(,total)
				else if(response.data.errorCode!=undefined&&response.data.errorCode!=null&&response.data.errorCode!==""){
//				else if(typeof response.data.errorCode == "string"){
					//成功回调
					if(response.data.errorCode==0&&(response.data.status=="success"||response.data.status=="ok")){
						callback(response,callbackparam);
					}
					//失败回调
					else{
//						alert("服务器繁忙:\r\n" + response.data.errorMessage);
						//console.log("服务器繁忙:\r\n" + response.data.errorMessage);
						failcallback && failcallback(response,callbackparam);
					}
				}
				//后台返回格式是：data,errorCode,errorMessage,status
				else if(response.data.error_code!=undefined&&response.data.error_code!=null&&response.data.error_code!==""){
//				else if(typeof response.data.error_code == "string"){
					//成功回调
					if(response.data.error_code==0&&(response.data.status=="success"||response.data.status=="ok")){
						callback(response,callbackparam);
					}
					//失败回调
					else{
//						alert("服务器繁忙:\r\n" + response.data.errorMessage);
					//	console.log("服务器繁忙:\r\n" + response.data.errorMessage);
						failcallback && failcallback(response,callbackparam);
					}
				}
				//其他格式直接走
				else if(typeof response.data==='string'&&response.data.indexOf("</div>")){
					callback(response,callbackparam);
				}
				else {
					callback(response,callbackparam);
					alert("此接口非正规接口，请在vommon.js中添加响应的格式识别判断！");
					//console.log("在此添加判断");
				}
			})
			 .catch(function(error){
				 if(error instanceof axios.Cancel){
					// console.log('canceled:',error);
					 return config.cancelFallback && config.cancelFallback(error);
				 }
				// console.log("fail!");
				 console.error(error);
				 //如果没权限或token失效就跳转到相应页面
				 if(!handleFailStatus(error.response.status)){
				 //如果不是，只是后台提示错误，就走失败回调
					 failcallback && failcallback(error,callbackparam);
				 }
			 	})//失败回调都在拦截器中执行
			 }

/***************展示黑层——Start***************/
//创建dom元素
//var synDiv = document.createElement('div');
//
//synDiv.appendChild()
//showSynchronize = function(){
//	document.body.appendChild(synDiv);
//}
/***************展示黑层——End*****************/
//测试方法
//var test = new Vue({
//	el:"#vommonTest",
//	template:"<div><input v-model='datax'><input type='button' @click='getDatax'></div>",
//	data:{
//		datax:"x",
//	},
//	methods:{
//		getDatax:function(){
//			var _this = this;
//			var dataa={
//					chargeEndTime:"2017-03-21",
//					chargeStartTime:"2017-03-21",
//					encounterIds:["36"],
//					orTaskStatusIds:[5358, 5359],
//					orderTypeId:"",
//					statusIds:[5343]
//			};
//			_this.getData("Post","http://10.130.203.45:9090/chapp/v1/ch/searchExpenseAudit",dataa,null,function(res){
//				console.log(res);
//				console.log(_this.datax);
//				_this.datax = res.data.data[0].submittedDoctorName;
//				console.log(_this.datax);
//			},'a')
//		}
//	}
//})

/**常用方法————Start*/
//判断入参是否为undefined
function _isUndefined(val){
	return typeof val === 'undefined';
}
//删除cookie
function deleteCookie(name){
	window.document.cookie = name + "=;path=/;expires=" + (new Date("1970")).toUTCString()
}
//设置cookie
function setCookie(name, value) {  
   var exp = new Date();
   //10小时过期
   exp.setTime(exp.getTime() + 10 * 60 * 60 * 1000);   
   document.cookie = name + "=" + encodeURIComponent(value) + ";expires=" + exp.toGMTString()+";path=/";  
       return true;  
}
//cookieHandler
function CH(cookie){
	if(typeof cookie=='string'&&cookie.length>1&&cookie[0]=="\""&&cookie[cookie.length-1]=="\"")
		cookie = cookie.slice(1,-1);
	return cookie;
}
function GC(key){
	var arr, reg = new RegExp("(^| )" + key + "=([^;]*)(;|$)");
	if(arr = document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}
function BC(name,value,options){
	var path, expires;
    options = options || {};
    expires = options.expires;
    path = typeof options.path!=='undefined' ? options.path : '/';
    if (typeof value==='undefined') {
      expires = 'Thu, 01 Jan 1970 00:00:00 GMT';
      value = '';
    }
    if (typeof expires==='string') {
      expires = new Date(expires);
    }

    var str = encodeURIComponent(name) + '=' + encodeURIComponent(value);
    str += path ? ';path=' + path : '';
    str += options.domain ? ';domain=' + options.domain : '';
    str += expires ? ';expires=' + expires.toUTCString() : '';
    str += options.secure ? ';secure' : '';
    return str;
}
function SC(key,value,options){
	document.cookie = BC(key,value,options);
}
function DC(key){
    var date=new Date();
    date.setTime(date.getTime()-10000);
    document.cookie=key+"=v; expire="+date.toGMTString();
}
/**常用方法————End*/
/**事件相关——Start*/
function winOn(topic,CB,_this){
	if(!_this._isVue)_this = this
	if(window.parent === window.self){
		if(_componentBus){
			if(_this && _this._isVue){
				on(topic,function(){
					CB.apply(_this,arguments)
				})
			} else {
				CB(event)
			}
		} else throw("请确认执行环境！")
	} else {
		EventBus.subcribe(topic,function(event){
			if(_this && _this._isVue){
				CB.apply(_this,arguments)
			} else {
				CB(event)
			}
		});
	}
}
function winEmit(topic,eventCode,data){
	if(window.parent === window.self){
		if(_componentBus){
			emit(topic,{topic:topic,eventCode:eventCode,data:data})
		} else throw("请确认执行环境！")
	}
	if(window.frames.length)
		EventBus.post({
			topic:topic,
			eventCode:eventCode,
			data:data
		})
	
}
/**事件相关——End*/
//继承
var Vue = Vue.extend({
	methods:{
		getData : getData,
		winOn : winOn,
		winEmit : winEmit
	}
})
/***************共通组件通信——Start**************/
/*声明为了实现各组件间的事件传输机制而存在的空Vue实例*/
if(!_componentBus){
	var _componentBus = new Vue();
}
var emit = function(){
	_componentBus.$emit.apply(_componentBus,arguments);
}
var on = function(){
	_componentBus.$on.apply(_componentBus,arguments);
}
/***************共通组件通信——End**************/
function _showPageHolder(){
	if(_hasPageHolder()){
		document.getElementById('pageHolder').style.display = 'block'
		document.getElementById('loadingPage').style.display = 'block'
	}
}
function _hidePageHolder(){
	if(_hasPageHolder()){
		document.getElementById('pageHolder').style.display = 'none'
		document.getElementById('loadingPage').style.display = 'none'
	}
}
function _pageHolderShowing(){
	return _hasPageHolder() ? document.getElementById('pageHolder').style.display != 'none' ? true : false :
			false
}
function _hasPageHolder() {
	return !!document.getElementById('pageHolder')
}