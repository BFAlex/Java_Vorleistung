import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DailyCinemaSchedule implements CinemaSchedule {
    private final Collection<Screening> screenings;

    public DailyCinemaSchedule() {
        screenings = new ArrayList<>();
    }

    public void addScreening(Screening screening) {
        screenings.add(screening);
    }

    public int totalDuration() {
        int duaration = 0;
        for (Screening screening : screenings){
            duaration += screening.durationMinutes();
        }
        return duaration;
    }

    public double expectedRevenue(int hallCapacity, double ticketPrice) {
        double reveneu = 0;

        for (Screening screening : screenings ){
            int soldSeats = hallCapacity - screening.freeSeats();
            reveneu += soldSeats*ticketPrice;
        }

        return reveneu;
    }

    public Optional<Screening> largestScreening() {
        if (screenings.isEmpty()){
            return Optional.empty();
        }

        Screening wenigplatz = null;
        for (Screening screening : screenings){
            if (wenigplatz == null || wenigplatz.freeSeats() > screening.freeSeats()){
                wenigplatz = screening;
            }
        }

        return Optional.of(wenigplatz);
    }

    public Collection<Screening> getScreenings() {
        return screenings;
    }
}
