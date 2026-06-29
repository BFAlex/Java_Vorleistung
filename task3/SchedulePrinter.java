import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SchedulePrinter {
    private final OutputStream out;

    public SchedulePrinter(OutputStream out) {
        this.out = new BufferedOutputStream(out);
    }

    public void printScreening(Screening screening) throws IOException {
        String line = screening.title() + "," + screening.hall() + "," + screening.durationMinutes() + "," + screening.freeSeats() + "\n";
        out.write(line.getBytes());
    }

    public void printSchedule(CinemaSchedule schedule) throws IOException {
        for (Screening screening : schedule.getScreenings()) {
            printScreening(screening);
        }
    }
}
