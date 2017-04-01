package com.rafael;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import liquibase.integration.spring.MultiTenantSpringLiquibase;

@SpringBootApplication
public class Application {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(Application.class);

	@Bean
	public MultiTenantSpringLiquibase liquibaseMt(DataSource dataSource)
			throws SQLException {
		MultiTenantSpringLiquibase multiTenantSpringLiquibase = new MultiTenantSpringLiquibase();
		multiTenantSpringLiquibase.setDataSource(dataSource);

		Statement stmt = null;
		stmt = dataSource.getConnection().createStatement();

		ResultSet rs = stmt
				.executeQuery("SELECT sv.schema_name FROM schema_view sv");
		List<String> schemas = new ArrayList<>();
		while (rs.next()) {
			String schemaName = rs.getString("schema_name");
			LOGGER.info(schemaName);
			dataSource.getConnection().createStatement()
					.executeUpdate("CREATE SCHEMA IF NOT EXISTS " + schemaName);
			schemas.add(schemaName);
		}

		multiTenantSpringLiquibase.setSchemas(schemas);
		multiTenantSpringLiquibase.setChangeLog(
				"classpath:db/changelog/schemas/db.changelog-specific.yaml");
		multiTenantSpringLiquibase.setShouldRun(true);

		return multiTenantSpringLiquibase;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
