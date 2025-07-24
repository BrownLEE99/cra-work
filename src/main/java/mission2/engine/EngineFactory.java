package mission2.engine;

public class EngineFactory {
    public static Engine create(EngineType type) {
        return switch (type) {
            case GM -> new Gm();
            case TOYOTA -> new Toyota();
            case WIA -> new Wia();
            case LEMON -> new Lemon();
        };
    }
}
