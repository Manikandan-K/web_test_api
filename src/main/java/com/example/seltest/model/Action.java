package com.example.seltest.model;

import org.openqa.selenium.WebDriver;

public enum Action {
    NAVIGATE(new NavigateAction()),
    CLICK(new ClickAction()),
    KEYBOARD_INPUT(new InputAction()),
    MOUSE_HOVER(new MouseHoverAction()),
    CHECK_CHECKBOX(new ClickAction()),
    WAIT(new WaitAction()),
    RADIO_BUTTON(new ClickAction()),
    ELEMENT_CHECK(new AssertTextAction()),
    ELEMENT_PRESENT(new AssertElementPresence());

    private final StepAction stepAction;

    Action(StepAction stepAction) {
        this.stepAction = stepAction;
    }

    public StepResponse perform(WebDriver driver, Step step) {
        return stepAction.perform(driver, step);
    }
}


