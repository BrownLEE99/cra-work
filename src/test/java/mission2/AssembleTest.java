package mission2;

import mission2.libs.Printer;
import mission2.types.Step;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class AssembleTest {
    final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    final PrintStream standardOut = System.out;

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
        }@Test
        void stepEngine() {
            String expected = "어떤 엔진을 탑재할까요?";

            Printer.menu(Step.ENGINE_Q);

            assertThat(outputStreamCaptor.toString()).contains(expected);
        }@Test
        void stepBrakeSystem() {
            String expected = "어떤 제동장치를 선택할까요?";

            Printer.menu(Step.BRAKE_SYSTEM_Q);

            assertThat(outputStreamCaptor.toString()).contains(expected);
        }@Test
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
}