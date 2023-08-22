 public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
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
            System.out.println("____________________________________________________________");
            System.out.println(doneMessage());
            System.out.println(Box() + description);
            System.out.println("____________________________________________________________");
        }
        public int getIndexOfMark(String str) {
            return Integer.parseInt(str.substring(5));
        }

        public int getIndexOfUnmark(String str) {
            return Integer.parseInt(str.substring(7));
        }

        public void markUndone() {
            isDone = false;
            System.out.println("____________________________________________________________");
            System.out.println(undoneMessage());
            System.out.println(Box() + description);
            System.out.println("____________________________________________________________");
        }

     @Override
     public String toString() {
         return this.description;
     }
 }
