package mission2;

import mission2.brakeSystem.BrakeSystem;
import mission2.brakeSystem.BrakeSystemFactory;
import mission2.brakeSystem.BrakeSystemType;
import mission2.car.Car;
import mission2.car.CarFactory;
import mission2.car.CarType;
import mission2.engine.Engine;
import mission2.engine.EngineFactory;
import mission2.engine.EngineType;
import mission2.libs.Command;
import mission2.libs.Printer;
import mission2.steeringSystem.SteeringSystem;
import mission2.steeringSystem.SteeringSystemFactory;
import mission2.steeringSystem.SteeringSystemType;
import mission2.types.Step;

public class Assemble {

    private static final CarMachine carMachine = new CarMachine();
    private static final int RUN = 1, TEST = 2;

    public static void main(String[] args) {
        Step step = Step.CAR_TYPE_Q;

        while (true) {
            try {
                Printer.init();

                Printer.menu(step);

                String input = Command.in();

                if (Command.isExit(input)) {
                    System.out.println("바이바이");
                    break;
                }

                int cmd = Integer.parseInt(input);

                if (Command.isBackwards(cmd)) {
                    step = Command.handleBackwards(step);
                    continue;
                }

                if (!Command.isValidStep(step, cmd)) throw new RuntimeException();

                step = handleStep(step, cmd);
            } catch (Exception e) {
                if (e.getClass() == NumberFormatException.class) {
                    Printer.error("숫자만 입력 가능");
                }
                delay(800);
            }
        }
        Command.close();
    }

    private static Step handleStep(Step step, int cmd) {
        switch (step) {
            case CAR_TYPE_Q -> {
                selectCarType(cmd);
                step = Step.ENGINE_Q;
            }
            case ENGINE_Q -> {
                selectEngine(cmd);
                step = Step.BRAKE_SYSTEM_Q;
            }
            case BRAKE_SYSTEM_Q -> {
                selectBrakeSystem(cmd);
                step = Step.STEERING_SYSTEM_Q;
            }
            case STEERING_SYSTEM_Q -> {
                selectSteeringSystem(cmd);
                step = Step.EXECUTE_TYPE_Q;
            }
            case EXECUTE_TYPE_Q -> {
                selectExecuteType(cmd);
            }
        }
        delay(800);
        return step;
    }

    private static void selectCarType(int cmd) {
        Car car = CarFactory.create(CarType.of(cmd));
        carMachine.setCar(car);
    }

    private static void selectEngine(int cmd) {
        Engine engine = EngineFactory.create(EngineType.of(cmd));
        carMachine.setEngine(engine);
    }

    private static void selectBrakeSystem(int cmd) {
        BrakeSystem brakeSystem = BrakeSystemFactory.create(BrakeSystemType.of(cmd));
        carMachine.setBrakeSystem(brakeSystem);
    }

    private static void selectSteeringSystem(int cmd) {
        SteeringSystem steeringSystem = SteeringSystemFactory.create(SteeringSystemType.of(cmd));
        carMachine.setSteeringSystem(steeringSystem);
    }

    private static void selectExecuteType(int cmd) {
        switch (cmd) {
            case RUN -> carMachine.runProducedCar();
            case TEST -> {
                delay(1500);
                carMachine.testProducedCar();
            }
        }

        delay(1200);
    }

    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}