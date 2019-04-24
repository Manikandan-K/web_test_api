package com.example.seltest.repository;

import com.example.seltest.model.Step;
import com.example.seltest.model.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ConfigRepositoryTest extends BaseRepositoryTest {

    private ConfigRepository configRepository;

    @Before
    public void setUp() {
        configRepository = handle.attach(ConfigRepository.class);
    }

    @Test
    public void shouldInsertTestCase() {
        Step step = Step.builder().locator("locator").build();
        TestCase testCase = TestCase.builder().name("Test").steps(Arrays.asList(step)).build();

        configRepository.saveTestCase(testCase);

        List<TestCase> actual = configRepository.getTestCases();

        assertEquals(1, actual.size());
        TestCase actualTestCase = actual.get(0);
        assertEquals(testCase.getName(), actualTestCase.getName());
        assertEquals(testCase.getSteps(), actualTestCase.getSteps());

        TestCase testCase1 = configRepository.getTestCase(actualTestCase.getId());

        assertEquals(testCase.getName(), testCase1.getName());
        assertEquals(testCase.getSteps(), testCase1.getSteps());

    }
}
