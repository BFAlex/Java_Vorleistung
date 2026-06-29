public class MovieScreening implements Screening {
    private final String title;
    private final int hall;
    private final int durationMinutes;
    private final int freeSeats;

    public MovieScreening(String title, int hall, int durationMinutes, int freeSeats) {
        if (durationMinutes <= 0 || hall <= 0  || freeSeats < 0){
            throw new IllegalArgumentException("...");
        }
        
        this.title = title;
        this.hall = hall;
        this.durationMinutes = durationMinutes;
        this.freeSeats = freeSeats;
    }

    public String title() {
        return title;
    }

    public int hall() {
        return hall;
    }

    public int durationMinutes() {
        return durationMinutes;
    }

    public int freeSeats() {
        return freeSeats;
    }
}
