public abstract class BaseSession implements TrainingSession {
    private final String name;
    private final String trainer;
    private final int capacity;
    private final int booked;

    protected BaseSession(String name, String trainer, int capacity, int booked) {
        if (name.isEmpty() || trainer.isEmpty()) {
            throw new IllegalArgumentException("Name und Trainer dürfen nicht leer sein");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Kapazität muss positiv sein");
        }
        if (booked < 0 || booked > capacity) {
            throw new IllegalArgumentException("Ungültige Buchungszahl");
        }
        this.name = name;
        this.trainer = trainer;
        this.capacity = capacity;
        this.booked = booked;
    }

    public String name() { return name; }
    public String trainer() { return trainer; }
    public int capacity() { return capacity; }
    public int booked() { return booked; }

    public abstract Intensity intensity();
}
