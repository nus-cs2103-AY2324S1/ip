package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Handles user interface interactions and displays messages to the user.
 */
public class Ui {

    private static final int BOX_WIDTH = 85;

    /**
     * Displays a message to the user with a framed border.
     *
     * @param messages The messages to be displayed.
     */
    public void showToUser(String... messages) {
        System.out.println("+" + "-".repeat(BOX_WIDTH - 2) + "+");

        for (String message : messages) {
            String[] lines = message.split("\n");
            for (String line : lines) {
                StringBuilder paddedLine = new StringBuilder("| ");
                paddedLine.append(line);
                int paddingLength = BOX_WIDTH - 4 - line.length();
                if (paddingLength > 0) {
                    paddedLine.append(" ".repeat(paddingLength));
                }
                paddedLine.append(" |");
                System.out.println(paddedLine);
            }
        }

        System.out.println("+" + "-".repeat(BOX_WIDTH - 2) + "+");
    }

    /**
     * Displays a greeting message to the user.
     */
    public void showGreetMessage() {
        showToUser(
                "Hello! I'm Atlas",
                "What can I do for you?",
                "Type 'help' to view available commands"
        );
    }

    /**
     * Displays an exit message to the user.
     */
    public void showExitMessage() {
        showToUser(
                "Bye. Hope to see you again soon!"
        );
    }

    /**
     * Displays a help message to the user, listing available commands.
     */
    public void showHelpMessage() {
        showToUser(
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
     */
    public void showTodoMessage(Task task, int size) {
        showToUser(
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
     */
    public void showDeadlineMessage(Task task, int size) {
        showToUser(
                "Got it. I've added this deadline task:",
                task.toString(),
                "Now you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list."
        );
    }

    /**
     * Displays a message indicating the successful addition of a Event task.
     *
     * @param task The Event task added.
     * @param size The updated size of the TaskList.
     */
    public void showEventMessage(Task task, int size) {
        showToUser(
                "Got it. I've added this event task:",
                task.toString(),
                "Now you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list."
        );
    }

    /**
     * Displays a message indicating the task is marked done.
     *
     * @param task The task that was marked done.
     */
    public void showMarkMessage(Task task) {
        showToUser(
                "Nice! I've marked this task as done:",
                task.toString()
        );
    }

    /**
     * Displays a message indicating the task is marked undone.
     *
     * @param task The task that was marked undone.
     */
    public void showUnmarkMessage(Task task) {
        showToUser(
                "OK, I've marked this task as not done yet:",
                task.toString()
        );
    }

    /**
     * Displays a message indicating the task is deleted.
     *
     * @param task The task that was deleted.
     * @param size The updated size of the TaskList.
     */
    public void showDeleteMessage(Task task, int size) {
        showToUser(
                "Noted. I've removed this task:",
                task.toString(),
                "Now you have " + size + " task" + (size <= 1 ? "" : "s") + " in the list."
        );
    }

    /**
     * Displays a message of the taskList indexed starting from 1.
     *
     * @param taskList The TaskList to display.
     */
    public void showListMessage(ArrayList<Task> taskList) {
        ArrayList<String> msg = new ArrayList<>();
        int num = 1;
        for (Task task : taskList) {
            msg.add(num + ": " + task);
            num ++;
        }
        if (taskList.size() == 0) {
            msg.add("You have no task currently.");
        }
        showToUser(
                msg.toArray(new String[0])
        );
    }

    /**
     * Displays an error message to the user.
     *
     * @param e The DukeException representing the error.
     */
    public void showError(DukeException e) {
        showToUser(
                e.toString()
        );
    }

    /**
     * Displays a message of the filtered taskList indexed starting from 1.
     *
     * @param filteredTasks The filtered TaskList to display.
     */
    public void showFilteredTasks(ArrayList<Task> filteredTasks) {
        ArrayList<String> msg = new ArrayList<>();
        int num = 1;
        for (Task task : filteredTasks) {
            msg.add(num + ": " + task);
            num++;
        }
        if (filteredTasks.size() == 0) {
            msg.add("You have no matching task.");
        }
        showToUser(
                msg.toArray(new String[0])
        );
    }
}
