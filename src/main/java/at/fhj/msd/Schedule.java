package at.fhj.msd;

public class Schedule {

    private String cohort;
    private String group;
    private String lecture;
    private String starts;
    private String ends;
    private String lecturer;
    private String place;

    public Schedule(
            String cohort,
            String group,
            String lecture,
            String starts,
            String ends,
            String lecturer,
            String place) {
        this.cohort = cohort;
        this.group = group;
        this.lecture = lecture;
        this.starts = starts;
        this.ends = ends;
        this.lecturer = lecturer;
        this.place = place;
    }


    public String asSql() {
        return String.format("INSERT INTO schedules VALUES ('%s','%s','%s','%s','%s','%s','%s');", this.cohort, this.group,
        this.lecture, this.starts, this.ends, this.lecturer, this.place);
    }

    public String asCsv(String delimiter) {
        return String.join(delimiter, this.cohort, this.group, this.lecture, this.starts, this.ends, this.lecturer, this.place);
    }

}
