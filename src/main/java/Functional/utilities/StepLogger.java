package Functional.utilities;

import com.aventstack.extentreports.ExtentTest;

public class StepLogger {

    private static final ThreadLocal<Integer> stepCounter =
            ThreadLocal.withInitial(() -> 1);

    public static void step(ExtentTest test, String message) {

        // Skip numbering for START / PASSED logs
        if (message.startsWith("=====")) {
            test.info(message);
            return;
        }

        int stepNumber = stepCounter.get();
        test.info("Step " + stepNumber + ": " + message);
        stepCounter.set(stepNumber + 1);
    }

    public static void reset() {
        stepCounter.set(1);
    }

    public static void remove() {
        stepCounter.remove();
    }
}