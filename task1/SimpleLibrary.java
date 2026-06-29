import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class SimpleLibrary implements Library {
    private final Collection<Loan> loans;

    public SimpleLibrary() {
        loans = new ArrayList<>();
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public Collection<Loan> openLoans() {
        Collection<Loan> nichtZuruckLoans = new ArrayList<>();

        for (Loan loan : loans){
            if (loan.returned() == false){
                nichtZuruckLoans.add(loan);
            }
        }
        return nichtZuruckLoans;
    }

    public double lateFee(int freeDays, double pricePerDay) {
        double fee = 0;

        for (Loan loan : openLoans()){
            int overdueDays = Math.max(0, loan.days() - freeDays);
            fee += overdueDays * pricePerDay;
        }

        return fee;
    }

    public void markReturned(String title) throws NoSuchElementException {
        boolean istin = false;
        for (Loan loan : loans){
            if (loan.title().equals(title)){
                loan.markReturned();
                istin = true;
            }
        }
        if (istin == false){
            throw new NoSuchElementException("...");
        }
    }
}
