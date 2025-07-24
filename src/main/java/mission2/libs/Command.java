package mission2.libs;

import mission2.types.Step;

import java.util.Scanner;

import static mission2.types.Step.CAR_TYPE_Q;
import static mission2.types.Step.EXECUTE_TYPE_Q;

public class Command {
    private static final Scanner sc = new Scanner(System.in);
    private static final int BACKWARDS_COMMAND = 0;

    public static String in() {
        System.out.print("INPUT > ");
        return sc.nextLine().trim();
    }

    public static void close() {
        sc.close();
    }

    public static boolean isExit(String cmd) {
        return cmd.equalsIgnoreCase("exit");
    }

    public static boolean isBackwards(int cmd) {
        return cmd == BACKWARDS_COMMAND;
    }

    public static Step handleBackwards(Step step) {
        if (step == EXECUTE_TYPE_Q || step == CAR_TYPE_Q) return CAR_TYPE_Q;
        int stepValue = step.getValue();
        return Step.of(stepValue - 1);
    }

    private static boolean isValidCarType(int input) {
        return 1 <= input && input <= 3;
    }

    private static boolean isValidEngine(int input) {
        return 1 <= input && input <= 4;
    }

    private static boolean isValidBrakeSystem(int input) {
        return 1 <= input && input <= 3;
    }

    private static boolean isValidSteeringSystem(int input) {
        return 1 <= input && input <= 2;
    }

    private static boolean isValidExecute(int input) {
        return 1 <= input && input <= 2;
    }

    public static boolean isValidStep(Step step, int cmd) {
        switch (step) {
            case CAR_TYPE_Q -> {
                if (!isValidCarType(cmd)) {
                    Printer.error("차량 타입은 1 ~ 3 범위만 선택 가능");
                    return false;
                }
            }
            case ENGINE_Q -> {
                if (!isValidEngine(cmd)) {
                    Printer.error("엔진은 1 ~ 4 범위만 선택 가능");
                    return false;
                }
            }
            case BRAKE_SYSTEM_Q -> {
                if (!isValidBrakeSystem(cmd)) {
                    Printer.error("제동장치는 1 ~ 3 범위만 선택 가능");
                    return false;
                }
            }
            case STEERING_SYSTEM_Q -> {
                if (!isValidSteeringSystem(cmd)) {
                    Printer.error("조향장치는 1 ~ 2 범위만 선택 가능");
                    return false;
                }
            }
            case EXECUTE_TYPE_Q -> {
                if (!isValidExecute(cmd)) {
                    Printer.error("Run 또는 Test 중 하나를 선택 필요");
                    return false;
                }
            }
        }
        return true;
    }
}
