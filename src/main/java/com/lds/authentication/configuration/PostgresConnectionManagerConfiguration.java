package com.lds.authentication.configuration;

import com.lds.authentication.port.service.util.ResourceFileService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

@Configuration
public class PostgresConnectionManagerConfiguration {
    @Value("${spring.datasource.base.url}")
    private String databaseBaseUrl;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String databaseUserName;

    @Value("${spring.datasource.password}")
    private String databasePassword;

    @Value("${spring.datasource.name}")
    private String databaseName;

    @Autowired
    private ResourceFileService resourceFileService;

    private void createDatabaseIfNotExists(Connection connection) throws SQLException {

        final Statement statement = connection.createStatement();
        String sql = "SELECT COUNT(*) AS dbs ";
        sql += " FROM pg_catalog.pg_database ";
        sql += " WHERE lower(datname) = '" + databaseName + "';";
        ResultSet resultSet = statement.executeQuery(sql);

        boolean dbExists = resultSet.next();
        if (!dbExists || resultSet.getInt("dbs") == 0) {
            String createDatabaseSql = "CREATE DATABASE " + databaseName + " WITH ";
            createDatabaseSql += " OWNER = postgres ENCODING = 'UTF8' ";
            createDatabaseSql += " CONNECTION LIMIT = -1; ";

            PreparedStatement preparedStatement = connection.prepareStatement(createDatabaseSql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        final DataSource build = DataSourceBuilder
                .create()
                .url(databaseBaseUrl)
                .username(databaseUserName)
                .password(databasePassword).build();

        final Connection connection = build.getConnection();
        createDatabaseIfNotExists(connection);

        return build;
    }

    @Bean
    @DependsOn("dataSource")
    public Connection getConnection() throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(databaseUrl);
        hikariConfig.setUsername(databaseUserName);
        hikariConfig.setPassword(databasePassword);
        return new HikariDataSource(hikariConfig).getConnection();
    }

    @Bean
    @DependsOn("getConnection")
    public boolean createTableAndInsertData() throws SQLException, IOException {
        Connection getConnection = getConnection();

        final String basePath = "db-scripts";
        final String createTable = resourceFileService.
                read(basePath + "/create-tables.sql");
        PreparedStatement createStatement = getConnection.prepareStatement(createTable);
        createStatement.executeUpdate();
        createStatement.close();


        final String insertData = resourceFileService.read(basePath + "/insert-data.sql");

        PreparedStatement insertStatement = getConnection.prepareStatement(insertData);
        insertStatement.execute();
        insertStatement.close();

        return true;
    }

}
