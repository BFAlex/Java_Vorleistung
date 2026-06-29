
// 1a) TODO: von BaseSession erben und intensity-Feld hinzufügen
public class GymSession extends BaseSession {
    public Intensity intensity;

    public GymSession(String name, String trainer, int capacity, int booked, Intensity intensity) {
        super(name, trainer, capacity, booked);
        this.intensity = intensity;
    }

    public Intensity intensity() {
        return intensity;
    }
}
