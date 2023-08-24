public class TaskList {
    private Task[] tasks;
    private int numTasks;

    public TaskList() {
        this.tasks = new Task[100];
        this.numTasks = 0;
    }

    public void addTask(Task task) {
        this.tasks[numTasks++] = task;
        System.out.println("Added: " + task.getDescription() + " ~Bzzz~");
    }

    public void listAllTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numTasks; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
        }
    }

    public void setTaskDone(int taskNumber) {
        this.tasks[taskNumber - 1].setDone();
        System.out.println("Nice! I've marked task " + taskNumber +  " as done: ~Bzzz~");
        System.out.println(this.tasks[taskNumber - 1].toString());
    }

    public void setTaskNotDone(int taskNumber) {
        this.tasks[taskNumber - 1].setNotDone();
        System.out.println("OK, I've marked task " + taskNumber +  " as not done yet: ~Bzzzz");
        System.out.println(this.tasks[taskNumber - 1].toString());
    }
}
