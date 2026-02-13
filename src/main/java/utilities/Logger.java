package utilities;

import org.testng.Reporter;

public class Logger {

    private static ThreadLocal<Integer> stepCount = ThreadLocal.withInitial(() -> 1);

    public static void log(String message) {
        int currentStep = stepCount.get();
        Reporter.log("Step " + currentStep + ": " + message, true);
        stepCount.set(currentStep + 1);
    }

    public static void reset() {
        stepCount.set(1);
    }

    public static void remove() {
        stepCount.remove(); // optional cleanup
    }
}