import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {
    private static final List<Task> tasks = new ArrayList<>();
    private static final Ui ui = new Ui();
    private static final Storage storage = new Storage(ui);


    private enum Command {
        BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, TASKS_ON_DATE, UNKNOWN
    }

    public static void echoMessages() {
        Scanner scanner = new Scanner(System.in);
        String input;

        tasks.addAll(storage.loadTasks());
        ui.printGreeting();

        while (true) {
            input = scanner.nextLine();
            CommandType command = Parser.parseCommand(input);

            ui.printHorizontalLine();

            try {
                switch (command) {
                    case BYE:
                        ui.printExit();
                        scanner.close();
                        return;

                    case LIST:
                        ui.printList(tasks);
                        break;

                    case TODO:
                        ToDo todo = new ToDo(input.substring(5));
                        tasks.add(todo);
                        ui.printTaskAdded(todo, tasks.size());
                        storage.saveTasks(tasks);
                        break;

                    case DEADLINE:
                        String[] parts = input.substring(9).split(" /by ");
                        if (parts.length < 2) {
                            throw new DukeException("Deadline format is incorrect.");
                        }
                        Deadline deadline = new Deadline(parts[0], parts[1]);
                        tasks.add(deadline);
                        ui.printTaskAdded(deadline, tasks.size());
                        storage.saveTasks(tasks);
                        break;

                    case EVENT:
                        String[] eventParts = input.substring(6).split(" /from ");
                        String[] timeParts = eventParts[1].split(" /to ");
                        if (timeParts.length < 2) {
                            throw new DukeException("Event format is incorrect.");
                        }
                        Event event = new Event(eventParts[0], timeParts[0], timeParts[1]);
                        tasks.add(event);
                        ui.printTaskAdded(event, tasks.size());
                        storage.saveTasks(tasks);
                        break;

                    case MARK:
                        int taskNumberMark = Integer.parseInt(input.split(" ")[1]);
                        tasks.get(taskNumberMark - 1).markAsDone();
                        ui.printMarkedAsDone(tasks.get(taskNumberMark - 1));
                        storage.saveTasks(tasks);
                        break;

                    case UNMARK:
                        int taskNumberUnmark = Integer.parseInt(input.split(" ")[1]);
                        tasks.get(taskNumberUnmark - 1).unmark();
                        ui.printMarkedAsNotDone(tasks.get(taskNumberUnmark - 1));
                        storage.saveTasks(tasks);
                        break;

                    case DELETE:
                        int taskNumberDelete = Integer.parseInt(input.split(" ")[1]);
                        Task removedTask = tasks.remove(taskNumberDelete - 1);
                        ui.printTaskDeleted(removedTask, tasks.size());
                        storage.saveTasks(tasks);
                        break;

                    case TASKS_ON_DATE:
                        LocalDate givenDate = Parser.getLocalDate(input);
                        List<Task> tasksOnGivenDate = tasks.stream()
                                .filter(task ->
                                        (task instanceof Deadline && ((Deadline) task).getBy().toLocalDate().isEqual(givenDate)) ||
                                                (task instanceof Event && Parser.isWithinEventDate((Event) task, givenDate)))
                                .collect(Collectors.toList());
                        ui.printTasksOnDate(tasksOnGivenDate, givenDate);
                        break;

                    default:
                        ui.showError("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }

            ui.printHorizontalLine();
        }
    }

    public static void main(String[] args) {
        echoMessages();
    }
}