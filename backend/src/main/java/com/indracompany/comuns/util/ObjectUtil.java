package com.indracompany.comuns.util;

import java.util.Collection;
import java.util.Date;

/**
 * @author 		Felipe Rudolfe
 * @since  		7 de jan de 2020
 */
public class ObjectUtil {

	private ObjectUtil() {}

	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(Collection value) {
		return isNull(value) || value.isEmpty();
	}

	public static boolean isNotNullOrEmpty(Integer value) {
		return !isNullOrEmpty(value);
	}

	public static boolean isNullOrEmpty(Long value) {
		return isNull(value) || value.equals(0L);
	}

	public static boolean isNotNullOrEmpty(Float value) {
		return !isNullOrEmpty(value);
	}

	public static boolean isNullOrEmpty(Double value) {
		return isNull(value) || value.intValue() == 0;
	}

	public static boolean isNotNullOrEmpty(String value) {
		return !isNullOrEmpty(value);
	}

	public static boolean isNullOrEmpty(Enum<?> value) {
		return isNull(value) || value.ordinal() < 0;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNotNullOrEmpty(Collection value) {
		return !isNullOrEmpty(value);
	}

	public static boolean isNullOrEmpty(Integer value) {
		return isNull(value) || value.equals(0);
	}

	public static boolean isNotNullOrEmpty(Long value) {
		return !isNullOrEmpty(value);
	}

	public static boolean isNullOrEmpty(Float value) {
		return isNull(value) || value.equals(0.0F);
	}

	public static boolean isNotNullOrEmpty(Double value) {
		return !isNullOrEmpty(value);
	}

	public static boolean isNullOrEmpty(String value) {
		return isNull(value) || value.trim().toString().equals("");
	}

	public static boolean isNullOrEmpty(String [] value) {
		return isNull(value) || value.length == 0;
	}

	public static boolean isNotNullOrEmpty(String [] value) {
        return !isNullOrEmpty(value);
    }

	public static boolean isNotNullOrEmpty(Enum<?> value) {
		return !isNullOrEmpty(value);
	}

	public static boolean isNull(Object obj) {

		boolean result = true;
		if (obj instanceof Object) {
			result = false;
		}

		return result;
	}

	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	public static boolean isCollection(Object obj) {
		return obj instanceof Collection;
	}

	public static boolean isDate(Object obj) {
		return obj instanceof Date;
	}

	public static boolean isNumber(Object obj) {
		return obj instanceof Number;
	}

	public static boolean isInteger(Object obj) {
		return obj instanceof Integer;
	}

	public static boolean isLong(Object obj) {
		return obj instanceof Long;
	}

	public static boolean isFloat(Object obj) {
		return obj instanceof Float;
	}

	public static boolean isDouble(Object obj) {
		return obj instanceof Double;
	}

	public static boolean isString(Object obj) {
		return obj instanceof String;
	}

	public static boolean isEnum(Object obj) {
		return obj instanceof Enum;
	}

}