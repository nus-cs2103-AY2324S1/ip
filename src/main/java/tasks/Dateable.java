package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Dateable {
    public static class DotDateTime extends Dateable {
        private LocalDateTime dotDateTime;
        DotDateTime(String input) {
            this.dotDateTime = LocalDateTime.parse(input);
        }

        @Override
        public String toString() {
            return this.dotDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }

    public static class TimeDescription extends Dateable {
        private String timeDescription;

        TimeDescription(String input) {
            this.timeDescription = input;
        }

        @Override
        public String toString() {
            return this.timeDescription;
        }
    }
    public static Dateable of(String input) {
        //(Only) regex inspired by ChatGPT
        //Prompt: Give me a regex crash course
        //We allow tutorial e.g. 2/12/2019 1800
        //We need to parse it into yyyy-MM-ddTHH:mm:ss
        String regex = "^([1-9]|0[1-9]|[1-2][0-9]|3[0-1])/([1-9]|0[1-9]|1[0-2])/[0-9]{4} " +
                "([0-1][0-9]|2[0-3])([0-5][0-9])$";
        if (input.matches(regex)) {
            // Parse into format accepted by LocalDateTime
            String[] splitBySpace = input.split(" ");
            String[] dayMonthYear = splitBySpace[0].split("/");
            String hour = splitBySpace[1].substring(0, 2);
            String minute = splitBySpace[1].substring(2);
            return new DotDateTime(String.format("%s-%02d-%02dT%s:%s:00", dayMonthYear[2],
                    Integer.valueOf(dayMonthYear[1]), Integer.valueOf(dayMonthYear[0]), hour, minute));
        } else {
            return new TimeDescription(input);
        }
    }
}
