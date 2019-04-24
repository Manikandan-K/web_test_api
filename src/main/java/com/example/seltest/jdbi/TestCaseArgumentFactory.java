package com.example.seltest.jdbi;

import com.example.seltest.model.TestCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.config.ConfigRegistry;
import org.postgresql.util.PGobject;

import java.sql.Types;
import java.util.Objects;

public class TestCaseArgumentFactory extends AbstractArgumentFactory<TestCase> {

    public TestCaseArgumentFactory() {
        super(Types.JAVA_OBJECT);
    }

    @Override
    protected Argument build(TestCase value, ConfigRegistry config) {
        return (position, statement, ctx1) -> {
            if (Objects.isNull(value)) {
                statement.setString(position, null);
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

                PGobject jsonObject = new PGobject();
                jsonObject.setType("json");
                try {
                    jsonObject.setValue(objectMapper.writeValueAsString(value.getSteps()));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                statement.setObject(position, jsonObject);

            }
        };
    }

}
