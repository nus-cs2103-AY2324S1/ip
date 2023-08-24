import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> tasks = new ArrayList<>();

    private final String LINE_SEPARATOR = "____________________________________________________________";

    public TaskList() {
    }

    public void addTask(String task) {
        tasks.add(task);
        printWithSeparator("added: " + task);
    }

    public void list() {
        System.out.println(LINE_SEPARATOR);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(LINE_SEPARATOR);
    }

    private void printWithSeparator(String message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(message);
        System.out.println(LINE_SEPARATOR);
    }
}

