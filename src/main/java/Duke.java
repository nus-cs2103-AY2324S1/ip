import java.time.LocalDate;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
                try {
                    LocalDateTime dateTime = formatDateTime(subParts[1]);
                    Duke.list.addTask(new Deadline(subParts[0], dateTime));
                } catch (DateTimeParseException e) {
                    throw new DukeException("Check the date time format again!");
                }
                break;
            case "event":
                String[] taskPart = details.split(" /from ", 2);
                String[] timePart = taskPart[1].split(" /to ", 2);
                try {
                    LocalDateTime from = formatDateTime(timePart[0]);
                    LocalDateTime to = formatDateTime(timePart[1]);
                    Duke.list.addTask(new Event(taskPart[0], from, to));
                } catch (DateTimeParseException e) {
                    throw new DukeException("Oh no! Check the date time format again!");
                }
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

    private static LocalDateTime formatDateTime(String input) throws DateTimeParseException {
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
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
