
import java.time.DateTimeException;


public class Event extends Task {


    public Event(String input) {
        super(input.split("/")[0]);
        try {
            setStartEnd(input);
        } catch (NullPointerException e) {
            throw new KieraException("     invalid input! write when your event starts and ends in the form: /from yyyy-mm-dd 0000 /to 2359)");
        } catch (DateTimeException e) {
            throw new KieraException("     fill in the date in the format: /from yyyy-mm-dd 0000 /to 2359!");
        }
    }


    @Override
    public String getDateString() {
        int startDay = this.getStartDate().getDayOfMonth();
        String startMonth =  this.getStartDate().getMonth().toString().substring(0, 3);
        int startYear = this.getStartDate().getYear();

        return startDay + " " + startMonth + " " + startYear + " " + this.getStartTime() + " - " + this.getEndTime();
    }

    @Override
    public String toStorageString() {
        int start = this.getStartTime().getHour() * 100 + this.getStartTime().getMinute();
        int end = this.getEndTime().getHour() * 100 + this.getEndTime().getMinute();
        return "E // " + this.getStatusIcon()
                + " // "
                + this.getDescription()
                + " /from "
                + this.getStartDate()
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
         + this.getDateString()
         + ")";
    }
}
