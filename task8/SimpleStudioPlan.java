import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class SimpleStudioPlan implements StudioPlan {
    private final Collection<TrainingSession> sessions;

    public SimpleStudioPlan() {
        sessions = new ArrayList<>();
    }

    public void addSession(TrainingSession session) {
        sessions.add(session);
    }

    public int freeSpots() {
        int total = 0;
        for (TrainingSession session : sessions){
            total += session.capacity() - session.booked();
        }
        return total;
    }

    public double expectedIncome(double pricePerPerson) {
        int total = 0;

        for(TrainingSession session:sessions){
            total += session.booked() * pricePerPerson;
        }

        return total;
    }

    public Optional<TrainingSession> mostPopularSession() {
        TrainingSession most = null;
        for(TrainingSession session:sessions){
            if(most == null || most.booked()<session.booked()){
                most = session;
            }
        }

        if (most == null) return Optional.empty();

        return Optional.of(most);
    }

    public Map<Intensity, Integer> bookedByIntensity() {
        Map<Intensity, Integer> map = new EnumMap<>(Intensity.class);
        for (TrainingSession session:sessions){
            Intensity typ = session.intensity();
            map.put(typ, map.getOrDefault(typ, 0)+session.booked());
        }
        return map;
    }

    public Collection<TrainingSession> sessionsAboveCapacity(int minCapacity) {
        Collection<TrainingSession> ses = new ArrayList<>();
        for (TrainingSession session:sessions){
            if (session.capacity() > minCapacity){
                ses.add(session);
            }
        }
        return ses;
    }

    public Collection<TrainingSession> getSessions() {
        return sessions;
    }
}
