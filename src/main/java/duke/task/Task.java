package duke.task;
public abstract class Task {

        protected String description;
        protected boolean isDone;
        public Task(String description, boolean isDone) {
            this.description = description;
            this.isDone = isDone;
        }

        public String getStatus() {
            return (isDone ? "X" : " ");
        }

        public void markTask() {
            this.isDone = true;
            System.out.println("Heyyo! I've marked this task as done!\n" + this);
        }

        public void unmarkTask() {
            this.isDone = false;
            System.out.println("Aww snap! I've unmarked this task!\n" + this);
        }

        public String getDescription() {
            return this.description;
        }

        @Override
        public String toString() {
            return "[" + this.getStatus() + "] " + description;
        }

        public abstract String toSave();

}
