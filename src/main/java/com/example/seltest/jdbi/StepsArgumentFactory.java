package com.example.seltest.jdbi;

import com.example.seltest.model.Step;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.config.ConfigRegistry;
import org.postgresql.util.PGobject;

import java.sql.Types;
import java.util.List;
import java.util.Objects;

public class StepsArgumentFactory extends AbstractArgumentFactory<List<Step>> {

    public StepsArgumentFactory() {
        super(Types.JAVA_OBJECT);
    }

    @Override
    protected Argument build(List<Step> value, ConfigRegistry config) {
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
                    jsonObject.setValue(objectMapper.writeValueAsString(value));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                statement.setObject(position, jsonObject);

            }
        };
    }

}
