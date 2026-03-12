package Functional.utilities;


public class Logger {

    private static final ThreadLocal<Integer> stepCount = ThreadLocal.withInitial(() -> 1);

    public static void reset() {
        stepCount.set(1);
    }

    public static void remove() {
        stepCount.remove(); // optional cleanup
    }
}