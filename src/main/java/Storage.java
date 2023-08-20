public class Storage {
    private Task[] tasks;
    private int count;

    public Storage() {
        this.tasks = new Task[100];
        this.count = 0;
    }

    public void add(String description) {
        this.tasks[this.count] = new Task(description);
        this.count++;
        System.out.println("\tadded: " + description);
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
}
