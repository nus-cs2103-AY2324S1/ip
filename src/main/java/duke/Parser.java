package duke;

/**
 * The `Parser` class handles user input parsing and command execution.
 */
public class Parser {
    /**
     * The `run` variable is used to control the main program loop.
     */
    public static int run = 1;

    /**
     * Parses the user input and executes the corresponding command.
     *
     * @param task The user input command.
     * @return The response message after executing the command.
     */
    public static String parse(String task) {
        try {
            if (task.equals("bye")) {
                return handleBye();
            }
            if (task.equals("list")) {
                return handleList();
            }
            String[] elements = task.split((" "));
            if (elements.length >= 2) {
                String instruction = elements[0];
                switch (instruction) {
                case "mark":
                case "unmark":
                    return handleMarkOrUnmark(instruction, elements);
                case "delete":
                    return handleDelete(elements);
                case "find":
                    return handleFind(elements);
                default:
                    return handleTaskCreation(task, instruction);
                }
            } else if (elements[0].equals("todo") || elements[0].equals("deadline") || elements[0].equals("event")) {
                throw new DukeException(Ui.horizontalLine + "OOPS!!! The description of a " + elements[0] + " cannot be empty.\n" + Ui.horizontalLine);
            } else {
                throw new DukeException(Ui.horizontalLine + "OOPS!!! I'm sorry, but I don't know what that means :-(\n" + Ui.horizontalLine);
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the "bye" command, setting the `run` variable to 0 and returning
     * the farewell message.
     *
     * @return The farewell message.
     */
    private static String handleBye() {
        run = 0;
        return Ui.sayBye();
    }

    /**
     * Handles the "list" command, returning the formatted list of tasks.
     *
     * @return The formatted list of tasks.
     */
    private static String handleList() {
        Task taskInstance = new Task();
        return taskInstance.formatList();
    }

    /**
     * Handles the "mark" or "unmark" command, marking or unmarking a task with
     * the given task number.
     *
     * @param instruction The command instruction ("mark" or "unmark").
     * @param elements    The command elements.
     * @return The response message after marking or unmarking the task.
     */
    private static String handleMarkOrUnmark(String instruction, String[] elements) {
        try {
            int no = Integer.parseInt(elements[1]);
            Task taskInstance = new Task();
            if (instruction.equals("mark")) {
                return taskInstance.mark(no);
            } else {
                return taskInstance.unmark(no);
            }
        } catch (NumberFormatException e) {
            return (Ui.horizontalLine
                    + "You did not enter a valid integer :(\n"
                    + Ui.horizontalLine);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the "delete" command, deleting the task with the specified task number.
     *
     * @param elements The command elements.
     * @return The response message after deleting the task.
     */
    private static String handleDelete(String[] elements) {
        try {
            int no = Integer.parseInt(elements[1]);
            Task taskInstance = new Task();
            return taskInstance.delete(no);
        } catch (NumberFormatException e) {
            return (Ui.horizontalLine
                    + "You did not enter a valid integer :(\n"
                    + Ui.horizontalLine);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the creation of a "todo" task.
     *
     * @param actualTask The description of the "todo" task.
     * @return The response message after creating the "todo" task.
     */
    private static String handleTodo(String actualTask) {
        Todo todo = new Todo(actualTask, true);
        return todo.printStr();
    }

    /**
     * Handles the creation of a "deadline" task.
     *
     * @param actualTask The description of the "deadline" task.
     * @return The response message after creating the "deadline" task.
     * @throws DukeException If the deadline format is invalid.
     */
    private static String handleDeadline(String actualTask) throws DukeException {
        String[] taskAndDeadline = actualTask.split("/by");
        if (taskAndDeadline.length == 1 || taskAndDeadline.length == 0) {
            throw new DukeException(Ui.horizontalLine
                    + "OOPS!!! Invalid format for deadline :-(\n"
                    + Ui.horizontalLine);
        }
        if (taskAndDeadline.length == 1 || taskAndDeadline.length == 0) {
            throw new DukeException(Ui.horizontalLine
                    + "OOPS!!! Invalid format for deadline :-(\n"
                    + Ui.horizontalLine);
        }
        String onlyTask = taskAndDeadline[0].trim(); // Trim to remove extra spaces
        String by = taskAndDeadline[1].trim();// Trim to remove extra spaces
        if (!by.contains(" ")) {
            by += " 2359";
        }
        Deadline deadline = new Deadline(onlyTask, true, by);
        return deadline.printStr();
    }

    /**
     * Handles the creation of an "event" task.
     *
     * @param actualTask The description of the "event" task.
     * @return The response message after creating the "event" task.
     * @throws DukeException If the event format is invalid.
     */
    private static String handleEvent(String actualTask) throws DukeException {
        String[] taskAndToFrom = actualTask.split("/from");
        if (taskAndToFrom.length == 1 || taskAndToFrom.length == 0) {
            throw new DukeException(Ui.horizontalLine
                    + "OOPS!!! Invalid format for event :-(\n"
                    + Ui.horizontalLine);
        }
        String onlyTask = taskAndToFrom[0].trim(); // Trim to remove extra spaces
        if (onlyTask.isEmpty()) {
            throw new DukeException(Ui.horizontalLine
                    + "OOPS!!! Invalid format for event :-(\n"
                    + Ui.horizontalLine);
        }
        String[] ToFrom = taskAndToFrom[1].split("/to");
        if (ToFrom.length == 1 || ToFrom.length == 0) {
            throw new DukeException(Ui.horizontalLine
                    + "OOPS!!! Invalid format for event :-(\n"
                    + Ui.horizontalLine);
        }
        String from = ToFrom[0].trim(); // Trim to remove extra spaces
        String to = ToFrom[1].trim(); // Trim to remove extra spaces
        if (!from.contains(" ")) {
            from += " 2359";
        }
        if (!to.contains(" ")) {
            to += " 2359";
        }
        Event event = new Event(onlyTask, true, from, to);
        return event.printStr();
    }

    /**
     * Handles the "find" command, searching for tasks containing the specified keyword.
     *
     * @param elements The command elements.
     * @return The response message after performing the search.
     */
    private static String handleFind(String[] elements) {
        String keyword = elements[1];
        return Ui.displaySearchResults(Duke.taskList, keyword);
    }

    /**
     * Handles the creation of tasks for commands other than "bye," "list," "mark," "unmark," "delete," and "find."
     *
     * @param task        The user input command.
     * @param instruction The command instruction.
     * @return The response message after creating the task.
     * @throws DukeException If the command or format is invalid.
     */
    private static String handleTaskCreation(String task, String instruction) throws DukeException {
        int firstSpaceIndex = task.indexOf(' ');
        String actualTask = task.substring(firstSpaceIndex + 1);
        switch (instruction) {
        case "todo":
            return handleTodo(actualTask);
        case "deadline":
            return handleDeadline(actualTask);
        case "event":
            return handleEvent(actualTask);
        default:
            throw new DukeException(Ui.horizontalLine
                    + "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + Ui.horizontalLine);
        }
    }

}
