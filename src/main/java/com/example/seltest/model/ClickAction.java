package com.example.seltest.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class ClickAction implements StepAction {

    @Override
    public StepResponse perform(WebDriver driver, Step step) {
        WebElement element = step.getWebElement(driver);
        if(Objects.isNull(element)) {
            return StepResponse.failure(step, "Element not found");
        }
        element.click();
        return StepResponse.success(step);
    }

}
