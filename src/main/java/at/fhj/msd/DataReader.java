package at.fhj.msd;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    private String resourceFileName;

    public DataReader(String resourceFileName) {
        this.resourceFileName = resourceFileName;
    }

    public List<Schedule> read() {
        List<Schedule> schedules = new ArrayList<>();
        try {
            Path filePath = Paths.get(App.class.getClassLoader().getResource(this.resourceFileName).toURI());
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String[] parts = line.split(";");
                var schedule = new Schedule(parts[6], parts[2], parts[1], parts[3], parts[4], parts[7], parts[5]);
                schedules.add(schedule);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return schedules;
    }
}
