import java.util.Collection;
import java.util.Optional;

public interface CinemaSchedule {
    void addScreening(Screening screening);
    int totalDuration();
    double expectedRevenue(int hallCapacity, double ticketPrice);
    Optional<Screening> largestScreening();
    Collection<Screening> getScreenings();
}
