package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Main class for the Duke application.
 * <p>
 * The Duke application is a task management application that allows users to manage and track
 * tasks such as todos, deadlines, and events. It provides a command-line interface for users
 * to interact with.
 * </p>
 */
public class Duke {
    /** Task list to store and manage all tasks. */
    private static final TaskList taskList = new TaskList();

    /** User interface object for interacting with the user. */
    private static final Ui ui = new Ui();

    /** Storage object for saving and loading tasks to/from a file. */
    private static final Storage storage = new Storage(ui);

    /**
     * Processes user input commands and interacts with the user.
     * <p>
     * This method listens for user inputs, parses the commands, processes them,
     * and provides appropriate responses. It handles task creation, modification,
     * deletion, listing, and other related operations.
     * </p>
     */
    public static void echoMessages() {
        Scanner scanner = new Scanner(System.in);
        String input;

        taskList.getTasks().addAll(storage.loadTasks());
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
                        ui.printList(taskList.getTasks());
                        break;

                    case TODO:
                        ToDo todo = new ToDo(input.substring(5));
                        taskList.add(todo);
                        ui.printTaskAdded(todo, taskList.getSize());
                        storage.saveTasks(taskList.getTasks());
                        break;

                    case DEADLINE:
                        String[] parts = input.substring(9).split(" /by ");
                        if (parts.length < 2) {
                            throw new DukeException("duke.task.Deadline format is incorrect.");
                        }
                        Deadline deadline = new Deadline(parts[0], parts[1]);
                        taskList.add(deadline);
                        ui.printTaskAdded(deadline, taskList.getSize());
                        storage.saveTasks(taskList.getTasks());
                        break;

                    case EVENT:
                        String[] eventParts = input.substring(6).split(" /from ");
                        String[] timeParts = eventParts[1].split(" /to ");
                        if (timeParts.length < 2) {
                            throw new DukeException("duke.task.Event format is incorrect.");
                        }
                        Event event = new Event(eventParts[0], timeParts[0], timeParts[1]);
                        taskList.add(event);
                        ui.printTaskAdded(event, taskList.getSize());
                        storage.saveTasks(taskList.getTasks());
                        break;

                    case MARK:
                        int taskNumberMark = Integer.parseInt(input.split(" ")[1]);
                        taskList.get(taskNumberMark - 1).markAsDone();
                        ui.printMarkedAsDone(taskList.get(taskNumberMark - 1));
                        storage.saveTasks(taskList.getTasks());
                        break;

                    case UNMARK:
                        int taskNumberUnmark = Integer.parseInt(input.split(" ")[1]);
                        taskList.get(taskNumberUnmark - 1).unmark();
                        ui.printMarkedAsNotDone(taskList.get(taskNumberUnmark - 1));
                        storage.saveTasks(taskList.getTasks());
                        break;

                    case DELETE:
                        int taskNumberDelete = Integer.parseInt(input.split(" ")[1]);
                        Task removedTask = taskList.remove(taskNumberDelete - 1);
                        ui.printTaskDeleted(removedTask, taskList.getSize());
                        storage.saveTasks(taskList.getTasks());
                        break;

                    case TASKS_ON_DATE:
                        LocalDate givenDate = Parser.getLocalDate(input);
                        List<Task> tasksOnGivenDate = taskList.getTasks().stream()
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

    /**
     * The entry point for the Duke application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        echoMessages();
    }
}