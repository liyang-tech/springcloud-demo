var resizeTimer = null;
$(window).bind('resize', function (){
        if (resizeTimer) clearTimeout(resizeTimer);
        resizeTimer = setTimeout(function(){
                bottomWrapC()
        } , 500);
});

bottomWrapC()

function bottomWrapC() {
		$(".bottomWrap").css("position","static");
    	var bottomWrapY=$(".bottomWrap").offset().top;//
		var bottomWrapH=$(".bottomWrap").height();
		var h=$(window).height();
		var x=bottomWrapY+bottomWrapH;
	
		if(h<x){
			$(".bottomWrap").css("position","static");
			$("body").css({"padding-bottom":"0"});
		}else{
			
			$(".bottomWrap").css({"position":"fixed","bottom":0});
			$("body").css({"padding-bottom":bottomWrapH});

		}		
} 
var setPassword = new Vue({
	el:'#setPassword',
	data:{
		oldPass:'',
		newPass:'',
		conPass:'',
		conPassMsg:'',
		newPassMsg:'',
		newPassMsgL:'',
		oldPassMsg:'',
		newPassShow:false,
		newPassShowL:false,
		conPassShow:false,
		oldPassShow:false,
		userCd:''
	},
	methods:{
		//获取userCd
		getUserCdBySession:function(){
			var _this = this
			_this.getData('get',interfaces.userSession,null,null,
				function(data){
					_this.userCd = data.data.data.userCd
				},null,
				function  (res) {
					//console.log(res)
					
				}
			)
		},
		setPassword:function(){
			if(!this.oldPass){
				this.oldPassShow = true
				this.oldPassMsg = '请输入旧密码'
				setTimeout(function(){
					setPassword.LoginMsgClose()
				},3000)
				return
			}else if(!this.newPass){
				this.newPassShow = true
				this.newPassMsg = '请输入新密码'
				setTimeout(function(){
					setPassword.LoginMsgClose()
				},3000)
				return
			}else if(this.newPass.length<6 || this.newPass.length>20){
				this.newPassShowL = true
				this.newPassMsg = '请您输入6-20位新密码'
				setTimeout(function(){
					setPassword.LoginMsgClose()
				},3000)
				return
			}else if(!this.conPass){
				this.conPassShow = true
				this.conPassMsg = '请确认新密码'
				setTimeout(function(){
					setPassword.LoginMsgClose()
				},3000)
				return
			}else if(this.conPass != this.newPass){
				this.conPassShow = true
				this.conPassMsg = '两次输入密码不一致'
				this.newPass = ''
				this.conPass = ''
				setTimeout(function(){
					setPassword.LoginMsgClose()
				},3000)
				return
			}
			if(this.newPass&&this.conPass&&this.oldPass&&this.conPass == this.newPass&&this.newPass.length>5&&this.newPass.length<21){
				var _this = this
				getData('PUT',interfaces.modifyPassword+_this.userCd+'/$modifyPassword',
					{
						oldPassword:_this.oldPass,
						newPassword:_this.newPass,
						userCd:_this.userCd
					},null,
					function(successData){
						//console.log(successData)
						if(successData.data.code=='0'){
							alert('密码修改成功，请重新登录')
							setTimeout(function(){
								location.href='/systemManage/index.html';
							},1800)
						}
					},null,
					function(errorData){
						if(errorData.data.code=='USR0330_05' || errorData.data.code=='USER1001'){
							_this.oldPass = ''
							_this.newPass = ''
							_this.conPass = ''
							alert('旧密码输入错误，请重新输入')
						}else if(errorData.data.code=='USER1002'){
							_this.oldPass = ''
							_this.newPass = ''
							_this.conPass = ''
							alert('新密码格式不正确，请重新输入')
						}else{
							_this.oldPass = ''
							_this.newPass = ''
							_this.conPass = ''
							alert('系统异常，稍后再试')
						}
					}
				)
			}
		},
		
		//关闭提示信息
		LoginMsgClose:function(){
			this.newPassShow=false
			this.newPassShowL=false
			this.conPassShow=false
			this.oldPassShow=false
		}
	},
	mounted:function(){
		this.getUserCdBySession()
	}
})
