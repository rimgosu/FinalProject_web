package kr.spring;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TableColumnsValues {

    public static class Result<T> {
        public String tableName;
        public String[] columnNames;
        public Object[] values;

        // 생성자, getter, setter 등 필요에 따라 추가
    }

    public static <T> Result<T> extractData(Class<T> clazz, T instance) {
        Result<T> result = new Result<>();

        // 테이블 이름 설정 (클래스 이름을 사용)
        String tableName = clazz.getSimpleName();

        // 필드명 추출
        Field[] fields = clazz.getDeclaredFields();
        String[] columnNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            columnNames[i] = fields[i].getName();
        }
        Arrays.sort(columnNames);

        // 메서드 추출 및 값 추출
        List<Object> valuesList = new ArrayList<>();
        Method[] methods = clazz.getMethods();
        Arrays.sort(methods, Comparator.comparing(Method::getName));

        for (Method method : methods) {
            if (method.getName().startsWith("get") && !method.getName().startsWith("getClass") && method.getParameterCount() == 0) {
                try {
                    valuesList.add(method.invoke(instance));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        result.tableName = tableName;
        result.columnNames = columnNames;
        result.values = valuesList.toArray();

        return result;
    }
}
