
import java.util.Map;
import java.util.Optional;

public class Main {
    static int points = 0;
    static int total = 0;

    public static void main(String[] args) {
        System.out.println("========== ПРОВЕРКА ЗАДАНИЙ ==========\n");

        // ===== Задание 1 — StandardBikeRental =====
        System.out.println("--- Zadanie 1 — StandardBikeRental ---");
        check("1a) Felder korrekt gespeichert", () -> {
            BikeRental r = new StandardBikeRental("B001", "Alice", 3.5, BikeType.CITY, false);
            if (!r.bikeId().equals("B001")) throw new RuntimeException("bikeId falsch");
            if (!r.customerName().equals("Alice")) throw new RuntimeException("customerName falsch");
            if (r.hours() != 3.5) throw new RuntimeException("hours falsch");
            if (r.bikeType() != BikeType.CITY) throw new RuntimeException("bikeType falsch");
            if (r.returned()) throw new RuntimeException("returned sollte false sein");
        });
        check("1b) markReturned setzt returned auf true", () -> {
            BikeRental r = new StandardBikeRental("B001", "Alice", 3.5, BikeType.CITY, false);
            r.markReturned();
            if (!r.returned()) throw new RuntimeException("returned sollte true sein!");
        });
        check("1c) IllegalArgumentException bei leerem bikeId", () -> {
            try {
                new StandardBikeRental("", "Alice", 3.5, BikeType.CITY, false);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1d) IllegalArgumentException bei leerem customerName", () -> {
            try {
                new StandardBikeRental("B001", "", 3.5, BikeType.CITY, false);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1e) IllegalArgumentException bei hours <= 0", () -> {
            try {
                new StandardBikeRental("B001", "Alice", 0, BikeType.CITY, false);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1f) BikeType ELECTRIC korrekt gespeichert", () -> {
            BikeRental r = new StandardBikeRental("E001", "Bob", 2.0, BikeType.ELECTRIC, false);
            if (r.bikeType() != BikeType.ELECTRIC) throw new RuntimeException("bikeType sollte ELECTRIC sein");
        });

        // ===== Задание 2 — SimpleRentalStation =====
        System.out.println("\n--- Zadanie 2 — SimpleRentalStation ---");
        check("2a) Station startet leer", () -> {
            SimpleRentalStation station = new SimpleRentalStation();
            if (station.getRentals() == null) throw new RuntimeException("getRentals() returned null!");
            if (!station.getRentals().isEmpty()) throw new RuntimeException("Station sollte leer sein!");
        });
        check("2b) addRental fügt Verleih hinzu", () -> {
            SimpleRentalStation station = new SimpleRentalStation();
            station.addRental(new StandardBikeRental("B001", "Alice", 3.5, BikeType.CITY, false));
            if (station.getRentals().size() != 1) throw new RuntimeException("Size sollte 1 sein!");
        });
        check("2b) addRental wirft IllegalStateException bei doppelter aktiver bikeId", () -> {
            SimpleRentalStation station = new SimpleRentalStation();
            station.addRental(new StandardBikeRental("B001", "Alice", 3.5, BikeType.CITY, false));
            try {
                station.addRental(new StandardBikeRental("B001", "Bob", 2.0, BikeType.CITY, false));
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalStateException e) {}
        });
        check("2b) addRental erlaubt gleiche bikeId wenn bereits returned", () -> {
            SimpleRentalStation station = new SimpleRentalStation();
            station.addRental(new StandardBikeRental("B001", "Alice", 3.5, BikeType.CITY, true));
            station.addRental(new StandardBikeRental("B001", "Bob", 2.0, BikeType.CITY, false));
            if (station.getRentals().size() != 2) throw new RuntimeException("Size sollte 2 sein!");
        });
        check("2c) rentalCost für CITY korrekt", () -> {
            SimpleRentalStation station = new SimpleRentalStation();
            BikeRental r = new StandardBikeRental("B001", "Alice", 3.0, BikeType.CITY, false);
            // 3.0 * 5.0 = 15.0
            double cost = station.rentalCost(r, 5.0, 10.0);
            if (cost != 15.0) throw new RuntimeException("Cost sollte 15.0 sein, war: " + cost);
        });
        check("2c) rentalCost für ELECTRIC mit Zuschlag", () -> {
            SimpleRentalStation station = new SimpleRentalStation();
            BikeRental r = new StandardBikeRental("E001", "Bob", 2.0, BikeType.ELECTRIC, false);
            // 2.0 * 5.0 + 10.0 = 20.0
            double cost = station.rentalCost(r, 5.0, 10.0);
            if (cost != 20.0) throw new RuntimeException("Cost sollte 20.0 sein, war: " + cost);
        });
        check("2d) totalRevenue berechnet Summe", () -> {
            SimpleRentalStation station = new SimpleRentalStation();
            station.addRental(new StandardBikeRental("B001", "Alice", 3.0, BikeType.CITY, false));
            station.addRental(new StandardBikeRental("E001", "Bob", 2.0, BikeType.ELECTRIC, false));
            // CITY: 3*5 = 15, ELECTRIC: 2*5+10 = 20 → 35
            double revenue = station.totalRevenue(5.0, 10.0);
            if (revenue != 35.0) throw new RuntimeException("Revenue sollte 35.0 sein, war: " + revenue);
        });
        check("2e) activeRentals gibt nur nicht returned zurück", () -> {
            SimpleRentalStation station = new SimpleRentalStation();
            station.addRental(new StandardBikeRental("B001", "Alice", 3.0, BikeType.CITY, false));
            station.addRental(new StandardBikeRental("B002", "Bob", 2.0, BikeType.MOUNTAIN, true));
            var active = station.activeRentals();
            if (active == null) throw new RuntimeException("activeRentals() returned null!");
            if (active.size() != 1) throw new RuntimeException("Sollte 1 aktiven Verleih haben, war: " + active.size());
        });
        check("2f) findByBikeId gibt Verleih zurück", () -> {
            SimpleRentalStation station = new SimpleRentalStation();
            station.addRental(new StandardBikeRental("B001", "Alice", 3.0, BikeType.CITY, false));
            Optional<BikeRental> result = station.findByBikeId("B001");
            if (result == null) throw new RuntimeException("findByBikeId() returned null!");
            if (!result.isPresent()) throw new RuntimeException("Sollte nicht empty sein!");
            if (!result.get().customerName().equals("Alice")) throw new RuntimeException("Falscher Kunde!");
        });
        check("2f) findByBikeId gibt empty zurück wenn nicht gefunden", () -> {
            SimpleRentalStation station = new SimpleRentalStation();
            Optional<BikeRental> result = station.findByBikeId("X999");
            if (result == null) throw new RuntimeException("findByBikeId() returned null!");
            if (result.isPresent()) throw new RuntimeException("Sollte empty sein!");
        });
        check("2g) countByBikeType gibt nicht null zurück", () -> {
            SimpleRentalStation station = new SimpleRentalStation();
            station.addRental(new StandardBikeRental("B001", "Alice", 3.0, BikeType.CITY, false));
            Map<BikeType, Long> result = station.countByBikeType();
            if (result == null) throw new RuntimeException("countByBikeType() returned null!");
        });
        check("2g) countByBikeType zählt korrekt", () -> {
            SimpleRentalStation station = new SimpleRentalStation();
            station.addRental(new StandardBikeRental("B001", "Alice", 3.0, BikeType.CITY, false));
            station.addRental(new StandardBikeRental("B002", "Bob", 2.0, BikeType.CITY, false));
            station.addRental(new StandardBikeRental("E001", "Carol", 1.0, BikeType.ELECTRIC, false));
            Map<BikeType, Long> result = station.countByBikeType();
            if (result == null) throw new RuntimeException("countByBikeType() returned null!");
            if (result.get(BikeType.CITY) == null) throw new RuntimeException("CITY fehlt!");
            if (result.get(BikeType.CITY) != 2) throw new RuntimeException("CITY sollte 2 sein, war: " + result.get(BikeType.CITY));
            if (result.get(BikeType.ELECTRIC) == null) throw new RuntimeException("ELECTRIC fehlt!");
            if (result.get(BikeType.ELECTRIC) != 1) throw new RuntimeException("ELECTRIC sollte 1 sein, war: " + result.get(BikeType.ELECTRIC));
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
