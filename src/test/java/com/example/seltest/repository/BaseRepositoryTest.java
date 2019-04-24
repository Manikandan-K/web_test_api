package com.example.seltest.repository;

import com.example.seltest.SelTestApplication;
import lombok.Getter;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SelTestApplication.class)
public class BaseRepositoryTest {
    @Getter
    protected Handle handle;
    @Autowired
    protected Jdbi jdbi;

    @Before
    public void before() throws Exception {
        handle = jdbi.open();
        handle.getConnection().setAutoCommit(false);
    }

    @After
    public void tearDown() {
        handle.rollback();
    }

}
