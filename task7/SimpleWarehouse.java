
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

public class SimpleWarehouse implements Warehouse {
    private final Collection<ItemBatch> batches;

    public SimpleWarehouse() {
        batches = new ArrayList<>();
    }

    public void addBatch(ItemBatch batch) {
        batches.add(batch);
    }

    public double totalValue() {
        double total = 0;
        for (ItemBatch batch : batches){
            total += batch.amount() * batch.unitPrice();
        }
        return total;
    }

    public int totalAmountOf(String productName) {
        int total = 0;

        for (ItemBatch batch : batches){
            if (batch.productName().equals(productName)){
                total += batch.amount();
            }
        }
        
        return total;
    }

    public void removeItems(String productName, int amount) {
        if (totalAmountOf(productName) < amount){
            throw new IllegalArgumentException("...");
        }
        
        int totalamount = amount;
        Collection<ItemBatch> newBatches = new ArrayList<>();

        for (ItemBatch batch : batches){
            if (!batch.productName().equals(productName) || totalamount == 0){
                newBatches.add(batch);
            }else if (batch.amount() <= totalamount){
                totalamount -= batch.amount();
            }else{
                newBatches.add(new StoredBatch(batch.productName(), batch.amount()-totalamount,
            batch.unitPrice(), batch.batchNumber(),
        batch.zone()));
        totalamount = 0;
            }
        }
        batches.clear();
        batches.addAll(newBatches);
    }

    public Map<StorageZone, Double> valueByZone() {
        Map<StorageZone, Double> value = new EnumMap<>(StorageZone.class);
        for (ItemBatch batch : batches){
            StorageZone stzone = batch.zone();
            value.put(stzone, value.getOrDefault(stzone, 0d)+batch.amount()*batch.unitPrice());
        }
        return value;
    }

    public Collection<ItemBatch> getBatches() {
        return batches;
    }
}
