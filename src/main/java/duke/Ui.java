package duke;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Handles user interactions through user interface.
 */
public class Ui {

    private String logo = "   / \\__\n"
            + "  (    @\\___\n"
            + "  /                   O\n"
            + " /   (_____/\n"
            + "/_____/   \n";

    /**
     * Initialises new Ui object.
     */
    public Ui() {
    }

    /**
     * Prints welcome message when user begins running application.
     *
     * @return String message.
     */
    public String showWelcome() {
        return "Hello I'm Barkley\n" + logo + "Howl can I help you?";
    }

    /**
     * Prints goodbye message when user exits application.
     *
     * @return String message.
     */
    public String showExit() {
        return "Goodbye! Have a paw-some day :-) \n" + logo;
    }

    /**
     * Prints exception message.
     *
     * @param e Exception that has arisen.
     * @return String message.
     */
    public String showError(DukeException e) {
        return e.getMessage();
    }

    /**
     * Prints error message for when date/time is not written correctly.
     *
     * @return String message.
     */
    public String showDateTimeParseError() {
        return "Error! Date/time should be in 'dd-mm-yyy hh:mm' format";
    }

    /**
     * Prints message to inform user that a task has been added.
     *
     * @param task Task to be added.
     * @param taskListLength Number of tasks that exist.
     * @return String message.
     */
    public String showAdd(Task task, int taskListLength) {
        return "Woof luck with your new task: \n" + task.toString() + "\n" + "You now have "
                + taskListLength + " tasks in the list";
    }

    /**
     * Prints message informing user that a task has been deleted.
     *
     * @param task Task to be deleted.
     * @param taskListLength Number of tasks that exist.
     * @return String message.
     */
    public String showDelete(Task task, int taskListLength) {
        return "Okay! Another dog-gone task down:  \n" + task.toString() + "\n"
                + "You now have " + taskListLength + " tasks in the list";
    }

    /**
     * Prints message to inform user that a task has been marked as done.
     *
     * @param task Task to be marked.
     * @return String message.
     */
    public String showMark(Task task) {
        return "Furtastic job completing this task: \n" + task.toString();
    }

    /**
     * Prints message to inform user that a task has been marked as undone.
     *
     * @param task Task to be marked.
     * @return String message.
     */
    public String showUnmark(Task task) {
        return "That's ruff! I've unmarked this task:  \n" + task.toString();
    }

    /**
     * Prints all existing tasks.
     *
     * @param taskList List of tasks to be displayed.
     * @return String message.
     */
    public String showList(ArrayList<Task> taskList) {
        String listOfTasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            listOfTasks = listOfTasks + ((i + 1) + ". " + taskList.get(i).toString() + "\n");
        }
        return listOfTasks;
    }

    /**
     * Prints out list of tasks containing a given keyword.
     *
     * @param taskList Initial list of tasks to be filtered.
     * @param keyword String keyword used to filter tasks.
     * @return String message.
     */
    public String showFindCommandList(ArrayList<Task> taskList, String keyword) {
        if (taskList.isEmpty()) {
            return "There are no tasks containing: " + keyword;
        } else {
            String listOfTasks = "\n";
            for (int i = 0; i < taskList.size(); i++) {
                listOfTasks = listOfTasks + ((i + 1) + ". " + taskList.get(i).toString() + "\n");
            }
            return "The following tasks contain '" + keyword + "' :" + listOfTasks;
        }
    }

    /**
     * Prints message to inform user task has been updated.
     *
     * @param task Task that has been updated.
     * @return String message.
     */
    public String showUpdate(Task task) {
        return "The following task has been updated: \n" + task.toString();
    }

    /**
     * Prints message to inform user all tasks have been cleared.
     *
     * @return String message.
     */
    public String showClearedMessage() {
        return "Paw-fect! All tasks have been cleared.";
    }
}
