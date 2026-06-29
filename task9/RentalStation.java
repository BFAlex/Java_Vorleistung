import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface RentalStation {
    void addRental(BikeRental rental);
    double rentalCost(BikeRental rental, double baseRate, double electricSurcharge);
    double totalRevenue(double baseRate, double electricSurcharge);
    Collection<BikeRental> activeRentals();
    Optional<BikeRental> findByBikeId(String bikeId);
    Map<BikeType, Long> countByBikeType();
    Collection<BikeRental> getRentals();
}
