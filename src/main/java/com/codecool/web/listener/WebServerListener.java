package com.codecool.web.listener;


import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class WebServerListener implements ServletContextListener {
    private final String INIT_FILE_PATH = "./init.sql";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataSource ds = prepareInitEnviroment(sce);
        runDatabaseInitScript(ds, INIT_FILE_PATH);
    }

    private DataSource prepareInitEnviroment(ServletContextEvent sce){
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource dataSource = (DataSource) envCtx.lookup("jdbc/database");
            ServletContext servletCtx = sce.getServletContext();
            servletCtx.setAttribute("dataSource", dataSource);
            return dataSource;

        } catch (NamingException e) {
            e.printStackTrace();
            throw new IllegalStateException("Error int prepareInitEnviroment");
        }

    }

    private void runDatabaseInitScript(DataSource dataSource, String initFilePath) {
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, new ClassPathResource(initFilePath));
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new IllegalStateException(ex);
        }
    }
}
