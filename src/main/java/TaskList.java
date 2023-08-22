public class TaskList {
    protected int len;
    protected Task[] taskArr;

    public TaskList() {
        this.len = 0;
        this.taskArr = new Task[100];
    }

    public Task getTask(int index) {
        return this.taskArr[index];
    }

    public int length() {
        return this.len;
    }

    public void markTaskAsDone(int index) {
        this.taskArr[index].markAsDone();
    }

    public void markTaskAsNotDone(int index) {
        this.taskArr[index].markAsNotDone();
    }

    public String taskToString(int index) {
        return this.taskArr[index].toString();
    }

    public void addTask(String description) {
        this.taskArr[len] = new Task(description);
        len += 1;
    }

}
