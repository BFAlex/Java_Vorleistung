import java.util.Optional;

public class Main {
    static int points = 0;
    static int total = 0;

    public static void main(String[] args) {
        System.out.println("========== ПРОВЕРКА ЗАДАНИЙ ==========\n");

        // ===== Задание 1 — MovieScreening =====
        System.out.println("--- Задание 1 — MovieScreening ---");
        check("1a) Поля MovieScreening", () -> {
            Screening s = new MovieScreening("Inception", 3, 120, 50);
            assert s.title().equals("Inception");
            assert s.hall() == 3;
            assert s.durationMinutes() == 120;
            assert s.freeSeats() == 50;
        });
        check("1b) IllegalArgumentException при durationMinutes <= 0", () -> {
            try {
                new MovieScreening("Inception", 3, 0, 50);
                assert false;
            } catch (IllegalArgumentException e) {}
        });
        check("1c) IllegalArgumentException при hall <= 0", () -> {
            try {
                new MovieScreening("Inception", 0, 120, 50);
                assert false;
            } catch (IllegalArgumentException e) {}
        });
        check("1d) IllegalArgumentException при freeSeats < 0", () -> {
            try {
                new MovieScreening("Inception", 3, 120, -1);
                assert false;
            } catch (IllegalArgumentException e) {}
        });

        // ===== Задание 2 — DailyCinemaSchedule =====
        System.out.println("\n--- Задание 2 — DailyCinemaSchedule ---");
        check("2a) Расписание начинается пустым", () -> {
            DailyCinemaSchedule schedule = new DailyCinemaSchedule();
            assert schedule.getScreenings().isEmpty();
        });
        check("2b) addScreening добавляет показ", () -> {
            DailyCinemaSchedule schedule = new DailyCinemaSchedule();
            schedule.addScreening(new MovieScreening("Inception", 3, 120, 50));
            assert schedule.getScreenings().size() == 1;
        });
        check("2c) totalDuration считает сумму", () -> {
            DailyCinemaSchedule schedule = new DailyCinemaSchedule();
            schedule.addScreening(new MovieScreening("Inception", 3, 120, 50));
            schedule.addScreening(new MovieScreening("Matrix", 2, 90, 30));
            assert schedule.totalDuration() == 210;
        });
        check("2d) expectedRevenue считает правильно", () -> {
            DailyCinemaSchedule schedule = new DailyCinemaSchedule();
            // hallCapacity=100, freeSeats=50 → soldSeats=50 → revenue = 50 * 10.0 = 500.0
            schedule.addScreening(new MovieScreening("Inception", 3, 120, 50));
            assert schedule.expectedRevenue(100, 10.0) == 500.0;
        });
        check("2d) expectedRevenue для нескольких показов", () -> {
            DailyCinemaSchedule schedule = new DailyCinemaSchedule();
            schedule.addScreening(new MovieScreening("Inception", 3, 120, 50)); // 50 * 10 = 500
            schedule.addScreening(new MovieScreening("Matrix", 2, 90, 30));     // 70 * 10 = 700
            assert schedule.expectedRevenue(100, 10.0) == 1200.0;
        });
        check("2e) largestScreening возвращает пустой Optional если пусто", () -> {
            DailyCinemaSchedule schedule = new DailyCinemaSchedule();
            assert schedule.largestScreening().isEmpty();
        });
        check("2e) largestScreening возвращает показ с наименьшим freeSeats", () -> {
            DailyCinemaSchedule schedule = new DailyCinemaSchedule();
            schedule.addScreening(new MovieScreening("Inception", 3, 120, 50));
            schedule.addScreening(new MovieScreening("Matrix", 2, 90, 10));
            Optional<Screening> result = schedule.largestScreening();
            assert result.isPresent();
            assert result.get().title().equals("Matrix");
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
