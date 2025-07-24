package mission2;

import mission2.brakeSystem.*;
import mission2.car.*;
import mission2.engine.*;
import mission2.libs.Command;
import mission2.libs.Printer;
import mission2.steeringSystem.*;
import mission2.types.Step;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class AssembleTest {
    final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    final PrintStream standardOut = System.out;
    Assemble assemble = new Assemble();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Nested
    @DisplayName("Menu 출력")
    class Menu {
        @Test
        void init() {
            String expected = "\033[H\033[2J";

            Printer.init();

            assertEquals(expected, outputStreamCaptor.toString());
        }

        @Test
        void carTypeMenu() {
            String expected = "어떤 차량 타입을 선택할까요?";

            Printer.carTypeMenu();

            assertThat(outputStreamCaptor.toString()).contains(expected);
        }

        @Test
        void engineMenu() {
            String expected = "어떤 엔진을 탑재할까요?";

            Printer.engineMenu();

            assertThat(outputStreamCaptor.toString()).contains(expected);
        }


        @Test
        void brakeMenu() {
            String expected = "어떤 제동장치를 선택할까요?";

            Printer.brakeMenu();

            assertThat(outputStreamCaptor.toString()).contains(expected);
        }

        @Test
        void steeringMenu() {
            String expected = "어떤 조향장치를 선택할까요?";

            Printer.steeringMenu();

            assertThat(outputStreamCaptor.toString()).contains(expected);
        }

        @Test
        void executeTypeMenu() {
            String expected = "멋진 차량이 완성되었습니다.";

            Printer.executeTypeMenu();

            assertThat(outputStreamCaptor.toString()).contains(expected);
        }

        @Test
        void error() {
            String expected = "ERROR :: ";

            Printer.error("something went wrong");

            assertThat(outputStreamCaptor.toString()).contains(expected);
        }

        @Test
        void fail() {
            String expected = "자동차 부품 조합 테스트 결과 : FAIL";

            Printer.fail("something went wrong");

            assertThat(outputStreamCaptor.toString()).contains(expected);
        }

        @Test
        void stepCarType() {
            String expected = "어떤 차량 타입을 선택할까요?";

            Printer.menu(Step.CAR_TYPE_Q);

            assertThat(outputStreamCaptor.toString()).contains(expected);
        }

        @Test
        void stepEngine() {
            String expected = "어떤 엔진을 탑재할까요?";

            Printer.menu(Step.ENGINE_Q);

            assertThat(outputStreamCaptor.toString()).contains(expected);
        }

        @Test
        void stepBrakeSystem() {
            String expected = "어떤 제동장치를 선택할까요?";

            Printer.menu(Step.BRAKE_SYSTEM_Q);

            assertThat(outputStreamCaptor.toString()).contains(expected);
        }

        @Test
        void stepSteeringSystem() {
            String expected = "어떤 조향장치를 선택할까요?";

            Printer.menu(Step.STEERING_SYSTEM_Q);

            assertThat(outputStreamCaptor.toString()).contains(expected);
        }

        @Test
        void stepExecuteTypeMenu() {
            String expected = "멋진 차량이 완성되었습니다.";

            Printer.menu(Step.EXECUTE_TYPE_Q);

            assertThat(outputStreamCaptor.toString()).contains(expected);
        }
    }

    @Nested
    @DisplayName("BrakeSystem")
    class BrakeSystemTest {
        @Test
        void BoschB() {
            String expected = "Bosch";

            BrakeSystem brakeSystem = new BoschB();

            assertThat(brakeSystem.getName()).isEqualTo(expected);
        }

        @Test
        void Continental() {
            String expected = "Continental";

            BrakeSystem brakeSystem = new Continental();

            assertThat(brakeSystem.getName()).isEqualTo(expected);
        }

        @Test
        void Mando() {
            String expected = "Mando";

            BrakeSystem brakeSystem = new Mando();

            assertThat(brakeSystem.getName()).isEqualTo(expected);
        }

        @Test
        void MandoFactory() {
            BrakeSystem brakeSystem = BrakeSystemFactory.create(BrakeSystemType.MANDO);

            assertThat(brakeSystem.getClass()).isEqualTo(Mando.class);
        }

        @Test
        void ContinentalFactory() {
            BrakeSystem brakeSystem = BrakeSystemFactory.create(BrakeSystemType.CONTINENTAL);

            assertThat(brakeSystem.getClass()).isEqualTo(Continental.class);
        }

        @Test
        void BoschBFactory() {
            BrakeSystem brakeSystem = BrakeSystemFactory.create(BrakeSystemType.BOSCH_B);

            assertThat(brakeSystem.getClass()).isEqualTo(BoschB.class);
        }

        @Test
        void of() {
            BrakeSystemType expected = BrakeSystemType.MANDO;

            BrakeSystemType actual = BrakeSystemType.of(1);

            assertEquals(expected, actual);
        }

        @Test
        void getValue() {
            int expected = 1;

            int actual = BrakeSystemType.of(1).getValue();

            assertEquals(expected, actual);

        }
    }

    @Nested
    @DisplayName("Car")
    class CarTest {
        @Test
        void Sedan() {
            String expected = "Sedan";

            Car car = new Sedan();

            assertThat(car.getName()).isEqualTo(expected);
        }

        @Test
        void SUV() {
            String expected = "SUV";

            Car car = new Suv();

            assertThat(car.getName()).isEqualTo(expected);
        }

        @Test
        void Truck() {
            String expected = "Truck";

            Car car = new Truck();

            assertThat(car.getName()).isEqualTo(expected);
        }

        @Test
        void SuvFactory() {
            Car car = CarFactory.create(CarType.SUV);

            assertThat(car.getClass()).isEqualTo(Suv.class);
        }

        @Test
        void SedanFactory() {
            Car car = CarFactory.create(CarType.SEDAN);

            assertThat(car.getClass()).isEqualTo(Sedan.class);
        }

        @Test
        void TruckFactory() {
            Car car = CarFactory.create(CarType.TRUCK);

            assertThat(car.getClass()).isEqualTo(Truck.class);
        }

        @Test
        void of() {
            CarType expected = CarType.TRUCK;

            CarType actual = CarType.of(3);

            assertEquals(expected, actual);
        }

        @Test
        void getValue() {
            int expected = 1;

            int actual = CarType.of(1).getValue();

            assertEquals(expected, actual);

        }
    }

    @Nested
    @DisplayName("Engine")
    class EngineTest {
        @Test
        void Gm() {
            String expected = "GM";

            Engine engine = new Gm();

            assertThat(engine.getName()).isEqualTo(expected);
        }

        @Test
        void Toyota() {
            String expected = "TOYOTA";

            Engine engine = new Toyota();

            assertThat(engine.getName()).isEqualTo(expected);
        }

        @Test
        void Wia() {
            String expected = "WIA";

            Engine engine = new Wia();

            assertThat(engine.getName()).isEqualTo(expected);
        }

        @Test
        void Lemon() {
            String expected = "고장난 엔진";

            Engine engine = new Lemon();

            assertThat(engine.getName()).isEqualTo(expected);
        }

        @Test
        void GmFactory() {
            Engine engine = EngineFactory.create(EngineType.GM);

            assertThat(engine.getClass()).isEqualTo(Gm.class);
        }

        @Test
        void ToyotaFactory() {
            Engine engine = EngineFactory.create(EngineType.TOYOTA);

            assertThat(engine.getClass()).isEqualTo(Toyota.class);
        }

        @Test
        void WiaFactory() {
            Engine engine = EngineFactory.create(EngineType.WIA);

            assertThat(engine.getClass()).isEqualTo(Wia.class);
        }

        @Test
        void LemonFactory() {
            Engine engine = EngineFactory.create(EngineType.LEMON);

            assertThat(engine.getClass()).isEqualTo(Lemon.class);
        }

        @Test
        void of() {
            EngineType expected = EngineType.LEMON;

            EngineType actual = EngineType.of(4);

            assertEquals(expected, actual);
        }

        @Test
        void getValue() {
            int expected = 1;

            int actual = EngineType.of(1).getValue();

            assertEquals(expected, actual);

        }
    }

    @Nested
    @DisplayName("SteeringSystem")
    class SteeringSystemTest {

        @Test
        void Bosch() {
            String expected = "Bosch";

            SteeringSystem steeringSystem = new BoschS();

            assertThat(steeringSystem.getName()).isEqualTo(expected);
        }

        @Test
        void Mobis() {
            String expected = "Mobis";

            SteeringSystem steeringSystem = new Mobis();

            assertThat(steeringSystem.getName()).isEqualTo(expected);
        }

        @Test
        void BoschFactory() {
            SteeringSystem steeringSystem = SteeringSystemFactory.create(SteeringSystemType.BOSCH_S);

            assertThat(steeringSystem.getClass()).isEqualTo(BoschS.class);
        }

        @Test
        void MobisFactory() {
            SteeringSystem steeringSystem = SteeringSystemFactory.create(SteeringSystemType.MOBIS);

            assertThat(steeringSystem.getClass()).isEqualTo(Mobis.class);
        }

        @Test
        void of() {
            SteeringSystemType expected = SteeringSystemType.MOBIS;

            SteeringSystemType actual = SteeringSystemType.of(2);

            assertEquals(expected, actual);
        }

        @Test
        void getValue() {
            int expected = 1;

            int actual = SteeringSystemType.of(1).getValue();

            assertEquals(expected, actual);
        }
    }

    @Test
    void exit() {
        String input = "exit";

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Assemble.main(null);

        assertThat(outputStreamCaptor.toString()).contains("바이바이");
    }
}