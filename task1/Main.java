import java.util.NoSuchElementException;

public class Main {
    static int points = 0;
    static int total = 0;

    public static void main(String[] args) {
        System.out.println("========== ПРОВЕРКА ЗАДАНИЙ ==========\n");

        // ===== Задание 1 =====
        System.out.println("--- Задание 1 — BookLoan ---");
        check("1a) Поля BookLoan", () -> {
            Loan loan = new BookLoan("Java Book", "Alice", 7, false);
            assert loan.title().equals("Java Book");
            assert loan.borrower().equals("Alice");
            assert loan.days() == 7;
            assert !loan.returned();
        });
        check("1b) markReturned", () -> {
            Loan loan = new BookLoan("Java Book", "Alice", 7, false);
            loan.markReturned();
            assert loan.returned();
        });
        check("1c) IllegalArgumentException при days < 0", () -> {
            try {
                new BookLoan("Java Book", "Alice", -1, false);
                assert false;
            } catch (IllegalArgumentException e) {}
        });
        check("1d) IllegalArgumentException при пустом title", () -> {
            try {
                new BookLoan("", "Alice", 7, false);
                assert false;
            } catch (IllegalArgumentException e) {}
        });
        check("1e) IllegalArgumentException при пустом borrower", () -> {
            try {
                new BookLoan("Java Book", "", 7, false);
                assert false;
            } catch (IllegalArgumentException e) {}
        });

        // ===== Задание 2 =====
        System.out.println("\n--- Задание 2 — SimpleLibrary ---");
        check("2a) Библиотека начинается пустой", () -> {
            SimpleLibrary library = new SimpleLibrary();
            assert library.openLoans().isEmpty();
        });
        check("2b) addLoan добавляет выдачу", () -> {
            SimpleLibrary library = new SimpleLibrary();
            library.addLoan(new BookLoan("Java Book", "Alice", 7, false));
            assert library.openLoans().size() == 1;
        });
        check("2c) openLoans возвращает только не возвращённые", () -> {
            SimpleLibrary library = new SimpleLibrary();
            library.addLoan(new BookLoan("Book A", "Alice", 7, false));
            library.addLoan(new BookLoan("Book B", "Bob", 5, true));
            assert library.openLoans().size() == 1;
        });
        check("2d) lateFee без просрочки = 0", () -> {
            SimpleLibrary library = new SimpleLibrary();
            library.addLoan(new BookLoan("Book A", "Alice", 5, false));
            assert library.lateFee(10, 2.0) == 0.0;
        });
        check("2d) lateFee с просрочкой", () -> {
            SimpleLibrary library = new SimpleLibrary();
            library.addLoan(new BookLoan("Book A", "Alice", 15, false));
            assert library.lateFee(10, 2.0) == 10.0;
        });
        check("2d) lateFee только для открытых выдач", () -> {
            SimpleLibrary library = new SimpleLibrary();
            library.addLoan(new BookLoan("Book A", "Alice", 15, false));
            library.addLoan(new BookLoan("Book B", "Bob", 15, true));
            assert library.lateFee(10, 2.0) == 10.0;
        });
        check("2e) markReturned помечает как возвращённую", () -> {
            SimpleLibrary library = new SimpleLibrary();
            library.addLoan(new BookLoan("Book A", "Alice", 7, false));
            library.markReturned("Book A");
            assert library.openLoans().isEmpty();
        });
        check("2e) markReturned бросает NoSuchElementException", () -> {
            SimpleLibrary library = new SimpleLibrary();
            try {
                library.markReturned("Nonexistent");
                assert false;
            } catch (NoSuchElementException e) {}
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