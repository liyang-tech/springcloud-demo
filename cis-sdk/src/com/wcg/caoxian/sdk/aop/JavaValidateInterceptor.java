package com.wcg.caoxian.sdk.aop;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.wcg.caoxian.sdk.bean.Result;
import com.wcg.caoxian.sdk.config.CodeProperties;
import com.wcg.caoxian.sdk.exception.BusinessException;


@Aspect
@Component
@Order(2)
@ConfigurationProperties(prefix="spring.log")
public class JavaValidateInterceptor {
	
	private Boolean write;
	
	private static final Logger logger=LoggerFactory.getLogger(JavaValidateInterceptor.class);

	@Pointcut(value="execution(* com.wcg..*Controller.*(..))")
	public void pointCut(){}
	
	@Around(value="pointCut()")
	public Object proceed(ProceedingJoinPoint pjp) {
		//获取请求信息
		RequestAttributes ras = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes)ras;
		HttpServletRequest request = servletRequestAttributes.getRequest();
		//获取方法公共参数
		String uuid = request.getHeader("TK_BUSINESS_SERIALID");//交易流水号
		String system = request.getHeader("TK_REQUEST_SYS_CODE");//请求方编码
		String module = request.getHeader("TK_REQUEST_MODULE_CODE");//请求方模块编码
		String ip = request.getHeader("TK_REQUEST_NODE_IP");//请求方ip
		/*String requestURL =  request.getRequestURL().toString();//请求地址*/
		String requestMethod = request.getMethod();//请求方法
		String methodName="";//方法名称或编码
		String methodDescription="";//方法描述
		Signature signature = pjp.getSignature();
		//获取入参
		Object[] paramsObject=pjp.getArgs();
		String params="[";
		for (Object object : paramsObject) {
			if(!(object instanceof BeanPropertyBindingResult||object instanceof HttpServletRequest||object instanceof HttpServletResponse)){
				params += JSON.toJSONString(object);
			}
		}
		params+="]";
		//获取方法名称/编码和方法描述
		MethodSignature methodSignature=(MethodSignature)signature;
		Method method = methodSignature.getMethod();
		if(method.isAnnotationPresent(InterfaceApiLabel.class)){
			InterfaceApiLabel interfaceApi = method.getAnnotation(InterfaceApiLabel.class);
			methodName=interfaceApi.value();
			methodDescription=interfaceApi.description();
		}
		//spring.log.write为true才会记录正常日志，为空或者false不记录
		writeLog("REQUEST",uuid, system, module, ip,requestMethod, methodName,methodDescription);
		try {
			//获取Controller的参数即(@Valid @RequestBody Customer customer,BindingResult bindingResult,@RequestHeader HttpHeaders headers)
			Object[] controllerParams = pjp.getArgs();
			// 输入参数对象通用校验
			for (Object controllerParam : controllerParams) {
				//循环参数找到BeanPropertyBindingResult类型，即BindingResult
				if (controllerParam instanceof BeanPropertyBindingResult) {	
					//转换为BeanPropertyBindingResult类型
					BeanPropertyBindingResult bindingResult = (BeanPropertyBindingResult) controllerParam;
					//如果有错误信息则进入
					if (bindingResult.hasFieldErrors()) {			
						//如果有错误则把错误信息装载进入lisr
						List<FieldError> fieldErrorsList = bindingResult.getFieldErrors();
						//循环获取错误列表里面的每个字段的字段名和vo里面定义的错误message
						for (FieldError error : fieldErrorsList) {
							//获取vo里面定义的错误message
							String labelMessage = error.getDefaultMessage();
							//获取增加标签的字段名
							String fieldName = error.getField();
		                    //取出错误message里面的message的code
							String code = this.fetchMsgCode(labelMessage);
							//根据message的code和字段名获取错误信息
							String resultMsg = this.getResultMessage(labelMessage, fieldName);
		                     //装载返回错误的格式串
							exceptionResponse(uuid, system, module, ip, requestMethod, methodName, methodDescription,resultMsg,params, null);
							//writeLog("RESPONSE",uuid, system, module, ip,requestMethod, methodName,methodDescription);
							return new Result<Object>(code,resultMsg);
						}
					}
				}

			}
			// 执行业务处理
			Object proceed = pjp.proceed();
			writeLog("RESPONSE",uuid, system, module, ip,requestMethod, methodName,methodDescription);
			return proceed;
		} catch (BusinessException e) {
			// 业务异常
			warnResponse(uuid, system, module, ip, requestMethod, methodName, methodDescription,"业务异常！",params, e);
			return new Result<Object>(e.getCode(), e.getMessage(), null, e.getCause());
		} catch (ArrayIndexOutOfBoundsException e) {
			// 数组下标越界!
			exceptionResponse(uuid, system, module, ip, requestMethod, methodName, methodDescription,"数组下标越界！",params, e);
			return new Result<Object>("10102", e);
		} catch (ArithmeticException e) {
			// 数学运算异常！
			exceptionResponse(uuid, system, module, ip, requestMethod, methodName, methodDescription,"数学运算异常！！",params, e);
			return new Result<Object>("10103", e);
		} catch (NullPointerException e) {
			// 空指针异常！
			exceptionResponse(uuid, system, module, ip, requestMethod, methodName, methodDescription,"空指针异常！",params, e);
			return new Result<Object>("10104", e);
		} catch (ClassNotFoundException e) {
			// 找不到类异常
			exceptionResponse(uuid, system, module, ip, requestMethod, methodName, methodDescription,"找不到类异常",params, e);
			return new Result<Object>("10105", e);
		} catch (IOException e) {
			// IO异常
			exceptionResponse(uuid, system, module, ip, requestMethod, methodName, methodDescription,"IO异常",params, e);
			return new Result<Object>("10106", e);
		} catch (IllegalArgumentException e) {
			// 方法的参数错误
			exceptionResponse(uuid, system, module, ip, requestMethod, methodName, methodDescription,"方法的参数错误",params, e);
			return new Result<Object>("10107", e);
		} catch (ClassCastException e) {
			// 类型强制转换错误！
			exceptionResponse(uuid, system, module, ip, requestMethod, methodName, methodDescription,"类型强制转换错误！",params, e);
			return new Result<Object>("10108", e);
		} catch (SecurityException e) {
			// 违背安全原则异常！
			exceptionResponse(uuid, system, module, ip, requestMethod, methodName, methodDescription,"违背安全原则异常！",params, e);
			return new Result<Object>("10109", e);
		} catch (SQLException e) {
			// 操作数据库异常！		
			exceptionResponse(uuid, system, module, ip, requestMethod, methodName, methodDescription,"操作数据库异常！",params, e);
			return new Result<Object>("10110", e);
		} catch (NoSuchMethodException e) {
			// 方法未找到异常！			
			exceptionResponse(uuid, system, module, ip, requestMethod, methodName, methodDescription,"方法未找到异常！",params, e);
			return new Result<Object>("10111", e);
		} catch (InternalError e) {
			// Java虚拟机发生了内部错误！
			exceptionResponse(uuid, system, module, ip, requestMethod, methodName, methodDescription,"Java虚拟机发生了内部错误!",params, e);
			return new Result<Object>("10112", e);
		} catch (Throwable e) {
			// 未知的错误
			exceptionResponse(uuid, system, module, ip, requestMethod, methodName, methodDescription,"未知的错误",params, e);
			return new Result<Object>("2", e);
		}
	}
	
	private void exceptionResponse(String uuid, String system, String module, String ip,
			String requestMethod, String methodName, String methodDescription, String errorMessage,String params,Throwable e) {
		logger.error("["+errorMessage+"][null]["+uuid+"][" + system + "][" + module + "][" + ip
				+ "]["+requestMethod+"]["+methodName+"]["+methodDescription+"]["+params+"]",e);
	}
	
	private void warnResponse(String uuid, String system, String module, String ip,
			String requestMethod, String methodName, String methodDescription, String errorMessage,String params,Throwable e) {
		logger.warn("["+errorMessage+"][null]["+uuid+"][" + system + "][" + module + "][" + ip
				+ "]["+requestMethod+"]["+methodName+"]["+methodDescription+"]["+params+"]",e);
	}
	
	private void writeLog(String type,String uuid, String system, String module, String ip,String requestMethod,
			String methodName, String methodDescription) {
		if(write!=null){
			if(write){
				logger.info("["+type+"][null]["+uuid+"][" + system + "][" + module + "][" + ip
						+ "]["+requestMethod+"]["+methodName+"]["+methodDescription+"]");
			}
		}
		
	}

	protected String getResultMessage(String labelMessage, String fieldName) {
		//根据标签message信息查询code
		String code = fetchMsgCode(labelMessage);
		//根据标签message和字段名称获取参数数组
		String[] params = fetchMsgParams(labelMessage, fieldName);
		//获取公共参数的消息串返回
		return CodeProperties.getInstance().getValue(code, params);
	}

	private String fetchMsgCode(String labelMessage) {
		if (StringUtils.isEmpty(labelMessage)) {
			return null;
		}

		String[] fields = labelMessage.split(",");
		return fields[0];
	}

	private String[] fetchMsgParams(String labelMessage, String fieldName) {
		if (StringUtils.isEmpty(labelMessage)) {
			return new String[] {};
		}

		String[] configParams = labelMessage.split(",");
		String[] params = new String[configParams.length];
		params[0] = fieldName;

		if (configParams.length > 1) {
			System.arraycopy(configParams, 1, params, 1, configParams.length - 1);
		}

		return params;
	}
	public Boolean getWrite() {
		return write;
	}
	public void setWrite(Boolean write) {
		this.write = write;
	}
}