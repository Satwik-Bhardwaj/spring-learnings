package com.learnings.springcore.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class BaseBallCoach implements Coach {

    BaseBallCoach() {
        System.out.println("Base ball Coach object created.");
    }

    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes in batting practice.";
    }
}
