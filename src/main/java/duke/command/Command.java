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
     * Marks task
     * 
     * @param message the command message
     * @param ui the ui of the program
     * @param tasks the list of tasks
     * @param storage the storage of the program
     * @throws InvalidCommandException
     */
    public static void mark(String message, Ui ui, TaskList tasks, Storage storage) throws InvalidCommandException {
        try {
            if(message.split(" ").length == 2) {
                Task markTask = tasks.getTask(Integer.parseInt(message.split(" ")[1]) - 1);
                markTask.mark();
                storage.updateTask(tasks);
                ui.printMarkTask(markTask);
            } else {
                throw new InvalidCommandException();
            }
        } catch(IndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        } catch(NumberFormatException e) {
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
     * @throws InvalidCommandException
     */
    public static void unmark(String message, Ui ui, TaskList tasks, Storage storage) throws InvalidCommandException {
        try {
            if(message.split(" ").length == 2) {
                Task unmarkTask = tasks.getTask(Integer.parseInt(message.split(" ")[1]) - 1);
                unmarkTask.unmark();
                storage.updateTask(tasks);
                ui.printUnmarkTask(unmarkTask);
            } else {
                throw new InvalidCommandException();
            }
        } catch(IndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        } catch(NumberFormatException e) {
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
     * @throws InvalidToDoException
     */
    public static void addToDo(String message, Ui ui, TaskList tasks, Storage storage) throws InvalidToDoException {
        Task newTask = ToDo.create(message);
        tasks.addTask(newTask);
        storage.addTask(newTask);
        ui.printAddTask(newTask, tasks.size());
    }

    /**
     * Adds deadline
     * 
     * @param message the command message
     * @param ui the ui of the program
     * @param tasks the list of tasks
     * @param storage the storage of the program
     * @throws InvalidDeadlineException
     */
    public static void addDeadline(String message, Ui ui, TaskList tasks, Storage storage) throws InvalidDeadlineException {
        Task newTask = Deadline.create(message);
        tasks.addTask(newTask);
        storage.addTask(newTask);
        ui.printAddTask(newTask, tasks.size());
    }

    /**
     * Adds event
     * 
     * @param message the command message
     * @param ui the ui of the program
     * @param tasks the list of tasks
     * @param storage the storage of the program
     * @throws InvalidEventException
     */
    public static void addEvent(String message, Ui ui, TaskList tasks, Storage storage) throws InvalidEventException {
        Task newTask = Event.create(message);
        tasks.addTask(newTask);
        storage.addTask(newTask);
        ui.printAddTask(newTask, tasks.size());
    }

    /**
     * Deletes task
     * 
     * @param message the command message
     * @param ui the ui of the program
     * @param tasks the list of tasks
     * @param storage the storage of the program
     * @throws InvalidCommandException
     */
    public static void delete(String message, Ui ui, TaskList tasks, Storage storage) throws InvalidCommandException {
        try {
            if(message.split(" ").length == 2) {
                Task removedTask = tasks.getTask(Integer.parseInt(message.split(" ")[1]) - 1);
                tasks.removeTask(Integer.parseInt(message.split(" ")[1]) - 1);
                storage.updateTask(tasks);
                ui.printRemoveTask(removedTask, tasks.size());
            } else {
                throw new InvalidCommandException();
            }
        } catch(IndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        } catch(NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

    public static void find(String message, Ui ui, TaskList tasks, Storage storage) throws InvalidCommandException {
        try {
            String[] messageWords = message.split(" ");
            int numOfWords = messageWords.length;
            if (numOfWords == 2) {
                String keyWord = messageWords[1];
                ui.printFindTask(tasks.find(keyWord));
            } else {
                throw new InvalidCommandException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }
}