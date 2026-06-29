public abstract class BaseTrack implements Track {
    private final String title;
    private final String artist;
    private final int durationSeconds;

    protected BaseTrack(String title, String artist, int durationSeconds) {
        if (title.isEmpty() || artist.isEmpty()) {
            throw new IllegalArgumentException("Titel und Künstler dürfen nicht leer sein");
        }
        if (durationSeconds <= 0) {
            throw new IllegalArgumentException("Dauer muss positiv sein");
        }
        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
    }

    public String title() { return title; }
    public String artist() { return artist; }
    public int durationSeconds() { return durationSeconds; }

    public abstract Genre genre();
}
