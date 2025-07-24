package mission2.steeringSystem;

public enum SteeringSystemType {
    BOSCH_S(1),
    MOBIS(2);

    private int value;

    private SteeringSystemType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SteeringSystemType of(int value) {
        return SteeringSystemType.values()[value - 1];
    }
}
