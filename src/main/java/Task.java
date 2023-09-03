abstract public class Task {
    private int status; //0 for uncompleted, 1 (or any other number) for completed
    private String task;
    private static String line = "--------------------------------------------------------------------";

    public Task(int status, String task) {
        this.status = status;
        this.task = task;
    }

    abstract public String convertTask();

    public void mark() {
        if (this.status == 0) {
            this.status = 1;
            System.out.println("ଘ(੭ˊᵕˋ)੭ Yay! This task is completed:\n" + this.toString());
            System.out.println(line);
        } else {
            System.out.println("┐(´～｀)┌ This task is already marked as completed!");
            System.out.println(line);
        }
    }

    public void unmark() {
        if (this.status != 0) {
            this.status = 0;
            System.out.println("໒( ̿･ ᴥ ̿･ )ʋ All righty, I've marked this task as uncompleted:\n" + this.toString());
            System.out.println(line);
        } else {
            System.out.println("┐(´～｀)┌ This task is already marked as uncompleted!");
            System.out.println(line);
        }
    }

    public int getStatus() {
        return this.status;
    }

    public String getTask() {
        return this.task;
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