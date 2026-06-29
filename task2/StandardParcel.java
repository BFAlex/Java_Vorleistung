public class StandardParcel implements Parcel {

    private final String address;
    private final double weight;
    private final int priority;
    private boolean shipped;

    public StandardParcel(String address, double weight, int priority, boolean shipped) {
        if (address.isEmpty() || weight <= 0){
            throw new IllegalArgumentException("...");
        }
        this.address = address;
        this.weight = weight;
        this.priority = priority;
        this.shipped = shipped;
    }

    public String address() {
        return address;
    }

    public double weight() {
        return weight;
    }

    public int priority() {
        return priority;
    }

    public boolean shipped() {
        return shipped;
    }

    public void markShipped() {
        shipped = true;
    }
}
