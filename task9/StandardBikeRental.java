
// 1a) TODO: BikeRental implementieren
public class StandardBikeRental implements BikeRental {
    private final String bikeId;
    private final String customerName;
    private final double hours;
    private final BikeType bikeType;
    private boolean returned;

    public StandardBikeRental(String bikeId, String customerName, double hours, BikeType bikeType, boolean returned) {
        if (bikeId.isEmpty() || customerName.isEmpty() ||hours <= 0){
            throw new IllegalArgumentException("...");
        }
        
        this.bikeId = bikeId;
        this.customerName = customerName;
        this.hours = hours;
        this.bikeType = bikeType;
        this.returned = returned;
    }

    public String bikeId() { return bikeId; }
    public String customerName() { return customerName; }
    public double hours() { return hours; }
    public BikeType bikeType() { return bikeType; }
    public boolean returned() { return returned; }
    public void markReturned() { returned = true;}
}
