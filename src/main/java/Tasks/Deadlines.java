    package Tasks;

    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;
    import java.time.format.DateTimeParseException;

    public class Deadlines extends Task {
        // additional deadline given for deadline tasks
        private LocalDateTime deadline;
        public Deadlines(String description, String deadline) {
            super(description, 'D');

//            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                LocalDateTime deadLineParsed = LocalDateTime.parse(deadline.trim(), formatter);
                this.deadline = deadLineParsed;
//            } catch (DateTimeParseException e) {
//                System.out.println("Your Date and time does not follow the format. This is not acceptable");
//            }
        }

        // getter for deadline
        public LocalDateTime getDeadline() {
            return this.deadline;
        }

        public String getDeadlineInWords() {
            String dayOfWeek = deadline.getDayOfWeek().toString();
            int dayOfMonth = deadline.getDayOfMonth();
            String month = deadline.getMonth().toString();
            int year = deadline.getYear();
            return dayOfWeek + " " + dayOfMonth + " " + month + " " + year;
        }

    }
