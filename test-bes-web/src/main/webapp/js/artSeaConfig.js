/*!art-template - Template Engine | http://aui.github.com/artTemplate/*/
!function(){function a(a){return a.replace(t,"").replace(u,",").replace(v,"").replace(w,"").replace(x,"").split(y)}function b(a){return"'"+a.replace(/('|\\)/g,"\\$1").replace(/\r/g,"\\r").replace(/\n/g,"\\n")+"'"}function c(c,d){function e(a){return m+=a.split(/\n/).length-1,k&&(a=a.replace(/\s+/g," ").replace(/<!--[\w\W]*?-->/g,"")),a&&(a=s[1]+b(a)+s[2]+"\n"),a}function f(b){var c=m;if(j?b=j(b,d):g&&(b=b.replace(/\n/g,function(){return m++,"$line="+m+";"})),0===b.indexOf("=")){var e=l&&!/^=[=#]/.test(b);if(b=b.replace(/^=[=#]?|[\s;]*$/g,""),e){var f=b.replace(/\s*\([^\)]+\)/,"");n[f]||/^(include|print)$/.test(f)||(b="$escape("+b+")")}else b="$string("+b+")";b=s[1]+b+s[2]}return g&&(b="$line="+c+";"+b),r(a(b),function(a){if(a&&!p[a]){var b;b="print"===a?u:"include"===a?v:n[a]?"$utils."+a:o[a]?"$helpers."+a:"$data."+a,w+=a+"="+b+",",p[a]=!0}}),b+"\n"}var g=d.debug,h=d.openTag,i=d.closeTag,j=d.parser,k=d.compress,l=d.escape,m=1,p={$data:1,$filename:1,$utils:1,$helpers:1,$out:1,$line:1},q="".trim,s=q?["$out='';","$out+=",";","$out"]:["$out=[];","$out.push(",");","$out.join('')"],t=q?"$out+=text;return $out;":"$out.push(text);",u="function(){var text=''.concat.apply('',arguments);"+t+"}",v="function(filename,data){data=data||$data;var text=$utils.$include(filename,data,$filename);"+t+"}",w="'use strict';var $utils=this,$helpers=$utils.$helpers,"+(g?"$line=0,":""),x=s[0],y="return new String("+s[3]+");";r(c.split(h),function(a){a=a.split(i);var b=a[0],c=a[1];1===a.length?x+=e(b):(x+=f(b),c&&(x+=e(c)))});var z=w+x+y;g&&(z="try{"+z+"}catch(e){throw {filename:$filename,name:'Render Error',message:e.message,line:$line,source:"+b(c)+".split(/\\n/)[$line-1].replace(/^\\s+/,'')};}");try{var A=new Function("$data","$filename",z);return A.prototype=n,A}catch(B){throw B.temp="function anonymous($data,$filename) {"+z+"}",B}}var d=function(a,b){return"string"==typeof b?q(b,{filename:a}):g(a,b)};d.version="3.0.0",d.config=function(a,b){e[a]=b};var e=d.defaults={openTag:"<%",closeTag:"%>",escape:!0,cache:!0,compress:!1,parser:null},f=d.cache={};d.render=function(a,b){return q(a,b)};var g=d.renderFile=function(a,b){var c=d.get(a)||p({filename:a,name:"Render Error",message:"Template not found"});return b?c(b):c};d.get=function(a){var b;if(f[a])b=f[a];else if("object"==typeof document){var c=document.getElementById(a);if(c){var d=(c.value||c.innerHTML).replace(/^\s*|\s*$/g,"");b=q(d,{filename:a})}}return b};var h=function(a,b){return"string"!=typeof a&&(b=typeof a,"number"===b?a+="":a="function"===b?h(a.call(a)):""),a},i={"<":"&#60;",">":"&#62;",'"':"&#34;","'":"&#39;","&":"&#38;"},j=function(a){return i[a]},k=function(a){return h(a).replace(/&(?![\w#]+;)|[<>"']/g,j)},l=Array.isArray||function(a){return"[object Array]"==={}.toString.call(a)},m=function(a,b){var c,d;if(l(a))for(c=0,d=a.length;d>c;c++)b.call(a,a[c],c,a);else for(c in a)b.call(a,a[c],c)},n=d.utils={$helpers:{},$include:g,$string:h,$escape:k,$each:m};d.helper=function(a,b){o[a]=b};var o=d.helpers=n.$helpers;d.onerror=function(a){var b="Template Error\n\n";for(var c in a)b+="<"+c+">\n"+a[c]+"\n\n";"object"==typeof console&&console.error(b)};var p=function(a){return d.onerror(a),function(){return"{Template Error}"}},q=d.compile=function(a,b){function d(c){try{return new i(c,h)+""}catch(d){return b.debug?p(d)():(b.debug=!0,q(a,b)(c))}}b=b||{};for(var g in e)void 0===b[g]&&(b[g]=e[g]);var h=b.filename;try{var i=c(a,b)}catch(j){return j.filename=h||"anonymous",j.name="Syntax Error",p(j)}return d.prototype=i.prototype,d.toString=function(){return i.toString()},h&&b.cache&&(f[h]=d),d},r=n.$each,s="break,case,catch,continue,debugger,default,delete,do,else,false,finally,for,function,if,in,instanceof,new,null,return,switch,this,throw,true,try,typeof,var,void,while,with,abstract,boolean,byte,char,class,const,double,enum,export,extends,final,float,goto,implements,import,int,interface,long,native,package,private,protected,public,short,static,super,synchronized,throws,transient,volatile,arguments,let,yield,undefined",t=/\/\*[\w\W]*?\*\/|\/\/[^\n]*\n|\/\/[^\n]*$|"(?:[^"\\]|\\[\w\W])*"|'(?:[^'\\]|\\[\w\W])*'|\s*\.\s*[$\w\.]+/g,u=/[^\w$]+/g,v=new RegExp(["\\b"+s.replace(/,/g,"\\b|\\b")+"\\b"].join("|"),"g"),w=/^\d[^,]*|,\d[^,]*/g,x=/^,+|,+$/g,y=/^$|,+/;"function"==typeof define?define(function(){return d}):"undefined"!=typeof exports?module.exports=d:this.template=d}();
/*! Sea.js 2.1.1 | seajs.org/LICENSE.md
//# sourceMappingURL=sea.js.map
*/(function(t,u){function v(b){return function(c){return Object.prototype.toString.call(c)==="[object "+b+"]"}}function Q(){return w++}function I(b,c){var a;a=b.charAt(0);if(R.test(b))a=b;else if("."===a){a=(c?c.match(E)[0]:h.cwd)+b;for(a=a.replace(S,"/");a.match(J);)a=a.replace(J,"/")}else a="/"===a?(a=h.cwd.match(T))?a[0]+b.substring(1):b:h.base+b;return a}function K(b,c){if(!b)return"";var a=b,d=h.alias,a=b=d&&F(d[a])?d[a]:a,d=h.paths,g;if(d&&(g=a.match(U))&&F(d[g[1]]))a=d[g[1]]+g[2];g=a;var e=h.vars;
e&&-1<g.indexOf("{")&&(g=g.replace(V,function(a,b){return F(e[b])?e[b]:a}));a=g.length-1;d=g.charAt(a);b="#"===d?g.substring(0,a):".js"===g.substring(a-2)||0<g.indexOf("?")||".css"===g.substring(a-3)||"/"===d?g:g+".js";g=I(b,c);var a=h.map,l=g;if(a)for(var d=0,f=a.length;d<f&&!(l=a[d],l=x(l)?l(g)||g:g.replace(l[0],l[1]),l!==g);d++);return l}function L(b,c){var a=b.sheet,d;if(M)a&&(d=!0);else if(a)try{a.cssRules&&(d=!0)}catch(g){"NS_ERROR_DOM_SECURITY_ERR"===g.name&&(d=!0)}setTimeout(function(){d?
c():L(b,c)},20)}function W(){if(y)return y;if(z&&"interactive"===z.readyState)return z;for(var b=s.getElementsByTagName("script"),c=b.length-1;0<=c;c--){var a=b[c];if("interactive"===a.readyState)return z=a}}function e(b,c){this.uri=b;this.dependencies=c||[];this.exports=null;this.status=0;this._waitings={};this._remain=0}if(!t.seajs){var f=t.seajs={version:"2.1.1"},h=f.data={},X=v("Object"),F=v("String"),A=Array.isArray||v("Array"),x=v("Function"),w=0,p=h.events={};f.on=function(b,c){(p[b]||(p[b]=
[])).push(c);return f};f.off=function(b,c){if(!b&&!c)return p=h.events={},f;var a=p[b];if(a)if(c)for(var d=a.length-1;0<=d;d--)a[d]===c&&a.splice(d,1);else delete p[b];return f};var m=f.emit=function(b,c){var a=p[b],d;if(a)for(a=a.slice();d=a.shift();)d(c);return f},E=/[^?#]*\//,S=/\/\.\//g,J=/\/[^/]+\/\.\.\//,U=/^([^/:]+)(\/.+)$/,V=/{([^{]+)}/g,R=/^\/\/.|:\//,T=/^.*?\/\/.*?\//,n=document,q=location,B=q.href.match(E)[0],k=n.getElementsByTagName("script"),k=n.getElementById("seajsnode")||k[k.length-
1],k=((k.hasAttribute?k.src:k.getAttribute("src",4))||B).match(E)[0],s=n.getElementsByTagName("head")[0]||n.documentElement,N=s.getElementsByTagName("base")[0],O=/\.css(?:\?|$)/i,Y=/^(?:loaded|complete|undefined)$/,y,z,M=536>1*navigator.userAgent.replace(/.*AppleWebKit\/(\d+)\..*/,"$1"),Z=/"(?:\\"|[^"])*"|'(?:\\'|[^'])*'|\/\*[\S\s]*?\*\/|\/(?:\\\/|[^\/\r\n])+\/(?=[^\/])|\/\/.*|\.\s*require|(?:^|[^$])\brequire\s*\(\s*(["'])(.+?)\1\s*\)/g,$=/\\\\/g,r=f.cache={},C,G={},H={},D={},j=e.STATUS={FETCHING:1,
SAVED:2,LOADING:3,LOADED:4,EXECUTING:5,EXECUTED:6};e.prototype.resolve=function(){for(var b=this.dependencies,c=[],a=0,d=b.length;a<d;a++)c[a]=e.resolve(b[a],this.uri);return c};e.prototype.load=function(){if(!(this.status>=j.LOADING)){this.status=j.LOADING;var b=this.resolve();m("load",b);for(var c=this._remain=b.length,a,d=0;d<c;d++)a=e.get(b[d]),a.status<j.LOADED?a._waitings[this.uri]=(a._waitings[this.uri]||0)+1:this._remain--;if(0===this._remain)this.onload();else{for(var g={},d=0;d<c;d++)a=
r[b[d]],a.status<j.FETCHING?a.fetch(g):a.status===j.SAVED&&a.load();for(var h in g)if(g.hasOwnProperty(h))g[h]()}}};e.prototype.onload=function(){this.status=j.LOADED;this.callback&&this.callback();var b=this._waitings,c,a;for(c in b)if(b.hasOwnProperty(c)&&(a=r[c],a._remain-=b[c],0===a._remain))a.onload();delete this._waitings;delete this._remain};e.prototype.fetch=function(b){function c(){var a=g.requestUri,b=g.onRequest,c=g.charset,d=O.test(a),e=n.createElement(d?"link":"script");if(c&&(c=x(c)?
c(a):c))e.charset=c;var f=e;d&&(M||!("onload"in f))?setTimeout(function(){L(f,b)},1):f.onload=f.onerror=f.onreadystatechange=function(){Y.test(f.readyState)&&(f.onload=f.onerror=f.onreadystatechange=null,!d&&!h.debug&&s.removeChild(f),f=null,b())};d?(e.rel="stylesheet",e.href=a):(e.async=!0,e.src=a);y=e;N?s.insertBefore(e,N):s.appendChild(e);y=null}function a(){delete G[f];H[f]=!0;C&&(e.save(d,C),C=null);var a,b=D[f];for(delete D[f];a=b.shift();)a.load()}var d=this.uri;this.status=j.FETCHING;var g=
{uri:d};m("fetch",g);var f=g.requestUri||d;!f||H[f]?this.load():G[f]?D[f].push(this):(G[f]=!0,D[f]=[this],m("request",g={uri:d,requestUri:f,onRequest:a,charset:h.charset}),g.requested||(b?b[g.requestUri]=c:c()))};e.prototype.exec=function(){function b(a){return e.get(b.resolve(a)).exec()}if(this.status>=j.EXECUTING)return this.exports;this.status=j.EXECUTING;var c=this.uri;b.resolve=function(a){return e.resolve(a,c)};b.async=function(a,g){e.use(a,g,c+"_async_"+w++);return b};var a=this.factory,a=
x(a)?a(b,this.exports={},this):a;a===u&&(a=this.exports);null===a&&!O.test(c)&&m("error",this);delete this.factory;this.exports=a;this.status=j.EXECUTED;m("exec",this);return a};e.resolve=function(b,c){var a={id:b,refUri:c};m("resolve",a);return a.uri||K(a.id,c)};e.define=function(b,c,a){var d=arguments.length;1===d?(a=b,b=u):2===d&&(a=c,A(b)?(c=b,b=u):c=u);if(!A(c)&&x(a)){var g=[];a.toString().replace($,"").replace(Z,function(a,b,c){c&&g.push(c)});c=g}d={id:b,uri:e.resolve(b),deps:c,factory:a};if(!d.uri&&
n.attachEvent){var f=W();f&&(d.uri=f.src)}m("define",d);d.uri?e.save(d.uri,d):C=d};e.save=function(b,c){var a=e.get(b);a.status<j.SAVED&&(a.id=c.id||b,a.dependencies=c.deps||[],a.factory=c.factory,a.status=j.SAVED)};e.get=function(b,c){return r[b]||(r[b]=new e(b,c))};e.use=function(b,c,a){var d=e.get(a,A(b)?b:[b]);d.callback=function(){for(var a=[],b=d.resolve(),e=0,f=b.length;e<f;e++)a[e]=r[b[e]].exec();c&&c.apply(t,a);delete d.callback};d.load()};e.preload=function(b){var c=h.preload,a=c.length;
a?e.use(c,function(){c.splice(0,a);e.preload(b)},h.cwd+"_preload_"+w++):b()};f.use=function(b,c){e.preload(function(){e.use(b,c,h.cwd+"_use_"+w++)});return f};e.define.cmd={};t.define=e.define;f.Module=e;h.fetchedList=H;h.cid=Q;f.resolve=K;f.require=function(b){return(r[e.resolve(b)]||{}).exports};h.base=(k.match(/^(.+?\/)(\?\?)?(seajs\/)+/)||["",k])[1];h.dir=k;h.cwd=B;h.charset="utf-8";var B=h,P=[],q=q.search.replace(/(seajs-\w+)(&|$)/g,"$1=1$2"),q=q+(" "+n.cookie);q.replace(/(seajs-\w+)=1/g,function(b,
c){P.push(c)});B.preload=P;f.config=function(b){for(var c in b){var a=b[c],d=h[c];if(d&&X(d))for(var e in a)d[e]=a[e];else A(d)?a=d.concat(a):"base"===c&&("/"===a.slice(-1)||(a+="/"),a=I(a)),h[c]=a}m("config",b);return f}}})(this);// config.js
seajs.config({
	base:'../tkui/',
	/*paths: {
	       'url': 'http://localhost:8088/tkh-user-app/static'
	   },*/
	alias:{
        eventbus: "lib/eventBus/EventBus-3.0",
		jquery: "lib/jquery/jquery-1.11.1.min",　
        bootstrap:"lib/bootstrap/js/bootstrap",
		angular:"lib/angular/angular",
		angularuitree:"lib/angular/angular-ui-tree.min",
		angularUIroute:"lib/angular/angular-ui-router",
		angularcookies:"lib/angular/angular-cookies.min",
		uibootstrap:'lib/angular/ui-bootstrap-tpls',
		common:"js/common",
		taggedautogrow:"js/taggedAutogrow",
		winpop:"js/winpop",
		scrollable:'lib/jquery/scrollable',
		smartTable:'lib/smarttable/smart-table',
		mySmartTable:'lib/smarttable/my-smart-table',
		SmartTableDebug:'lib/smarttable/Smart-Table.debug',
		bootstrapSelect:'lib/bootstrap/js/bootstrap-select2',
		angucomplete:"lib/angular/angucomplete",
		pinyinO:"lib/getFirstLetter/pinyin_dict_firstletter",
		pinyinS:"lib/getFirstLetter/pinyinUtil"
	},
	debug: true,
	preload: ['jquery','interface/interfacesConfig'],
	map: [
          [ /^(.*\.(?:css|js))(.*)$/i, '$1?2017040264' ]
    ]
});
seajs.use(["eventbus","jquery","bootstrap","angular"],function(){
	//加载全站都需要的入口
	var _page=$("#moduleId").attr("pagename");
	//需要html5校验时加入js
	
    seajs.use('angularuitree');
    seajs.use('angularUIroute');
	seajs.use("angularcookies");
	seajs.use('uibootstrap');
	seajs.use('taggedautogrow');
	seajs.use('scrollable');
	seajs.use('smartTable');
	seajs.use('mySmartTable');
	seajs.use('SmartTableDebug');
	seajs.use('bootstrapSelect');
	seajs.use('angucomplete');
	seajs.use('pinyinO');
	seajs.use('pinyinS');
	if(!_page){return false};
    //页面结尾以Search结束的代表是查询页面，需要引入表格组件
    /*  //if(_page.indexOf("Search")!=-1){
    	seajs.use('bootstrap_table');
    	$.getScript("../static/lib/bootstrap-table.min.js",function(){
    		seajs.use('bootstrap_table_CN');
    		});
    //}*/

    seajs.use(['eventbus',"jquery","angular"],function(){
//     console.info('eventbus,jquery,angular  加载完毕......');
        seajs.use(["modules/"+_page],function(){
        	seajs.use('common');
			seajs.use("winpop");
        });
    });

});
