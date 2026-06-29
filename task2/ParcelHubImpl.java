import java.util.ArrayList;
import java.util.Collection;

public class ParcelHubImpl implements ParcelHub {
    private final Collection<Parcel> parcels;

    public ParcelHubImpl() {
        parcels = new ArrayList<>();
    }

    public void addParcel(Parcel parcel) {
        if (!parcel.shipped()){
            parcels.add(parcel);
        }
    }

    public double totalWeight() {
        double total = 0;
        for (Parcel parcel : parcels){
            total += parcel.weight();
        }
        return total;
    }

    public double shippingCost(double basePrice, double prioritySurcharge) {
        double cost = 0;
        for (Parcel parcel : parcels){
            cost = Math.ceil(parcel.weight()) * basePrice + prioritySurcharge;
        }
        
        return cost;
    }

    public int shipAll() {
        if (parcels.isEmpty()){
            throw new IllegalStateException("...");
        }

        int total = 0;
        for (Parcel parcel : parcels){
            parcel.markShipped();
            total++;
        }
        return total;
    }

    public Collection<Parcel> getParcels() {
        return parcels;
    }
}
