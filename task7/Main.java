import java.util.Map;

public class Main {
    static int points = 0;
    static int total = 0;

    public static void main(String[] args) {
        System.out.println("========== ПРОВЕРКА ЗАДАНИЙ ==========\n");

        // ===== Задание 1 — StoredBatch =====
        System.out.println("--- Zadanie 1 — StoredBatch ---");
        check("1a) StoredBatch erbt von BaseBatch", () -> {
            if (!BaseBatch.class.isAssignableFrom(StoredBatch.class))
                throw new RuntimeException("StoredBatch soll von BaseBatch erben!");
        });
        check("1b) Felder korrekt gespeichert", () -> {
            ItemBatch b = new StoredBatch("Apples", 100, 0.5, 1, StorageZone.COLD);
            if (!b.productName().equals("Apples")) throw new RuntimeException("productName falsch");
            if (b.amount() != 100) throw new RuntimeException("amount falsch");
            if (b.unitPrice() != 0.5) throw new RuntimeException("unitPrice falsch");
            if (b.batchNumber() != 1) throw new RuntimeException("batchNumber falsch");
            if (b.zone() != StorageZone.COLD) throw new RuntimeException("zone falsch");
        });
        check("1c) IllegalArgumentException bei leerem productName", () -> {
            try {
                new StoredBatch("", 100, 0.5, 1, StorageZone.COLD);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1d) IllegalArgumentException bei amount < 0", () -> {
            try {
                new StoredBatch("Apples", -1, 0.5, 1, StorageZone.COLD);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1e) IllegalArgumentException bei unitPrice < 0", () -> {
            try {
                new StoredBatch("Apples", 100, -1.0, 1, StorageZone.COLD);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1f) StorageZone HAZARDOUS korrekt gespeichert", () -> {
            ItemBatch b = new StoredBatch("Chemicals", 10, 5.0, 2, StorageZone.HAZARDOUS);
            if (b.zone() != StorageZone.HAZARDOUS) throw new RuntimeException("zone sollte HAZARDOUS sein");
        });

        // ===== Задание 2 — SimpleWarehouse =====
        System.out.println("\n--- Zadanie 2 — SimpleWarehouse ---");
        check("2a) Warehouse startet leer", () -> {
            SimpleWarehouse wh = new SimpleWarehouse();
            if (wh.getBatches() == null) throw new RuntimeException("getBatches() returned null!");
            if (!wh.getBatches().isEmpty()) throw new RuntimeException("Warehouse sollte leer sein!");
        });
        check("2b) addBatch fügt Posten hinzu", () -> {
            SimpleWarehouse wh = new SimpleWarehouse();
            wh.addBatch(new StoredBatch("Apples", 100, 0.5, 1, StorageZone.COLD));
            if (wh.getBatches().size() != 1) throw new RuntimeException("Size sollte 1 sein!");
        });
        check("2c) totalValue berechnet korrekt", () -> {
            SimpleWarehouse wh = new SimpleWarehouse();
            wh.addBatch(new StoredBatch("Apples", 100, 0.5, 1, StorageZone.COLD));
            wh.addBatch(new StoredBatch("Flour", 50, 1.0, 2, StorageZone.DRY));
            // 100*0.5 + 50*1.0 = 50 + 50 = 100
            double val = wh.totalValue();
            if (val != 100.0) throw new RuntimeException("totalValue sollte 100.0 sein, war: " + val);
        });
        check("2d) totalAmountOf zählt korrekt", () -> {
            SimpleWarehouse wh = new SimpleWarehouse();
            wh.addBatch(new StoredBatch("Apples", 100, 0.5, 1, StorageZone.COLD));
            wh.addBatch(new StoredBatch("Apples", 50, 0.5, 2, StorageZone.COLD));
            wh.addBatch(new StoredBatch("Flour", 30, 1.0, 3, StorageZone.DRY));
            int amount = wh.totalAmountOf("Apples");
            if (amount != 150) throw new RuntimeException("Menge sollte 150 sein, war: " + amount);
        });
        check("2d) totalAmountOf gibt 0 zurück wenn Produkt nicht vorhanden", () -> {
            SimpleWarehouse wh = new SimpleWarehouse();
            int amount = wh.totalAmountOf("Apples");
            if (amount != 0) throw new RuntimeException("Menge sollte 0 sein, war: " + amount);
        });
        check("2e) removeItems entnimmt korrekt", () -> {
            SimpleWarehouse wh = new SimpleWarehouse();
            wh.addBatch(new StoredBatch("Apples", 100, 0.5, 1, StorageZone.COLD));
            wh.removeItems("Apples", 30);
            if (wh.totalAmountOf("Apples") != 70) throw new RuntimeException("Sollte 70 übrig sein!");
        });
        check("2e) removeItems wirft IllegalArgumentException wenn nicht genug", () -> {
            SimpleWarehouse wh = new SimpleWarehouse();
            wh.addBatch(new StoredBatch("Apples", 10, 0.5, 1, StorageZone.COLD));
            try {
                wh.removeItems("Apples", 50);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
            if (wh.totalAmountOf("Apples") != 10) throw new RuntimeException("Zustand sollte unverändert sein!");
        });
        check("2e) removeItems entfernt leere Posten", () -> {
            SimpleWarehouse wh = new SimpleWarehouse();
            wh.addBatch(new StoredBatch("Apples", 100, 0.5, 1, StorageZone.COLD));
            wh.removeItems("Apples", 100);
            if (wh.totalAmountOf("Apples") != 0) throw new RuntimeException("Sollte 0 sein!");
        });
        check("2f) valueByZone gibt nicht null zurück", () -> {
            SimpleWarehouse wh = new SimpleWarehouse();
            wh.addBatch(new StoredBatch("Apples", 100, 0.5, 1, StorageZone.COLD));
            Map<StorageZone, Double> result = wh.valueByZone();
            if (result == null) throw new RuntimeException("valueByZone() returned null!");
        });
        check("2f) valueByZone berechnet korrekt", () -> {
            SimpleWarehouse wh = new SimpleWarehouse();
            wh.addBatch(new StoredBatch("Apples", 100, 0.5, 1, StorageZone.COLD));
            wh.addBatch(new StoredBatch("Ice cream", 50, 2.0, 2, StorageZone.COLD));
            wh.addBatch(new StoredBatch("Flour", 30, 1.0, 3, StorageZone.DRY));
            Map<StorageZone, Double> result = wh.valueByZone();
            if (result == null) throw new RuntimeException("valueByZone() returned null!");
            // COLD: 100*0.5 + 50*2.0 = 50 + 100 = 150
            if (result.get(StorageZone.COLD) == null) throw new RuntimeException("COLD fehlt!");
            if (result.get(StorageZone.COLD) != 150.0) throw new RuntimeException("COLD sollte 150.0 sein, war: " + result.get(StorageZone.COLD));
            // DRY: 30*1.0 = 30
            if (result.get(StorageZone.DRY) == null) throw new RuntimeException("DRY fehlt!");
            if (result.get(StorageZone.DRY) != 30.0) throw new RuntimeException("DRY sollte 30.0 sein, war: " + result.get(StorageZone.DRY));
        });

        // ===== Итог =====
        System.out.println("\n========== ИТОГ ==========");
        System.out.println("Пройдено: " + points + " / " + total);
        System.out.println("==========================");
    }

    static void check(String name, Runnable test) {
        total++;
        try {
            test.run();
            System.out.println("✓ " + name);
            points++;
        } catch (Exception e) {
            System.out.println("✗ " + name + " → " + e.getMessage());
        }
    }
}
