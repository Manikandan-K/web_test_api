package com.example.seltest.controller;

import com.example.seltest.model.ResponseDto;
import com.example.seltest.model.TestCase;
import com.example.seltest.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@RestController
@Produces(MediaType.APPLICATION_JSON)
//@Slf4j
public class TestCaseController {

    @Autowired
    private TestCaseService testCaseService;

    @PostMapping("/test")
    public ResponseDto test(@RequestBody TestCase input) {
        return new ResponseDto(testCaseService.execute(input));
    }

    @PostMapping("/test_cases/{id}/run")
    public ResponseDto test(@PathVariable Long id) {
        return new ResponseDto(testCaseService.execute(id));
    }

    @PostMapping("/test_cases")
    public void create(@RequestBody TestCase input) {
        testCaseService.save(input);
    }

    @GetMapping("/test_cases")
    public List<TestCase> getTestCases() {
        return testCaseService.getTestCases();
    }


}
