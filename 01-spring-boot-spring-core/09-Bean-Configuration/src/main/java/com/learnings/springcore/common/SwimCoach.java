package com.learnings.springcore.common;

public class SwimCoach implements Coach{

    public SwimCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice for swimming for 5 hours per day.";
    }
}
