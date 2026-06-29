import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LibraryPrinter {
    private final OutputStream out;

    public LibraryPrinter(OutputStream out) {
        this.out = new BufferedOutputStream(out);
    }

    public void printLoan(Loan loan) throws IOException  {
        String line = loan.title() + ";" + loan.borrower() + ";" + loan.days() + ";" + loan.returned() + "\n";
        out.write(line.getBytes());
    }

    public void printLibrary(Library library) throws IOException {
        for (Loan loan : library.openLoans()) {
            printLoan(loan);
        }
    }
}
