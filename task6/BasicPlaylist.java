import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

// 2a) TODO: Playlist implementieren und Konstruktor vervollständigen
public class BasicPlaylist implements Playlist {
    private final List<Track> tracks;

    public BasicPlaylist() {
        tracks = new ArrayList<>();
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public int totalDuration() {
        int total = 0;
        for (Track track : tracks){
            total += track.durationSeconds();
        }

        return total;
    }

    public Collection<Track> tracksByArtist(String artist) {
        List<Track> trackss = new ArrayList<>();

        for (Track track : tracks) {
            if (track.artist().equals(artist)) {
                trackss.add(track);
            }
        }

        return trackss;
    }

    public Map<Genre, Integer> durationByGenre() {
        Map<Genre, Integer> count = new EnumMap<>(Genre.class);

        for(Track track : tracks){
            Genre typ = track.genre();
            count.put(typ, count.getOrDefault(typ, 0)+track.durationSeconds());
        }

        return count;
    }

    public Playlist shortenTo(int maxDuration) {
        BasicPlaylist newPlay = new BasicPlaylist();
        int total = 0;

        for (Track track : tracks){
            if (total + track.durationSeconds() > maxDuration){
                break;
            }
            newPlay.addTrack(track);
            total += track.durationSeconds();
        }

        return  newPlay;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
