package mission1;

import java.util.Scanner;

public class Assemble {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    private static final int BACKWARDS_COMMAND = 0;
    private static final int CarType_Q = 0;
    private static final int Engine_Q = 1;
    private static final int BrakeSystem_Q = 2;
    private static final int SteeringSystem_Q = 3;
    private static final int ExecuteType_Q = 4;

    private static final int SEDAN = 1, SUV = 2, TRUCK = 3;
    private static final int GM = 1, TOYOTA = 2, WIA = 3, LEMON = 4;
    private static final int MANDO = 1, CONTINENTAL = 2, BOSCH_B = 3;
    private static final int BOSCH_S = 1, MOBIS = 2;
    private static final int RUN = 1, TEST = 2;

    private static final String[] carNames = {"", "Sedan", "SUV", "Truck"};
    private static final String[] engNames = {"", "GM", "TOYOTA", "WIA"};
    private static final String[] brakeSystemNames = {"", "Mando", "Continental", "Bosch"};
    private static final String[] steeringSystemNames = {"", "Bosch", "Mobis"};


    private static final int[] commands = new int[5];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int step = CarType_Q;

        while (true) {
            try {
                initScreen();

                showMenuForStep(step);

                String input = getCommand(sc);
                if (isExitCommand(input)) {
                    System.out.println("바이바이");
                    break;
                }

                int cmd = Integer.parseInt(input);

                if (isBackwardsCommand(cmd)) {
                    step = handleBackwardsCommand(step);
                    continue;
                }

                if (!isValidStepCommand(step, cmd)) throw new RuntimeException();

                step = handleStep(step, cmd);
            } catch (Exception e) {
                if (e.getClass() == NumberFormatException.class) {
                    error("숫자만 입력 가능");
                }
                delay(800);
            }
        }
        sc.close();
    }

    private static int handleStep(int step, int answer) {
        switch (step) {
            case CarType_Q -> {
                selectCarType(answer);
                step = Engine_Q;
            }
            case Engine_Q -> {
                selectEngine(answer);
                step = BrakeSystem_Q;
            }
            case BrakeSystem_Q -> {
                selectBrakeSystem(answer);
                step = SteeringSystem_Q;
            }
            case SteeringSystem_Q -> {
                selectSteeringSystem(answer);
                step = ExecuteType_Q;
            }
            case ExecuteType_Q -> {
                selectExecuteType(answer);
            }
            default -> throw new RuntimeException();
        }
        delay(800);
        return step;
    }

    private static String getCommand(Scanner sc) {
        System.out.print("INPUT > ");
        return sc.nextLine().trim();
    }

    private static boolean isExitCommand(String cmd) {
        return cmd.equalsIgnoreCase("exit");
    }

    private static boolean isBackwardsCommand(int cmd) {
        return cmd == BACKWARDS_COMMAND;
    }

    private static int handleBackwardsCommand(int step) {
        if (step == ExecuteType_Q || step == CarType_Q) return CarType_Q;
        return step - 1;
    }

    private static void showMenuForStep(int step) {
        switch (step) {
            case CarType_Q -> showCarTypeMenu();
            case Engine_Q -> showEngineMenu();
            case BrakeSystem_Q -> showBrakeMenu();
            case SteeringSystem_Q -> showSteeringMenu();
            case ExecuteType_Q -> showExecuteTypeMenu();
            default -> throw new RuntimeException();
        }
    }

    private static void initScreen() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }

    private static void showCarTypeMenu() {
        System.out.println("        ______________");
        System.out.println("       /|            |");
        System.out.println("  ____/_|_____________|____");
        System.out.println(" |                      O  |");
        System.out.println(" '-(@)----------------(@)--'");
        System.out.println("===============================");
        System.out.println("어떤 차량 타입을 선택할까요?");
        System.out.println("1. Sedan");
        System.out.println("2. SUV");
        System.out.println("3. Truck");
        System.out.println("===============================");
    }

    private static void showEngineMenu() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. GM");
        System.out.println("2. TOYOTA");
        System.out.println("3. WIA");
        System.out.println("4. 고장난 엔진");
        System.out.println("===============================");
    }

    private static void showBrakeMenu() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. MANDO");
        System.out.println("2. CONTINENTAL");
        System.out.println("3. BOSCH");
        System.out.println("===============================");
    }

    private static void showSteeringMenu() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. BOSCH");
        System.out.println("2. MOBIS");
        System.out.println("===============================");
    }

    private static void showExecuteTypeMenu() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("===============================");
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

    private static boolean isValidExecuteCommand(int input) {
        return 1 <= input && input <= 2;
    }

    private static boolean isValidStepCommand(int step, int ans) {
        switch (step) {
            case CarType_Q -> {
                if (!isValidCarType(ans)) {
                    error("차량 타입은 1 ~ 3 범위만 선택 가능");
                    return false;
                }
            }
            case Engine_Q -> {
                if (!isValidEngine(ans)) {
                    error("엔진은 1 ~ 4 범위만 선택 가능");
                    return false;
                }
            }
            case BrakeSystem_Q -> {
                if (!isValidBrakeSystem(ans)) {
                    error("제동장치는 1 ~ 3 범위만 선택 가능");
                    return false;
                }
            }
            case SteeringSystem_Q -> {
                if (!isValidSteeringSystem(ans)) {
                    error("조향장치는 1 ~ 2 범위만 선택 가능");
                    return false;
                }
            }
            case ExecuteType_Q -> {
                if (!isValidExecuteCommand(ans)) {
                    error("Run 또는 Test 중 하나를 선택 필요");
                    return false;
                }
            }
            default -> throw new RuntimeException();
        }
        return true;
    }

    private static void selectCarType(int cmd) {
        commands[CarType_Q] = cmd;

        String car = switch (cmd) {
            case SEDAN -> "Sedan";
            case SUV -> "SUV";
            case TRUCK -> "Truck";
            default -> throw new RuntimeException();
        };

        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", car);
    }

    private static void selectEngine(int cmd) {
        commands[Engine_Q] = cmd;

        String engine = switch (cmd) {
            case GM -> "GM";
            case TOYOTA -> "TOYOTA";
            case WIA -> "WIA";
            case LEMON -> "고장난 엔진";
            default -> throw new RuntimeException();
        };

        System.out.printf("%s 엔진을 선택하셨습니다.\n", engine);
    }

    private static void selectBrakeSystem(int cmd) {
        commands[BrakeSystem_Q] = cmd;

        String brakeSystem = switch (cmd) {
            case MANDO -> "MANDO";
            case CONTINENTAL -> "CONTINENTAL";
            case BOSCH_B -> "BOSCH";
            default -> throw new RuntimeException();
        };

        System.out.printf("%s 제동장치를 선택하셨습니다.\n", brakeSystem);
    }

    private static void selectSteeringSystem(int cmd) {
        commands[SteeringSystem_Q] = cmd;

        String steeringSystem = switch (cmd) {
            case BOSCH_S -> "BOSCH";
            case MOBIS -> "MOBIS";
            default -> throw new RuntimeException();
        };

        System.out.printf("%s 조향장치를 선택하셨습니다.\n", steeringSystem);
    }

    private static void selectExecuteType(int cmd) {
        switch (cmd) {
            case RUN -> runProducedCar();
            case TEST -> {
                System.out.println("Test...");
                delay(1500);
                testProducedCar();
            }
        }

        delay(1200);
    }

    private static boolean isValidParts() {
        int carType = commands[CarType_Q];
        int engine = commands[Engine_Q];
        int brakeSystem = commands[BrakeSystem_Q];
        int steeringSystem = commands[SteeringSystem_Q];

        if (carType == SEDAN && brakeSystem == CONTINENTAL) return false;
        if (carType == SUV && engine == TOYOTA) return false;
        if (carType == TRUCK && (engine == WIA || brakeSystem == MANDO)) return false;
        if (brakeSystem == BOSCH_B && steeringSystem != BOSCH_S) return false;
        return true;
    }

    private static boolean isEngineBroken() {
        return commands[Engine_Q] == LEMON;
    }

    private static void runProducedCar() {
        if (!isValidParts()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (isEngineBroken()) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }

        System.out.printf("Car Type : %s\n", carNames[commands[CarType_Q]]);
        System.out.printf("Engine   : %s\n", engNames[commands[Engine_Q]]);
        System.out.printf("Brake    : %s\n", brakeSystemNames[commands[BrakeSystem_Q]]);
        System.out.printf("Steering : %s\n", steeringSystemNames[commands[SteeringSystem_Q]]);
        System.out.println("자동차가 동작됩니다.");
    }

    private static void testProducedCar() {
        int carType = commands[CarType_Q];
        int brakeSystem = commands[BrakeSystem_Q];
        int engine = commands[Engine_Q];
        int steeringSystem = commands[SteeringSystem_Q];

        if (carType == SEDAN && brakeSystem == CONTINENTAL) {
            fail("Sedan에는 Continental제동장치 사용 불가");
            return;
        }
        if (carType == SUV && engine == TOYOTA) {
            fail("SUV에는 TOYOTA엔진 사용 불가");
            return;
        }
        if (carType == TRUCK && engine == WIA) {
            fail("Truck에는 WIA엔진 사용 불가");
            return;
        }
        if (carType == TRUCK && brakeSystem == MANDO) {
            fail("Truck에는 Mando제동장치 사용 불가");
            return;
        }
        if (brakeSystem == BOSCH_B && steeringSystem != BOSCH_S) {
            fail("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
            return;
        }
        System.out.println("자동차 부품 조합 테스트 결과 : PASS");
    }

    private static void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }

    private static void error(String msg) {
        System.out.println("ERROR :: " + msg);
    }

    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}