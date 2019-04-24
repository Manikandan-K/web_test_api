package com.example.seltest.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class AssertTextAction implements StepAction {
    @Override
    public StepResponse perform(WebDriver driver, Step step) {
        WebElement element = step.getWebElement(driver);
        if(Objects.isNull(element)) {
            return StepResponse.failure(step, "Element not found");
        }
        if(!element.getText().equals(step.getValue())) {
            return StepResponse.failure(step, String.format("Expected %s but found %s", step.getValue(), element.getText()));
        }

        return StepResponse.success(step);
    }
}
