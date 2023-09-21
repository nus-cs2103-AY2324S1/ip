package duke.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Handles user interface interactions and displays messages to the user.
 */
public class Ui {

    /**
     * Displays a message to the user with a framed border.
     *
     * @param messages The messages to be displayed.
     * @return A formatted message.
     */
    public String showToUser(String... messages) {
        String result = "";
        for (String message : messages) {
            result = result + message + "\n";
        }
        return result;
    }

    /**
     * Displays an exit message to the user.
     *
     * @return A farewell message.
     */
    public String showExitMessage() {
        return showToUser(
                "Bye. Hope to see you again soon!"
        );
    }

    /**
     * Displays a help message to the user, listing available commands.
     *
     * @return A list of available commands.
     */
    public String showHelpMessage() {
        return showToUser(
                "Here are the available commands:",
                "1. bye - Exit the program",
                "2. list - List all tasks",
                "3. mark <taskNumber> - Mark a task as done",
                "4. unmark <taskNumber> - Mark a task as undone",
                "5. delete <taskNumber> - Delete a task",
                "6. todo <description> - Add a new todo task",
                "7. deadline <description> /by <dueDate> - Add a new deadline task",
                "8. event <description> /from <startDate> /to <endDate> - Add a new event task",
                "9. help - Displays the available commands",
                "10. find <keyword> - Search for tasks with a keyword"
        );
    }

    /**
     * Displays a message indicating the successful addition of a Todo task.
     *
     * @param task The Todo task added.
     * @param size The updated size of the TaskList.
     * @return A confirmation message.
     */
    public String showTodoMessage(Task task, int size) {
        return showToUser(
                "Got it. I've added this task:",
                 task.toString(),
                "Now you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list."
        );
    }

    /**
     * Displays a message indicating the successful addition of a Deadline task.
     *
     * @param task The Deadline task added.
     * @param size The updated size of the TaskList.
     * @return A confirmation message.
     */
    public String showDeadlineMessage(Task task, int size) {
        return showToUser(
                "Got it. I've added this deadline task:",
                task.toString(),
                "Now you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list."
        );
    }

    /**
     * Displays a message indicating the successful addition of an Event task.
     *
     * @param task The Event task added.
     * @param size The updated size of the TaskList.
     * @return A confirmation message.
     */
    public String showEventMessage(Task task, int size) {
        return showToUser(
                "Got it. I've added this event task:",
                task.toString(),
                "Now you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list."
        );
    }

    /**
     * Displays a message indicating the task is marked done.
     *
     * @param task The task that was marked done.
     * @return A confirmation message.
     */
    public String showMarkMessage(Task task) {
        return showToUser(
                "Nice! I've marked this task as done:",
                task.toString()
        );
    }

    /**
     * Displays a message indicating the task is marked undone.
     *
     * @param task The task that was marked undone.
     * @return A confirmation message.
     */
    public String showUnmarkMessage(Task task) {
        return showToUser(
                "OK, I've marked this task as not done yet:",
                task.toString()
        );
    }

    /**
     * Displays a message indicating the task is deleted.
     *
     * @param task The task that was deleted.
     * @param size The updated size of the TaskList.
     * @return A confirmation message.
     */
    public String showDeleteMessage(Task task, int size) {
        return showToUser(
                "Noted. I've removed this task:",
                task.toString(),
                "Now you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list."
        );
    }

    /**
     * Displays a message of the taskList indexed starting from 1.
     *
     * @param taskList The TaskList to display.
     * @return A list of tasks.
     */
    public String showListMessage(ArrayList<Task> taskList) {
        List<String> msg = IntStream.range(0, taskList.size())
                .mapToObj(i -> (i + 1) + ": " + taskList.get(i).toString())
                .collect(Collectors.toList());

        if (taskList.isEmpty()) {
            msg.add("You have no task currently.");
        }

        return showToUser(msg.toArray(new String[0]));
    }


    /**
     * Displays an error message to the user.
     *
     * @param e The DukeException representing the error.
     * @return An error message.
     */
    public String showError(DukeException e) {
        return showToUser(
                e.toString()
        );
    }

    /**
     * Displays a message of the filtered taskList indexed starting from 1.
     *
     * @param filteredTasks The filtered TaskList to display.
     * @return A list of filtered tasks.
     */
    public String showFilteredTasks(ArrayList<Task> filteredTasks) {
        List<String> msg = IntStream.range(0, filteredTasks.size())
                .mapToObj(i -> (i + 1) + ": " + filteredTasks.get(i).toString())
                .collect(Collectors.toList());

        if (msg.isEmpty()) {
            msg.add("You have no matching task.");
        }

        return showToUser(
                msg.toArray(new String[0])
        );
    }
}
