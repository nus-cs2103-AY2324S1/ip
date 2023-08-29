import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
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
            //System.out.println(ddln);
            String[] dateFormats = {
                    "d/M/yyyy",          // 12/2/2021
                    "d/M/yyyy H:mm",          // 12/2/2021
                    "d/M/yyyy H",          // 12/2/2021
                    "d/M/yyyy HHmm",          // 12/2/2021
                    "M/d/yyyy ",          // 2/12/2021
                    "M/d/yyyy H:mm",          // 2/12/2021
                    "M/d/yyyy H",          // 2/12/2021
                    "M/d/yyyy HHmm",          // 2/12/2021
                    "d MMM yyyy",        // 12 Feb 2021
                    "d MMM yyyy HHmm",        // 12 Feb 2021
                    "d MMM yyyy H",        // 12 Feb 2021
                    "d MMM yyyy H:mm",        // 12 Feb 2021
                    "EEEE HHmm",              // Sunday
                    "EEEE H",              // Sunday
                    "EEEE H:mm",              // Sunday
                    "EEEE",              // Sunday
            };

            String res = "Couldn't parse";
            LocalDate parsedDate = null;
            LocalTime parsedTime = null;
            boolean isDay = false;
//            int f = 0;

            try {
                DayOfWeek.valueOf(ddln.toUpperCase());
                isDay = true;
            } catch (IllegalArgumentException e) {
                //System.out.println("Any specific day in mind?");
            }
            if (isDay) {
                res = ddln; //just return this result eg 'Sunday'

            } else {
                for (String format : dateFormats) {
                    try {
//                        f++;
                        //System.out.println("in try loop");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
//                        System.out.println("matching format now: format " + f);
                        LocalDateTime date = LocalDateTime.parse(ddln, formatter);
//                        System.out.println("formatting to parse now");
                        parsedDate = date.toLocalDate();
//                        System.out.println("parsed date");
                        if (format.contains("H")) {
                            parsedTime = date.toLocalTime();
//                            System.out.println("parsed time");
                        }
                        if (parsedDate != null) {
//                            System.out.println("parsed date success");
                            if (parsedTime != null) {
//                                System.out.println("parsed time success");
                                res = LocalDateTime.of(parsedDate, parsedTime)
                                        .format(DateTimeFormatter.ofPattern("MMM d yyyy H:mm"));
                            } else {
                                res = parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                            }
                        }
                        break;
                    } catch (DateTimeParseException e) {
                        //System.out.println("error in parsing");
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
