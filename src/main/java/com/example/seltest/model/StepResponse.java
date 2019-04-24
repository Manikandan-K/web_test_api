package com.example.seltest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StepResponse {
    private Step step;
    private boolean isSuccess;
    private String message;

    public StepResponse(Step step, boolean isSuccess, String message) {
        this.step = step;
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public static StepResponse failure(Step step, String errorMessage) {
        return new StepResponse(step, false, errorMessage);
    }

    public static StepResponse success(Step step) {
        return new StepResponse(step, true, null);
    }
}
