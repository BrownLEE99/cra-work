package mission2.brakeSystem;

public enum BrakeSystemType {
    MANDO(1),
    CONTINENTAL(2),
    BOSCH_B(3);

    private int value;

    private BrakeSystemType(int value) {
        this.value = value;
    }

    public static BrakeSystemType of(int value) {
        return BrakeSystemType.values()[value - 1];
    }

    public int getValue() {
        return value;
    }
}