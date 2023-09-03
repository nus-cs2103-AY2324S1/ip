package duke;

import java.time.format.DateTimeParseException;

import exceptions.DukeException;
import javafx.application.Platform;
import javafx.scene.image.Image;
import tasks.Task;
import tasks.TaskList;

/**
 * Duke is a chatbot that allows users to manage tasks.
 */
public class Duke {

    protected static TaskList taskList = new TaskList();
    protected static Storage storage;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/userAvatar.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/dukeAvatar.png"));

    /**
     * Marks task as done.
     *
     * @param index Index of task.
     * @return String to be shown to user.
     * @throws DukeException If index is out of range.
     */
    public static String markTask(int index)
            throws DukeException {
        Task task = Duke.taskList.markTaskAsDone(index);
        storage.saveChanges(Duke.taskList);
        return Ui.getTaskMarkingMessage(task);
    }

    /**
     * Marks task as not done.
     *
     * @param index Index of task.
     * @return String to be shown to user.
     * @throws DukeException If index is out of range.
     */
    public static String unmarkTask(int index)
            throws DukeException {
        Task task = Duke.taskList.unmarkTask(index);
        storage.saveChanges(Duke.taskList);
        return Ui.getTaskUnmarkingMessage(task);
    }

    /**
     * Deletes task.
     *
     * @param index Index of task.
     * @return String to be shown to user.
     * @throws DukeException If index is out of range.
     */
    public static String deleteTask(int index)
            throws DukeException {
        Task task = Duke.taskList.deleteTask(index);
        storage.saveChanges(Duke.taskList);
        return Ui.getTaskDeletingMessage(task, Duke.taskList.getSize());
    }

    /**
     * Creates todo task.
     *
     * @param desc Description of task.
     * @return Message to be shown after task is created.
     */
    public static String createTodo(String desc) {
        Task task = Duke.taskList.addTodo(desc, 0);
        storage.saveChanges(Duke.taskList);
        return Ui.getTaskAddingMessage(task, Duke.taskList.getSize());
    }

    /**
     * Creates deadline task.
     *
     * @param desc     Description of task.
     * @param deadline Deadline date/time.
     * @return Message to be shown after task is created.
     * @throws DateTimeParseException If deadline doesn't match format "yyyy-MM-dd HHmm".
     */
    public static String createDeadline(String desc, String deadline)
            throws DateTimeParseException {
        Task task = Duke.taskList.addDeadline(desc, deadline, 0);
        storage.saveChanges(Duke.taskList);
        return Ui.getTaskAddingMessage(task, Duke.taskList.getSize());
    }

    /**
     * Creates event task.
     *
     * @param desc  Description of task.
     * @param start Start date/time.
     * @param end   End date/time.
     * @return Message to be shown after task is created.
     * @throws DateTimeParseException If start/end don't match format "yyyy-MM-dd HHmm".
     */
    public static String createEvent(String desc, String start, String end)
            throws DateTimeParseException {
        Task task = Duke.taskList.addEvent(desc, start, end, 0);
        storage.saveChanges(Duke.taskList);
        return Ui.getTaskAddingMessage(task, Duke.taskList.getSize());
    }

    /**
     * Gives list of current tasks in output message format.
     *
     * @return Message to be shown to user.
     */
    public static String listTasks() {
        return Duke.taskList.toString();
    }

    /**
     * Gives list of current tasks in output message format.
     *
     * @param keyword String of keyword.
     * @return Message to be shown to user.
     */
    public static String listFilteredTasks(String keyword) {
        return Duke.taskList.getMatchingTasks(keyword);
    }

    /**
     * Gets the response string to be printed to the user after processing user input.
     *
     * @param userInput User input.
     * @return The response string.
     */
    protected String getResponse(String userInput) {
        if (userInput.trim().toLowerCase().equals("bye")) {
            Platform.exit();
        }
        String output = "";
        try {
            output = Parser.parseUserInput(userInput, Duke.taskList.getSize());
        } catch (RuntimeException e) {
            output = e.getMessage();
        } finally {
            return output;
        }
    }

}
