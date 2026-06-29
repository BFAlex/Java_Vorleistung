
import java.util.Map;

public class Main {
    static int points = 0;
    static int total = 0;

    public static void main(String[] args) {
        System.out.println("========== ПРОВЕРКА ЗАДАНИЙ ==========\n");

        // ===== Задание 1 — StudentRegistration =====
        System.out.println("--- Zadanie 1 — StudentRegistration ---");
        check("1a) StudentRegistration erbt von BaseRegistration", () -> {
            if (!BaseRegistration.class.isAssignableFrom(StudentRegistration.class))
                throw new RuntimeException("StudentRegistration soll von BaseRegistration erben!");
        });
        check("1b) Felder korrekt gespeichert", () -> {
            CourseRegistration r = new StudentRegistration("CS101", "Alice", 5, ExamType.WRITTEN);
            if (!r.courseCode().equals("CS101")) throw new RuntimeException("courseCode falsch");
            if (!r.studentName().equals("Alice")) throw new RuntimeException("studentName falsch");
            if (r.credits() != 5) throw new RuntimeException("credits falsch");
            if (r.examType() != ExamType.WRITTEN) throw new RuntimeException("examType falsch");
        });
        check("1c) IllegalArgumentException bei leerem courseCode", () -> {
            try {
                new StudentRegistration("", "Alice", 5, ExamType.WRITTEN);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1d) IllegalArgumentException bei leerem studentName", () -> {
            try {
                new StudentRegistration("CS101", "", 5, ExamType.WRITTEN);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1e) IllegalArgumentException bei credits <= 0", () -> {
            try {
                new StudentRegistration("CS101", "Alice", 0, ExamType.WRITTEN);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1f) ExamType NONE korrekt gespeichert", () -> {
            CourseRegistration r = new StudentRegistration("PE101", "Bob", 2, ExamType.NONE);
            if (r.examType() != ExamType.NONE) throw new RuntimeException("examType sollte NONE sein");
        });

        // ===== Задание 2 — SimpleCourseCatalog =====
        System.out.println("\n--- Zadanie 2 — SimpleCourseCatalog ---");
        check("2a) Katalog startet leer", () -> {
            SimpleCourseCatalog catalog = new SimpleCourseCatalog();
            if (catalog.getRegistrations() == null) throw new RuntimeException("getRegistrations() returned null!");
            if (!catalog.getRegistrations().isEmpty()) throw new RuntimeException("Katalog sollte leer sein!");
        });
        check("2b) addRegistration fügt hinzu", () -> {
            SimpleCourseCatalog catalog = new SimpleCourseCatalog();
            catalog.addRegistration(new StudentRegistration("CS101", "Alice", 5, ExamType.WRITTEN));
            if (catalog.getRegistrations().size() != 1) throw new RuntimeException("Size sollte 1 sein!");
        });
        check("2c) totalCredits berechnet Summe", () -> {
            SimpleCourseCatalog catalog = new SimpleCourseCatalog();
            catalog.addRegistration(new StudentRegistration("CS101", "Alice", 5, ExamType.WRITTEN));
            catalog.addRegistration(new StudentRegistration("MATH201", "Bob", 3, ExamType.ORAL));
            if (catalog.totalCredits() != 8) throw new RuntimeException("Credits sollten 8 sein, war: " + catalog.totalCredits());
        });
        check("2c) totalCredits bei leerem Katalog ist 0", () -> {
            SimpleCourseCatalog catalog = new SimpleCourseCatalog();
            if (catalog.totalCredits() != 0) throw new RuntimeException("Credits sollten 0 sein!");
        });
        check("2d) registrationsForCourse filtert korrekt", () -> {
            SimpleCourseCatalog catalog = new SimpleCourseCatalog();
            catalog.addRegistration(new StudentRegistration("CS101", "Alice", 5, ExamType.WRITTEN));
            catalog.addRegistration(new StudentRegistration("CS101", "Bob", 5, ExamType.WRITTEN));
            catalog.addRegistration(new StudentRegistration("MATH201", "Carol", 3, ExamType.ORAL));
            var result = catalog.registrationsForCourse("CS101");
            if (result == null) throw new RuntimeException("registrationsForCourse() returned null!");
            if (result.size() != 2) throw new RuntimeException("Sollte 2 sein, war: " + result.size());
        });
        check("2d) registrationsForCourse gibt leere Collection zurück wenn nicht gefunden", () -> {
            SimpleCourseCatalog catalog = new SimpleCourseCatalog();
            var result = catalog.registrationsForCourse("CS999");
            if (result == null) throw new RuntimeException("registrationsForCourse() returned null!");
            if (!result.isEmpty()) throw new RuntimeException("Sollte leer sein!");
        });
        check("2e) creditsByExamType gibt nicht null zurück", () -> {
            SimpleCourseCatalog catalog = new SimpleCourseCatalog();
            catalog.addRegistration(new StudentRegistration("CS101", "Alice", 5, ExamType.WRITTEN));
            Map<ExamType, Integer> result = catalog.creditsByExamType();
            if (result == null) throw new RuntimeException("creditsByExamType() returned null!");
        });
        check("2e) creditsByExamType berechnet korrekt", () -> {
            SimpleCourseCatalog catalog = new SimpleCourseCatalog();
            catalog.addRegistration(new StudentRegistration("CS101", "Alice", 5, ExamType.WRITTEN));
            catalog.addRegistration(new StudentRegistration("CS102", "Bob", 3, ExamType.WRITTEN));
            catalog.addRegistration(new StudentRegistration("MATH201", "Carol", 4, ExamType.ORAL));
            Map<ExamType, Integer> result = catalog.creditsByExamType();
            if (result == null) throw new RuntimeException("creditsByExamType() returned null!");
            if (result.get(ExamType.WRITTEN) == null) throw new RuntimeException("WRITTEN fehlt!");
            if (result.get(ExamType.WRITTEN) != 8) throw new RuntimeException("WRITTEN sollte 8 sein, war: " + result.get(ExamType.WRITTEN));
            if (result.get(ExamType.ORAL) == null) throw new RuntimeException("ORAL fehlt!");
            if (result.get(ExamType.ORAL) != 4) throw new RuntimeException("ORAL sollte 4 sein, war: " + result.get(ExamType.ORAL));
        });
        check("2f) limitToStudents gibt nicht null zurück", () -> {
            SimpleCourseCatalog catalog = new SimpleCourseCatalog();
            catalog.addRegistration(new StudentRegistration("CS101", "Alice", 5, ExamType.WRITTEN));
            CourseCatalog result = catalog.limitToStudents(1);
            if (result == null) throw new RuntimeException("limitToStudents() returned null!");
        });
        check("2f) limitToStudents begrenzt pro Kurs", () -> {
            SimpleCourseCatalog catalog = new SimpleCourseCatalog();
            catalog.addRegistration(new StudentRegistration("CS101", "Alice", 5, ExamType.WRITTEN));
            catalog.addRegistration(new StudentRegistration("CS101", "Bob", 5, ExamType.WRITTEN));
            catalog.addRegistration(new StudentRegistration("CS101", "Carol", 5, ExamType.WRITTEN));
            catalog.addRegistration(new StudentRegistration("MATH201", "Dave", 3, ExamType.ORAL));
            catalog.addRegistration(new StudentRegistration("MATH201", "Eve", 3, ExamType.ORAL));
            // maxStudents=2: CS101 → 2, MATH201 → 2 → всего 4
            CourseCatalog result = catalog.limitToStudents(2);
            if (result.getRegistrations().size() != 4)
                throw new RuntimeException("Sollte 4 sein, war: " + result.getRegistrations().size());
        });
        check("2f) limitToStudents lässt ursprünglichen Katalog unverändert", () -> {
            SimpleCourseCatalog catalog = new SimpleCourseCatalog();
            catalog.addRegistration(new StudentRegistration("CS101", "Alice", 5, ExamType.WRITTEN));
            catalog.addRegistration(new StudentRegistration("CS101", "Bob", 5, ExamType.WRITTEN));
            catalog.addRegistration(new StudentRegistration("CS101", "Carol", 5, ExamType.WRITTEN));
            catalog.limitToStudents(1);
            if (catalog.getRegistrations().size() != 3)
                throw new RuntimeException("Originalkatalog wurde verändert!");
        });
        check("2f) limitToStudents behält Einfügereihenfolge", () -> {
            SimpleCourseCatalog catalog = new SimpleCourseCatalog();
            catalog.addRegistration(new StudentRegistration("CS101", "Alice", 5, ExamType.WRITTEN));
            catalog.addRegistration(new StudentRegistration("CS101", "Bob", 5, ExamType.WRITTEN));
            catalog.addRegistration(new StudentRegistration("CS101", "Carol", 5, ExamType.WRITTEN));
            CourseCatalog result = catalog.limitToStudents(2);
            var list = new java.util.ArrayList<>(result.getRegistrations());
            if (!list.get(0).studentName().equals("Alice")) throw new RuntimeException("Reihenfolge falsch! Erwartet Alice");
            if (!list.get(1).studentName().equals("Bob")) throw new RuntimeException("Reihenfolge falsch! Erwartet Bob");
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
