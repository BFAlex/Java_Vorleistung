import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Main {
    static int points = 0;
    static int total = 0;

    public static void main(String[] args) {
        System.out.println("========== ПРОВЕРКА ЗАДАНИЙ ==========\n");

        // ===== Задание 1 — AudioTrack =====
        System.out.println("--- Zadanie 1 — AudioTrack ---");
        check("1a) AudioTrack erbt von BaseTrack", () -> {
            if (!BaseTrack.class.isAssignableFrom(AudioTrack.class))
                throw new RuntimeException("AudioTrack soll von BaseTrack erben!");
        });
        check("1b) Felder korrekt gespeichert", () -> {
            Track t = new AudioTrack("Bohemian Rhapsody", "Queen", 354, Genre.ROCK);
            if (!t.title().equals("Bohemian Rhapsody")) throw new RuntimeException("title falsch");
            if (!t.artist().equals("Queen")) throw new RuntimeException("artist falsch");
            if (t.durationSeconds() != 354) throw new RuntimeException("durationSeconds falsch");
            if (t.genre() != Genre.ROCK) throw new RuntimeException("genre falsch");
        });
        check("1c) IllegalArgumentException bei leerem title", () -> {
            try {
                new AudioTrack("", "Queen", 354, Genre.ROCK);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1d) IllegalArgumentException bei leerem artist", () -> {
            try {
                new AudioTrack("Bohemian Rhapsody", "", 354, Genre.ROCK);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1e) IllegalArgumentException bei durationSeconds <= 0", () -> {
            try {
                new AudioTrack("Bohemian Rhapsody", "Queen", 0, Genre.ROCK);
                throw new RuntimeException("Keine Exception geworfen!");
            } catch (IllegalArgumentException e) {}
        });
        check("1f) Genre JAZZ korrekt gespeichert", () -> {
            Track t = new AudioTrack("So What", "Miles Davis", 562, Genre.JAZZ);
            if (t.genre() != Genre.JAZZ) throw new RuntimeException("genre sollte JAZZ sein");
        });

        // ===== Задание 2 — BasicPlaylist =====
        System.out.println("\n--- Zadanie 2 — BasicPlaylist ---");
        check("2a) Playlist startet leer", () -> {
            BasicPlaylist pl = new BasicPlaylist();
            if (pl.getTracks() == null) throw new RuntimeException("getTracks() returned null!");
            if (!pl.getTracks().isEmpty()) throw new RuntimeException("Playlist sollte leer sein!");
        });
        check("2b) addTrack fügt hinzu", () -> {
            BasicPlaylist pl = new BasicPlaylist();
            pl.addTrack(new AudioTrack("Song A", "Artist A", 200, Genre.POP));
            if (pl.getTracks().size() != 1) throw new RuntimeException("Size sollte 1 sein!");
        });
        check("2b) Reihenfolge der Tracks bleibt erhalten", () -> {
            BasicPlaylist pl = new BasicPlaylist();
            pl.addTrack(new AudioTrack("Song A", "Artist A", 200, Genre.POP));
            pl.addTrack(new AudioTrack("Song B", "Artist B", 300, Genre.ROCK));
            pl.addTrack(new AudioTrack("Song C", "Artist A", 150, Genre.POP));
            List<Track> tracks = pl.getTracks();
            if (!tracks.get(0).title().equals("Song A")) throw new RuntimeException("Reihenfolge falsch!");
            if (!tracks.get(1).title().equals("Song B")) throw new RuntimeException("Reihenfolge falsch!");
            if (!tracks.get(2).title().equals("Song C")) throw new RuntimeException("Reihenfolge falsch!");
        });
        check("2c) totalDuration berechnet Summe", () -> {
            BasicPlaylist pl = new BasicPlaylist();
            pl.addTrack(new AudioTrack("Song A", "Artist A", 200, Genre.POP));
            pl.addTrack(new AudioTrack("Song B", "Artist B", 300, Genre.ROCK));
            if (pl.totalDuration() != 500) throw new RuntimeException("Dauer sollte 500 sein, war: " + pl.totalDuration());
        });
        check("2c) totalDuration bei leerer Playlist ist 0", () -> {
            BasicPlaylist pl = new BasicPlaylist();
            if (pl.totalDuration() != 0) throw new RuntimeException("Dauer sollte 0 sein!");
        });
        check("2d) tracksByArtist filtert korrekt", () -> {
            BasicPlaylist pl = new BasicPlaylist();
            pl.addTrack(new AudioTrack("Song A", "Queen", 200, Genre.ROCK));
            pl.addTrack(new AudioTrack("Song B", "Beatles", 300, Genre.POP));
            pl.addTrack(new AudioTrack("Song C", "Queen", 150, Genre.ROCK));
            Collection<Track> result = pl.tracksByArtist("Queen");
            if (result == null) throw new RuntimeException("tracksByArtist() returned null!");
            if (result.size() != 2) throw new RuntimeException("Sollte 2 Tracks sein, war: " + result.size());
        });
        check("2d) tracksByArtist gibt leere Collection wenn nicht gefunden", () -> {
            BasicPlaylist pl = new BasicPlaylist();
            pl.addTrack(new AudioTrack("Song A", "Queen", 200, Genre.ROCK));
            Collection<Track> result = pl.tracksByArtist("Beatles");
            if (result == null) throw new RuntimeException("tracksByArtist() returned null!");
            if (!result.isEmpty()) throw new RuntimeException("Sollte leer sein!");
        });
        check("2e) durationByGenre gibt nicht null zurück", () -> {
            BasicPlaylist pl = new BasicPlaylist();
            pl.addTrack(new AudioTrack("Song A", "Artist A", 200, Genre.POP));
            Map<Genre, Integer> result = pl.durationByGenre();
            if (result == null) throw new RuntimeException("durationByGenre() returned null!");
        });
        check("2e) durationByGenre berechnet korrekt", () -> {
            BasicPlaylist pl = new BasicPlaylist();
            pl.addTrack(new AudioTrack("Song A", "Artist A", 200, Genre.POP));
            pl.addTrack(new AudioTrack("Song B", "Artist B", 300, Genre.ROCK));
            pl.addTrack(new AudioTrack("Song C", "Artist C", 150, Genre.POP));
            Map<Genre, Integer> result = pl.durationByGenre();
            if (result == null) throw new RuntimeException("durationByGenre() returned null!");
            if (result.get(Genre.POP) == null) throw new RuntimeException("POP fehlt in der Map!");
            if (result.get(Genre.POP) != 350) throw new RuntimeException("POP sollte 350 sein, war: " + result.get(Genre.POP));
            if (result.get(Genre.ROCK) == null) throw new RuntimeException("ROCK fehlt in der Map!");
            if (result.get(Genre.ROCK) != 300) throw new RuntimeException("ROCK sollte 300 sein, war: " + result.get(Genre.ROCK));
        });
        check("2f) shortenTo gibt nicht null zurück", () -> {
            BasicPlaylist pl = new BasicPlaylist();
            pl.addTrack(new AudioTrack("Song A", "Artist A", 200, Genre.POP));
            Playlist result = pl.shortenTo(100);
            if (result == null) throw new RuntimeException("shortenTo() returned null!");
        });
        check("2f) shortenTo übernimmt Tracks bis maxDuration", () -> {
            BasicPlaylist pl = new BasicPlaylist();
            pl.addTrack(new AudioTrack("Song A", "Artist A", 200, Genre.POP));
            pl.addTrack(new AudioTrack("Song B", "Artist B", 300, Genre.ROCK));
            pl.addTrack(new AudioTrack("Song C", "Artist C", 150, Genre.JAZZ));
            // 200 + 300 = 500 ≤ 600, 500 + 150 = 650 > 600 → nur 2 Tracks
            Playlist result = pl.shortenTo(600);
            if (result.getTracks().size() != 2) throw new RuntimeException("Sollte 2 Tracks haben, hatte: " + result.getTracks().size());
        });
        check("2f) shortenTo lässt ursprüngliche Playlist unverändert", () -> {
            BasicPlaylist pl = new BasicPlaylist();
            pl.addTrack(new AudioTrack("Song A", "Artist A", 200, Genre.POP));
            pl.addTrack(new AudioTrack("Song B", "Artist B", 300, Genre.ROCK));
            pl.addTrack(new AudioTrack("Song C", "Artist C", 150, Genre.JAZZ));
            pl.shortenTo(400);
            if (pl.getTracks().size() != 3) throw new RuntimeException("Originalplaylist wurde verändert!");
        });
        check("2f) shortenTo gibt leere Playlist zurück wenn erster Track nicht passt", () -> {
            BasicPlaylist pl = new BasicPlaylist();
            pl.addTrack(new AudioTrack("Song A", "Artist A", 200, Genre.POP));
            Playlist result = pl.shortenTo(100);
            if (!result.getTracks().isEmpty()) throw new RuntimeException("Sollte leer sein!");
        });
        check("2f) shortenTo gibt Kopie zurück wenn alles passt", () -> {
            BasicPlaylist pl = new BasicPlaylist();
            pl.addTrack(new AudioTrack("Song A", "Artist A", 200, Genre.POP));
            pl.addTrack(new AudioTrack("Song B", "Artist B", 300, Genre.ROCK));
            Playlist result = pl.shortenTo(1000);
            if (result.getTracks().size() != 2) throw new RuntimeException("Sollte alle 2 Tracks haben!");
        });

        // ===== Итог =====
        System.out.println("\n========== ИТОГ ==========");
        System.out.println("Пройдено: " + points + " / " + total);
        System.out.println("==========================");
    }

    static void check(String name, Runnable test) {
        total++;
        try {
            test.run();
            System.out.println("✓ " + name);
            points++;
        } catch (Exception e) {
            System.out.println("✗ " + name + " → " + e.getMessage());
        }
    }
}
