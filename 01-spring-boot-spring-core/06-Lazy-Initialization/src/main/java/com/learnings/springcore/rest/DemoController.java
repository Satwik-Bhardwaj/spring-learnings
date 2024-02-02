package com.learnings.springcore.rest;

import com.learnings.springcore.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Coach myCoach;

    // creating a constructor for constructor dependency injection
    @Autowired
    public DemoController(@Qualifier("tennisCoach") Coach myCoach) {
        System.out.println("Constructor initialized of " + getClass().getSimpleName());
        this.myCoach = myCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailWorkout() {
        return myCoach.getDailyWorkout();
    }

}
