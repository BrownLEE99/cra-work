package mission2.types;

public enum Step {
    CAR_TYPE_Q(0),
    ENGINE_Q(1),
    BRAKE_SYSTEM_Q(2),
    STEERING_SYSTEM_Q(3),
    EXECUTE_TYPE_Q(4);

    private int value;

    private Step(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Step of(int value) {
        return Step.values()[value];
    }
}
