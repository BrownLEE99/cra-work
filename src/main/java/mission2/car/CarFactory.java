package mission2.car;

public class CarFactory {
    public static Car create(CarType type) {
        return switch (type) {
            case SEDAN -> new Sedan();
            case SUV -> new Suv();
            case TRUCK -> new Truck();
        };
    }
}
