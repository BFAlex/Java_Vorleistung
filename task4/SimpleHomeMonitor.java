import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class SimpleHomeMonitor implements HomeMonitor {
    private final Collection<SensorReading> readings;

    public SimpleHomeMonitor() {
        readings = new ArrayList<>();
    }

    public void addReading(SensorReading reading) {
        readings.add(reading);
    }

    public double averageValue(String deviceName) {
        Collection<SensorReading> devicereadings = new ArrayList<>();
        for (SensorReading reading : readings) {
            if (reading.deviceName().equals(deviceName)){
                devicereadings.add(reading);
            }
        }

        if (devicereadings.isEmpty()){
            throw new NoSuchElementException("...");
        }

        double total = 0;
        for (SensorReading reading : devicereadings){
            total += reading.value();
        }

        return total/devicereadings.size();
    }

    public Collection<SensorReading> aboveThreshold(double threshold) {
        Collection<SensorReading> devicethreshold = new ArrayList<>();

        for(SensorReading reading : readings){
            if (reading.value() > threshold){
                devicethreshold.add(reading);
            }
        }

        return devicethreshold;
    }

    public Optional<SensorReading> latestReading(String deviceName) {

        SensorReading devicelstest = null;
        for (SensorReading reading : readings) {
            if (!reading.deviceName().equals(deviceName)) continue;;

            if (devicelstest == null || devicelstest.timestamp() < reading.timestamp() ){
                devicelstest = reading;
            }
        }

        if (devicelstest == null){
            return Optional.empty();
        }

        return Optional.of(devicelstest);
    }

    public Map<Unit, Long> countByUnit() {
        EnumMap<Unit, Long> counts = new EnumMap<>(Unit.class);

        for(SensorReading reading : readings){
            Unit unit = reading.unit();
            counts.put(unit, counts.getOrDefault(unit, 0L) + 1L);
        }

        return counts;
    }

    public Collection<SensorReading> getReadings() {
        return readings;
    }
}
