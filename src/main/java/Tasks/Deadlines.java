    package Tasks;

    public class Deadlines extends Task {
        // additional deadline given for deadline tasks
        String deadline;
        public Deadlines(String description, String deadline) {
            super(description);
            super.taskType = 'D';
            this.deadline = deadline;
        }

        // getter for deadline
        public String getDeadline() {
            return this.deadline;
        }

    }
