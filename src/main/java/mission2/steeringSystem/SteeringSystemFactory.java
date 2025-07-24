package mission2.steeringSystem;

public class SteeringSystemFactory {
    public static SteeringSystem create(SteeringSystemType type) {
        return switch (type) {
            case BOSCH_S -> new BoschS();
            case MOBIS -> new Mobis();
        };
    }
}
