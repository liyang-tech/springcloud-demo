//console.info('加载','VueCommon.js');
Vue.http.options.emulateJSON = true;
var VueCommonFun = new Vue({
	methods:{
		/**
		 * 通用请求方法
		 * @param {Object} options  包含的属性
		 * url 必填 请求地址;
		 * method 必填请求方式;
		 * header 选填 自定义请求头；默认携带token 如不需要可设置header.noToken = true
		 * reqData 选填 要发送的数据
		 * @param {Function} callback 成功回调函数
		 * @param {Function} failCallback 失败回调函数可选
		 */
		getData : function(options,callback,failCallback){
			'use strict';
            var _this = this;
            var _token = _this.getStorageItem('token');
			var _headers = {};
			
			/*url 为空*/
			if(!options.url){
				throw "make sure the props of 'url' exist in options";
				return;
			}
			/*methods 为空*/
			if(!options.method){
				throw "ake sure the props of 'method' exist in options";
				return;
			}
			
			if(options.header){
				for(var item in options.header){
					if(item !== 'noToken'){
						_headers[item] = options.header[item];
					}
				}
				
				if(!options.header.noToken){
					_headers["TK_AUTHEN_TOKEN"] = _token
				}
				
			}else{
				_headers = {"TK_AUTHEN_TOKEN":_token}
			}
			
			/*发送请求*/
			_this.$http({
				url:options.url,
				method:options.method,
				body:options.reqData ? options.reqData : null,
				headers:_headers
			}).then(function(resp){
				/* 调用回调函数*/
				callback && callback(resp);
			},function(err){
				/*请求数据失败*/
				failCallback && failCallback(err);
			});
        },
        /**
         * 设置缓存*/
		setStorageItem:function(itemName,value){
			return window.localStorage.setItem(itemName,value);
		},
		/*获取缓存数据*/
		getStorageItem:function(itemName){
			return window.localStorage.getItem(itemName);
		},
		/*清除缓存*/
		clearStorage:function(){
			window.localStorage.clear();
		},
		/*设置cookie*/
		setCookie:function(name, value) {  
		   var exp = new Date();
		   //3天过期
		   exp.setTime(exp.getTime() + 3 * 24 * 60 * 60 * 1000);   
		   document.cookie =name + "=" + encodeURIComponent(value) + ";expires=" + exp.toGMTString()+";path=/";  
		       return true;  
		},
		setLoginCookie:function(name, value) {  
		   var exp = new Date();
		   //3天过期
		   exp.setTime(exp.getTime() + 10 * 60 * 60 * 1000);   
		   document.cookie =name + "=" + encodeURIComponent(value) + ";expires=" + exp.toGMTString()+";path=/";  
		       return true;  
		},
		/*获取cookie*/
		getCookie:function (name) {
			var arr, reg = new RegExp("(^| )" +name + "=([^;]*)(;|$)");
			if(arr = document.cookie.match(reg))
				return unescape(arr[2]);
			else
				return null;
		}
	}
});