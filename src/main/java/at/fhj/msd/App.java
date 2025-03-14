package at.fhj.msd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        String filename = "src/main/resources/data.txt";
        ArrayList<String> ValidList = read(filename);
        System.out.println(ValidList);

        System.out.println("");

        List<Schedule> schedules = readData("data.txt");
        for (Schedule schedule: schedules) {
            System.out.println(schedule.asCsv(","));
        }

    }

    public static ArrayList<String> read(String filename) {

        ArrayList<String> ValidList = new ArrayList<>();
        File file = new File(filename);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {
                ValidList.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.printf("File '%s' was not found", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ValidList;

    }

    public static List<Schedule> readData(String file) {
        List<Schedule> schedules = new ArrayList<>();
        try {
        
            Path filePath = Paths.get(App.class.getClassLoader().getResource(file).toURI());
            List<String> lines = Files.readAllLines(filePath);
            for(String line: lines) {
                String[] parts = line.split(";");
                Schedule schedule = new Schedule(parts[6], parts[2], parts[1], parts[3], parts[4], parts[7], parts[5]);
                schedules.add(schedule);
            }     
        } catch (Exception e) {
            System.out.println(e);
        }
        return schedules;
    }

}
