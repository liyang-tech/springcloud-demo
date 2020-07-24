package com.wcg.caoxian.sdk.cache;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidateValid implements ConstraintValidator<DateValide, Object>{

	private String dateType;
	
	@Override
	public void initialize(DateValide arg0) {
		this.dateType = arg0.dateType();
	}

	@Override
	public boolean isValid(Object arg0, ConstraintValidatorContext arg1) {
		if(arg0 == null || "".equals(arg0)){
			return true;
		}
		String date = arg0.toString();
		if(dateType.equals("date")){
			String format = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
			Pattern compile = Pattern.compile(format);
			Matcher matcher = compile.matcher(date);
			if(matcher.matches()){
				return true;
			}
		}
		if(dateType.equals("datetime")){
			String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
			Pattern compile = Pattern.compile(format);
			Matcher matcher = compile.matcher(date);
			if(matcher.matches()){
				return true;
			}
		}
		return false;
	}

}
