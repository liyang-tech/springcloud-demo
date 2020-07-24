//console.info("加载 winpop.js");
(function(window, jQuery, undefined) {
	var __this = window,param;
    var HTMLS = {
        ovl: '<div class="J_WinpopMask winpop-mask" id="J_WinpopMask"></div>' + '<div class="J_WinpopBox winpop-box" id="J_WinpopBox">' + '<div class="topTips"><img src="../images/indeximg/mark-prompt.png" class="tipsIcon"/><div class="J_WinpopMain winpop-main"></div></div>' + '<div class="J_WinpopBtns winpop-btns"></div>' + '</div>',
        /*alert: '<input type="button" class="J_AltBtn pop-btn alert-button" value="确定">',*/
       alertSuc: '<input type="button" class="errorBtn alert-button successBtn" value="确定">',
       alertErr: '<input type="button" class="J_AltBtn errorBtn alert-button" value="知道了">',
       confirm: '<button class="J_CfmTrue pop-btn choice">确定</button>' + '<button class="J_CfmFalse pop-btn">取消</button>'
      
    }

    function Winpop() {
        var config = {};
        this.get = function(n) {
            return config[n];
        }

        this.set = function(n, v) {
            config[n] = v;
        }
        this.init();
    }

    Winpop.prototype = {
        init: function() {
            this.createDom();
            this.bindEvent();
        },
        createDom: function() {
            var body = jQuery("body"),
                ovl = jQuery("#J_WinpopBox");

            if (ovl.length === 0) {
                body.append(HTMLS.ovl);
            }

            this.set("ovl", jQuery("#J_WinpopBox"));
            this.set("mask", jQuery("#J_WinpopMask"));
        },
        bindEvent: function() {
            var _this = this,
                ovl = _this.get("ovl"),
                mask = _this.get("mask");
            ovl.on("click", ".J_AltBtn", function(e) {
                _this.hide();
            });
             ovl.on("click", ".successBtn", function(e) {
            	$('#adddevi_Modal').modal('hide');
            	$('#edit_Modal').modal('hide');
                _this.hide();
            });
            ovl.on("click", ".J_CfmTrue", function(e) {
               var cb = _this.get("confirmBack");
                _this.hide();
                cb && cb.call(__this,true,param);
                __this = window,param=undefined;
            });
            ovl.on("click", ".J_CfmFalse", function(e) {
                var cb = _this.get("confirmBack");
                _this.hide();
                cb && cb.call(__this,false,param);
                __this = window,param=undefined;
            });
            //不让模板层点击
            /*mask.on("click", function(e) {
                _this.hide();
            });*/
            jQuery(document).on("keyup", function(e) {
                var kc = e.keyCode,
                    cb = _this.get("confirmBack");;
                if (kc === 27) {
                    _this.hide();
                } 
//                else if (kc === 13) {
//                	if(new Date().getTime()-_this.timeTemp<300)return;
//                    _this.hide();
//                    if (_this.get("type") === "confirm") {
//                        cb && cb(true);
//                    }
//                }
            });
        },
        alert: function(str, btnstr) {
        	if(!str)str = '';
        	//更改提示文字位置的样式
            if(str.length>=15&&str.length<40){
            	$('.winpop-main').css('padding-top','46px');
            }else if(str.length>40){
            	$('.winpop-main').css('padding-top','17px');
            }else{
            	$('.winpop-main').css('padding-top','46px');
            }
            var str = typeof str === 'string' ? str : str.toString(),
                ovl = this.get("ovl");
            this.set("type", "alert");
            ovl.find(".J_WinpopMain").html(str);
            ovl.find(".J_WinpopBtns").html(HTMLS.alertSuc);
            ovl.find('.topTips img').attr('src','../images/indeximg/mark-success.png');
            ovl.fadeIn();
            jQuery(".winpop-mask").fadeIn();
           
//          this.set("timeout",setTimeout(function(){
//          	ovl.fadeOut(300);
//          	jQuery(".winpop-mask").stop().fadeOut(300);
//
//          	},1700));
        
        },
        alertER: function(str) {
        	if(!str)str = '';
        	//更改提示文字位置的样式
            if(str.length>=15&&str.length<40){
            	$('.winpop-main').css('padding-top','46px');
            }else if(str.length>40){
            	$('.winpop-main').css('padding-top','17px');
            }else{
            	$('.winpop-main').css('padding-top','46px');
            }
            var str = typeof str === 'string' ? str : str.toString(),
                ovl = this.get("ovl");
            this.set("type", "alert");
            ovl.find(".J_WinpopMain").html(str);
            ovl.find(".J_WinpopBtns").html(HTMLS.alertErr);
            ovl.find('.topTips img').attr('src','../images/indeximg/mark-err.png');
            
            ovl.fadeIn();
            jQuery(".winpop-mask").fadeIn();
           
//          this.set("timeout",setTimeout(function(){
//          	ovl.fadeOut(300);
//          	jQuery(".winpop-mask").stop().fadeOut(300);
//
//          	},1700));
        
        },
        confirm: function(str, callback) {
            var str = typeof str === 'string' ? str : str.toString(),
                ovl = this.get("ovl");
            ovl.find('.topTips img').attr('src','../images/indeximg/mark-prompt.png');
            //更改提示文字位置的样式
            if(str.length>=15&&str.length<40){
            	$('.winpop-main').css('padding-top','46px');
            }else if(str.length>40){
            	$('.winpop-main').css('padding-top','17px');
            }else{
            	$('.winpop-main').css('padding-top','46px');
            }
            this.set("type", "confirm");
            ovl.find(".J_WinpopMain").html(str);
            ovl.find(".J_WinpopBtns").html(HTMLS.confirm);
            this.set("confirmBack", (callback || function() {}));
            this.show();
        },
        show: function() {
            this.get("ovl").show();
          this.get("mask").show();
        	
        },
        hide: function() {
        	 var ovl = this.get("ovl");
             ovl.find(".J_WinpopMain").html("");
             ovl.find(".J_WinpopBtns").html("");
             ovl.hide();
             this.get("mask").hide();
             if(this.get("timeout")){
             	clearTimeout(this.get("timeout"));
             	this.set("timeout",0);
             }
        },
        hideconfirm: function() {
            var ovl = this.get("ovl");
            ovl.find(".J_WinpopMain").html("");
            ovl.find(".J_WinpopBtns").html("");
        },
        destory: function() {
            this.get("ovl").remove();
            this.get("mask").remove();
            delete window.alert;
            delete window.confirm;
        }
    };

    var obj = new Winpop();
    window.alert = function(str,btnstr) {
        obj.alert.call(obj, str,btnstr);
    };
     window.alertER = function(str,btnstr) {
        obj.alertER.call(obj, str,btnstr);
    };
    window.confirm = function(str, cb, _this, _param) {
    	if(_this==undefined || _this==null || typeof _this != 'object')
    		obj.confirm.call(obj, str, cb);
    	else{
    		__this = _this;
    		param = _param;
    		obj.confirm.call(obj, str, cb);
    	}
    };
})(window, jQuery);