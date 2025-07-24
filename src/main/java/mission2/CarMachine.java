package mission2;

import mission2.brakeSystem.BrakeSystem;
import mission2.car.Car;
import mission2.engine.Engine;
import mission2.engine.Lemon;
import mission2.libs.Printer;
import mission2.steeringSystem.SteeringSystem;

public class CarMachine {
    private Car car;
    private Engine engine;
    private BrakeSystem brakeSystem;
    private SteeringSystem steeringSystem;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", car.getName());
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
        System.out.printf("%s 엔진을 선택하셨습니다.\n", engine.getName());
    }

    public BrakeSystem getBrakeSystem() {
        return brakeSystem;
    }

    public void setBrakeSystem(BrakeSystem brakeSystem) {
        this.brakeSystem = brakeSystem;
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", brakeSystem.getName());
    }

    public SteeringSystem getSteeringSystem() {
        return steeringSystem;
    }

    public void setSteeringSystem(SteeringSystem steeringSystem) {
        this.steeringSystem = steeringSystem;
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", steeringSystem.getName());
    }

    public boolean isValidParts() {
        String carName = car.getName();
        String engineName = engine.getName();
        String brakeSystemName = brakeSystem.getName();
        String steeringSystemName = steeringSystem.getName();

        if (carName.equals("Sedan") && brakeSystemName.equals("Continental")) return false;
        if (carName.equals("SUV") && engineName.equals("TOYOTA")) return false;
        if (carName.equals("Truck") && (engineName.equals("WIA") || brakeSystemName.equals("Mando"))) return false;
        if (brakeSystemName.equals("Bosch") && !steeringSystemName.equals("Bosch")) return false;
        return true;
    }

    public boolean isBrokenEngine() {
        return engine.getClass() == Lemon.class;
    }

    public void runProducedCar() {
        if (!isValidParts()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (isBrokenEngine()) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }
        System.out.printf("Car Type : %s\n", car.getName());
        System.out.printf("Engine   : %s\n", engine.getName());
        System.out.printf("Brake    : %s\n", brakeSystem.getName());
        System.out.printf("Steering : %s\n", steeringSystem.getName());
        System.out.println("자동차가 동작됩니다.");
    }

    public void testProducedCar() {
        System.out.println("Test...");

        String carName = car.getName();
        String engineName = engine.getName();
        String brakeSystemName = brakeSystem.getName();
        String steeringSystemName = steeringSystem.getName();

        if (carName.equals("Sedan") && brakeSystemName.equals("Continental")) {
            Printer.fail("Sedan에는 Continental제동장치 사용 불가");
            return;
        }
        if (carName.equals("SUV") && engineName.equals("TOYOTA")) {
            Printer.fail("SUV에는 TOYOTA엔진 사용 불가");
            return;
        }
        if (carName.equals("Truck") && engineName.equals("WIA")) {
            Printer.fail("Truck에는 WIA엔진 사용 불가");
            return;
        }
        if (carName.equals("Truck") && brakeSystemName.equals("Mando")) {
            Printer.fail("Truck에는 Mando제동장치 사용 불가");
            return;
        }
        if (brakeSystemName.equals("Bosch") && !steeringSystemName.equals("Bosch")) {
            Printer.fail("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
            return;
        }
        System.out.println("자동차 부품 조합 테스트 결과 : PASS");
    }

    public String toString() {
        return "Car Type   : " + car.getName() + "\n" +
                "Engine     : " + engine.getName() + "\n" +
                "Brake      : " + brakeSystem.getName() + "\n" +
                "Steering   : " + steeringSystem.getName();
    }
}
