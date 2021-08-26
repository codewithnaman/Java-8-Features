package com.codewithnaman.java8.feature2;

public class FunctionalProgrammingWay {
    public static void main(String[] args) {
        MeasureTime measureTime = new MeasureTime();
        measureTime.measureTime(()-> {
            try {
                Thread.sleep(2*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        measureTime.measureTime(()-> {
            try {
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
