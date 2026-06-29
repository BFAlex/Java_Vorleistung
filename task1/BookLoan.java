
public class BookLoan implements Loan {
    private final String title;
    private final String borrower;
    private final int days;
    private boolean returned;

    public BookLoan(String title, String borrower, int days, boolean returned) {
        if (days < 0 || title.isEmpty() || borrower.isEmpty()){
            throw new IllegalArgumentException("...");
        }

        this.title = title;
        this.borrower = borrower;
        this.days = days;
        this.returned = returned;
    }

    public String title() {
        return title;
    }

    public String borrower() {
        return borrower;
    }

    public int days() {
        return days;
    }

    public boolean returned() {
        return returned;
    }

    public void markReturned() {
        this.returned = true;
    }
}
