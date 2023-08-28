public class Deadline extends Task {

        String deadline;

        public Deadline(String description, String deadline, String marked) {
            super(description, marked);
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + deadline + ")";
        }
}
