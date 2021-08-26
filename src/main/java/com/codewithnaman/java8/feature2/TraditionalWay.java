package com.codewithnaman.java8.feature2;

public class TraditionalWay {
    public static void main(String[] args) {
        WaitForIt waitForIt = new WaitForIt();
        MeasureTime measureTime = new MeasureTime();
        measureTime.measureTime(waitForIt);

        measureTime.measureTime(new MeasurableMethod() {
            @Override
            public void performOperation() {
                try {
                    Thread.sleep(3*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

class WaitForIt implements MeasurableMethod{

    @Override
    public void performOperation() {
        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
