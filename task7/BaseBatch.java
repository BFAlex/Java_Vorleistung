
public abstract class BaseBatch implements ItemBatch {
    private final String productName;
    private final int amount;
    private final double unitPrice;
    private final int batchNumber;

    protected BaseBatch(String productName, int amount, double unitPrice, int batchNumber) {
        if (productName.isEmpty()) {
            throw new IllegalArgumentException("Produktname darf nicht leer sein");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Menge darf nicht negativ sein");
        }
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Preis darf nicht negativ sein");
        }
        this.productName = productName;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.batchNumber = batchNumber;
    }

    public String productName() { return productName; }
    public int amount() { return amount; }
    public double unitPrice() { return unitPrice; }
    public int batchNumber() { return batchNumber; }

    public abstract StorageZone zone();
}
