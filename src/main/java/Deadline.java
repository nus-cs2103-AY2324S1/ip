import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Deadline extends Task{
    private final String descr;

        public Deadline(String descr) {
            super(descr.split("/by")[0]);
            this.descr = descr;
        }

        public String parser(String descr) {

            String ddln = descr.split("/by")[1].trim();
            String[] dateFormats = {
                    "d/M/yyyy",          // 12/2/2021
                    "M/d/yyyy",          // 2/12/2021
                    "d MMM yyyy",        // 12 Feb 2021
                    "EEEE",              // Sunday
            };

            String res = null;
            boolean isDay = false;
            try {
                DayOfWeek.valueOf(ddln.toUpperCase());
                isDay = true;
            } catch (IllegalArgumentException e) { }
                //catches if number cos cannot be changed to upper case
            if (isDay) {
                res = ddln; //just return this result eg 'Sunday'
            } else {
                for (String format : dateFormats) {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                        LocalDate date = LocalDate.parse(ddln, formatter);
                        res = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                        break;
                    } catch (DateTimeParseException e) {
                    }
                }
            }
            return res;
        }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + parser(this.descr) + ")";
    }
}

//deadline return book /by Sunday
//deadline return book /by 28/8/22
//[D][ ] return book (by: Sunday)