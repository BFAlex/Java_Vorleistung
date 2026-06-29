import java.util.Collection;
import java.util.Map;

public interface Warehouse {
    void addBatch(ItemBatch batch);
    double totalValue();
    int totalAmountOf(String productName);
    void removeItems(String productName, int amount);
    Map<StorageZone, Double> valueByZone();
    Collection<ItemBatch> getBatches();
}
