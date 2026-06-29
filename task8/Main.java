
import java.util.Map;
import java.util.Optional;

public class Main {
    static int points = 0;
    static int total = 0;

    public static void main(String[] args) {
        System.out.println("========== ПРОВЕРКА ЗАДАНИЙ ==========\n");

        // ===== Задание 1 — GymSession =====
        System.out.println("--- Zadanie 1 — GymSession ---");
        check("1a) GymSession erbt von BaseSession", () -> {
            if (!BaseSession.class.isAssignableFrom(GymSession.class))
                throw new RuntimeException("GymSession soll von BaseSession erben!");
        });
        check("1b) Felder korrekt gespeichert", () -> {
            TrainingSession s = new GymSession("Yoga", "Anna", 20, 10, Intensity.LOW);
            if (!s.name().equals("Yoga")) throw new RuntimeException("name falsch");
            if (!s.trainer().equals("Anna")) throw new RuntimeException("trainer falsch");
            if (s.capacity() != 20) throw new RuntimeException("capacity falsch");
            if (s.booked() != 10) throw new RuntimeException("booked falsch");
            if (s.intensity() != Intensity.LOW) throw new RuntimeException("intensity falsch");
        });
        check("1c) IllegalArgumentException bei leerem name", () -> {
            try {
                new GymSession("", "Anna", 20, 10, Intensity.LOW);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1d) IllegalArgumentException bei leerem trainer", () -> {
            try {
                new GymSession("Yoga", "", 20, 10, Intensity.LOW);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1e) IllegalArgumentException bei capacity <= 0", () -> {
            try {
                new GymSession("Yoga", "Anna", 0, 0, Intensity.LOW);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1f) IllegalArgumentException bei booked < 0", () -> {
            try {
                new GymSession("Yoga", "Anna", 20, -1, Intensity.LOW);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1g) IllegalArgumentException bei booked > capacity", () -> {
            try {
                new GymSession("Yoga", "Anna", 20, 25, Intensity.LOW);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1h) Intensity EXTREME korrekt gespeichert", () -> {
            TrainingSession s = new GymSession("CrossFit", "Max", 15, 5, Intensity.EXTREME);
            if (s.intensity() != Intensity.EXTREME) throw new RuntimeException("intensity sollte EXTREME sein");
        });

        // ===== Задание 2 — SimpleStudioPlan =====
        System.out.println("\n--- Zadanie 2 — SimpleStudioPlan ---");
        check("2a) Plan startet leer", () -> {
            SimpleStudioPlan plan = new SimpleStudioPlan();
            if (plan.getSessions() == null) throw new RuntimeException("getSessions() returned null!");
            if (!plan.getSessions().isEmpty()) throw new RuntimeException("Plan sollte leer sein!");
        });
        check("2b) addSession fügt hinzu", () -> {
            SimpleStudioPlan plan = new SimpleStudioPlan();
            plan.addSession(new GymSession("Yoga", "Anna", 20, 10, Intensity.LOW));
            if (plan.getSessions().size() != 1) throw new RuntimeException("Size sollte 1 sein!");
        });
        check("2c) freeSpots berechnet korrekt", () -> {
            SimpleStudioPlan plan = new SimpleStudioPlan();
            plan.addSession(new GymSession("Yoga", "Anna", 20, 10, Intensity.LOW));
            plan.addSession(new GymSession("CrossFit", "Max", 15, 5, Intensity.HIGH));
            // (20-10) + (15-5) = 10 + 10 = 20
            if (plan.freeSpots() != 20) throw new RuntimeException("freeSpots sollte 20 sein, war: " + plan.freeSpots());
        });
        check("2d) expectedIncome berechnet korrekt", () -> {
            SimpleStudioPlan plan = new SimpleStudioPlan();
            plan.addSession(new GymSession("Yoga", "Anna", 20, 10, Intensity.LOW));
            plan.addSession(new GymSession("CrossFit", "Max", 15, 5, Intensity.HIGH));
            // (10 + 5) * 15.0 = 225.0
            double income = plan.expectedIncome(15.0);
            if (income != 225.0) throw new RuntimeException("Income sollte 225.0 sein, war: " + income);
        });
        check("2e) mostPopularSession gibt empty zurück wenn leer", () -> {
            SimpleStudioPlan plan = new SimpleStudioPlan();
            Optional<TrainingSession> result = plan.mostPopularSession();
            if (result == null) throw new RuntimeException("mostPopularSession() returned null!");
            if (result.isPresent()) throw new RuntimeException("Sollte empty sein!");
        });
        check("2e) mostPopularSession gibt Session mit meisten booked zurück", () -> {
            SimpleStudioPlan plan = new SimpleStudioPlan();
            plan.addSession(new GymSession("Yoga", "Anna", 20, 10, Intensity.LOW));
            plan.addSession(new GymSession("CrossFit", "Max", 15, 14, Intensity.HIGH));
            plan.addSession(new GymSession("Pilates", "Lisa", 25, 5, Intensity.MEDIUM));
            Optional<TrainingSession> result = plan.mostPopularSession();
            if (result == null) throw new RuntimeException("mostPopularSession() returned null!");
            if (!result.isPresent()) throw new RuntimeException("Sollte nicht empty sein!");
            if (!result.get().name().equals("CrossFit")) throw new RuntimeException("Sollte CrossFit sein, war: " + result.get().name());
        });
        check("2f) bookedByIntensity gibt nicht null zurück", () -> {
            SimpleStudioPlan plan = new SimpleStudioPlan();
            plan.addSession(new GymSession("Yoga", "Anna", 20, 10, Intensity.LOW));
            Map<Intensity, Integer> result = plan.bookedByIntensity();
            if (result == null) throw new RuntimeException("bookedByIntensity() returned null!");
        });
        check("2f) bookedByIntensity berechnet korrekt", () -> {
            SimpleStudioPlan plan = new SimpleStudioPlan();
            plan.addSession(new GymSession("Yoga", "Anna", 20, 10, Intensity.LOW));
            plan.addSession(new GymSession("Stretching", "Lisa", 15, 8, Intensity.LOW));
            plan.addSession(new GymSession("CrossFit", "Max", 15, 14, Intensity.HIGH));
            Map<Intensity, Integer> result = plan.bookedByIntensity();
            if (result == null) throw new RuntimeException("bookedByIntensity() returned null!");
            if (result.get(Intensity.LOW) == null) throw new RuntimeException("LOW fehlt!");
            if (result.get(Intensity.LOW) != 18) throw new RuntimeException("LOW sollte 18 sein, war: " + result.get(Intensity.LOW));
            if (result.get(Intensity.HIGH) == null) throw new RuntimeException("HIGH fehlt!");
            if (result.get(Intensity.HIGH) != 14) throw new RuntimeException("HIGH sollte 14 sein, war: " + result.get(Intensity.HIGH));
        });
        check("2g) sessionsAboveCapacity filtert korrekt", () -> {
            SimpleStudioPlan plan = new SimpleStudioPlan();
            plan.addSession(new GymSession("Yoga", "Anna", 20, 10, Intensity.LOW));
            plan.addSession(new GymSession("CrossFit", "Max", 15, 5, Intensity.HIGH));
            plan.addSession(new GymSession("Pilates", "Lisa", 30, 5, Intensity.MEDIUM));
            var result = plan.sessionsAboveCapacity(18);
            if (result == null) throw new RuntimeException("sessionsAboveCapacity() returned null!");
            if (result.size() != 2) throw new RuntimeException("Sollte 2 Sessions sein, war: " + result.size());
        });
        check("2g) sessionsAboveCapacity gibt leere Collection zurück wenn keine passt", () -> {
            SimpleStudioPlan plan = new SimpleStudioPlan();
            plan.addSession(new GymSession("Yoga", "Anna", 20, 10, Intensity.LOW));
            var result = plan.sessionsAboveCapacity(50);
            if (result == null) throw new RuntimeException("sessionsAboveCapacity() returned null!");
            if (!result.isEmpty()) throw new RuntimeException("Sollte leer sein!");
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
