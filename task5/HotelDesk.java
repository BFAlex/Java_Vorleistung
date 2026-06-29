import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface HotelDesk {
    boolean addBooking(Booking booking);
    double bookingPrice(Booking booking, double basePricePerNight, double breakfastPricePerGuest);
    double totalRevenue(double basePricePerNight, double breakfastPricePerGuest);
    Optional<Booking> findBooking(int roomNumber);
    Map<RoomType, Long> countByRoomType();
    Collection<Booking> getBookings();
}
