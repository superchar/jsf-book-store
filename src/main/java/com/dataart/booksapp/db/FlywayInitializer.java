package com.dataart.booksapp.db;

import org.flywaydb.core.Flyway;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by vlobyntsev on 19.07.2016.
 */
@Singleton
@Startup
public class FlywayInitializer {

    @Resource(lookup = "jdbc/books-h2")
    private DataSource dataSource;

    @PostConstruct
    public void initialize() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();
    }
}
