package duke.main;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import java.util.ArrayList;

public class UI {

    public String printLine() {
        return "______________________________________";
    }

    public String greetUser(String chatBotName) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo + "\n"
                + printLine() + "\n"
                + "Hello! I'm " + chatBotName + "! SUIIII!!!\n"
                + "What can I do for you?";
    }


    /**
     * Displays the task list to the user.
     *
     * @param list The list of tasks.
     * @param numOfTasks The number of tasks in the list.
     * @return A string representation of the task list.
     */
    public String displayList(ArrayList<Task> list, int numOfTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(printLine()).append("\n");
        sb.append("Here are the tasks in your list. Finish them all so we can beat Messi.\n");
        for (int i = 0; i < numOfTasks; i++) {
            sb.append((i + 1) + ". " + list.get(i)).append("\n");
        }
        sb.append(printLine());
        return sb.toString();
    }

    /**
     * Displays errors to the user.
     *
     * @param e The exception containing the error message.
     * @return A string representation of the error message.
     */
    public String displayError(DukeException e) {
        return printLine() + "\n" + e.getMessage() + "\n" + printLine();
    }

    /**
     * Displays exit message to the user.
     *
     * @return A string representation of the exit message.
     */
    public String exit() {
        return printLine() + "\n" + "Bye. Hope to see you again in the Saudi League!" + "\n" + printLine();
    }

    /**
     * Informs the user that a task has been added and displays the updated number of tasks.
     *
     * @param taskName The name of the task added.
     * @param numOfTasks The updated number of tasks in the list.
     * @return A string representation of the add task message.
     */

    public String addTask(String taskName, int numOfTasks) {
        String message = numOfTasks != 1
                ? "Now you have " + numOfTasks + " tasks in your list, just like how I have 5 Ballon d'Ors."
                : "Now you have " + numOfTasks + " task in your list, just like how I have 5 Ballon d'Ors.";
        return printLine() + "\n"
                + "Got it. I've added the task:\n" + taskName + "\n"
                + message + "\n"
                + printLine();
    }

    /**
     * Informs the user that a task has been deleted and displays the updated number of tasks.
     *
     * @param taskName The name of the task deleted.
     * @param numOfTasks The updated number of tasks in the list.
     * @return A string representation of the delete task message.
     */
    public String deleteTask(String taskName, int numOfTasks) {
        return printLine() + "\n"
                + "Removed task:\n" + taskName + "\n"
                + "Now you have " + numOfTasks + " tasks in your list.\n"
                + printLine();
    }

    /**
     * Informs the user that a task has been marked as done.
     *
     * @param taskName The name of the task marked.
     * @return A string representation of the mark task message.
     */
    public String markTask(String taskName) {
        return printLine() + "\n"
                + "SIUUU! I've marked this task as done. We will make Saudi League number 1.\n [X] " + taskName + "\n"
                + printLine();
    }

    /**
     * Informs the user that a task has been unmarked.
     *
     * @param taskName The name of the task unmarked.
     * @return A string representation of the unmark task message.
     */
    public String unMarkTask(String taskName) {
        return printLine() + "\n"
                + "OK, I've marked this task as not done. Cheeky boy!!\n [ ] " + taskName + "\n"
                + printLine();
    }

    /**
     * Displays a list of tasks filtered by a keyword to the user.
     *
     * @param filteredList The list of tasks after applying the filter.
     * @param numOfTasks The number of tasks in the filtered list.
     * @return A string representation of the filtered task list.
     */
    protected String displayFilteredList(ArrayList<Task> filteredList, int numOfTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(printLine()).append("\n");
        if (numOfTasks == 0) {
            sb.append("There are no tasks matching your search :(");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < numOfTasks; i++) {
                sb.append((i + 1) + ". " + filteredList.get(i)).append("\n");
            }
        }
        sb.append(printLine());
        return sb.toString();
    }

    public String addNote(int index, String taskName) {
        StringBuilder sb = new StringBuilder();
        sb.append(printLine()).append("\n");
        sb.append("Added your descriptive note for task ");
        sb.append(index);
        sb.append(": " + taskName);
        sb.append("\n");
        sb.append(printLine()).append("\n");
        return sb.toString();
    }

    public String getDescription(String note) {
        StringBuilder sb = new StringBuilder();
        sb.append(printLine()).append("\n");
        if (note != null) {
            sb.append(note);
        } else {
            sb.append("You did not add a note for this task");
        }
        sb.append("\n");
        sb.append(printLine()).append("\n");
        return sb.toString();
    }
}
