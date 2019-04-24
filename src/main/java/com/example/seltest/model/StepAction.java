package com.example.seltest.model;

import org.openqa.selenium.WebDriver;

public interface StepAction {
    StepResponse perform(WebDriver driver, Step step);
}
