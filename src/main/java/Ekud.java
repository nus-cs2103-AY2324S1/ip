import java.util.ArrayList;
import java.util.Scanner;

public class Ekud {
    public static final String GREETING = "Hello! I'm Ekud!\nWhat can I do for you?";
    public static final String FAREWELL = "Bye. Hope to see you again soon!";

    public static final String PROMPT = "> ";

    public ArrayList<Task> tasks = new ArrayList<>();

    public boolean processLine(String line) {
        if (line.equals("list")) {
            if (tasks.isEmpty()) {
                System.out.println("No tasks yet. Add one!");
                return false;
            }
            for (int index = 0; index < tasks.size(); index++) {
                // Add padding to align single-digit numbers if we'll render
                // two-digit numbers later on.
                if (tasks.size() > 9 && index < 9) {
                    System.out.print(" ");
                }
                System.out.print(index + 1);
                Task task = tasks.get(index);
                System.out.println(". " + task.getTitle());
            }
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
