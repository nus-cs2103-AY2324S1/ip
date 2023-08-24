import java.util.Scanner;

public class Duke {
    private static final ToDoList list = new ToDoList();
    private static void greet() {
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
        System.out.println("Hello! I'm Bot");
        System.out.println("What can I do for you?");
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
    }

    private static void exit() {
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("–––––––––––––––––––––––––––––––––––––––––");
    }

    public static boolean executeCommand(String input) {
        String[] parts = input.split(" ", 2);
        String action = parts[0];
        String details = parts.length == 1 ? "" : parts[1];

        switch (action) {
            case "bye":
                Duke.exit();
                return false;
            case "list":
                Duke.list.listTasks();
                break;
            case "mark":
                Duke.list.markAsDone(Integer.parseInt(details));
                break;
            case "todo":
                Duke.list.addTask(new ToDo(details));
                break;
            case "deadline":
                String[] subParts = details.split(" /by ", 2);
                Duke.list.addTask(new Deadline(subParts[0], subParts[1]));
                break;
            case "event":
                String[] taskPart = details.split(" /from ", 2);
                String[] timePart = taskPart[1].split(" /to ", 2);
                Duke.list.addTask(new Event(taskPart[0], timePart[0], timePart[1]));
                break;
            default:
                System.out.println("–––––––––––––––––––––––––––––––––––––––––");
                System.out.println("Unknown command...");
                System.out.println("–––––––––––––––––––––––––––––––––––––––––");
        }
        return true;
    }
    public static void main(String[] args) {
        Duke.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (!Duke.executeCommand(input)) {
                break;
            }
        }
    }
}
