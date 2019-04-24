package com.example.seltest.repository;

import com.example.seltest.model.TestCase;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface ConfigRepository {

    @SqlUpdate
    void saveTestCase(@BindBean TestCase testCase);

    @SqlQuery
    List<TestCase> getTestCases();

    @SqlQuery
    TestCase getTestCase(@Bind("locator") Long id);
}
