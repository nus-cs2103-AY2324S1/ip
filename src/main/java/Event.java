import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DateTimeException;
import java.time.LocalDate;

public class Event extends Task {

    private LocalDate startDate;
    private LocalTime startTime;
    private LocalTime end;
    public Event(String input) {
        super(input.split("/")[0]);
        try {
            setDate(input);
        } catch (NullPointerException e) {
            throw new KieraException("invalid input! write when your event starts and ends in the form: /from yyyy-mm-dd 0000 /to 2359)");
        } catch (DateTimeException e) {
            throw new KieraException("fill in the date in the format: /from yyyy-mm-dd 0000 /to 2359!");
        }
    }
    @Override
    public void setDate(String input) {
        String[] inputs = input.split("/");
        String start = inputs[1].replace("from ", "");

        String[] times = start.split("-");
        int y = Integer.parseInt(times[0]);
        int m = Integer.parseInt(times[1]);
        int d = Integer.parseInt(times[2].split(" ")[0]);

        int time = Integer.parseInt(times[2].split(" ")[1]);
        int startHour = (int) Math.floor(time / 100);
        int startMin = time % 100;

        int end = Integer.parseInt(inputs[2].replace("to ", ""));
        int endHour = (int) Math.floor(end / 100);
        int endMin = end % 100;

        this.startDate = LocalDate.of(y, m, d);
        this.startTime = LocalTime.of(startHour, startMin);
        this.end = LocalTime.of(endHour, endMin);
    }
    @Override
    public LocalDate getDate() {
        return this.startDate;
    }
    @Override
    public String printDate() {
        int startDay = this.startDate.getDayOfMonth();
        String startMonth =  this.startDate.getMonth().toString().substring(0, 3);
        int startYear = this.startDate.getYear();

        return startDay + " " + startMonth + " " + startYear + " " + this.startTime + " - " + this.end;
    }

    @Override
    public String toStorageString() {
        int start = this.startTime.getHour() * 100 + this.startTime.getMinute();
        int end = this.end.getHour() * 100 + this.end.getMinute();
        return "E // " + this.getStatusIcon()
                + " // "
                + this.getDescription()
                + " /from "
                + this.startDate
                + " "
                + start
                + " /to "
                + end;
    }
    @Override
    public String toString() {
         return "[E]" 
         + "[" 
         + this.getStatusIcon()
         + "] " 
         + this.getDescription()
         + " (from " 
         + this.printDate()
         + ")";
    }
}
