import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface HomeMonitor {
    void addReading(SensorReading reading);
    double averageValue(String deviceName);
    Collection<SensorReading> aboveThreshold(double threshold);
    Optional<SensorReading> latestReading(String deviceName);
    Map<Unit, Long> countByUnit();
    Collection<SensorReading> getReadings();
}
