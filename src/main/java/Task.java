public class Task {
    private String task;
    private boolean done = false;

    public Task(String task) {
        this.task = task;
    }

    public boolean checkDone() {
        return this.done;
    }
    public void markDone() {
        if (this.done) {
            System.out.println("____________________________________________________________\n" +
                    " This task is already marked done!\n" +
                    "____________________________________________________________\n");
        } else {
            this.done = true;
        }
    }

    public void unmarkDone() {
        this.done = false;
    }

    public String toList() {
        return String.format(" | %s | %s", done ? "X" : " ", this.task);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "X" : " ", this.task);
    }

}
