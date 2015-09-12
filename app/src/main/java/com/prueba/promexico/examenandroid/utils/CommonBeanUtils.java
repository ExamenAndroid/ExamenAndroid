package com.prueba.promexico.examenandroid.utils;

import com.prueba.promexico.examenandroid.annotation.EntityFieldAnnotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by egm on 10/07/15.
 */
public class CommonBeanUtils {

    public static <T> List<T> getDataList(List<Map<String, String>> list, Class<T> clazz) {
        List<T> listResult = new ArrayList<T>();
        try {
            for (int i = 0; i < list.size(); i++) {
                Set<Map.Entry<String, String>> mapEntry = list.get(i).entrySet();
                T object = clazz.newInstance();
                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    field.setAccessible(true);
                    EntityFieldAnnotation annotationField = field.getAnnotation(EntityFieldAnnotation.class);
                    if (annotationField != null) {
                        for (Map.Entry<String, String> entry : mapEntry) {
                            if (annotationField.name().equals(entry.getKey())) {
                                field.set(object, entry.getValue());
                            }
                        }
                    } else {
                        for (Map.Entry<String, String> entry : mapEntry) {
                            if (field.getName().toLowerCase().equals(entry.getKey().toLowerCase())) {
                                field.set(object, entry.getValue());
                            }
                        }
                    }

                }
                listResult.add(object);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return listResult;
    }


    public static <T> T getDataMap(Map<String, String> map, Class<T> clazz) {
        T object = null;
        try {
            object = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                EntityFieldAnnotation annotationField = field.getAnnotation(EntityFieldAnnotation.class);
                if (annotationField != null) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        if (annotationField.equals(entry.getKey())) {
                            field.set(object, entry.getValue());
                        }
                    }
                } else {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        if (field.getName().toLowerCase().equals(entry.getKey().toLowerCase())) {
                            field.set(object, entry.getValue());
                        }
                    }
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }


    public static <T> Map<String, String> convertObjectToMap(T object) {

        Map<String, String> mapResult = new HashMap<String, String>();
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            EntityFieldAnnotation annotationField = field.getAnnotation(EntityFieldAnnotation.class);
            if (annotationField != null) {
                try {
                    mapResult.put(annotationField.name(), field.get(object).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        return mapResult;
    }

}
