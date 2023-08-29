import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class Chatbot {
    private TaskList taskList;
    private final String name;

    public Chatbot(String name) {
        this.taskList = new TaskList();
        this.name = name;
    }

    public void start(Scanner scanner) {
        giveIntro();
        String userInput;

        do {
            userInput = scanner.nextLine();
            printHorizontalLine();
            processInput(userInput);
            printHorizontalLine();
        } while (!userInput.equals("bye"));
    }

    public void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void giveIntro() {
        printHorizontalLine();
        System.out.println("Hello " + this.name + "! I'm Sam, your personal AI chatbot, ready to serve you today");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public void giveOutro() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("Remember, the universe is vast, but I'm always here for you :D");
    }

    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    private LocalDateTime parseDate(String input) {
        List<DateTimeFormatter> dateFormatters = Arrays.asList(
                DateTimeFormatter.ofPattern("d/M/yyyy"),
                DateTimeFormatter.ofPattern("d-M-yyyy"),
                DateTimeFormatter.ofPattern("yyyy-M-d")
        );

        List<DateTimeFormatter> dateTimeFormatters = Arrays.asList(
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy-M-d HHmm")
        );

        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (Exception e) {
                // Try the next formatter if parsing fails
            }
        }

        for (DateTimeFormatter formatter : dateFormatters) {
            try {
                return LocalDateTime.of(LocalDate.parse(input, formatter), LocalTime.MIDNIGHT);
            } catch (Exception e) {
                // Try the next formatter if parsing fails
            }
        }

        throw new IllegalArgumentException("Invalid date format: " + input);
    }

    public void processInput(String input) {
        try {
            String[] parts = input.split(" ", 2);
            Command command = Command.valueOf(parts[0].toUpperCase());

            switch (command) {
                case BYE:
                    giveOutro();
                    break;
                case LIST:
                    taskList.listTasks();
                    break;
                case MARK:
                    int index = Integer.parseInt(parts[1]);
                    taskList.markTaskAsDone(index);
                    break;
                case UNMARK:
                    int unmarkIndex = Integer.parseInt(parts[1]);
                    taskList.markTaskAsNotDone(unmarkIndex);
                    break;
                case DELETE:
                    int deleteIndex = Integer.parseInt(parts[1]);
                    taskList.deleteTask(deleteIndex);
                    break;
                case TODO:
                    if (parts.length <= 1 || parts[1].isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    taskList.addTask(new ToDo(parts[1]));
                    break;
                case DEADLINE:
                    if (parts.length <= 1) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] deadlineParts = parts[1].split(" /by ");
                    if (deadlineParts.length != 2) {
                        throw new DukeException("☹ OOPS!!! Please provide a valid deadline description and date.");
                    }
                    LocalDateTime by = parseDate(deadlineParts[1]);
                    taskList.addTask(new Deadline(deadlineParts[0], by));
                    break;
                case EVENT:
                    if (parts.length <= 1) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String[] eventParts = parts[1].split(" /from | /to ");
                    if (eventParts.length != 3) {
                        throw new DukeException("☹ OOPS!!! Please provide a valid event description, start date, and end date.");
                    }
                    LocalDateTime from = parseDate(eventParts[1]);
                    LocalDateTime to = parseDate(eventParts[2]);
                    taskList.addTask(new Event(eventParts[0], from, to));
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
