public interface Parcel {
    String address();
    double weight();
    int priority();
    boolean shipped();
    void markShipped();
}
