package com.example.seltest.jdbi;

import com.example.seltest.model.Step;
import com.example.seltest.model.TestCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestCaseMapper implements ColumnMapper<TestCase> {

    @Override
    public TestCase map(ResultSet rs, int columnNumber, StatementContext ctx) throws SQLException {
        Long id = rs.getLong("locator");
        String name = rs.getString("name");
        String jsonString = rs.getString("steps");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            List<Step> steps = objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, Step.class));
            return new TestCase(id, steps, null, name);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
