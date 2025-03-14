package at.fhj.msd;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataWriter {

    private String fileName;
    private List<Schedule> schedules;
  
    public DataWriter(String fileName, List<Schedule> schedules) {
        if (schedules == null || schedules.size() == 0) {
          throw new IllegalArgumentException("please provide at least one schedule!");
        }
        this.fileName = fileName;
        this.schedules = schedules;
    }

    public void writeSql() {
        List<String> lines = new ArrayList<>();
        for(Schedule schedule: this.schedules) {
            lines.add(schedule.asSql());
        }
        try {
            Files.writeString(Paths.get(this.fileName + ".sql"), String.join("\n", lines.toArray(new String[0])));
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void writeCsv() {
        List<String> lines = new ArrayList<>();
        for (Schedule schedule: this.schedules) {
            lines.add(schedule.asCsv(","));
        }

        try {
            Files.writeString(Paths.get(this.fileName + ".csv"), String.join("\n", lines.toArray(new String[0])));
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void writeTsv() {
        List<String> lines = new ArrayList<>();
        for (Schedule schedule : this.schedules) {
          lines.add(schedule.asCsv("\t"));
        }
    
        try {
          Files.writeString(Paths.get(this.fileName + ".tsv"), String.join("\n", lines.toArray(new String[0])));
        } catch (Exception e) {
          System.out.println(e);
        }
      }
    
}
