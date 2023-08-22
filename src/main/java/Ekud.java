import java.util.ArrayList;
import java.util.Scanner;

public class Ekud {
    public static final String GREETING = "Hello! I'm Ekud!\nWhat can I do for you?";
    public static final String FAREWELL = "Bye. Hope to see you again soon!";
    public static final String LIST_SUCCESS = "Here are the tasks in your list:";
    public static final String LIST_EMPTY = "No tasks yet. Add one!";
    public static final String ADD_SUCCESS = "Got it. I've added this task:";
    public static final String ADD_TASK_COUNT = "Now you have %d tasks in the list.";
    public static final String MARK_SUCCESS = "Nice! I've marked this task as done:";
    public static final String MARK_FAILURE_NO_TASK = "Please provide task number to mark.";
    public static final String MARK_FAILURE_INVALID_TASK = "Invalid task number provided.";
    public static final String UNMARK_SUCCESS = "OK, I've marked this task as not done yet:";
    public static final String UNMARK_FAILURE_NO_TASK = "Please provider task number to unmark.";
    public static final String UNMARK_FAILURE_INVALID_TASK = "Invalid task number provided.";
    public static final String PROMPT = "> ";

    public ArrayList<Task> tasks = new ArrayList<>();

    public void processList() {
        if (tasks.isEmpty()) {
            System.out.println(LIST_EMPTY);
            return;
        }

        System.out.println(LIST_SUCCESS);

        for (int index = 0; index < tasks.size(); index++) {
            // Add padding to align single-digit numbers if we'll render
            // two-digit numbers later on.
            if (tasks.size() > 9 && index < 9) {
                System.out.print(" ");
            }
            System.out.print(index + 1);

            Task task = tasks.get(index);
            System.out.println(". " + task.toString());
        }
    }

    public void processMark(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println(MARK_FAILURE_INVALID_TASK);
            return;
        }

        Task task = tasks.get(index);
        task.mark();

        System.out.println(MARK_SUCCESS);
        System.out.println("  " + task);
    }

    public void processUnmark(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println(UNMARK_FAILURE_INVALID_TASK);
            return;
        }

        Task task = tasks.get(index);
        task.unmark();

        System.out.println(UNMARK_SUCCESS);
        System.out.println("  " + task);
    }

    public void processAddEvent(String title) {
        Task task = new EventTask(title);
        tasks.add(task);

        System.out.println(ADD_SUCCESS);
        System.out.println("  " + task);
        System.out.println(String.format(ADD_TASK_COUNT, tasks.size()));
    }

    public boolean processLine(String line) {
        String[] components = line.split("\\s+");
        String command = components[0]; // guaranteed due to isEmpty check in main method
        if (command.equals("list")) {
            processList();
        } else if (command.equals("event")) {
            String title = line.substring("event".length()).trim();
            processAddEvent(title);
        } else if (command.equals("mark")) {
            if (components.length < 2) {
                System.out.println(MARK_FAILURE_NO_TASK);
                return false;
            }
            int index;
            try {
                index = Integer.parseInt(components[1]) - 1;
            } catch (NumberFormatException error) {
                System.out.println(MARK_FAILURE_INVALID_TASK);
                return false;
            }
            processMark(index);
        } else if (command.equals("unmark")) {
            if (components.length < 2) {
                System.out.println(UNMARK_FAILURE_NO_TASK);
                return false;
            }
            int index;
            try {
                index = Integer.parseInt(components[1]) - 1;
            } catch (NumberFormatException error) {
                System.out.println(UNMARK_FAILURE_INVALID_TASK);
                return false;
            }
            processUnmark(index);
        } else if (command.equals("bye")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Ekud program = new Ekud();
        Scanner scanner = new Scanner(System.in);
        System.out.println(GREETING);
        while (true) {
            System.out.print(PROMPT);
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            if (program.processLine(line)) {
                break;
            }
        }
        System.out.println(FAREWELL);
        scanner.close();
    }
}
