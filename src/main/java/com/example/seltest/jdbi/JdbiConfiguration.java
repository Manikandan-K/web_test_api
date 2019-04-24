package com.example.seltest.jdbi;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.jdbi.v3.sqlobject.SqlObjects;

public class JdbiConfiguration {

    public void configure(Jdbi jdbi) {
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.installPlugin(new PostgresPlugin());
        jdbi.configure(SqlObjects.class, c -> c.setSqlLocator(new QueryLocator()));
        jdbi.registerArgument(new TestCaseArgumentFactory());
        jdbi.registerArgument(new StepsArgumentFactory());
        jdbi.registerColumnMapper(new TestCaseMapper());
    }
}
