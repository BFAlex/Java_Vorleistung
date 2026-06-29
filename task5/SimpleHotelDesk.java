import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class SimpleHotelDesk implements HotelDesk {
    private final Collection<Booking> bookings;

    public SimpleHotelDesk() {
        bookings = new ArrayList<>();
    }

    public boolean addBooking(Booking booking) {
        for (Booking a: bookings){
            if (booking.roomNumber() == a.roomNumber()){
                return false;
            }
        }
        bookings.add(booking);
        return true;
    }

    public double bookingPrice(Booking booking, double basePricePerNight, double breakfastPricePerGuest) {
        return booking.nights() * basePricePerNight + booking.guests() * breakfastPricePerGuest;
    }

    public double totalRevenue(double basePricePerNight, double breakfastPricePerGuest) {
        double summe = 0;
        for (Booking booking : bookings){
            summe += bookingPrice(booking, basePricePerNight, breakfastPricePerGuest);
        }
        return summe;
    }

    public Optional<Booking> findBooking(int roomNumber) {
        Booking book = null;
        for (Booking booking : bookings){
            if (booking.roomNumber() == roomNumber){
                book = booking;
            }
        }
        if (book == null){
            return Optional.empty();
        }

        return Optional.of(book);
    }

    public Map<RoomType, Long> countByRoomType() {
        Map<RoomType, Long> count = new EnumMap<>(RoomType.class);
        
        for (Booking booking : bookings){
            RoomType roomtype = booking.roomType();
            count.put(roomtype, count.getOrDefault(roomtype, 0L)+1L);
        }
        
        return count;
    }

    public Collection<Booking> getBookings() {
        return bookings;
    }
}
