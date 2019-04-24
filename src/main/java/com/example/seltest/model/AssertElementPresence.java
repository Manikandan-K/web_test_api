package com.example.seltest.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class AssertElementPresence implements StepAction {
    @Override
    public StepResponse perform(WebDriver driver, Step step) {
        WebElement element = step.getWebElement(driver);
        if(Objects.isNull(element)) {
            return StepResponse.failure(step, "Element not found");
        }
        return StepResponse.success(step);
    }
}
