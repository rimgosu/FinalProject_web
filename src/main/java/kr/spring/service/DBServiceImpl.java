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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import kr.spring.entity.ChatRoom;

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
		
	    TableColumnsValues.Result<T> result = TableColumnsValues.extractData(entityClass, entity);
	    try (CqlSession session = CqlSession.builder()
	            .withConfigLoader(loader)
	            .build()) {

	        String columns = String.join(", ", result.columnNames);
	        String placeholders = String.join(", ", Collections.nCopies(result.columnNames.length, "?"));
	        String cql = String.format("INSERT INTO %s (%s) VALUES (%s)", "member."+result.tableName, columns, placeholders);

	        PreparedStatement preparedStatement = session.prepare(cql);
	        session.execute(preparedStatement.bind((Object[]) result.values));
	    } catch (Exception e) {
	        // 오류 처리 로직
	        System.out.println("save Error: " + e);
	    }
	}


	@Override
	public <T> List<T> findAll(DriverConfigLoader loader, Class<T> classType) {
	    List<T> entities = new ArrayList<>();
	    try (CqlSession session = CqlSession.builder()
	            .withConfigLoader(loader)
	            .build()) {

	        String cql = String.format("SELECT * FROM %s", "member." + classType.getSimpleName().toLowerCase());

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
	    try (CqlSession session = CqlSession.builder()
	            .withConfigLoader(loader)
	            .build()) {

	        // WHERE 절 추가
	        String cql = String.format("SELECT * FROM %s WHERE %s = ?", 
	                "member." + classType.getSimpleName().toLowerCase(), columnName);

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
	    try (CqlSession session = CqlSession.builder()
	            .withConfigLoader(loader)
	            .build()) {

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

	        String cql = String.format("SELECT * FROM %s WHERE %s", 
	                "member." + classType.getSimpleName().toLowerCase(), whereClause.toString());
	        cql += " ALLOW FILTERING";
	        System.out.println("execute cql : " + cql);

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
	            
	            System.out.println(entity.toString());

	            entities.add(entity);
	        }

	    } catch (Exception e) {
	        // 오류 처리 로직
	        System.out.println("Error: " + e);
	    }

	    return entities;
	}

	

	@Override
	public <T> void setFieldValue(Field field, T entity, Row row) throws IllegalAccessException {
	    String columnName = field.getName();
	    
	    if (field.getType().equals(String.class)) {
	        field.set(entity, row.getString(columnName));
	    } else if (field.getType().equals(UUID.class)) {
	        field.set(entity, row.getUuid(columnName));
	    } else if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
	        field.set(entity, row.getInt(columnName));
	    } else if (field.getType().equals(Instant.class)) {
	        field.set(entity, row.getInstant(columnName));
	    } else if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
	        field.set(entity, row.getLong(columnName));
	    } else if (field.getType().equals(Double.class) || field.getType().equals(double.class)) {
	        field.set(entity, row.getDouble(columnName));
	    } else if (field.getType().equals(Float.class) || field.getType().equals(float.class)) {
	        field.set(entity, row.getFloat(columnName));
	    } else if (field.getType().equals(List.class)) {
	        // 여기에서 필요한 List 타입 처리
	        // 예시: String, Integer, Float, Long, Double
	        ParameterizedType listType = (ParameterizedType) field.getGenericType();
	        Class<?> listClass = (Class<?>) listType.getActualTypeArguments()[0];
	        field.set(entity, row.getList(columnName, listClass)); 
	    } else if (field.getType().equals(Map.class)) {
	        // 여기에서 필요한 Map 타입 처리
	        // 예시: Instant to String, Integer to String, Float to String
	        ParameterizedType mapType = (ParameterizedType) field.getGenericType();
	        Class<?> keyClass = (Class<?>) mapType.getActualTypeArguments()[0];
	        Class<?> valueClass = (Class<?>) mapType.getActualTypeArguments()[1];
	        field.set(entity, row.getMap(columnName, keyClass, valueClass)); 
	    }
	    // 추가적인 타입에 대한 처리는 여기에 추가
	}




	
}