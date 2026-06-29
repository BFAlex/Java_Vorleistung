import java.util.Collection;

public interface ParcelHub {
    void addParcel(Parcel parcel);
    double totalWeight();
    double shippingCost(double basePrice, double prioritySurcharge);
    int shipAll();
    Collection<Parcel> getParcels();
}
