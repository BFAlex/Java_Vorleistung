import java.util.Map;
import java.util.Optional;

public class Main {
    static int points = 0;
    static int total = 0;

    public static void main(String[] args) {
        System.out.println("========== ПРОВЕРКА ЗАДАНИЙ ==========\n");

        // ===== Задание 1 — RoomBooking =====
        System.out.println("--- Zadanie 1 — RoomBooking ---");
        check("1a) Felder korrekt gespeichert", () -> {
            Booking b = new RoomBooking("Alice", 101, 3, 2, RoomType.DOUBLE);
            if (!b.guestName().equals("Alice")) throw new RuntimeException("guestName falsch");
            if (b.roomNumber() != 101) throw new RuntimeException("roomNumber falsch");
            if (b.nights() != 3) throw new RuntimeException("nights falsch");
            if (b.guests() != 2) throw new RuntimeException("guests falsch");
            if (b.roomType() != RoomType.DOUBLE) throw new RuntimeException("roomType falsch");
        });
        check("1b) IllegalArgumentException bei leerem guestName", () -> {
            try {
                new RoomBooking("", 101, 3, 2, RoomType.DOUBLE);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1c) IllegalArgumentException bei roomNumber <= 0", () -> {
            try {
                new RoomBooking("Alice", 0, 3, 2, RoomType.DOUBLE);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1d) IllegalArgumentException bei nights <= 0", () -> {
            try {
                new RoomBooking("Alice", 101, 0, 2, RoomType.DOUBLE);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1e) IllegalArgumentException bei guests <= 0", () -> {
            try {
                new RoomBooking("Alice", 101, 3, 0, RoomType.DOUBLE);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1f) RoomType SUITE korrekt gespeichert", () -> {
            Booking b = new RoomBooking("Bob", 202, 5, 1, RoomType.SUITE);
            if (b.roomType() != RoomType.SUITE) throw new RuntimeException("roomType sollte SUITE sein");
        });

        // ===== Задание 2 — SimpleHotelDesk =====
        System.out.println("\n--- Zadanie 2 — SimpleHotelDesk ---");
        check("2a) Desk startet leer", () -> {
            SimpleHotelDesk desk = new SimpleHotelDesk();
            if (desk.getBookings() == null) throw new RuntimeException("getBookings() returned null!");
            if (!desk.getBookings().isEmpty()) throw new RuntimeException("Desk sollte leer sein!");
        });
        check("2b) addBooking fügt Buchung hinzu", () -> {
            SimpleHotelDesk desk = new SimpleHotelDesk();
            boolean added = desk.addBooking(new RoomBooking("Alice", 101, 3, 2, RoomType.DOUBLE));
            if (!added) throw new RuntimeException("addBooking sollte true zurückgeben!");
            if (desk.getBookings().size() != 1) throw new RuntimeException("Size sollte 1 sein!");
        });
        check("2b) addBooking lehnt doppeltes Zimmer ab", () -> {
            SimpleHotelDesk desk = new SimpleHotelDesk();
            desk.addBooking(new RoomBooking("Alice", 101, 3, 2, RoomType.DOUBLE));
            boolean added = desk.addBooking(new RoomBooking("Bob", 101, 2, 1, RoomType.SINGLE));
            if (added) throw new RuntimeException("addBooking sollte false zurückgeben bei doppeltem Zimmer!");
            if (desk.getBookings().size() != 1) throw new RuntimeException("Size sollte immer noch 1 sein!");
        });
        check("2c) bookingPrice berechnet korrekt", () -> {
            SimpleHotelDesk desk = new SimpleHotelDesk();
            Booking b = new RoomBooking("Alice", 101, 3, 2, RoomType.DOUBLE);
            // 3 * 50.0 + 2 * 10.0 = 150 + 20 = 170
            double price = desk.bookingPrice(b, 50.0, 10.0);
            if (price != 170.0) throw new RuntimeException("Preis sollte 170.0 sein, war: " + price);
        });
        check("2d) totalRevenue berechnet Summe", () -> {
            SimpleHotelDesk desk = new SimpleHotelDesk();
            desk.addBooking(new RoomBooking("Alice", 101, 3, 2, RoomType.DOUBLE));
            desk.addBooking(new RoomBooking("Bob", 102, 2, 1, RoomType.SINGLE));
            // Alice: 3*50 + 2*10 = 170
            // Bob:   2*50 + 1*10 = 110
            double revenue = desk.totalRevenue(50.0, 10.0);
            if (revenue != 280.0) throw new RuntimeException("Revenue sollte 280.0 sein, war: " + revenue);
        });
        check("2e) findBooking gibt Buchung zurück", () -> {
            SimpleHotelDesk desk = new SimpleHotelDesk();
            desk.addBooking(new RoomBooking("Alice", 101, 3, 2, RoomType.DOUBLE));
            Optional<Booking> result = desk.findBooking(101);
            if (result == null) throw new RuntimeException("findBooking() returned null!");
            if (!result.isPresent()) throw new RuntimeException("Sollte nicht empty sein!");
            if (!result.get().guestName().equals("Alice")) throw new RuntimeException("Falscher Gast!");
        });
        check("2e) findBooking gibt empty zurück wenn nicht gefunden", () -> {
            SimpleHotelDesk desk = new SimpleHotelDesk();
            Optional<Booking> result = desk.findBooking(999);
            if (result == null) throw new RuntimeException("findBooking() returned null!");
            if (result.isPresent()) throw new RuntimeException("Sollte empty sein!");
        });
        check("2f) countByRoomType gibt nicht null zurück", () -> {
            SimpleHotelDesk desk = new SimpleHotelDesk();
            desk.addBooking(new RoomBooking("Alice", 101, 3, 2, RoomType.DOUBLE));
            Map<RoomType, Long> counts = desk.countByRoomType();
            if (counts == null) throw new RuntimeException("countByRoomType() returned null!");
        });
        check("2f) countByRoomType zählt korrekt", () -> {
            SimpleHotelDesk desk = new SimpleHotelDesk();
            desk.addBooking(new RoomBooking("Alice", 101, 3, 2, RoomType.DOUBLE));
            desk.addBooking(new RoomBooking("Bob", 102, 2, 1, RoomType.SINGLE));
            desk.addBooking(new RoomBooking("Carol", 103, 1, 2, RoomType.DOUBLE));
            Map<RoomType, Long> counts = desk.countByRoomType();
            if (counts == null) throw new RuntimeException("countByRoomType() returned null!");
            if (counts.get(RoomType.DOUBLE) == null) throw new RuntimeException("DOUBLE fehlt in der Map!");
            if (counts.get(RoomType.DOUBLE) != 2) throw new RuntimeException("DOUBLE sollte 2 sein, war: " + counts.get(RoomType.DOUBLE));
            if (counts.get(RoomType.SINGLE) == null) throw new RuntimeException("SINGLE fehlt in der Map!");
            if (counts.get(RoomType.SINGLE) != 1) throw new RuntimeException("SINGLE sollte 1 sein, war: " + counts.get(RoomType.SINGLE));
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
