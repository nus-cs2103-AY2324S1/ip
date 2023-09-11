package duke.command;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.exception.InvalidToDoException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * The class for Command
 */
public class Command {

    /**
     * Lists all the tasks
     *
     * @param message the command message
     * @param ui the ui of the program
     * @param tasks the list of tasks
     * @param storage the storage of the program
     * @return the message of the list task command
     * @throws InvalidCommandException when the list command is invalid
     */
    public static String list(String message, Ui ui, TaskList tasks, Storage storage) throws InvalidCommandException {
        assert message.split(" ")[0].equalsIgnoreCase("list") : "First word of message must be list";
        if (message.equals("list")) {
            return ui.printTasks(tasks);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Marks task
     *
     * @param message the command message
     * @param ui the ui of the program
     * @param tasks the list of tasks
     * @param storage the storage of the program
     * @return the message of the mark task command
     * @throws InvalidCommandException when the mark command is invalid
     */
    public static String mark(String message, Ui ui, TaskList tasks, Storage storage) throws InvalidCommandException {
        assert message.split(" ")[0].equalsIgnoreCase("mark") : "First word of message must be mark";
        try {
            String[] messageWords = message.split(" ");
            int numOfWords = messageWords.length;
            if (numOfWords == 2) {
                int index = Integer.parseInt(messageWords[1]) - 1;
                tasks.markTask(index);
                storage.updateTask(tasks);
                return ui.printMarkTask(tasks.getTask(index));
            } else {
                throw new InvalidCommandException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Unmarks task
     *
     * @param message the command message
     * @param ui the ui of the program
     * @param tasks the list of tasks
     * @param storage the storage of the program
     * @return the message of the unmark task command
     * @throws InvalidCommandException when the unmark command is invalid
     */
    public static String unmark(String message, Ui ui, TaskList tasks, Storage storage) throws InvalidCommandException {
        assert message.split(" ")[0].equalsIgnoreCase("unmark") : "First word of message must be unmark";
        try {
            String[] messageWords = message.split(" ");
            int numOfWords = messageWords.length;
            if (numOfWords == 2) {
                int index = Integer.parseInt(messageWords[1]) - 1;
                tasks.unmarkTask(index);
                storage.updateTask(tasks);
                return ui.printUnmarkTask(tasks.getTask(index));
            } else {
                throw new InvalidCommandException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Adds to do
     *
     * @param message the command message
     * @param ui the ui of the program
     * @param tasks the list of tasks
     * @param storage the storage of the program
     * @return the message of the add todo command
     * @throws InvalidToDoException when the todo command is invalid
     */
    public static String addToDo(String message, Ui ui, TaskList tasks, Storage storage) throws InvalidToDoException {
        assert message.split(" ")[0].equalsIgnoreCase("todo") : "First word of message must be todo";
        Task newTask = ToDo.create(message);
        tasks.addTask(newTask);
        storage.addTask(newTask);
        return ui.printAddTask(newTask, tasks.getSize());
    }

    /**
     * Adds deadline
     *
     * @param message the command message
     * @param ui the ui of the program
     * @param tasks the list of tasks
     * @param storage the storage of the program
     * @return the message of the add deadline command
     * @throws InvalidDeadlineException when the deadline command is invalid
     */
    public static String addDeadline(String message, Ui ui, TaskList tasks, Storage storage)
            throws InvalidDeadlineException {
        assert message.split(" ")[0].equalsIgnoreCase("deadline") : "First word of message must be deadline";
        Task newTask = Deadline.create(message);
        tasks.addTask(newTask);
        storage.addTask(newTask);
        return ui.printAddTask(newTask, tasks.getSize());
    }

    /**
     * Adds event
     *
     * @param message the command message
     * @param ui the ui of the program
     * @param tasks the list of tasks
     * @param storage the storage of the program
     * @return the message of the add event command
     * @throws InvalidEventException when the event command is invalid
     */
    public static String addEvent(String message, Ui ui, TaskList tasks, Storage storage) throws InvalidEventException {
        assert message.split(" ")[0].equalsIgnoreCase("event") : "First word of message must be event";
        Task newTask = Event.create(message);
        tasks.addTask(newTask);
        storage.addTask(newTask);
        return ui.printAddTask(newTask, tasks.getSize());
    }

    /**
     * Deletes task
     *
     * @param message the command message
     * @param ui the ui of the program
     * @param tasks the list of tasks
     * @param storage the storage of the program
     * @return the message of the delete task command
     * @throws InvalidCommandException when the delete command is invalid
     */
    public static String delete(String message, Ui ui, TaskList tasks, Storage storage) throws InvalidCommandException {
        assert message.split(" ")[0].equalsIgnoreCase("delete") : "First word of message must be delete";
        try {
            String[] messageWords = message.split(" ");
            int numOfWords = messageWords.length;
            if (numOfWords == 2) {
                int index = Integer.parseInt(messageWords[1]) - 1;
                Task removedTask = tasks.getTask(index);
                tasks.removeTask(index);
                storage.updateTask(tasks);
                return ui.printRemoveTask(removedTask, tasks.getSize());
            } else {
                throw new InvalidCommandException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Finds the compatible tasks
     *
     * @param message the message
     * @param ui the ui
     * @param tasks the list of tasks
     * @param storage the storage to store
     * @return the message of the find task command
     * @throws InvalidCommandException if the command is invalid
     */
    public static String find(String message, Ui ui, TaskList tasks, Storage storage) throws InvalidCommandException {
        assert message.split(" ")[0].equalsIgnoreCase("find") : "First word of message must be find";
        try {
            String[] messageWords = message.split(" ");
            int numOfWords = messageWords.length;
            if (numOfWords == 2) {
                String keyWord = messageWords[1];
                return ui.printFindTask(tasks.find(keyWord));
            } else {
                throw new InvalidCommandException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Responds to bye message
     *
     * @param message the message
     * @param ui the ui
     * @param tasks the list of tasks
     * @param storage the storage
     * @return the goodbye string
     * @throws InvalidCommandException if the command is invalid
     */
    public static String bye(String message, Ui ui, TaskList tasks, Storage storage) throws InvalidCommandException {
        assert message.split(" ")[0].equalsIgnoreCase("find") : "First word of message must be find";
        if (message.equalsIgnoreCase("bye")) {
            return ui.printEnd();
        } else {
            throw new InvalidCommandException();
        }
    }
}
