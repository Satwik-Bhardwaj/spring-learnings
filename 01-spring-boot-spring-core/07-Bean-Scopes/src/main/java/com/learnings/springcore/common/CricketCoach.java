package com.learnings.springcore.common;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {

    CricketCoach() {
        System.out.println("Cricket Coach object created.");
    }
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes :-)";
    }
}
