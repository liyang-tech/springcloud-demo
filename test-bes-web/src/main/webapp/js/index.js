
var insuranceIndex = new Vue({
	el: '#indexContent',
	data: {
		/**首页insuranceIndex实例数据*/
		loginParam: {
			"loginName": '',
			"passWord": '',
		},
		usernameMsg:'',
		passwordMsg:'',
		usernameShow:false,
		passwordShow:false,
	},
	methods: {
		userLogin :function(){
			var userName = this.loginParam.loginName;
			var passWord = this.loginParam.passWord;
			//用户信息验证 暂无验证规则 alert 模拟
			if (!userName){
				this.usernameShow = true
				this.usernameMsg = '请输入用户名'
				setTimeout(function(){
					insuranceIndex.LoginMsgClose()
				},3000)
				return;
			}else if(!passWord){
				this.passwordShow = true
				this.passwordMsg = '请输入密码'
				setTimeout(function(){
					insuranceIndex.LoginMsgClose()
				},3000)
				return;
			}
			if(userName&&passWord){
				var key = CryptoJS.enc.Utf8.parse("wenchanggeliyang");
   				var srcs = CryptoJS.enc.Utf8.parse(passWord);
    			var encrypted = CryptoJS.AES.encrypt(srcs, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});								
				
				var loginData = {
	              		"loginName":userName,
						"password":encrypted.toString(),
					}
				var loginDataStr  = JSON.stringify(loginData);
				console.log(JSON.stringify(loginData));
				this.$http.post(interfaces.userLogin + '?jaraxxus=' + Date.now(),loginDataStr,{'bx_token': '','Cache-Control' : "no-cache, no-store, max-age=0"})
				.then(
					function (successData){
						switch (successData.data.code){
							case '0':
								//登陆成功方法
								var userCd = successData.data.data
								document.cookie="userCd=" + userCd;
								location.href='../systemManage.html';
								/*if(successData.data.data.initPassFlag==true){
									location.href='/systemManage/setPassword.html';
								}else{
									//var placeCode = this.queryString('place')?this.queryString('place'):''
									// VueCommonFun.setCookie('place',placeCode);
									location.href='../systemManage.html';
								}*/
							break;
							case '1':
								//用户名错误或密码错误
								insuranceIndex.usernameShow = true
								insuranceIndex.usernameMsg = successData.data.msg
								insuranceIndex.loginParam.passWord = ''
								setTimeout(function(){
									insuranceIndex.LoginMsgClose()
								},3000)
							break;
							case '2':
								//系统异常
								alert(successData.data.msg)
								//insuranceIndex.loginParam.loginName = ''
								insuranceIndex.loginParam.passWord = ''
							break;
							default:
								//系统异常
								alert(successData.data.msg)
								//insuranceIndex.loginParam.loginName = ''
								insuranceIndex.loginParam.passWord = ''
							break;
						}
					},
					function (errorResponse){
						//console.log(errorResponse)
						//系统异常
						alert('系统异常，稍后再试')
						//insuranceIndex.loginParam.loginName = ''
						insuranceIndex.loginParam.passWord = ''
					}
				)
			}
		},
		//关闭提示信息
		LoginMsgClose:function(){
			this.usernameShow=false
			this.passwordShow=false
		},
		queryString :function (name, notDecoded) {
	        name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
	        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)")
	          , results = regex.exec(location.search)
	          , encoded = null
	          ;
	        if (results === null) {
	            regex = new RegExp("[\\?&]" + name + "(\\&([^&#]*)|$)");
	            if (regex.test(location.search)) {
	                return true;
	            }
	            return undefined;
	        } else {
	            encoded = results[1].replace(/\+/g, " ");
	            if (notDecoded) {
	                return encoded;
	            }
	            return decodeURIComponent(encoded);
	        }
	    },
	},

});

