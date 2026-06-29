
// 1a) TODO: von BaseBatch erben und zone-Feld hinzufügen
public class StoredBatch extends BaseBatch {
    public final StorageZone zone;
    public StoredBatch(String productName, int amount, double unitPrice, int batchNumber, StorageZone zone) {
        super(productName, amount, unitPrice, batchNumber);
        this.zone = zone;
    }

    public StorageZone zone() {
        return zone;
    }
}
