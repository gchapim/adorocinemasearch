package com.github.gchapim.adorocinemasearch.util;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	public static final Locale LOCALE_PT_BR = new Locale("pt", "BR");

	public static boolean empty(Object object) {

		if (object == null) {
			return true;
		}

		if (object instanceof String) {
			if (((String) object).trim().equals("")) {
				return true;
			} else {
				return false;
			}
		}

		if (object instanceof Collection) {
			if (((Collection<?>) object).isEmpty()) {
				return true;
			} else {
				return false;
			}
		}

		if (object instanceof Map) {
			if (((Map<?, ?>) object).isEmpty()) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	public static boolean filled(Object object) {
		return !empty(object);
	}

	public static boolean isTrue(Boolean check) {
		if(Util.filled(check)){
			return check;
		}else{
			return false;
		}
	}
	
	public static boolean isInteger(String number) {
		return checkFormat(number, "^\\d{1,3}(\\.?\\d{3})*$");
	}
	
    public static boolean checkFormat(String string, String format) {
    	
        Pattern pattern = Pattern.compile(format);

        Matcher search = pattern.matcher(string);

        if(search.matches()) {
            return true;
        } else {
            return false;
        }
    }
	
}
