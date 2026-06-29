public class RoomBooking extends GuestBooking {

    public RoomType roomType;

    public RoomBooking(String guestName, int roomNumber, int nights, int guests, RoomType roomType) {
        super(guestName, roomNumber, nights, guests);
        this.roomType = roomType;
    }

    public RoomType roomType() {
        return roomType;
    }
}
