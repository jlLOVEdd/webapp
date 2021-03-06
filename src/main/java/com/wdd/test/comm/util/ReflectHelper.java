package com.wdd.test.comm.util;

import com.wdd.test.comm.log.ErrorLog;
import com.wdd.test.comm.log.InnerLog;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * @Author: weidongdong
 * @Description:
 * @Date: Created in 16:52 2018/9/18
 */
public class ReflectHelper {

    /**
     *
     * @param obj
     * @param fieldName
     * @return
     */
    public static Field getFieldByFieldName(Object obj, String fieldName) {
        if (obj == null || fieldName == null) {
            return null;
        }
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static Object getValueByFieldName(Object obj, String fieldName) {
        Object value = null;
        try {
            Field field = getFieldByFieldName(obj, fieldName);
            if (field != null) {
                if (field.isAccessible()) {
                    value = field.get(obj);
                } else {
                    field.setAccessible(true);
                    value = field.get(obj);
                    field.setAccessible(false);
                }
            }
        } catch (Exception e) {
        }
        return value;
    }

    /**
     * 根据get方法获取字段value
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object getValueByGetMethod(Object obj, String fieldName) {
        Object value = null;
        try {
            Method m = obj.getClass().getMethod("get" + StringUtils.capitalize(fieldName));
            if (m != null) {
                value = m.invoke(obj);
            }
        } catch (Exception e) {
            InnerLog.error("{}没有method",fieldName);
        }
        return value;
    }

    /**
     * 得到字段类型
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object getFieldTypeByGetMethod(Object obj, String fieldName) {
        try {
            Field field = getFieldByFieldName(obj, fieldName);
            if (field != null) {
                return field.getType().getSimpleName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getValueByFieldType(Object obj, Class<T> fieldType) {
        Object value = null;
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                Field[] fields = superClass.getDeclaredFields();
                for (Field f : fields) {
                    if (f.getType() == fieldType) {
                        if (f.isAccessible()) {
                            value = f.get(obj);
                            break;
                        } else {
                            f.setAccessible(true);
                            value = f.get(obj);
                            f.setAccessible(false);
                            break;
                        }
                    }
                }
                if (value != null) {
                    break;
                }
            } catch (Exception e) {
            }
        }
        return (T) value;
    }

    public static boolean setValueByFieldName(Object obj, String fieldName, Object value) {
        if (value == null){
            return false;
        }
        try {
            //java.lang.Class.getDeclaredField()方法用法实例教程 - 方法返回一个Field对象，它反映此Class对象所表示的类或接口的指定已声明字段。
            //此方法返回这个类中的指定字段的Field对象
//            Field field = obj.getClass().getDeclaredField(fieldName);
            Field field = getFieldByFieldName(obj,fieldName);
            if(field == null){
                return false;
            }

            if (field.isAccessible()) {//获取此对象的 accessible 标志的值。
                field.set(obj, value);//将指定对象变量上此 Field 对象表示的字段设置为指定的新值
            } else {
                field.setAccessible(true);
                InnerLog.debug(">>>> fileName :{},fileName type :{},value:{},value type:{}",fieldName,field.getType().getSimpleName(),value,value.getClass());
                String simplename = field.getType().getSimpleName();
                if("String".equals(simplename)){
                    field.set(obj,String.valueOf(value));
                }else if("BigDecimal".equals(simplename)){
                    field.set(obj,new BigDecimal(String.valueOf(value)));
                }else if("Long".equals(simplename)){
                    field.set(obj,Long.valueOf(String.valueOf(value)));
                }else if("Integer".equals(simplename)){
                    field.set(obj,Integer.valueOf(StringUtils.isBlank(String.valueOf(value))?"0":String.valueOf(value)));
                }else{
                    field.set(obj, value);
                }
                field.setAccessible(false);
            }
            return true;
        } catch (IllegalArgumentException |  SecurityException | IllegalAccessException e) {
            InnerLog.error("", e);
            throw new RuntimeException("[" + fieldName + ":" + value + "]反向赋值异常：参数错误", e);
        }
    }
}
