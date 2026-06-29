public interface BikeRental {
    String bikeId();
    String customerName();
    double hours();
    BikeType bikeType();
    boolean returned();
    void markReturned();
}
