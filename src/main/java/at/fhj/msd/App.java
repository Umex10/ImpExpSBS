package at.fhj.msd;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        //? Read file with static method
        //List<Schedule> schedules = readData("data.txt");
        //? Read file with class DataReader
        DataReader reader = new DataReader("data.txt");
        List<Schedule> schedules = reader.read();
        writeData(schedules, "sql");

    }

    public static List<Schedule> readData(String file) {
        List<Schedule> schedules = new ArrayList<>();
        try {

            Path filePath = Paths.get(App.class.getClassLoader().getResource(file).toURI());
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String[] parts = line.split(";");
                Schedule schedule = new Schedule(parts[6], parts[2], parts[1], parts[3], parts[4], parts[7], parts[5]);
                schedules.add(schedule);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return schedules;
    }

    public static void writeData(List<Schedule> data, String type) {
        List<String> lines = new ArrayList<>();

        String delimiter = null;
        switch (type) {
            case "csv":
                delimiter = ",";
                break;
            case "tsv":
                delimiter = "\t";
        }

        if (delimiter != null) {
            for (Schedule schedule : data) {
                lines.add(schedule.asCsv(delimiter));
            }
        } else {
            for (Schedule schedule : data) {
                lines.add(schedule.asSql());
            }
        }

        try {
            Files.writeString(Paths.get("data." + type), String.join("\n", lines.toArray(new String[0]))); //Give empty Array as parameter! The Method should specify the length by itself
            //The new file will result in root folder. 
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
