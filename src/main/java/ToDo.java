public class ToDo extends Task {
        public ToDo (String taskName) {
            super(taskName);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }

        @Override
        public String saveString() {
            return "T | " + (this.getIsDone() ? "1" : "0") + " | " + this.getTaskName();
        }
}