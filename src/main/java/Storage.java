public class Storage {
    private Task[] tasks;
    private int count;

    public Storage() {
        this.tasks = new Task[100];
        this.count = 0;
    }

    public void add(String description) {
        Todo task = new Todo(description);
        this.tasks[this.count] = task;
        this.count++;
        printAddTask(task);
    }

    public void add(String description, String by) {
        Deadline task = new Deadline(description, by);
        this.tasks[this.count] = task;
        this.count++;
        printAddTask(task);
    }

    public void add(String description, String from, String to) {
        Event task = new Event(description, from, to);
        this.tasks[this.count] = task;
        this.count++;
        printAddTask(task);
    }

    private void printAddTask(Task task) {
        System.out.println("\tGot it. I've added this task:\n\t" + task);
        this.printNumOfTasks();
    }

    public void list() {
        System.out.println("\tAs requested, here are the tasks in your list:");
        for (int i = 0; i < this.count; i++) {
            System.out.printf("\t%d.%s\n", i + 1, this.tasks[i]);
        }
    }

    public void markTaskDone(int index) {
        System.out.println("\tNice! I've marked this task as done:");
        this.tasks[index - 1].markTaskDone();
    }

    public void markTaskNotDone(int index) {
        System.out.println("\tSure, I've marked this task as not done yet:");
        this.tasks[index - 1].markTaskNotDone();
    }

    private void printNumOfTasks() {
        if (this.count < 2) {
            System.out.printf("\tNow you have %d task in the list.\n", this.count);
        } else {
            System.out.printf("\tNow you have %d tasks in the list.\n", this.count);
        }

    }
}
