package com.learnings.springcore.rest;

import com.learnings.springcore.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Coach myCoach;

    // defining a constructor for dependency injection
    @Autowired      // optional annotation if you have only one constructor
    public DemoController(Coach theCoach) {
        this.myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailWorkout() {
        return myCoach.getDailyWorkout();
    }

}
