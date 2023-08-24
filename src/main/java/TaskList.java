import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    private final String LINE_SEPARATOR = "____________________________________________________________";

    public TaskList() {
    }

    public void addTask(String task) {
        Task newTask = new Task(task);
        tasks.add(newTask);
        printWithSeparator("added: " + task);
    }

    public void markTaskAsDone(int index) {
        if (index < 1 || index > tasks.size()) {
            printWithSeparator("Please enter a valid number.");
        } else {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            printWithSeparator("Nice! I've marked this task as done:\n" + "[" + task.getStatusIcon() + "] " + task);
        }
    }

    public void unmarkTask(int index) {
        if (index < 1 || index > tasks.size()) {
            printWithSeparator("Please enter a valid number.");
        } else {
            Task task = tasks.get(index - 1);
            task.unmark();
            printWithSeparator("OK, I've marked this task as not done yet:\n" + "[" + task.getStatusIcon() + "] " + task);
        }
    }

    public void list() {
        System.out.println(LINE_SEPARATOR);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + "." + "[" + task.getStatusIcon() + "] " + task);
        }
        System.out.println(LINE_SEPARATOR);
    }

    private void printWithSeparator(String message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(message);
        System.out.println(LINE_SEPARATOR);
    }
}

