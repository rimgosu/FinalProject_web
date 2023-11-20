package kr.spring.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

public class CassandraSessionManager {
	private static CqlSession session = null;

    private CassandraSessionManager() {
        // private constructor to prevent instantiation
    }

    public static CqlSession getSession(DriverConfigLoader loader) {
        if (session == null) {
            synchronized (CassandraSessionManager.class) {
                if (session == null) {
                    session = CqlSession.builder()
                                        .withConfigLoader(loader)
                                        .build();
                }
            }
        }
        return session;
    }

    public static void closeSession() {
        if (session != null) {
            session.close();
            session = null;
        }
    }
}
