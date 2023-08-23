 public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description.strip();
            this.isDone = false;
        }

        public Task() {

        }

        public String doneMessage() {
            return "Nice! I've marked this task as done:";
        }

        public String undoneMessage() {
            return "OK, I've marked this task as not done yet:";
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String Box() {
            return "[" + getStatusIcon() +"] ";
        }

        public void markAsDone() {
            isDone = true;
        }
        public int getIndexOfMark(String str) {
            return Integer.parseInt(str.substring(5));
        }

        public int getIndexOfUnmark(String str) {
            return Integer.parseInt(str.substring(7));
        }

        public void markUndone() {
            isDone = false;
        }

     @Override
     public String toString() {
         return "[ ]" + this.Box() + this.description;
     }
 }
