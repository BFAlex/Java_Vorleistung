import java.util.ArrayList;
import java.util.Collection;

public class ParcelConfigReader {
    private final Collection<Parcel> parcels;

    public ParcelConfigReader() {
        parcels = new ArrayList<>();
    }

    public void readConfig(String input) {
        String[] lines = input.split("\n");
    
        for (String line : lines) {
            if (line.isEmpty()) continue;  // пропускаем пустые
            
            String[] parts = line.split("\\|");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid line: " + line);
            }
            
            String address = parts[0];
            double weight = Double.parseDouble(parts[1]);
            int priority = Integer.parseInt(parts[2]);
            
            parcels.add(new StandardParcel(address, weight, priority, false));
        }
    }

    public Collection<Parcel> getConfiguration() {
        return parcels;
    }
}
