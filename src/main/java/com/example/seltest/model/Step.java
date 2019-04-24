package com.example.seltest.model;

import lombok.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Step {

    private String locator;
    private ActionType type;
    private Action action;
    private String value;


    public StepResponse perform(WebDriver driver) {
        return action.perform(driver, this);
    }

    WebElement getWebElement(WebDriver driver) {
        return driver.findElement(By.cssSelector(getLocator()));
    }
}
