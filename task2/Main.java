public class Main {
    static int points = 0;
    static int total = 0;

    public static void main(String[] args) {
        System.out.println("========== ПРОВЕРКА ЗАДАНИЙ ==========\n");

        // ===== Задание 1 — StandardParcel =====
        System.out.println("--- Задание 1 — StandardParcel ---");
        check("1a) Поля StandardParcel", () -> {
            Parcel p = new StandardParcel("Berlin", 2.5, 1, false);
            assert p.address().equals("Berlin");
            assert p.weight() == 2.5;
            assert p.priority() == 1;
            assert !p.shipped();
        });
        check("1b) markShipped", () -> {
            Parcel p = new StandardParcel("Berlin", 2.5, 1, false);
            p.markShipped();
            assert p.shipped();
        });
        check("1c) IllegalArgumentException при пустом address", () -> {
            try {
                new StandardParcel("", 2.5, 1, false);
                assert false;
            } catch (IllegalArgumentException e) {}
        });
        check("1d) IllegalArgumentException при weight <= 0", () -> {
            try {
                new StandardParcel("Berlin", 0, 1, false);
                assert false;
            } catch (IllegalArgumentException e) {}
        });
        check("1e) IllegalArgumentException при weight < 0", () -> {
            try {
                new StandardParcel("Berlin", -1, 1, false);
                assert false;
            } catch (IllegalArgumentException e) {}
        });

        // ===== Задание 2 — ParcelHubImpl =====
        System.out.println("\n--- Задание 2 — ParcelHubImpl ---");
        check("2a) Hub начинается пустым", () -> {
            ParcelHubImpl hub = new ParcelHubImpl();
            assert hub.getParcels().isEmpty();
        });
        check("2b) addParcel добавляет посылку", () -> {
            ParcelHubImpl hub = new ParcelHubImpl();
            hub.addParcel(new StandardParcel("Berlin", 2.5, 1, false));
            assert hub.getParcels().size() == 1;
        });
        check("2b) addParcel не добавляет уже отправленную", () -> {
            ParcelHubImpl hub = new ParcelHubImpl();
            hub.addParcel(new StandardParcel("Berlin", 2.5, 1, true));
            assert hub.getParcels().isEmpty();
        });
        check("2c) totalWeight считает правильно", () -> {
            ParcelHubImpl hub = new ParcelHubImpl();
            hub.addParcel(new StandardParcel("Berlin", 2.5, 1, false));
            hub.addParcel(new StandardParcel("Munich", 1.5, 2, false));
            assert hub.totalWeight() == 4.0;
        });
        check("2d) shippingCost считает правильно", () -> {
            ParcelHubImpl hub = new ParcelHubImpl();
            hub.addParcel(new StandardParcel("Berlin", 2.5, 1, false));
            // ceil(2.5) * 3.0 + 5.0 = 3 * 3.0 + 5.0 = 14.0
            assert hub.shippingCost(3.0, 5.0) == 14.0;
        });
        check("2e) shipAll бросает IllegalStateException если пусто", () -> {
            ParcelHubImpl hub = new ParcelHubImpl();
            try {
                hub.shipAll();
                assert false;
            } catch (IllegalStateException e) {}
        });
        check("2e) shipAll возвращает количество", () -> {
            ParcelHubImpl hub = new ParcelHubImpl();
            hub.addParcel(new StandardParcel("Berlin", 2.5, 1, false));
            hub.addParcel(new StandardParcel("Munich", 1.5, 2, false));
            assert hub.shipAll() == 2;
        });
        check("2e) shipAll помечает все как отправленные", () -> {
            ParcelHubImpl hub = new ParcelHubImpl();
            hub.addParcel(new StandardParcel("Berlin", 2.5, 1, false));
            hub.shipAll();
            for (Parcel p : hub.getParcels()) {
                assert p.shipped();
            }
        });

        // ===== Задание 3 — ParcelConfigReader =====
        System.out.println("\n--- Задание 3 — ParcelConfigReader ---");
        check("3a) readConfig читает валидные строки", () -> {
            ParcelConfigReader reader = new ParcelConfigReader();
            reader.readConfig("Berlin|2.5|1\nMunich|1.5|2");
            assert reader.getConfiguration().size() == 2;
        });
        check("3b) readConfig игнорирует пустые строки", () -> {
            ParcelConfigReader reader = new ParcelConfigReader();
            reader.readConfig("Berlin|2.5|1\n\nMunich|1.5|2");
            assert reader.getConfiguration().size() == 2;
        });
        check("3c) readConfig бросает IllegalArgumentException при невалидной строке", () -> {
            ParcelConfigReader reader = new ParcelConfigReader();
            try {
                reader.readConfig("INVALID_LINE");
                assert false;
            } catch (IllegalArgumentException e) {}
        });
        check("3d) readConfig допускает дубликаты адресов", () -> {
            ParcelConfigReader reader = new ParcelConfigReader();
            reader.readConfig("Berlin|2.5|1\nBerlin|1.0|2");
            assert reader.getConfiguration().size() == 2;
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
        } catch (AssertionError | Exception e) {
            System.out.println("✗ " + name + " → " + e.getMessage());
        }
    }
}
