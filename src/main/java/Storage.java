import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> tasks;
    private int count;

    public Storage() {
        this.tasks = new ArrayList<>();
        this.count = 0;
    }

    public void add(String description) {
        Todo task = new Todo(description);
        this.tasks.add(task);
        this.count++;
        printAddTask(task);
    }

    public void add(String description, String by) {
        Deadline task = new Deadline(description, by);
        this.tasks.add(task);
        this.count++;
        printAddTask(task);
    }

    public void add(String description, String from, String to) {
        Event task = new Event(description, from, to);
        this.tasks.add(task);
        this.count++;
        printAddTask(task);
    }

    private void printAddTask(Task task) {
        System.out.println("\tGot it. I've added this task:\n\t  " + task);
        this.printNumOfTasks();
    }

    public void list() {
        if (this.count == 0) {
            System.out.println("\tThere are currently no tasks in your list:");
        } else {
            System.out.println("\tAs requested, here are the tasks in your list:");
            for (int i = 0; i < this.count; i++) {
                System.out.printf("\t%d.%s\n", i + 1, this.tasks.get(i));
            }
        }
    }

    public void markTaskDone(int index) throws DukeException {
        if (index > this.count || index <= 0) {
            throw new DukeException("\tHmm, this task does not exist :|");
        }
        System.out.println("\tNice! I've marked this task as done:");
        this.tasks.get(index - 1).markTaskDone();
    }

    public void markTaskNotDone(int index) {
        if (index > this.count || index <= 0) {
            throw new DukeException("\tHmm, this task does not exist :|");
        }
        System.out.println("\tSure, I've marked this task as not done yet:");
        this.tasks.get(index - 1).markTaskNotDone();
    }

    private void printNumOfTasks() {
        if (this.count < 2) {
            System.out.printf("\tNow you have %d task in the list.\n", this.count);
        } else {
            System.out.printf("\tNow you have %d tasks in the list.\n", this.count);
        }
    }

    public void deleteTask(int index) throws DukeException {
        if (index < 1 || index > this.count) {
            throw new DukeException("\tHmm, this task does not exist :|");
        }
        this.tasks.remove(index - 1);
        this.count--;
        System.out.println("\tNoted. I've removed this task.");
        printNumOfTasks();
    }
}
