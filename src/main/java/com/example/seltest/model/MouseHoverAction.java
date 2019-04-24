package com.example.seltest.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Objects;

public class MouseHoverAction implements StepAction  {
    @Override
    public StepResponse perform(WebDriver driver, Step step) {
        WebElement element = step.getWebElement(driver);
        if(Objects.isNull(element)) {
            return StepResponse.failure(step, "Element not found");
        }

        Actions builder = new Actions(driver);
        Actions hoverOverRegistrar = builder.moveToElement(element);
        hoverOverRegistrar.perform();

        return StepResponse.success(step);
    }
}
