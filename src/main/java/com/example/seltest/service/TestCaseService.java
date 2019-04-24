package com.example.seltest.service;

import com.example.seltest.model.StepResponse;
import com.example.seltest.model.TestCase;
import com.example.seltest.repository.ConfigRepository;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TestCaseService {

    @Autowired
    private ConfigRepository configRepository;

    public List<TestCase> getTestCases() {
        return configRepository.getTestCases();
    }

    public void save(TestCase testCase) {
        configRepository.saveTestCase(testCase);
    }

    public List<StepResponse> execute(Long id) {
        return execute(configRepository.getTestCase(id));
    }

    public List<StepResponse> execute(TestCase testCase) {
        String nodeURL = "http://localhost:4444/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        capabilities.setCapability("name", "LiveTest");

        WebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL(nodeURL), capabilities);
//            driver.get(testCase.getUrl());

            return performStepActions(testCase, driver);

        } catch (Exception e) {
            if (Objects.nonNull(driver)) {
                driver.quit();
            }
        } finally {
            if (Objects.nonNull(driver)) {
                driver.quit();
            }
            return new ArrayList<>();

        }

    }

    private List<StepResponse> performStepActions(TestCase testCase, WebDriver driver) {
        return testCase.getSteps().stream().map(s -> s.perform(driver)).collect(Collectors.toList());
    }
}
