package at.fhj.msd;

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
}
