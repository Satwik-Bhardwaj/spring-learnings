package com.learnings.springcore.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {

    CricketCoach() {
        System.out.println("Cricket Coach object created.");
    }

    // define our init method
    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("In do my start up stuff: " + getClass().getSimpleName());
    }

    // define our destroy method
    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println("In do my clean up stuff: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes :-)";
    }
}
