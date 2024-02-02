package com.learnings.springcore.common;

import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach{

    TennisCoach() {
        System.out.println("Tennis Coach object created.");
    }
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}
