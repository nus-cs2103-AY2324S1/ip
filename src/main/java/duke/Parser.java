package duke;

/**
 * Represents a parser for the Duke application.
 * Handles the interpretation and execution of user commands.
 */
public class Parser {

    private boolean isExit = false;

    /**
     * Parses and executes the given user command.
     *
     * @param fullCommand The full string of the user's command.
     * @param tasks The current list of tasks.
     * @param ui The UI of the Duke application.
     * @param storage The storage of the Duke application.
     * @throws DukeException If there's an error in command execution.
     */
    public void parseAndExecute(String fullCommand, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert fullCommand != null : "fullCommand should not be null"; // fullCommand should not be null
        assert tasks != null : "tasks should not be null"; // tasks should not be null
        assert ui != null : "ui should not be null"; // ui should not be null
        assert storage != null : "storage should not be null"; // storage should not be null
        validateInput(fullCommand);
        String[] tokens = fullCommand.split("\\s+");
        String command = tokens[0];
        switch (command) {
        case "bye":
            this.isExit = true;
            ui.printBye();
            break;
        case "list":
            ui.printList(tasks);
            break;
        case "todo":
            Task todo = new Todo (fullCommand.substring(5));
            tasks.add(todo);
            ui.printAdded(todo, tasks.getSize());
            storage.save(tasks.getTasks());
            break;
        case "deadline":
            String[] arr = fullCommand.substring(9).split(" /by ");
            Task deadline = new Deadline(arr[0], arr[1]);
            tasks.add(deadline);
            ui.printAdded(deadline, tasks.getSize());
            storage.save(tasks.getTasks());
            break;
        case "event":
            String[] arr1 = fullCommand.substring(6).split("\\s*/from\\s*|\\s*/to\\s*");
            Task event = new Event(arr1[0], arr1[1], arr1[2]);
            tasks.add(event);
            ui.printAdded(event, tasks.getSize());
            storage.save(tasks.getTasks());
            break;
        case "period":
            String[] arr2 = fullCommand.substring(7).split("\\s*/from\\s*|\\s*/to\\s*");
            Task period = new Period(arr2[0], arr2[1], arr2[2]);
            tasks.add(period);
            ui.printAdded(period, tasks.getSize());
            storage.save(tasks.getTasks());
            break;
        case "delete":
            int indexToDelete = Integer.parseInt(fullCommand.substring(7));
            Task taskToDelete = tasks.get(indexToDelete);
            tasks.delete(indexToDelete - 1);
            ui.printDeleted(taskToDelete, tasks.getSize());
            storage.save(tasks.getTasks());
            break;
        case "mark":
            int indexToMark = Integer.parseInt(fullCommand.substring(5));
            Task taskToMark = tasks.get(indexToMark);
            taskToMark.markAsDone();
            ui.printDone(taskToMark);
            storage.save(tasks.getTasks());
            break;
        case "unmark":
            int indexToUnmark = Integer.parseInt(fullCommand.substring(7));
            Task taskToUnmark = tasks.get(indexToUnmark);
            taskToUnmark.markAsUndone();
            ui.printUndone(taskToUnmark);
            storage.save(tasks.getTasks());
            break;
        case "find":
            String keyword = fullCommand.substring(5);
            ui.printFound(tasks.find(keyword));
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Checks if the exit command has been issued.
     *
     * @return true if the exit command has been issued, false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Validates the given user input.
     *
     * @param input The user's input string.
     * @throws DukeException If the input is invalid.
     */
    public void validateInput(String input) throws DukeException {
        if (input.equals("todo") || input.equals("deadline") || input.equals("event") || input.equals("mark")
                || input.equals("unmark") || input.equals("delete")) {
            throw new DukeException("OOPS!!! The description of a " + input + " cannot be empty.");
        }

        if (input.startsWith("deadline ") && !input.contains("/by")) {
            throw new DukeException("OOPS!!! The description of a deadline must contain /by.");
        }

        if (input.startsWith("event ") && !input.contains("/from") && !input.contains("/to")) {
            throw new DukeException("OOPS!!! The description of a event must contain /from and /to.");
        }

        if (input.startsWith("period ") && !input.contains("/from") && !input.contains("/to")) {
            throw new DukeException("OOPS!!! The description of a period must contain /from and /to.");
        }

        if (!input.startsWith("todo ") && !input.startsWith("deadline ") && !input.startsWith("event ")
                && !input.equals("list") && !input.equals("bye") && !input.startsWith("mark ")
                && !input.startsWith("unmark ") && !input.startsWith("delete ") && !input.startsWith("find ")
                && !input.startsWith("period ")) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
