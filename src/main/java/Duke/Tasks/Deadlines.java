    package Duke.Tasks;

    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;

    public class Deadlines extends Task {
        // additional deadline given for deadline tasks
        private LocalDateTime deadline;
        public Deadlines(String description, String deadline) {
            super(description, 'D');

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime deadLineParsed = LocalDateTime.parse(deadline.trim(), formatter);
            this.deadline = deadLineParsed;
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


        @Override
        public String toString() {
            return "[D]" + super.toString() + String.format("(by:%s)", getDeadlineInWords());
        }
    }
