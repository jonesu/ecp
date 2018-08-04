package com.ecp.core.basic;


public class StringHelper {

	public static String objToStr(Object obj){
		if(obj == null){
			return "";
		}
		return obj.toString();
	}
	
	private static boolean eq(Object o1,Object o2){
		if((o1 == null && o2 != null)||(o1 != null && o2 == null)){
			if(o1 instanceof String){
				if(o1 == null){
					o1 = "";
				}
				if(o2 == null){
					o2 = "";
				}
				return o1.equals(o2); 
			}
			return false;
		}
		if(o1 == null && o2 == null){
			return true;
		}
		if(o1 instanceof String){
			return o1.equals(o2);
		}else if(o1 instanceof Integer){
			Integer i1 = (Integer)o1;
			Integer i2 = (Integer)o2;
			return i1.compareTo(i2) == 0 ? true :false;
		}else if( o1 instanceof Long){
			Long i1 = (Long)o1;
			Long i2 = (Long)o2;
			return i1.compareTo(i2) == 0 ? true :false;
		}else if(o1 instanceof Double){
			Double i1 = (Double)o1;
			Double i2 = (Double)o2;
			return i1.compareTo(i2) == 0 ? true :false;
		}
		return false;
	}

	public static boolean neq(Object o1,Object o2){
		return !eq(o1,o2);
	}
	
}
