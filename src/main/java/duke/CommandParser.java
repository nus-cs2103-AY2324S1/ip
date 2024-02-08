package duke;

import java.io.IOException;

/**
 * This class has only static methods that handle parsing of Commands
 * by the Parser class. These methods are abstracted out to ensure
 * better compliance with OOP principles.
 */
public class CommandParser {


    /**
     * Parses a given input command as an event. Adds the event to a
     * provided TaskList object and stores the event with a provided
     * Storage object.
     *
     * @param p The provided Parser object.
     * @param tl The provided TaskList object.
     * @param store The provided Storage object.
     * @return The relevant execution output as a String.
     * @throws DukeException
     * @throws IOException
     */
    public static String eventCommand(Parser p, TaskList tl, Storage store)
            throws DukeException, IOException {
        Event tempEvent = null;
        try {
            tempEvent = p.parseEvent();
        } catch (DukeException e) {
            throw new DukeException(new Ui().eventErrorString());
        } catch (Exception ae) {
            throw new DukeException(new Ui().generalErrorString());
        }

        tl.add(tempEvent);

        try {
            store.taskListToFile(tl);
        } catch (IOException e) {
            throw new IOException(new Ui().fileErrorString());
        }
        return new Ui().addedTaskScreen(tempEvent, tl.size());
    }

    /**
     * Parses a given input command as a deadline. Adds the deadline to a
     * provided TaskList object and stores the deadline with a provided
     * Storage object.
     *
     * @param p The provided Parser object.
     * @param tl The provided TaskList object.
     * @param store The provided Storage object.
     * @return The relevant execution output as a String.
     * @throws DukeException
     * @throws IOException
     */
    public static String deadlineCommand(Parser p, TaskList tl, Storage store)
            throws DukeException, IOException {
        Deadline tempDeadline = null;
        try {
            tempDeadline = p.parseDeadline();
        } catch (DukeException e) {
            throw new DukeException(new Ui().deadlineErrorString());
        } catch (Exception ae) {
            throw new DukeException(new Ui().deadlineFormatErrorString());
        }
        tl.add(tempDeadline);

        try {
            store.taskListToFile(tl);
        } catch (IOException e) {
            throw new IOException(new Ui().fileErrorString());
        }

        return new Ui().addedTaskScreen(tempDeadline, tl.getTaskList().size());

    }

    /**
     * Parses a given input command as a todo. Adds the todo to a
     * provided TaskList object and stores the todo with a provided
     * Storage object.
     *
     * @param p The provided Parser object.
     * @param tl The provided TaskList object.
     * @param store The provided Storage object.
     * @return The relevant execution output as a String.
     * @throws DukeException
     * @throws IOException
     */
    public static String todoCommand(Parser p, TaskList tl, Storage store)
            throws DukeException, IOException {
        ToDo tempTodo = null;
        try {
            tempTodo = p.parseTodo();
        } catch (DukeException e) {
            throw e;
        } catch (Exception ae) {
            throw new DukeException(new Ui().generalErrorString());
        }
        tl.add(tempTodo);

        try {
            store.taskListToFile(tl);
        } catch (IOException e) {
            throw new IOException(new Ui().fileErrorString());
        }
        return new Ui().addedTaskScreen(tempTodo, tl.size());

    }

    /**
     * Executes the find command where a given text is used to
     * search the provided TaskList object for matches.
     *
     * @param p The provided Parser object that contains the search text
     * @param tl The provided TaskList object that contains the tasks.
     * @return All tasks found as a singular String.
     */
    public static String findCommand(Parser p, TaskList tl) throws DukeException {
        if (p.commandOnly()) {
            throw new DukeException(new Ui().textErrorString());
        }

        return new Ui().findListPrinter(tl, p.word());
    }

    /**
     * Returns the list of tasks contained in the TaskList as a String.
     *
     * @param tl The provided TaskList object that contains the tasks.
     * @return All contained tasks as a singular String.
     */
    public static String listCommand(TaskList tl) {
        if (tl.size() == 0) {
            return new Ui().emptyListErrorString();
        }

        return new Ui().taskListPrinter(tl);
    }
}
