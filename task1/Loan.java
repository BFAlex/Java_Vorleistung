public interface Loan {
    String title();
    String borrower();
    int days();
    boolean returned();
    void markReturned();
}
