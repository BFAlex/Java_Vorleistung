import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

// 2a) TODO: RentalStation implementieren und Konstruktor vervollständigen
public class SimpleRentalStation implements RentalStation {
    private final Collection<BikeRental> rentals;

    public SimpleRentalStation() {
        rentals = new ArrayList<>();
    }

    public void addRental(BikeRental rental) {
        for (BikeRental rent : rentals){
            if(rent.bikeId().equals(rental.bikeId()) && !rent.returned()){
                throw new IllegalStateException("...");
            }
        }
        rentals.add(rental);
    }

    public double rentalCost(BikeRental rental, double baseRate, double electricSurcharge) {
        double cost = rental.hours()*baseRate;

        if(rental.bikeType() == BikeType.ELECTRIC){
            cost = rental.hours()*baseRate+electricSurcharge;
        }

        return cost;
    }

    public double totalRevenue(double baseRate, double electricSurcharge) {
        double sum = 0;
        for (BikeRental rental:rentals){
            sum += rentalCost(rental, baseRate, electricSurcharge);
        }
        return sum;
    }

    public Collection<BikeRental> activeRentals() {
        Collection<BikeRental> act = new ArrayList<>();

        for (BikeRental rental:rentals){
            if (!rental.returned()){
                act.add(rental);
            }
        }

        return act;
    }

    public Optional<BikeRental> findByBikeId(String bikeId) {
        BikeRental find = null;
        for (BikeRental rental:rentals){
            if(rental.bikeId().equals(bikeId)){
                find = rental;
            }
        }
        
        if (find == null){
            return Optional.empty();
        }

        return Optional.of(find);
    }

    public Map<BikeType, Long> countByBikeType() {
        
        Map<BikeType, Long> map = new EnumMap<>(BikeType.class);

        for (BikeRental rental : rentals){
            BikeType typ = rental.bikeType();
            map.put(typ, map.getOrDefault(typ, 0L)+1L);
        }

        return map;
    }

    public Collection<BikeRental> getRentals() {
        return rentals;
    }
}
