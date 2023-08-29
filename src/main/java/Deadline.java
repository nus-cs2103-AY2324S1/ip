import java.time.LocalDate;

public class Deadline extends Task {
        private LocalDate deadline;

        public Deadline (String taskName, String deadline) {
            super(taskName);
            // assume deadline is of the format 2019-12-01
            this.deadline = LocalDate.parse(deadline);
        }

        private String dateToString() {
            // desired format is MMM dd yyyy
            return this.deadline.format(java.time.format.DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }

        private String parseDate() {
            // convert deadline back to yyyy-mm-dd
            return this.deadline.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + dateToString() + ")";
        }

        @Override
        public String saveString() {
            return "D | " + (this.getIsDone() ? "1" : "0") + " | " + this.getTaskName() + " | " + parseDate();
        }
}
