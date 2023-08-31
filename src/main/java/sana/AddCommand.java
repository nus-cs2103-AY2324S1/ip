package sana;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command for adding tasks to the task list.
 */
public class AddCommand extends Command {

    /**
     * Constructs an AddCommand object.
     *
     * @param cmd       The command type (e.g., "todo", "deadline", "event").
     * @param arguments The arguments associated with the command.
     */
    public AddCommand(String cmd, String arguments) {
        super(cmd, arguments);
    }

    /**
     * Executes the add command by adding a task to the task list and saving to file.
     *
     * @param tasks   The TaskList object containing the list of tasks.
     * @param ui      The Ui object for handling user interface.
     * @param storage The Storage object for saving and loading tasks.
     * @throws SanaException If an error specific to Sana occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SanaException {
        String cmd = getCmd();
        String arguments = getArguments();
        switch (cmd) {
        case "todo":
            if (arguments.isBlank()) {
                throw new SanaException("OOPS!!! The description of a todo cannot be empty.");
            } else {
                Task newTodo = new Todo(arguments, false);
                tasks.add(newTodo);
                storage.save("/Users/ariellacallista/Desktop",
                        "/Users/ariellacallista/Desktop/SanaTasks.txt", newTodo);

                System.out.println("Got it. I've added this task:\n" + newTodo + "\n"
                        + "Now you have " + tasks.size() + (tasks.size() <= 1 ? " task" : " tasks")
                        + " in the list");
            }
            break;

        case "deadline":
            if (arguments.isBlank()) {
                throw new SanaException("OOPS!!! Incomplete task description.\nMake sure you follow the format "
                        + "'deadline [name of task] /by [deadline]'");
            }

            int lastDescId = arguments.indexOf('/');

            if (lastDescId == -1 || arguments.length() < lastDescId + 4
                    || arguments.substring(lastDescId + 4).isBlank()) {
                throw new SanaException("OOPS!! The deadline cannot be empty.\nMake sure you follow the format "
                        + "'deadline [name of task] /by [deadline]'");
            }

            String desc = arguments.substring(0, lastDescId - 1);
            String by = arguments.substring(lastDescId + 4);

            try {
                LocalDate byDate = LocalDate.parse(by);
                Task newDeadline = new Deadline(desc, byDate, false);

                tasks.add(newDeadline);
                storage.save("/Users/ariellacallista/Desktop",
                        "/Users/ariellacallista/Desktop/SanaTasks.txt", newDeadline);
                System.out.println("Got it. I've added this task:\n" + newDeadline + "\n"
                        + "Now you have " + tasks.size() + (tasks.size() <= 1 ? " task" : " tasks")
                        + " in the list\n");
            } catch (DateTimeParseException e) {
                ui.showError(e.getMessage());
            }
            break;

        case "event":
            if (arguments.isBlank()) {
                throw new SanaException("OOPS!!! Incomplete command. Make sure you follow the format "
                        + "'event [name of task] /from [from] /to [to]'");
            }

            lastDescId = arguments.indexOf('/');

            if (lastDescId == -1 || arguments.length() < lastDescId + 6
                    || arguments.substring(lastDescId + 6).isBlank()) {
                throw new SanaException("OOPS!! The 'from' field cannot be empty.\nMake sure you follow the format "
                        + "'deadline [name of task] /from [from] /to [to]'");
            }
            desc = arguments.substring(0, lastDescId - 1);

            int lastFromId = arguments.indexOf('/', lastDescId + 1);
            if (lastFromId == -1 || arguments.length() < lastFromId + 4
                    || arguments.substring(lastFromId + 4).isBlank()) {
                throw new SanaException("OOPS!! The 'to' field cannot be empty.\nMake sure you follow the format "
                        + "'deadline [name of task] /from [from] /to [to]'");
            }
            String from = arguments.substring(lastDescId + 6, lastFromId - 1);
            String to = arguments.substring(lastFromId + 4);
            Task newEvent = null;

            try {
                LocalDate fromDate = LocalDate.parse(from);
                LocalDate toDate = LocalDate.parse(to);
                newEvent = new Event(desc, fromDate, toDate, false);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Make sure it is yyyy-mm-dd");
            }

            if (newEvent != null) {
                tasks.add(newEvent);
                storage.save("/Users/ariellacallista/Desktop",
                        "/Users/ariellacallista/Desktop/SanaTasks.txt", newEvent);
                System.out.println("Got it. I've added this task:\n" + newEvent + "\n"
                        + "Now you have " + tasks.size() + (tasks.size() <= 1 ? " task" : " tasks")
                        + " in the list");
            }
            break;
        default:
            throw new SanaException("Task type not recognized!");
        }
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return returns false as add command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
