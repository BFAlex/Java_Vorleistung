public interface SensorReading {
    String deviceName();
    double value();
    Unit unit();
    long timestamp();
}
