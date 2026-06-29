public class BasicSensorReading implements SensorReading {
    private final String deviceName;
    private final double value;
    private final Unit unit;
    private final long timestamp;

    public BasicSensorReading(String deviceName, double value, Unit unit, long timestamp) {
        if (deviceName.isEmpty()){
            throw new IllegalArgumentException("...");
        }
        
        this.deviceName = deviceName;
        this.value = value;
        this.unit = unit;
        this.timestamp = timestamp;
    }

    public String deviceName() {
        return deviceName;
    }

    public double value() {
        return value;
    }

    public Unit unit() {
        return unit;
    }

    public long timestamp() {
        return timestamp;
    }
}
