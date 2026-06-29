import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface StudioPlan {
    void addSession(TrainingSession session);
    int freeSpots();
    double expectedIncome(double pricePerPerson);
    Optional<TrainingSession> mostPopularSession();
    Map<Intensity, Integer> bookedByIntensity();
    Collection<TrainingSession> sessionsAboveCapacity(int minCapacity);
    Collection<TrainingSession> getSessions();
}
