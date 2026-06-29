// 1a) TODO: von BaseTrack erben und genre-Feld hinzufügen
public class AudioTrack extends BaseTrack {
    public Genre genre;

    public AudioTrack(String title, String artist, int durationSeconds, Genre genre) {
        super(title, artist, durationSeconds);
        this.genre = genre;
    }

    public Genre genre() {
        return genre;
    }
}
