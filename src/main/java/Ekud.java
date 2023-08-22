import java.util.ArrayList;
import java.util.Scanner;

public class Ekud {
    public static final String GREETING = "Hello! I'm Ekud!\nWhat can I do for you?";
    public static final String FAREWELL = "Bye. Hope to see you again soon!";
    public static final String LIST_SUCCESS = "Here are the tasks in your list:";
    public static final String LIST_EMPTY = "No tasks yet. Add one!";
    public static final String MARK_SUCCESS = "Nice! I've marked this task as done:";
    public static final String MARK_FAILURE = "Invalid task number provided.";
    public static final String UNMARK_SUCCESS = "OK, I've marked this task as not done yet:";
    public static final String UNMARK_FAILURE = "Invalid task number provided.";

    public static final String PROMPT = "> ";

    public ArrayList<Task> tasks = new ArrayList<>();

    public boolean processLine(String line) {
        if (line.equals("list")) {
            if (tasks.isEmpty()) {
                System.out.println(LIST_EMPTY);
                return false;
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
        } else if (line.startsWith("mark ")) {
            String indexString = line.substring("mark ".length());
            int index;
            try {
                index = Integer.parseInt(indexString) - 1;
            } catch (NumberFormatException error) {
                System.out.println(MARK_FAILURE);
                return false;
            }
            if (index < 0 || index >= tasks.size()) {
                System.out.println(MARK_FAILURE);
                return false;
            }
            Task task = tasks.get(index);
            task.mark();
            System.out.println(MARK_SUCCESS);
            System.out.println("  " + task);
        } else if (line.startsWith("unmark ")) {
            String indexString = line.substring("unmark ".length());
            int index;
            try {
                index = Integer.parseInt(indexString) - 1;
            } catch (NumberFormatException error) {
                System.out.println(UNMARK_FAILURE);
                return false;
            }
            if (index < 0 || index >= tasks.size()) {
                System.out.println(UNMARK_FAILURE);
                return false;
            }
            Task task = tasks.get(index);
            task.unmark();
            System.out.println(UNMARK_SUCCESS);
            System.out.println("  " + task);
        } else if (line.equals("bye")) {
            return true;
        } else if (!line.isEmpty()) {
            Task task = new Task(line);
            tasks.add(task);
            System.out.println("added: " + task.getTitle());
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
            if (program.processLine(line)) {
                break;
            }
        }
        System.out.println(FAREWELL);
        scanner.close();
    }
}
