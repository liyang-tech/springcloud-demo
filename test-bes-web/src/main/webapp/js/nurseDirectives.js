/**
 * 实现子元素(div)的显示和隐藏切换
 * */
app.directive('show',function(){
	    		return{
	    			restrict:'EA',
	    			link:function(scope,elem,attr){
	    				elem.bind('click',function(){
	    					elem.children('div').toggle();
	    				})
	    			}
	    		}
	    	});

/**
 * 实现下个兄弟元素的显示和隐藏切换
 * */
app.directive('showBrother',function(){
	    		return{
	    			restrict:'EA',
	    			link:function(scope,elem,attr){
	    				elem.bind('click',function(){
	    					elem.next().toggle();
	    				})
	    			}
	    		}
	    	});	    	
	    	
/**
 * 计划制定--快捷添加--常用点击样式
 * */
app.directive('planAdd',function(){
	    		return{
	    			restrict:'EA',
	    			link:function(scope,elem,attr){
	    				elem.bind('click',function(){
	    					elem.addClass('addLeft').siblings().removeClass('addLeft');
	    				})
	    			}
	    		}
	    	});	       	
/**
 * 计划制定--快捷添加--全部点击样式
 * */
app.directive('planTableAdd',function(){
	    		return{
	    			restrict:'EA',
	    			link:function(scope,elem,attr){
	    				elem.bind('click',function(){
	    					elem.addClass('mainBg navColor').siblings().removeClass('mainBg navColor');
	    				})
	    			}
	    		}
	    	});	    	
	    	
/**
 * 点击当前行--样式
 * */
app.directive('currentLine',function(){
	    		return{
	    			restrict:'EA',
	    			link:function(scope,elem,attr){
	    				elem.bind('click',function(){
	    					//elem.children().eq(0).css('border-left','1px solid #8d9eff');
	    					//elem.siblings().children().eq(0).css('border-left','1px solid #eee');
	    					elem.parent().children().removeClass('tdborder');
	    					elem.addClass('tdborder');
	    				})
	    					
	    			}
	    		}
	    	});		    	
	    	
/**
 * 点击th--取消当前行的样式
 * */
app.directive('removecurrentLine',function(){
	    		return{
	    			restrict:'EA',
	    			link:function(scope,elem,attr){
	    				elem.bind('click',function(){
	    					elem.parents('table').find('tr').removeClass('tdborder');
	    				})
	    			}
	    		}
	    	});	  
/**
 * 点击th--取消当前行的样式
 * */
app.directive('removeTbodyLine',function(){
	    		return{
	    			restrict:'EA',
	    			link:function(scope,elem,attr){
	    				elem.bind('click',function(){
	    					elem.parents('table').find('tbody').removeClass('tdborder');
	    				})
	    			}
	    		}
	    	});	

/**
 * 修改备注--样式
 * */
app.directive('updataMemo',function(){
	    		return{
	    			restrict:'EA',
	    			link:function(scope,elem,attr){
	    				elem.bind('click',function(event){
	    					event.stopPropagation();
	    					
	    					elem.parents('tr').removeClass('tdborder');
	    					elem.parents('td').addClass('tdborder');
	    				})
	    				elem.bind('blur',function(event){
	    					event.stopPropagation();
	    					elem.parents('td').removeClass('tdborder');
	    				})
	    			}
	    		}
	    	});	  
	    	
/**
 * input/textrea--获取焦点/失去焦点-样式
 * */
app.directive('tdtoStyle',function(){
	    		return{
	    			restrict:'EA',
	    			link:function(scope,elem,attr){
	    				elem.bind('focus',function(){
	    					elem.parent().addClass('tdborder');
	    				});
	    				elem.bind('blur',function(){
	    					elem.parent().removeClass('tdborder');
	    				});
	    			}
	    		}
	    	});	
/*
 * 频率查询--table样式
 * */
app.directive('tableToggle',function(){
	return{
		restrict:'EA',
		link:function(scope,elem,attr){
			elem.bind('focus',function(){
				elem.siblings().css('display','block');
			});
			elem.bind('blur',function(){
				elem.siblings().css('display','none');
			});
		}
	}
});	
//原因提示框鼠标滑入
app.directive('infotipToggle',function(){
	return{
		restrict:'EA',
		link:function(scope,elem,attr){
			elem.bind('mouseover',function(){
				elem.children('#infotip').css('display','block');
				elem.children('.tuihui').css({'color':'#8d9eff'});
			});
			elem.bind('mouseout',function(){
				elem.children('#infotip').css('display','none');
				elem.children('.tuihui').css({'color':'#363636'});
			});
		}
	}
});
//原因提示框鼠标滑入————医嘱分解
app.directive('infotipToggleEp',function(){
	return{
		restrict:'EA',
		link:function(scope,elem,attr){
			elem.parent().bind('mouseover',function(){
				elem.children('#infotip').show();
//				elem.children('.tuihui').css({'color':'#8d9eff'});
			});
			elem.parent().bind('mouseout',function(){
				elem.children('#infotip').hide();
//				elem.children('.tuihui').css({'color':'#363636'});
			});
		}
	}
});
/*
 * 可编辑--鼠标滑入/滑出样式
 * */
app.directive('editableFlag',function(){
	return{
		restrict:'EA',
		link:function(scope,elem,attr){
			elem.bind('mouseover',function(){
				elem.parent().find('img').css('display','block');
			});
			elem.bind('mouseout',function(){
				elem.parent().find('img').css('display','none');
			});
			elem.bind('focus',function(){
				elem.parent().find('img').css('display','none');
			});
		}
	}
});	

/*
 * 滚动加载更多信息
 * 
 * */
app.directive('scrollMore', function(){
	return {
		restrict:'AE',
		link:function(scope,elem,attrs){
			 	scope.presDownloadMore = false ;
			 			elem.on('scroll',function(){
			 					var sum = elem.children().eq(0).height();
								console.log(sum+'   sum');
								//console.log(scope.presDownloadMore)
								if (sum-40 <= elem.scrollTop() + elem.height() && sum-40>0) {  
									console.log('scrollAdd');
									if(scope.presDownloadMore == false){
										//scope.presDownloadMore = true ;
										scope.seachNum +=1;
										scope.$apply(attrs.loaddatafn);
									}
			 				}
								
						})
			 	}
				
			}
});

/*
 * 修改信息后，隐藏
 * 
 * */
app.directive('verDispear', function(){
	return {
		restrict:'AE',
		link:function(scope,elem,attrs){
			elem.bind('click',function(){
				elem.parent().hide();
			});
		}
	}
});
/*
 * 频率查询
 * 
 * */
app.directive('frequency',function(){
    return {
        restrict: 'AE',
        transclude:true,
        template:'<table ng-show="freqData.length>0 && prsttabShow.value==$index && prsttabShow.judge==$parent.$index" style="width:240%;position:absolute;top:100%;left:-2px;z-index:999;height:80px;overflow:auto;">'
        		+'<tr>'
	        		+'<th>编码</th>'
	        		+'<th>名称</th>'
	        	+'</tr>'
        		+'<tr ng-repeat="i in freqData" ng-mousedown="makeClick(itemData,i,$index,$event)" ng-mouseover="mouseover($index)" ng-mouseout="mouseout($index)" ng-class="{activeCurTr:act.judge==$index}">'
 					+'<td>{{i.code}}</td>'
 					+'<td>{{i.name}}</td>'
     			+'</tr>'
     			+'</table>',
        link: function(scope, elem, attr) {
        	
        }
    
    }
});
/**
 * 单次剂量和单次数量输入控制
 * */
//正数（只能输入小数点后2位）
//使用方法：在标签中加入valid-number
app.directive('validNumber', function() {
  return {
    require: '?ngModel',
    link: function(scope, element, attrs, ngModelCtrl) {
      if(!ngModelCtrl) {
        return; 
      }

      ngModelCtrl.$parsers.push(function(val) {
        if (angular.isUndefined(val)) {
            var val = '';
        }        
        var clean = val.replace(/[^0-9\.]/g, '');
			var decimalCheck = clean.split('.');
//        var negativeCheck = clean.split('-');
//        if(!angular.isUndefined(negativeCheck[1])) {
//            negativeCheck[1] = negativeCheck[1].slice(0, negativeCheck[1].length);
//            clean =negativeCheck[0] + '-' + negativeCheck[1];
//            if(negativeCheck[0].length > 0) {
//            	clean =negativeCheck[0];
//            }  
//        }
          
        if(!angular.isUndefined(decimalCheck[1])) {
      	  if(!angular.isUndefined(decimalCheck[2]))
      		  decimalCheck[1]=decimalCheck[1]+decimalCheck[2];
              decimalCheck[1] = decimalCheck[1].slice(0,2);
      	      decimalCheck[1] = decimalCheck[1].slice(0);
              clean =decimalCheck[0] + '.' + decimalCheck[1];
          }
	        if (val !== clean) {
	          ngModelCtrl.$setViewValue(clean);
	          ngModelCtrl.$render();
	        }
	        return clean;
	      });

      element.bind('keypress', function(event) {
        if(event.keyCode === 32) {
          event.preventDefault();
        }
      });
      
    }
  };
});
/**
 * 单次剂量和单次数量输入控制
 * */
//正数（只能输入正整数）
app.directive('validNumberEntireA', function(){
  return {
    require: '?ngModel',
    link: function(scope, element, attrs, ngModelCtrl) {
      if(!ngModelCtrl) {
        return; 
      }
      
      ngModelCtrl.$parsers.push(function(val) {
        if (angular.isUndefined(val)) {
            var val = '';
        }        
        var clean = val.replace(/[^0-9\.]/g, '');
			var decimalCheck = clean.split('.');
          
        if(!angular.isUndefined(decimalCheck[1])) {
      	  if(!angular.isUndefined(decimalCheck[2]))
      		  decimalCheck[1]=decimalCheck[1]+decimalCheck[2];
      	      decimalCheck[1] = decimalCheck[1].slice(0);
              clean =decimalCheck[0];
          }
	        if (val !== clean) {
	          ngModelCtrl.$setViewValue(clean);
	          ngModelCtrl.$render();
	        }
	        return clean;
	      });

      element.bind('keypress', function(event) {
        if(event.keyCode === 32) {
          event.preventDefault();
        }
      });
    }
  };
});


app.directive('myTest',function(){
	return {
		restrict:'AE',
		scope:{
			myTest:'='
			},
		link: function(scope, element, attrs, ngModelCtrl) {
			element.children('textarea').bind('focus',function(){
				console.log("aaaaa");
				console.log(scope.myTest);
			})
		}
	}
});



/*
 * 取消医嘱模块--当前的样式（文字变蓝）
 * 
 * */
app.directive('presModuleChoiced', function(){
	return {
		restrict:'AE',
		link:function(scope,elem,attrs){
			elem.bind('click',function(){
				elem.addClass('active').siblings().removeClass('active').end().children('.appSpanP').show().end().siblings().children('.appSpanP').hide();
			});
		}
	}
});
/***
 * input--ngmodel(时间转化)
 * ***/
//app.directive('datetimeFilter', ['$filter',function($filter) {  
//    var dateFilter = $filter('date');  
//    return {  
//        require: '^ngModel',  
//        link: function(scope, elm, attrs, ctrl) {  
//  
//            function formatter(value) {  
//                return dateFilter(value,'yyyy-MM-dd HH:mm'); //format  
//            }  
//  
//            function parser() {  
//                return ctrl.$modelValue;  
//            }    
//            ctrl.$formatters.push(formatter);  
//            ctrl.$parsers.unshift(parser);  
//  
//        }  
//    };  
//}]);  
/***
 * input--ngmodel(显示2位小数)
 * ***/
app.directive('numTofix', function(){
	return {
		restrict:'AE',
		require: '^ngModel',  
        link: function(scope, elem, attrs, ngModel){ 
        	// 更新模型上的视图值
            function setViewValue() {
                var val = elem.val();
                ngModel.$setViewValue(val);
            }
            /*ngModel.$viewChangeListeners = function(){
            	console.log(elem.val());
    			setViewValue();
            }*/
            /*scope.$watch(elem.val(),function(){
            	console.log(444)
            	console.log(elem.val());
            	setViewValue();
            })*/
			console.log(elem.val());
			setViewValue();
		}
	}
});
/***
 * input--ngmodel(数据双向绑定)
 * ***/
app.directive('datePicker', function($timeout){
	return {
		restrict: 'EA',
		require: '^ngModel',
		/*scope: {
			ngModel: '='
		},*/
		link: function(scope, elements, attrs, ngModel) {
			$timeout(function(){
				elements.jeDate({
				//skinCell: attrs.skinCell,
				//format: attrs.format,
				format:"YYYY-MM-DD hh:mm",
				isinitVal:false,
				isTime:true,
				isClear:true,
				minDate:"1900-01-01 00:00:00",
				maxDate:"2099-12-31 23:59:59",
				zIndex:100000,
				okfun: function(elemet,data) {
					//scope.ngModel = data;
					//scope.$apply();
					scope.$apply(setViewValue);
					console.log(elem.val());
					console.log(attrs.time());
				},
				choosefun: function(elemet, data) {
					//scope.ngModel = data;
					//scope.$apply();
					scope.$apply(setViewValue);
				}
			});
			   // 模型值同步到视图上
             ngModel.$render = function() {
                 elements.val(ngModel.$viewValue || '');
             };
             // 更新模型上的视图值
             function setViewValue() {
                 var val = elements.val();
                 ngModel.$setViewValue(val);
             }
             setViewValue();
		},0)
			
	 }
	}
});

/***
 * 科研(鼠标滑入滑出效果)
 * **/
app.directive('researchFlag', function(){
	return {
		restrict:'AE',
		link:function(scope,elem,attrs){
			elem.bind('mouseover',function(){
				elem.children('.researchflag').show();
			});
			elem.bind('mouseout',function(){
				elem.children('.researchflag').hide();
			});
		}
	}
});

/***
 * 自备(鼠标滑入滑出效果)
 * **/
app.directive('selfprovideFlag', function(){
	return {
		restrict:'AE',
		link:function(scope,elem,attrs){
			elem.bind('mouseover',function(){
				elem.siblings('.selfprovideflag').show();
			});
			elem.bind('mouseout',function(){
				elem.siblings('.selfprovideflag').hide();
			});
		}
	}
});
/***
 * 对于成组的table(鼠标滑入滑出效果)
 * **/
app.directive('groupTableStyle', function(){
	return {
		restrict:'AE',
		link:function(scope,elem,attrs){
			elem.bind('mouseover',function(){
				elem.addClass('trHoverBg');
			});
			elem.bind('mouseout',function(){
				elem.removeClass('trHoverBg');
			});
		}
	}
});

/**
 * 使input支持弹出Table的方向键操作
 */
app.directive('directionSupport', function(){
	return {
		restrict:'AE',
		scope:{
			listData:'=',
			targetData:'=',
			directionSupport:'&',
			callbackMethod:'&doEvent'
//			ngChangeFunction:'&makePresMedname'
		},
		link:function(scope,element,attrs){
			//index
			var index = -1;
			
			//判断条件
			var judgeFlag = "0noFlag";
			var judgeName = "0noName";
			var digCount = 2;
			var digRule = ['div','table','tbody','tr'];
			
			//CSS
			/*可取到的CSS：
			 * 被选中的Option-CSS：
			 * 	selectCss
			 * 	selectStyle
			 * 未选中的Option-CSS：
			 * 	defCss
			 * 	defStyle
			 * */
			var selectcolor="#f0f0f0";
			var defcolor="white";
//			if(attrs. !=undefined&&
//					attrs. !=null&&
//					attrs. !="")
//				=attrs.;
			if(attrs.selectcolor !=undefined&&
					attrs.selectcolor !=null&&
					attrs.selectcolor !="")
				selectcolor=attrs.selectcolor;
			if(attrs.defcolor !=undefined&&
					attrs.defcolor !=null&&
					attrs.defcolor !="")
				defcolor=attrs.defcolor;
			
			//获取tableBody中的tr
			var getTrGroup = function(digRule){
				var result = element;
				for(var i=0;i<digRule.length;i++){
					result=result.children(digRule[i]);
				}
				console.log("Table:");
				console.log(element.children('div').children('table'));
				console.log("tBody:");
				console.log(element.children('div').children('table').children('tbody'));
				var trGroup = element.children('div').children('table').children('tbody');
				if(trGroup.length==1)
					return trGroup.children('tr');
				else if(trGroup.length==2){
					trGroup[0] = trGroup[1];
					return trGroup.children('tr');
				}
					
			};
			
			//对应输入改变CSS Style
			var changeTr = function(choice,index){
				var trGroup = getTrGroup(digRule);
				switch(choice){
				case "up":
					//重置所有选项回到默认样式
					for(var i=0;i<trGroup.length;i++){
						trGroup[i].style.backgroundColor=defcolor;
					}
					//将选中选项变为选项样式
					trGroup[index].style.backgroundColor=selectcolor;
					break;
				case "down":
					for(var i=0;i<trGroup.length;i++){
						trGroup[i].style.backgroundColor=defcolor;
					}
					trGroup[index].style.backgroundColor=selectcolor;
					console.log("trGroup:"+index);
					console.log(trGroup);
					break;
				case "init":
					trGroup[index].style.backgroundColor=selectcolor;
					break;
				default:
				}
			};
			
			//根据所给字段，提取出数组中的对应数据
//			var findValue(array,key){
//				
//			}
			
			var keydown = function(event){
				/*左	37
				 *上	38
				 *右	39
				 *下	40
				 * */
				//获取DigRule
				if(attrs.digrule !=undefined&&
						attrs.digrule !=null&&
						attrs.digrule !="")
					digRule=attrs.digrule;
				//获取要修改的目标集合
				var trGroup = getTrGroup(digRule);
				
				//判断
				if(getTrGroup(digRule).length>0){
					
					if(element.children('div').children('table').children('tbody').children('tr').length>0){
						//按方向键上
						switch(event.keyCode){
							case 38:
								if(index==-1){
									index=0;
									changeTr('init',0);
								}
								else if(index-1>=0){
									index--;
									changeTr('up',index);
								}
								break;
							case 40:
								if(index==-1){
									index=0;
									changeTr('init',0);
								}
								else if(index+1<trGroup.length){
									index++;
									changeTr('down',index);
									console.log("down:"+index);
								}
								break;
							//回车，执行回调方法
							case 13:
							case 108:
								var temp = scope.listData[index];

								var elementnum = attrs.elementnum==undefined ? 2 : attrs.elementnum;
								if(elementnum==1){
										scope.callbackMethod()(temp);
										scope.$apply();
									}
								if(elementnum==2){
									if(attrs.judgeflag!=undefined&&
											attrs.judgeflag!=null&&
											attrs.judgeflag!='')
										judgeFlag=attrs.judgeflag;
									if(attrs.judgename!=undefined&&
											attrs.judgename!=null&&
											attrs.judgename!='')
										judgeName=attrs.judgename;
									if(attrs.digcount!=undefined&&
											attrs.digcount!=null&&
											attrs.digcount!='')
										digCount=attrs.digcount;
									if(judgeName!="0noName"&&judgeFlag!="0noFlag"){
										if(digCount==1){
											for(var i=0;i<scope.targetData.length;i++){
												if(scope.targetData[i][judgeName]==judgeFlag){//judgeFlag现在尚不支持对对应数据的匹配
													scope.callbackMethod()(temp,scope.targetData[i]);
													scope.$apply();
												}
											}
										}
										else if(digCount==2){
											for(var i=0;i<scope.targetData.length;i++){
												for(var j=0;j<scope.targetData[i].length;j++){
													if(scope.targetData[i][j][judgeName]==judgeFlag){//judgeFlag现在尚不支持对对应数据的匹配
																scope.callbackMethod()(temp,scope.targetData[i][j]);
																scope.$apply();
														}
												}
											}
										}
									}
								}
								judgeName="0noName";
								judgeFlag="0noFlag";
								digCount=2;
								digRule = ['div','table','tbody','tr'];
							default:
						}
						console.log(index);
					}
				}
				else index=-1;
			}
			
			//按键判断
			if(element.children("input").length!=0){
				element.children("input").on('keydown',keydown);
				
				element.children("input").on('blur',function(){
					index=-1;
				});
				
				element.children("input").on('focus',function(){
					index=-1;
				});
			}
			else if(element.children("textarea").length!=0){
				element.children("textarea").on('keydown',keydown);
				
				element.children("textarea").on('blur',function(){
					index=-1;
				});
				
				element.children("textarea").on('focus',function(){
					index=-1;
				});
			}
		}
	}
});

/***
 * 医嘱确认--右侧菜单栏(鼠标滑入滑出效果)
 * **/
app.directive('nurseConfirm', function(){
	return {
		restrict:'AE',
		link:function(scope,elem,attrs){
			elem.bind('mouseenter',function(){
				elem.children('.nurseConfirm').show();
				elem.children('.confirmChoiced1').hide();
			});
			elem.bind('mouseleave',function(){
				elem.children('.nurseConfirm').hide();
				elem.children('.confirmChoiced1').show();
			});
		}
	}
});

/***
 * 分页--点击当前样式
 * **/
app.directive('pagenate', function(){
	return {
		restrict:'AE',
		link:function(scope,elem,attrs){
			elem.on('click',function(){
				console.log(20202)
				elem.children('li').find('a').addClass('aVisited').stop().siblings().find('a').removeClass('aVisited');
			})
		}
	}
});



