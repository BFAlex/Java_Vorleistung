import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface Playlist {
    void addTrack(Track track);
    int totalDuration();
    Collection<Track> tracksByArtist(String artist);
    Map<Genre, Integer> durationByGenre();
    Playlist shortenTo(int maxDuration);
    List<Track> getTracks();
}
