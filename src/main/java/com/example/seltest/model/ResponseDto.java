package com.example.seltest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

    List<StepResponse> stepResponses;

    public Boolean isSuccess() {
        return stepResponses.stream().allMatch(StepResponse::isSuccess);
    }

}
