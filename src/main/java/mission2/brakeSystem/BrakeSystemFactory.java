package mission2.brakeSystem;

public class BrakeSystemFactory {
    public static BrakeSystem create(BrakeSystemType type) {
        return switch (type) {
            case MANDO -> new Mando();
            case CONTINENTAL -> new Continental();
            case BOSCH_B -> new BoschB();
        };
    }
}
