public class Task {

        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
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

        @Override
        public String toString() {
            return "[" + this.getStatus() + "] " + description;
        }
}
