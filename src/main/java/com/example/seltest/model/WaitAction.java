package com.example.seltest.model;

import org.openqa.selenium.WebDriver;

public class WaitAction implements StepAction {
    @Override
    public StepResponse perform(WebDriver driver, Step step) {
        try {
            Thread.sleep(Long.parseLong(step.getValue()));
            return StepResponse.success(step);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return StepResponse.failure(step, "Internal Error");
        }

    }
}
