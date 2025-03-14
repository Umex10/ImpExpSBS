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
        this.write(lines, "sql");
    }

    public void writeCsv() {
        List<String> lines = new ArrayList<>();
        for (Schedule schedule: this.schedules) {
            lines.add(schedule.asCsv(","));
        }

       this.write(lines, "csv");
    }

    public void writeTsv() {
        List<String> lines = new ArrayList<>();
        for (Schedule schedule : this.schedules) {
          lines.add(schedule.asCsv("\t"));
        }
    
        this.write(lines, "tsv");
      }
    
      private void write(List<String> lines, String fileExtension) {
        try {
            Files.writeString(Paths.get(this.fileName + "." + fileExtension), String.join("\n", lines.toArray(new String[0])));
          } catch (Exception e) {
            System.out.println(e);
          }
      }
}
