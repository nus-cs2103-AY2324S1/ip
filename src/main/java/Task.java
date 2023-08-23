public class Task {
    private String task;
    private boolean done = false;

    public Task(String task) {
        this.task = task;
    }

    public void markDone() {
        if (done) {
            System.out.println("____________________________________________________________\n" +
                    " This task is already marked done!\n" +
                    "____________________________________________________________\n");
        } else {
            this.done = true;
            System.out.println("____________________________________________________________\n" +
                    " Nice! I've marked this task as done:\n " +
                    this.toString() +
                    "\n____________________________________________________________\n");
        }
    }

    public void unmarkedDone() {
        if (!done) {
            System.out.println("____________________________________________________________\n" +
                    " This task is not done yet!\n" +
                    "____________________________________________________________\n");
        } else {
            this.done = false;
            System.out.println("____________________________________________________________\n" +
                    " OK, I've marked this task not done yet:\n " +
                    this.toString() +
                    "\n____________________________________________________________\n");
        }
        this.done = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "X" : " ", this.task);
    }

}
