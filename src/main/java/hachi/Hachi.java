package hachi;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import exceptions.DateFormatWrongException;
import exceptions.EmptyDeadlineException;
import exceptions.EmptyTaskException;
import exceptions.EventDateException;
import exceptions.HachiException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;
import exceptions.NoDeadlineException;

/**
 * A task list app that allows the user to add Todos, Deadlines, and Events.
 * The app saves the list locally on the computer, and thus can remember tasks entered previously.
 * The user can add tasks, delete tasks, mark tasks as done, list all tasks, and search for them by date and name.
 */
public class Hachi {

    private static final String DEFAULT_TASK_PATH = "./data/tasks.txt";

    private Ui ui;

    private Storage storage;

    private String filePath;

    /**
     * Overloaded constructor for the Hachi class.
     * Initialises the UI, Storage, and uses the default filePath to store the tasks at.
     */
    public Hachi() {
        ui = new Ui();
        storage = new Storage();
        filePath = DEFAULT_TASK_PATH;
    }

    /**
     * Overloaded constructor for the Hachi class.
     * Initialises the UI, Storage, and takes in a filePath to store the tasks at.
     * @param filePath The relative location to store the text file for the tasks in.
     */
    public Hachi(String filePath) {
        ui = new Ui();
        storage = new Storage();
        this.filePath = filePath;
    }

    public static void main(String[] args) throws HachiException {
        new Hachi(DEFAULT_TASK_PATH).run();
    }

    /**
     * Runs the task list program.
     * @throws HachiException
     */
    public void run() throws HachiException {
        String name = "Hachi";

        TaskList taskList = storage.getTaskList();
        // creating the directory and file to store the tasks in
        ArrayList<Task> tasks = taskList.getArrayList();

        // Printing opening line
        ui.showWelcome();

        boolean isExit = false;


        // repeats until user types bye, which quits the program
        while (!isExit) {
            // getting command and argument separately
            Command cmd = Parser.parse(ui.getInput());
            String command = cmd.getCommand();
            String[] arguments = cmd.getArguments();

            // main logic
            try {
                switch (command) {
                case ByeCommand.COMMAND_WORD:
                    Parser.checkArgumentLength(ByeCommand.COMMAND_WORD, arguments.length);
                    isExit = true;
                    ui.showMessage("Bye. Hope to see you again soon!");
                    break;
                case ListCommand.COMMAND_WORD:
                    Parser.checkArgumentLength(ListCommand.COMMAND_WORD, arguments.length);
                    ui.showMessage(taskList.toString());
                    break;
                case MarkCommand.COMMAND_WORD:
                    Parser.checkArgumentLength(MarkCommand.COMMAND_WORD, arguments.length);
                    try {
                        int i = Parser.parseTaskNumber(arguments[0], tasks.size());
                        Task t = taskList.mark(i);
                        ui.showMessage("Nice! I've marked this task as done\n   " + t);
                        storage.updateTaskFile(taskList);
                    } catch (NumberFormatException e) {
                        throw new InvalidArgumentException(MarkCommand.COMMAND_WORD);
                    }
                    break;
                case UnmarkCommand.COMMAND_WORD:
                    Parser.checkArgumentLength(UnmarkCommand.COMMAND_WORD, arguments.length);
                    try {
                        int i = Parser.parseTaskNumber(arguments[0], tasks.size());
                        Task t = taskList.unmark(i);
                        ui.showMessage("OK, I've marked this task as not done yet:\n   " + t);
                        storage.updateTaskFile(taskList);
                    } catch (NumberFormatException e) {
                        throw new InvalidArgumentException(UnmarkCommand.COMMAND_WORD);
                    }
                    break;
                case DeleteCommand.COMMAND_WORD:
                    Parser.checkArgumentLength(DeleteCommand.COMMAND_WORD, arguments.length);
                    try {
                        int i = Parser.parseTaskNumber(arguments[0], tasks.size());
                        Task t = taskList.remove(i);
                        ui.showMessage("Noted. I've removed this task:\n   " + t
                                + String.format("\nNow you have %d tasks in the list.", tasks.size()));
                        storage.updateTaskFile(taskList);
                    } catch (NumberFormatException e) {
                        throw new InvalidArgumentException(DeleteCommand.COMMAND_WORD);
                    }
                    break;
                case TodoCommand.COMMAND_WORD:
                    Parser.checkArgumentLength(TodoCommand.COMMAND_WORD, arguments.length);
                    Todo td = new Todo(Parser.parseTaskArguments(TodoCommand.COMMAND_WORD, arguments));
                    taskList.add(td);
                    ui.showMessage("Got it. I've added this task:\n   " + td
                            + String.format("\nNow you have %d tasks in the list.", tasks.size()));
                    storage.updateTaskFile(taskList);
                    break;
                case DeadlineCommand.COMMAND_WORD:
                    Parser.checkArgumentLength(DeadlineCommand.COMMAND_WORD, arguments.length);
                    int byIndex = Parser.getWordIndex("/by", arguments);
                    if (byIndex == -1) {
                        throw new NoDeadlineException();
                    }
                    if (byIndex == arguments.length - 1) {
                        throw new EmptyDeadlineException("deadline");
                    }
                    String deadlineTask = String.join(" ",
                            Arrays.copyOfRange(arguments, 0, byIndex));
                    String deadlineDate = String.join(" ",
                            Arrays.copyOfRange(arguments, byIndex + 1, arguments.length));
                    try {
                        Deadline dl = new Deadline(deadlineTask, LocalDate.parse(deadlineDate));
                        taskList.add(dl);
                        ui.showMessage("Got it. I've added this task:\n   " + dl
                                + String.format("\nNow you have %d tasks in the list.", tasks.size()));
                        storage.updateTaskFile(taskList);
                    } catch (DateTimeParseException e) {
                        throw new DateFormatWrongException(deadlineDate);
                    }
                    break;
                case EventCommand.COMMAND_WORD:
                    Parser.checkArgumentLength(EventCommand.COMMAND_WORD, arguments.length);
                    int fromIndex = Parser.getWordIndex("/from", arguments);
                    int toIndex = Parser.getWordIndex("/to", arguments);
                    if (fromIndex == -1 && toIndex == -1) {
                        throw new EventDateException("/from and /to");
                    } else if (toIndex == -1) {
                        throw new EventDateException("/to");
                    } else if (fromIndex == -1) {
                        throw new EventDateException("/from");
                    } else {
                        String eventTask = String.join(" ",
                                Arrays.copyOfRange(arguments, 0, fromIndex));
                        String eventStartDate = String.join(" ",
                                Arrays.copyOfRange(arguments, fromIndex + 1, toIndex));
                        String eventEndDate = String.join(" ",
                                Arrays.copyOfRange(arguments, toIndex + 1, arguments.length));
                        if (eventTask.equals("")) {
                            throw new EmptyTaskException("event");
                        } else if (eventStartDate.equals("") && eventEndDate.equals("")) {
                            throw new EventDateException("start date and end date");
                        } else if (eventEndDate.equals("")) {
                            throw new EventDateException("end date");
                        } else if (eventStartDate.equals("")) {
                            throw new EventDateException("start date");
                        }
                        try {
                            Event ev = new Event(eventTask, LocalDate.parse(eventStartDate),
                                    LocalDate.parse(eventEndDate));
                            taskList.add(ev);
                            ui.showMessage("Got it. I've added this task:\n   " + ev
                                    + String.format("\nNow you have %d tasks in the list.", tasks.size()));
                            storage.updateTaskFile(taskList);
                        } catch (DateTimeParseException e) {
                            throw new DateFormatWrongException(eventStartDate + ", " + eventEndDate);
                        }
                    }
                    break;
                case SearchdateCommand.COMMAND_WORD:
                    Parser.checkArgumentLength(SearchdateCommand.COMMAND_WORD, arguments.length);
                    LocalDate searchDate = LocalDate.parse(arguments[0]);
                    ArrayList<Task> filteredDates = new ArrayList<>();
                    taskList.iter(task -> {
                        if (task.isDateWithinRange(searchDate)) {
                            filteredDates.add(task);
                        }
                    });
                    ui.showMessage(new TaskList(filteredDates).toString());
                    break;
                case FindCommand.COMMAND_WORD:
                    Parser.checkArgumentLength(FindCommand.COMMAND_WORD, arguments.length);
                    String str = Parser.parseTaskArguments(FindCommand.COMMAND_WORD, arguments);
                    ArrayList<Task> filteredTasks = new ArrayList<>();
                    tasks.forEach(task -> {
                        if (task.isStringWithinTaskName(str)) {
                            filteredTasks.add(task);
                        }
                    });
                    if (filteredTasks.isEmpty()) {
                        ui.showMessage("No tasks found! Maybe try changing your search terms.");
                    } else {
                        ui.showMessage(new TaskList(filteredTasks).toString());
                    }
                    break;
                default:
                    throw new InvalidCommandException(command);
                }
            } catch (HachiException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
