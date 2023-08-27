import java.util.Scanner;

public class Duke {
    private static ToDoList list;
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

    public static boolean executeCommand(String input) throws DukeException {
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
                try {
                    Duke.list.markAsDone(Integer.parseInt(details));
                } catch (NumberFormatException e) {
                    throw new DukeException("Please enter a valid number...");
                }
                break;
            case "todo":
                if (details.equals("")) {
                    throw new DukeException("So what exactly do you want to do?");
                }
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
            case "delete":
                try {
                    Duke.list.delete(Integer.parseInt(details));
                } catch (NumberFormatException e) {
                    throw new DukeException("Please enter a valid number...");
                }
                break;
            default:
                throw new DukeException("I have no idea what that means...");
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        list = new ToDoList("./data/duke.txt");
        Duke.greet();
        while (true) {
            try {
                String input = scanner.nextLine();
                if (!Duke.executeCommand(input)) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println("–––––––––––––––––––––––––––––––––––––––––");
                System.out.println(e.toString());
                System.out.println("–––––––––––––––––––––––––––––––––––––––––");
            }
        }
        scanner.close();
    }
}
