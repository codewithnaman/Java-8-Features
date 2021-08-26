package com.codewithnaman.java8.feature2;

import java.time.Duration;
import java.time.LocalTime;

public class MeasureTime {

    public void measureTime(MeasurableMethod measurableMethod) {
        LocalTime startTime = LocalTime.now();
        measurableMethod.performOperation();
        LocalTime endTime = LocalTime.now();
        System.out.println("Time Took by Method is : "+
                Duration.between(startTime,endTime).toMillis());
    }
}
