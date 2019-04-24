package com.example.seltest.model;

import org.openqa.selenium.WebDriver;

public class NavigateAction implements StepAction {
    @Override
    public StepResponse perform(WebDriver driver, Step step) {
        driver.get(step.getValue());
        return StepResponse.success(step);
    }
}
