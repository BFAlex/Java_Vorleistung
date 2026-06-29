import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Main {
    static int points = 0;
    static int total = 0;

    public static void main(String[] args) {
        System.out.println("========== ПРОВЕРКА ЗАДАНИЙ ==========\n");

        // ===== Задание 1 — BasicSensorReading =====
        System.out.println("--- Zadanie 1 — BasicSensorReading ---");
        check("1a) Felder korrekt gespeichert", () -> {
            SensorReading r = new BasicSensorReading("Thermostat", 22.5, Unit.CELSIUS, 1000L);
            if (!r.deviceName().equals("Thermostat")) throw new RuntimeException("deviceName falsch");
            if (r.value() != 22.5) throw new RuntimeException("value falsch");
            if (r.unit() != Unit.CELSIUS) throw new RuntimeException("unit falsch");
            if (r.timestamp() != 1000L) throw new RuntimeException("timestamp falsch");
        });
        check("1b) IllegalArgumentException bei leerem deviceName", () -> {
            try {
                new BasicSensorReading("", 22.5, Unit.CELSIUS, 1000L);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1c) Enum Unit korrekt gespeichert", () -> {
            SensorReading r = new BasicSensorReading("Sensor", 50.0, Unit.PERCENT, 2000L);
            if (r.unit() != Unit.PERCENT) throw new RuntimeException("unit falsch, erwartet PERCENT");
        });

        // ===== Задание 2 — SimpleHomeMonitor =====
        System.out.println("\n--- Zadanie 2 — SimpleHomeMonitor ---");
        check("2a) Monitor startet leer", () -> {
            SimpleHomeMonitor monitor = new SimpleHomeMonitor();
            if (monitor.getReadings() == null) throw new RuntimeException("getReadings() returned null!");
            if (!monitor.getReadings().isEmpty()) throw new RuntimeException("Monitor sollte leer sein!");
        });
        check("2b) addReading fügt hinzu", () -> {
            SimpleHomeMonitor monitor = new SimpleHomeMonitor();
            monitor.addReading(new BasicSensorReading("Thermostat", 22.5, Unit.CELSIUS, 1000L));
            if (monitor.getReadings().size() != 1) throw new RuntimeException("Size sollte 1 sein, war: " + monitor.getReadings().size());
        });
        check("2c) averageValue berechnet Durchschnitt", () -> {
            SimpleHomeMonitor monitor = new SimpleHomeMonitor();
            monitor.addReading(new BasicSensorReading("Thermostat", 20.0, Unit.CELSIUS, 1000L));
            monitor.addReading(new BasicSensorReading("Thermostat", 30.0, Unit.CELSIUS, 2000L));
            double avg = monitor.averageValue("Thermostat");
            if (avg != 25.0) throw new RuntimeException("Durchschnitt sollte 25.0 sein, war: " + avg);
        });
        check("2c) averageValue wirft NoSuchElementException wenn leer", () -> {
            SimpleHomeMonitor monitor = new SimpleHomeMonitor();
            try {
                monitor.averageValue("Thermostat");
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (NoSuchElementException e) {}
        });
        check("2d) aboveThreshold filtert korrekt", () -> {
            SimpleHomeMonitor monitor = new SimpleHomeMonitor();
            monitor.addReading(new BasicSensorReading("S1", 10.0, Unit.CELSIUS, 1000L));
            monitor.addReading(new BasicSensorReading("S2", 30.0, Unit.CELSIUS, 2000L));
            monitor.addReading(new BasicSensorReading("S3", 50.0, Unit.CELSIUS, 3000L));
            int size = monitor.aboveThreshold(20.0).size();
            if (size != 2) throw new RuntimeException("Sollte 2 sein, war: " + size);
        });
        check("2e) latestReading gibt empty zurück wenn leer", () -> {
            SimpleHomeMonitor monitor = new SimpleHomeMonitor();
            Optional<SensorReading> result = monitor.latestReading("Thermostat");
            if (result == null) throw new RuntimeException("latestReading() returned null!");
            if (result.isPresent()) throw new RuntimeException("Sollte empty sein!");
        });
        check("2e) latestReading gibt neuesten Wert zurück", () -> {
            SimpleHomeMonitor monitor = new SimpleHomeMonitor();
            monitor.addReading(new BasicSensorReading("Thermostat", 20.0, Unit.CELSIUS, 1000L));
            monitor.addReading(new BasicSensorReading("Thermostat", 25.0, Unit.CELSIUS, 3000L));
            monitor.addReading(new BasicSensorReading("Thermostat", 22.0, Unit.CELSIUS, 2000L));
            Optional<SensorReading> latest = monitor.latestReading("Thermostat");
            if (latest == null) throw new RuntimeException("latestReading() returned null!");
            if (!latest.isPresent()) throw new RuntimeException("Sollte nicht empty sein!");
            if (latest.get().timestamp() != 3000L) throw new RuntimeException("Timestamp sollte 3000 sein, war: " + latest.get().timestamp());
        });
        check("2f) countByUnit gibt nicht null zurück", () -> {
            SimpleHomeMonitor monitor = new SimpleHomeMonitor();
            monitor.addReading(new BasicSensorReading("S1", 20.0, Unit.CELSIUS, 1000L));
            Map<Unit, Long> counts = monitor.countByUnit();
            if (counts == null) throw new RuntimeException("countByUnit() returned null!");
        });
        check("2f) countByUnit zählt CELSIUS korrekt", () -> {
            SimpleHomeMonitor monitor = new SimpleHomeMonitor();
            monitor.addReading(new BasicSensorReading("S1", 20.0, Unit.CELSIUS, 1000L));
            monitor.addReading(new BasicSensorReading("S2", 50.0, Unit.PERCENT, 2000L));
            monitor.addReading(new BasicSensorReading("S3", 21.0, Unit.CELSIUS, 3000L));
            Map<Unit, Long> counts = monitor.countByUnit();
            if (counts == null) throw new RuntimeException("countByUnit() returned null!");
            if (counts.get(Unit.CELSIUS) == null) throw new RuntimeException("CELSIUS fehlt in der Map!");
            if (counts.get(Unit.CELSIUS) != 2) throw new RuntimeException("CELSIUS sollte 2 sein, war: " + counts.get(Unit.CELSIUS));
        });
        check("2f) countByUnit zählt PERCENT korrekt", () -> {
            SimpleHomeMonitor monitor = new SimpleHomeMonitor();
            monitor.addReading(new BasicSensorReading("S1", 20.0, Unit.CELSIUS, 1000L));
            monitor.addReading(new BasicSensorReading("S2", 50.0, Unit.PERCENT, 2000L));
            monitor.addReading(new BasicSensorReading("S3", 21.0, Unit.CELSIUS, 3000L));
            Map<Unit, Long> counts = monitor.countByUnit();
            if (counts == null) throw new RuntimeException("countByUnit() returned null!");
            if (counts.get(Unit.PERCENT) == null) throw new RuntimeException("PERCENT fehlt in der Map!");
            if (counts.get(Unit.PERCENT) != 1) throw new RuntimeException("PERCENT sollte 1 sein, war: " + counts.get(Unit.PERCENT));
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