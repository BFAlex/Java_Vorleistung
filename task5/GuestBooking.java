public abstract class GuestBooking implements Booking {
    private final String guestName;
    private final int roomNumber;
    private final int nights;
    private final int guests;

    protected GuestBooking(String guestName, int roomNumber, int nights, int guests) {
        if (guestName.isEmpty() || roomNumber <= 0 || nights <= 0 || guests <= 0) {
            throw new IllegalArgumentException("Ungültige Buchungsdaten");
        }
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.nights = nights;
        this.guests = guests;
    }

    public String guestName() { return guestName; }
    public int roomNumber() { return roomNumber; }
    public int nights() { return nights; }
    public int guests() { return guests; }

    public abstract RoomType roomType();
}
