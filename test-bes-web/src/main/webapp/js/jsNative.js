/**JS原生方法*/
	/**Alert替代方法*/
	var alertYeo = function(text){
		console.log("Yeoing");
		EventBus.send(window.parent,{
            "eventCode": "alert", //消息编码(必须)
            "eventId": "11251820",
            "srcEventId": "0201",
            "data": text
        });
	};
	/**解析地址中'?'后的参数方法*/
	var GetURLString = function(name)
	{
		 var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.parent.window.location.href;
	     r = new String(r);
	     r = r.substr(r.indexOf('?'));
	     r = r.substr(1).match(reg);
	     if(r!=null)return  r[2]; return undefined;
	}
	/**
	 * 日期格式修改器
	 * */
	var dateShaper=function(date,rule){
		var dateCollection = analyzeDateStr(date);
		//替换
		rule=rule;
		rule=rule.replace("YYYY",dateCollection.year);
		rule=rule.replace("MM",dateCollection.month);
		rule=rule.replace("DD",dateCollection.day);
		rule=rule.replace("hh",dateCollection.hour);
		rule=rule.replace("mm",dateCollection.minute);
		rule=rule.replace("ss",dateCollection.second);
		if(rule.lastIndexOf(':')==(rule.length-1))
			rule=rule.slice(0,rule.length-1);
		dateCollection.result=rule;
		return dateCollection.result;
	}
	
	//将字符串型日期解析成普通对象
	var analyzeDateStr = function(date){
		var dateCollection={};
		//解析
		dateCollection.date=date.split(' ')[0];
		dateCollection.time=date.split(' ')[1];
		dateCollection.year =dateCollection.date.split('-')[0]||'';
		dateCollection.month=dateCollection.date.split('-')[1]||'';
		dateCollection.day	=dateCollection.date.split('-')[2]||'';
		dateCollection.hour =dateCollection.time.split(':')[0]||'';
		dateCollection.minute=dateCollection.time.split(':')[1]||'';
		dateCollection.second=dateCollection.time.split(':')[2]||'';
		return dateCollection;
	}
	
	/**将new Date() 获取的时间变为yyyy-mm-dd hh:mm:ss*/
	var commonDate = function(time){
		var m = time.getMonth()+1;
		var d = time.getDate();
		var h = time.getHours();
		var mins=time.getMinutes();
		var sec=time.getSeconds();
		var result = time.getFullYear()	+'-';
		//月
		if(m>0&&m<10)result += '0' + m;
		else if(10<=m&&m<=12)result += m;
		result+='-';
		//日
		if(0<d&&d<10)result += '0' + d;
		else if(10<=d&&d<=31)result +=d;
		result +=	' ';
		//小时
		if(0<=h&&h<10)result +=	'0' + h;
		else if(10<=h&&h<=24)result += h;
		result += ':';
		//分钟
		if(0<=mins&&mins<10)result += '0' + mins;
		else if(10<=mins&&mins<=60)result +=mins;
		result += ':';
		//秒
		if(0<=sec&&sec<10)result += '0' + sec;
		else if(10<=sec&&sec<=60)result +=sec;
		return result;
//		return (time.getFullYear()	+'-'+
//				time.getMonth()		+'-'+
//				time.getDate()		+
//				' '					+
//				time.getHours()		+':'+
//				time.getMinutes()	+':'+
//				time.getSeconds());
	};
	//将YYYY-MM-DD hh:mm:ss变为Date类型
	var Str2Date = function(str){
		var dateCollection = $scope.analyzeDateStr(str);
		var result = new Date();
		result.setYear(dateCollection.year);
		result.setMonth(dateCollection.month);
		result.setDate(dateCollection.day);
		result.setHours(dateCollection.hour);
		result.setMinutes(dateCollection.minute);
		result.setSeconds(dateCollection.second);
		return result;
	}
	var showIframe = function(){
		document.getElementById("ui-view").style.display="inline";
//		document.getElementsByTagName("iframe")[0].style.display="inline";
//		document.getElementsByClassName("routerArea")[0].style.display="inline";
//		window.frames[0].document.getElementsByTagName("body")[0].style.display="inline";
	}