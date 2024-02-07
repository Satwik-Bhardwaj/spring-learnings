package com.learnings.springcore;

import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Coach myCoach;

    // defining a constructor for dependency injection
    @Autowired      // optional annotation if you have only one constructor
    // @Autowired annotation tells Spring to inject the dependency here
    public DemoController(Coach theCoach) {
        this.myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailWorkout() {
        return myCoach.getDailyWorkout();
    }

}
