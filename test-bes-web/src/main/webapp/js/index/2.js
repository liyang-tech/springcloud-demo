app.factory("$tools",function($http,$cookieStore){
	var $tools={
		http:function(type,url,data,headers,callbackfun,failedcallbackfun){
			$http({
				method:type,
				url:url,
				data:data,
				headers:headers
			}).success(function(data,status,headers,config){
				callbackfun(data,status,headers,config);
			}).error(function(data,status,headers,config){
				console.log("调用服务端失败了 ",url,data);
			});
		}
	};
	return $tools;
});