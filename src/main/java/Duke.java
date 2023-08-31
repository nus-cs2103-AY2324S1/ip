import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    private static Command parseCommand(String input) {
        if (input.equalsIgnoreCase("bye")) return Command.BYE;
        if (input.equalsIgnoreCase("list")) return Command.LIST;
        if (input.startsWith("todo")) return Command.TODO;
        if (input.startsWith("deadline")) return Command.DEADLINE;
        if (input.startsWith("event")) return Command.EVENT;
        if (input.startsWith("mark")) return Command.MARK;
        if (input.startsWith("unmark")) return Command.UNMARK;
        if (input.startsWith("delete")) return Command.DELETE;
        if (input.startsWith("tasks on")) return Command.TASKS_ON_DATE;
        return Command.UNKNOWN;
    }

    private static Task parseFileTask(String taskData) throws DukeException {
        String[] parts = taskData.split(" \\| ");
        switch (parts[0]) {
            case "T":
                ToDo todo = new ToDo(parts[2]);
                if (parts[1].equals("1")) todo.markAsDone();
                return todo;
            case "D":
                Deadline deadline = new Deadline(parts[2], parts[3]);
                if (parts[1].equals("1")) deadline.markAsDone();
                return deadline;
            case "E":
                Event event = new Event(parts[2], parts[3], parts[4]);
                if (parts[1].equals("1")) event.markAsDone();
                return event;
            default:
                throw new DukeException("Unknown task type: " + parts[0]);
        }
    }

    public static void echoMessages() {
        Scanner scanner = new Scanner(System.in);
        String input;

        tasks.addAll(storage.loadTasks());
        ui.printGreeting();

        while (true) {
            input = scanner.nextLine();
            Command command = parseCommand(input);

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
                        LocalDate givenDate = getLocalDate(input);
                        List<Task> tasksOnGivenDate = tasks.stream()
                                .filter(task ->
                                        (task instanceof Deadline && ((Deadline) task).getBy().toLocalDate().isEqual(givenDate)) ||
                                                (task instanceof Event && isWithinEventDate((Event) task, givenDate)))
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


    private static LocalDate getLocalDate(String input) throws DukeException {
        String[] dateParts = input.split(" ");
        if (dateParts.length < 3) {
            throw new DukeException("Please provide a valid date in the format d/M/yyyy.");
        }
        LocalDate givenDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            givenDate = LocalDate.parse(dateParts[2], formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use d/M/yyyy.");
        }
        return givenDate;
    }

    private static boolean isWithinEventDate(Event event, LocalDate date) {
        LocalDate startDate = event.getFrom().toLocalDate();
        LocalDate endDate = event.getTo().toLocalDate();
        return (date.isEqual(startDate) || date.isEqual(endDate) || (date.isAfter(startDate) && date.isBefore(endDate)));
    }

    public static void main(String[] args) {
        echoMessages();
    }
}