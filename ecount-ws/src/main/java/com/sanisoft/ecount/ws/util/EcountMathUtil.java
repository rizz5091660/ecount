package com.sanisoft.ecount.ws.util;

import java.math.BigDecimal;

import com.sanisoft.ecount.constant.EcountConstant;

public class EcountMathUtil {
	
	public static void validate(BigDecimal value1, BigDecimal value2)
	throws Exception{
		/*
		if(value1!=null) {
			if(value1.doubleValue()<0) {
				throw new Exception("value1D is less than zero (0)");
			}
		}
		if(value2!=null) {
			if(value2.doubleValue()<0) {
				throw new Exception("value2D is less than zero (0)");
			}
		}
		*/
		
	}
	
	
	
	public static BigDecimal add(BigDecimal value1,BigDecimal value2) throws Exception {
		validate(value1,value2);
		return value1.add(value2);
	}
	
	public static BigDecimal subtract(BigDecimal value1,BigDecimal value2) throws Exception {
		validate(value1,value2);
		return value1.subtract(value2);
	}
	
	public static BigDecimal multiply(BigDecimal value1,BigDecimal value2) throws Exception {
		validate(value1,value2);
		return value1.multiply(value2);
	}
	
}
