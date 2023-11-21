package kr.spring.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import kr.spring.TableColumnsValues;
import kr.spring.cassandra.CassandraSessionManager;

@Service
public class DBServiceImpl implements DBService{

	@Override
	public DriverConfigLoader getConnection() {
		Path configPath = Paths.get("c:/keys/keyspace/application.conf");
        DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
        
        return loader;
	}
	

	@Override
	public <T> void save(DriverConfigLoader loader, Class<T> entityClass, T entity) {
	    System.out.println("[DBServiceImpl][save]");
	    TableColumnsValues.Result<T> result = TableColumnsValues.extractData(entityClass, entity);
	    CqlSession session = CassandraSessionManager.getSession(loader);
	    try {

	        String columns = String.join(", ", result.columnNames);
	        String placeholders = String.join(", ", Collections.nCopies(result.columnNames.length, "?"));
	        String cql = String.format("INSERT INTO %s (%s) VALUES (%s)", "member."+result.tableName, columns, placeholders);

	        System.out.println("[cql:]" + cql);

	        PreparedStatement preparedStatement = session.prepare(cql);
	        Object[] boundValues = convertToAppropriateType(result.values);
	        session.execute(preparedStatement.bind(boundValues));
	    } catch (Exception e) {
	        // 오류 처리 로직
	        System.out.println("save Error: " + e);
	    }
	}



	@Override
	public <T> List<T> findAll(DriverConfigLoader loader, Class<T> classType) {
	    List<T> entities = new ArrayList<>();
	    CqlSession session = CassandraSessionManager.getSession(loader);
	    try {
	        // 엔티티 클래스의 모든 필드 이름을 추출
	        String fields = Arrays.stream(classType.getDeclaredFields())
	                              .map(Field::getName)
	                              .collect(Collectors.joining(", "));

	        // SELECT 절에 필드 이름 포함
	        String cql = String.format("SELECT %s FROM %s", 
	                                   fields, "member." + classType.getSimpleName().toLowerCase());

	        System.out.println("[execute cql] " + cql);

	        PreparedStatement preparedStatement = session.prepare(cql);
	        ResultSet resultSet = session.execute(preparedStatement.bind());
	        for (Row row : resultSet) {
	            T entity = classType.getDeclaredConstructor().newInstance();

	            for (Field field : classType.getDeclaredFields()) {
	                field.setAccessible(true); // 필드 접근 허용

	                try {
	                    setFieldValue(field, entity, row);
	                } catch (IllegalAccessException e) {
	                    System.out.println("Reflection error: " + e.getMessage());
	                    // 적절한 예외 처리
	                }
	            }

	            entities.add(entity);
	        }

	    } catch (Exception e) {
	        // 오류 처리 로직
	        System.out.println("Error: " + e);
	    }

	    return entities;
	}

	
	@Override
	public <T> List<T> findAllByColumnValue(DriverConfigLoader loader, Class<T> classType, String columnName, Object value) {
	    List<T> entities = new ArrayList<>();
	    CqlSession session = CassandraSessionManager.getSession(loader);
	    try {
	        // 엔티티 클래스의 모든 필드 이름을 추출
	        String fields = Arrays.stream(classType.getDeclaredFields())
	                              .map(Field::getName)
	                              .collect(Collectors.joining(", "));

	        // WHERE 절 추가
	        String cql = String.format("SELECT %s FROM %s WHERE %s = ?", 
	                                   fields, "member." + classType.getSimpleName().toLowerCase(), columnName);
	        
	        System.out.println("[execute cql] " + cql);

	        PreparedStatement preparedStatement = session.prepare(cql);
	        // 바인딩된 값 추가
	        ResultSet resultSet = session.execute(preparedStatement.bind(value));
	        for (Row row : resultSet) {
	            T entity = classType.getDeclaredConstructor().newInstance();

	            for (Field field : classType.getDeclaredFields()) {
	                field.setAccessible(true); // 필드 접근 허용

	                try {
	                    setFieldValue(field, entity, row);
	                } catch (IllegalAccessException e) {
	                    System.out.println("Reflection error: " + e.getMessage());
	                    // 적절한 예외 처리
	                }
	            }

	            entities.add(entity);
	        }

	    } catch (Exception e) {
	        // 오류 처리 로직
	        System.out.println("Error: " + e);
	    }

	    return entities;
	}

	
	@Override
	public <T> List<T> findAllByColumnValues(DriverConfigLoader loader, Class<T> classType, Map<String, Object> columnValues) {
	    List<T> entities = new ArrayList<>();
	    CqlSession session = CassandraSessionManager.getSession(loader);
	    try {
	        // 엔티티 클래스의 모든 필드 이름을 추출
	        String fields = Arrays.stream(classType.getDeclaredFields())
	                              .map(Field::getName)
	                              .collect(Collectors.joining(", "));

	        // WHERE 절 동적 생성
	        StringBuilder whereClause = new StringBuilder();
	        List<Object> bindValues = new ArrayList<>();
	        for (Map.Entry<String, Object> entry : columnValues.entrySet()) {
	            if (whereClause.length() > 0) {
	                whereClause.append(" AND ");
	            }
	            whereClause.append(entry.getKey()).append(" = ?");
	            bindValues.add(entry.getValue());
	        }

	        // SELECT 절에 필드 이름 포함
	        String cql = String.format("SELECT %s FROM %s WHERE %s", 
	                                   fields, "member." + classType.getSimpleName().toLowerCase(), whereClause.toString());
	        cql += " ALLOW FILTERING";

	        System.out.println("[execute cql] " + cql);

	        PreparedStatement preparedStatement = session.prepare(cql);
	        // 바인딩된 값 추가
	        BoundStatement boundStatement = preparedStatement.bind(bindValues.toArray());
	        ResultSet resultSet = session.execute(boundStatement);
	        for (Row row : resultSet) {
	            T entity = classType.getDeclaredConstructor().newInstance();

	            for (Field field : classType.getDeclaredFields()) {
	                field.setAccessible(true); // 필드 접근 허용

	                try {
	                    setFieldValue(field, entity, row);
	                } catch (IllegalAccessException e) {
	                    System.out.println("Reflection error: " + e.getMessage());
	                    // 적절한 예외 처리
	                }
	            }
	            
	            entities.add(entity);
	        }

	    } catch (Exception e) {
	        // 오류 처리 로직
	        System.out.println("Error: " + e);
	    }

	    return entities;
	}


	
	
	@Override
	public <T> void updateByColumnValues(DriverConfigLoader loader, Class<T> classType, 
	                                     Map<String, Object> updateValues, Map<String, Object> whereConditions) {
		CqlSession session = CassandraSessionManager.getSession(loader);
		try {

	        // SET 절 동적 생성
	        StringBuilder setClause = new StringBuilder();
	        List<Object> bindSetValues = new ArrayList<>();
	        for (Map.Entry<String, Object> entry : updateValues.entrySet()) {
	            if (setClause.length() > 0) {
	                setClause.append(", ");
	            }
	            setClause.append(entry.getKey()).append(" = ?");
	            bindSetValues.add(entry.getValue());
	        }

	        // WHERE 절 동적 생성
	        StringBuilder whereClause = new StringBuilder();
	        List<Object> bindWhereValues = new ArrayList<>();
	        for (Map.Entry<String, Object> entry : whereConditions.entrySet()) {
	            if (whereClause.length() > 0) {
	                whereClause.append(" AND ");
	            }
	            whereClause.append(entry.getKey()).append(" = ?");
	            bindWhereValues.add(entry.getValue());
	        }

	        String cql = String.format("UPDATE %s SET %s WHERE %s", 
	                "member." + classType.getSimpleName().toLowerCase(), setClause.toString(), whereClause.toString());
	        System.out.println("execute cql : " + cql);

	        PreparedStatement preparedStatement = session.prepare(cql);

	        // 바인딩된 값 추가
	        List<Object> bindValues = new ArrayList<>(bindSetValues);
	        bindValues.addAll(bindWhereValues);
	        BoundStatement boundStatement = preparedStatement.bind(bindValues.toArray());
	        session.execute(boundStatement);

	    } catch (Exception e) {
	        // 오류 처리 로직
	        System.out.println("Error: " + e);
	    }
	}

	
	
	
	

	public Object[] convertToAppropriateType(Object[] values) {
	    Object[] convertedValues = new Object[values.length];
	    for (int i = 0; i < values.length; i++) {
	        Object value = values[i];
	        if (value == null) {
	            System.out.println("Value: null");
	            convertedValues[i] = null;
	            continue; // 다음 반복으로 넘어갑니다.
	        }

	        System.out.println("Value: " + value + ", Class: " + value.getClass().getSimpleName()); // 로그 출력

	        if (value instanceof UUID) {
	            convertedValues[i] = (UUID) value;
	        } else if (value instanceof Instant) {
	            convertedValues[i] = (Instant) value;
	        } else if (value instanceof Boolean) {
	            convertedValues[i] = (Boolean) value;
	        } else if (value instanceof String) {
	            convertedValues[i] = (String) value;
	        } else if (value instanceof Float) {
	            convertedValues[i] = (Float) value;
	        } else if (value instanceof Integer) {
	            convertedValues[i] = (Integer) value;
	        } else {
	            // 다른 타입에 대한 처리
	            convertedValues[i] = value;
	        }
	    }
	    return convertedValues;
	}





	@Override
	public <T> void setFieldValue(Field field, T entity, Row row) throws IllegalAccessException {
	    String columnName = field.getName();
	    
	    try {
	    	if (field.getType().equals(String.class)) {
//		    	System.out.println("[setFieldValue][equals(String.class)][row.getString(columnName)]"+row.getString(columnName));
		        field.set(entity, row.getString(columnName));
		    } 
		    else if (field.getType().equals(UUID.class)) {
//		    	System.out.println("[setFieldValue][equals(UUID.class)][row.getUuid(columnName)]"+row.getUuid(columnName));
		    	UUID uuidValue = row.getUuid(columnName);
	            if (uuidValue != null) {
	                field.set(entity, uuidValue);
	            }
		    } 
		    else if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
//		    	System.out.println("[setFieldValue][equals(Integer.class][row.getInt(columnName)]"+row.getInt(columnName));
		        field.set(entity, row.getInt(columnName));
		    } 
		    else if (field.getType().equals(Instant.class)) {
//		    	System.out.println("[setFieldValue][equals(Instant.class)][row.getInstant(columnName)]"+row.getInstant(columnName));
		        field.set(entity, row.getInstant(columnName));
		    } 
		    else if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
//		    	System.out.println("[setFieldValue][row.getLong(columnName)]"+row.getLong(columnName));
		        field.set(entity, row.getLong(columnName));
		    } 
		    else if (field.getType().equals(Double.class) || field.getType().equals(double.class)) {
		        field.set(entity, row.getDouble(columnName));
		    } 
		    else if (field.getType().equals(Float.class) || field.getType().equals(float.class)) {
		        field.set(entity, row.getFloat(columnName));
		    } 
		    else if (field.getType().equals(List.class)) {
		        // 여기에서 필요한 List 타입 처리
		        // 예시: String, Integer, Float, Long, Double
		        ParameterizedType listType = (ParameterizedType) field.getGenericType();
		        Class<?> listClass = (Class<?>) listType.getActualTypeArguments()[0];
		        field.set(entity, row.getList(columnName, listClass)); 
		    } 
		    else if (field.getType().equals(Map.class)) {
		        // 여기에서 필요한 Map 타입 처리
		        // 예시: Instant to String, Integer to String, Float to String
		        ParameterizedType mapType = (ParameterizedType) field.getGenericType();
		        Class<?> keyClass = (Class<?>) mapType.getActualTypeArguments()[0];
		        Class<?> valueClass = (Class<?>) mapType.getActualTypeArguments()[1];
		        field.set(entity, row.getMap(columnName, keyClass, valueClass)); 
		    }
	    } catch (Exception e) {
	    	System.out.println("Error setting field value for column: " + columnName + ", Error: " + e.getMessage());
		}
	    
	    // 추가적인 타입에 대한 처리는 여기에 추가
	}

	
}