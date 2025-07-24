package mission2.engine;

import mission2.car.CarType;

public enum EngineType {
    GM(1),
    TOYOTA(2),
    WIA(3),
    LEMON(4);

    private int value;

    private EngineType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EngineType of(int value) {
        return EngineType.values()[value -1];
    }
}
