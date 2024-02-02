package com.learnings.springcore.rest;

import com.learnings.springcore.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Coach myCoach;
    private Coach anotherCoach;

    // creating a constructor for constructor dependency injection
    @Autowired
    public DemoController(
            @Qualifier("tennisCoach") Coach myCoach,
            @Qualifier("tennisCoach") Coach anotherCoach
            ) {
        System.out.println("Constructor initialized of " + getClass().getSimpleName());
        this.myCoach = myCoach;
        this.anotherCoach = anotherCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String checkCoach() {
        // will return true if object are singleton bean, else prototype
        return "Checking: myCoach == anotherCoach: " + (myCoach == anotherCoach);
    }

}
