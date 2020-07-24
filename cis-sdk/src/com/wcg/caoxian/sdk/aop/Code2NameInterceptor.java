package com.wcg.caoxian.sdk.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.wcg.caoxian.sdk.bean.Result;
import com.wcg.caoxian.sdk.cache.Codes2Names;

@Aspect
@Component
@Order(0)
public class Code2NameInterceptor {

	@Autowired
	private Codes2Names codes2Names;
	
	@Pointcut(value="execution(* com.wcg..*Controller.*(..))")
	public void pointCut(){}
	
	@Around(value="pointCut()")
	public Object proceed(ProceedingJoinPoint pjp) throws Throwable{
		Object object = null;
		try {
			object = pjp.proceed();
			if(object instanceof Result){
				Result result = (Result) object;
				if(result.getData() != null){
					codes2Names.code2NameMain(result.getData());
					object = result;
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();;
		}
		return object;
	}
	
}
