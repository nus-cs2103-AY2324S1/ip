package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
    LocalDate ld;
    LocalTime lt;

    public DateTime(String dt) throws DateTimeParseException {
        String[] splt = dt.split(" ");

        if (splt.length == 1) {
            this.ld = LocalDate.parse(splt[0], dateFormatter);
        } else if (splt.length == 2) {
            this.ld = LocalDate.parse(splt[0], dateFormatter);
            this.lt = LocalTime.parse(splt[1], timeFormatter);
        } else {
            throw new DateTimeParseException(
                    "Sorry I cannot recognise your date and time!"
                            + " Please follow the format: yyyy/M/d HHmm", dt, 0);
        }
    }

    public String getFormattedDate() {
        return this.ld.format(DateTimeFormatter.ofPattern("MM-d-yyyy"));
    }

    public String getFormattedTime() {
        if (this.lt != null) {
            return this.lt.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return null;
    }

    public String toString(){
        return getFormattedDate() +
                ((this.lt == null) ? "" : " " + getFormattedTime());
    }
}
