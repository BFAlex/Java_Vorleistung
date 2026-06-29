import java.util.Collection;
import java.util.NoSuchElementException;

public interface Library {
    void addLoan(Loan loan);
    Collection<Loan> openLoans();
    double lateFee(int freeDays, double pricePerDay);
    void markReturned(String title) throws NoSuchElementException;
}
