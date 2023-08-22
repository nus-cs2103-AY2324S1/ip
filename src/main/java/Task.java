abstract public class Task {
    private int status; //0 for uncompleted, 1 (or any other number) for completed
    private String task;

    public Task(int status, String task) {
        this.status = status;
        this.task = task;
    }

    public void mark() {
        if (this.status == 0) {
            this.status = 1;
            System.out.println("Yay! This task is completed: \n" + this.toString() + "\n");
        } else {
            System.out.println("This task is already marked as completed!");
        }
    }

    public void unmark() {
        if (this.status != 0) {
            this.status = 0;
            System.out.println("All righty, I've marked this task as uncompleted: \n" + this.toString() + "\n");
        } else {
            System.out.println("This task is already marked as uncompleted!");
        }
    }
    @Override
    public String toString() {
        if (status == 0) {
            return "[ ] " + task;
        } else {
            return "[X] " + task;
        }
    }
}