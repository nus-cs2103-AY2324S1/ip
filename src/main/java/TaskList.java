public class TaskList {
    private Task[] tasks;
    private int numTasks;

    public TaskList() {
        this.tasks = new Task[100];
        this.numTasks = 0;
    }

    public void addTask(Task task) {
        this.tasks[numTasks++] = task;
        System.out.println("Got it. I've added this task: ~Bzzz~");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + numTasks + " tasks in the list. ~Bzzz~");
    }

    public void listAllTasks() {
        System.out.println("Here are the tasks in your list: ~Bzzz~");
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
