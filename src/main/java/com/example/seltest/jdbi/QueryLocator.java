package com.example.seltest.jdbi;

import org.jdbi.v3.core.config.ConfigRegistry;
import org.jdbi.v3.sqlobject.locator.SqlLocator;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;

public class QueryLocator implements SqlLocator {

    static ConcurrentHashMap<String, String> queryMap = new ConcurrentHashMap<>();
    private final String path;

    public QueryLocator() {
        this.path = "config";
    }

    @Override
    public String locate(Class<?> sqlObjectType, Method method, ConfigRegistry config) {
        InputStream resource = QueryLocator.class.getResourceAsStream(format("/queries/%s.sql", method.getName()));
        if (resource == null) {
            throw new RuntimeException("Unable to find any query for '" + method.getName() + "'");
        }
        initializeLazyCacheOfQueries(method.getName(), resource);
        return queryMap.get(method.getName());
    }

    private void initializeLazyCacheOfQueries(String name, InputStream resource) {
        if (!queryMap.contains(name)) {
            String query = getQueryFromJar(resource);
            queryMap.put(name, query);
        }
    }

    private String getQueryFromJar(InputStream resource) {
        Scanner scanner = new Scanner(resource);
        String query = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return query;
    }

}
