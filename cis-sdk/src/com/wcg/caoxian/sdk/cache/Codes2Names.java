package com.wcg.caoxian.sdk.cache;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcg.caoxian.sdk.exception.ErrorHandler;

@Service("codes2Names")
public class Codes2Names {

	@Autowired
	private MasterService masterService;
	
	public void code2NameMain(Object voParam){
		if(voParam instanceof List){
			code2NameBatch(voParam);
			return;
		}else{
			code2Name(voParam);
		}
	}

	//code转name
	private void code2Name(Object voParam) {
		if(voParam == null){
			ErrorHandler.reportError("10104");
		}
		if(isVo(voParam)){
			Map<String, String> resultMap = new HashMap<String, String>();
			Field[] resultFields = voParam.getClass().getDeclaredFields();
			for (Field field : resultFields) {
				field.setAccessible(true);
				try {
					Object fieldObject = field.get(voParam);
					if(resultMap.containsKey(field.getName())){
						loadName(voParam, resultMap, field, field.getName());
					}
					if(fieldObject != null){
						if(fieldObject instanceof List){
							code2NameBatch(fieldObject);
						}else if(isVo(fieldObject)){
							code2Name(fieldObject);
						}else if(field.isAnnotationPresent(Code2NameLabel.class)){
							switchName(resultMap, field, fieldObject);
						}
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				} finally{
					field.setAccessible(false);
				}
			}
		}
	}


	private void switchName(Map<String, String> resultMap, Field field, Object fieldObject) {
		if(fieldObject.getClass().getTypeName().equals("java.lang.String")){
			Code2NameLabel code2NameLabel = field.getAnnotation(Code2NameLabel.class);
			String objectCd = code2NameLabel.objectCd();
			String target = code2NameLabel.target();
			resultMap.put(target, masterService.getNameByObjectCdAndCode(objectCd, fieldObject.toString()));
		}
	}

	private void loadName(Object voParam, Map<String, String> resultMap, Field field, String name) {
		if(field.getGenericType().toString().equals("class java.lang.String")){
			field.setAccessible(true);
			try {
				field.set(voParam, resultMap.get(name));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			} finally{
				field.setAccessible(false);
			}
		}
	}

	private boolean isVo(Object voParam) {
		if(voParam != null && !voParam.getClass().isArray()){
			Package packageObject = voParam.getClass().getPackage();
			String pName = packageObject.getName();
			Boolean flag = (pName.startsWith("com.wcg"))&&(pName.endsWith("vo")||pName.endsWith("model"));
			return flag;
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	private void code2NameBatch(Object voParam) {
		List voList = (List) voParam;
		for (Object object : voList) {
			if(object != null){
				code2Name(object);
			}
		}
	}
	
	
	
}
