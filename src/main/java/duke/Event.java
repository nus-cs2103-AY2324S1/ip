package duke;
public class Event extends Task {
        private String fromString;
        private String toString;

        public Event (String taskName, String from, String to) {
            super(taskName);
            this.fromString = from;
            this.toString = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + this.fromString + " to: " + this.toString + ")";
        }
        @Override
        public String saveString() {
            return "E | " + (this.getIsDone() ? "1" : "0") + " | " + this.getTaskName() + " | " + this.fromString + " | " + this.toString;
        }
}
