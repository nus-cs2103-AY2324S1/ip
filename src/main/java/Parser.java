import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Parser {
    private final TaskList list;
    private final Ui ui;
    private boolean status;

    public Parser(TaskList list, Ui ui) {
        this.list = list;
        this.ui = ui;
        this.status = true;
    }

    public void parse(String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        String action = parts[0];
        String details = parts.length == 1 ? "" : parts[1];
        switch (action) {
            case "bye":
                this.ui.exit();
                break;
            case "list":
                this.ui.listTasks(this.list);
                break;
            case "mark":
                try {
                    this.list.markAsDone(Integer.parseInt(details));
                } catch (NumberFormatException e) {
                    throw new DukeException("Please enter a valid number...");
                }
                break;
            case "todo":
                if (details.equals("")) {
                    throw new DukeException("So what exactly do you want to do?");
                }
                this.list.addTask(new ToDo(details));
                break;
            case "deadline":
                String[] subParts = details.split(" /by ", 2);
                try {
                    LocalDateTime dateTime = formatDateTime(subParts[1]);
                    this.list.addTask(new Deadline(subParts[0], dateTime));
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
                    this.list.addTask(new Event(taskPart[0], from, to));
                } catch (DateTimeParseException e) {
                    throw new DukeException("Oh no! Check the date time format again!");
                }
                break;
            case "delete":
                try {
                    this.list.delete(Integer.parseInt(details));
                } catch (NumberFormatException e) {
                    throw new DukeException("Please enter a valid number...");
                }
                break;
            default:
                throw new DukeException("I have no idea what that means...");
        }
    }

    private static LocalDateTime formatDateTime(String input) throws DateTimeParseException {
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    public boolean status() {
        return this.status;
    }
 }
