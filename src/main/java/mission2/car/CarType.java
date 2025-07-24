package mission2.car;

import mission2.types.Step;

public enum CarType {
    SEDAN(1),
    SUV(2),
    TRUCK(3);

    private int value;

    private CarType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CarType of(int value) {
        return CarType.values()[value - 1];
    }
}
