package duke;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user interactions through user interface.
 */
public class Ui {

    String logo = "   / \\__\n"
            + "  (    @\\___\n"
            + "  /          O\n"
            + " /   (_____/\n"
            + "/_____/   \n";

    /**
     * Initialises new Ui object.
     */
    public Ui() {
    }

    /**
     * Reads user's input/command.
     *
     * @return String representing user command.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Prints line divider.
     */
    public void showLine() {
        System.out.println("________________________________________");
    }

    /**
     * Prints welcome message when user begins running application.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello I'm Barkley\n"+logo);
        System.out.println("Howl can I help you?");
        showLine();
    }

    /**
     * Prints goodbye message when user exits application.
     */
    public void showExit() {
        System.out.println("Goodbye! Have a paw-some day :-) \n" + logo);
    }

    /**
     * Prints exception message.
     *
     * @param e Exception that has arisen.
     */
    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints error message for when date/time is not written correctly.
     */
    public void showDateTimeParseError() {
        System.out.println("Error! Date/time should be in 'dd-mm-yyy hh:mm' format");
    }

    /**
     * Prints message to inform user that a task has been added.
     *
     * @param task Task to be added.
     * @param taskListLength Number of tasks that exist.
     */
    public void showAdd(Task task, int taskListLength) {
        System.out.println("Woof luck with your new task: \n" + task.toString());
        System.out.println("You now have " + taskListLength + " tasks in the list");
    }

    /**
     * Prints message informing user that a task has been deleted.
     *
     * @param task Task to be deleted.
     * @param taskListLength Number of tasks that exist.
     */
    public void showDelete(Task task, int taskListLength) {
        System.out.println("Okay! Another dog-gone task down:  \n" + task.toString());
        System.out.println("You now have " + taskListLength + " tasks in the list");
    }

    /**
     * Prints message to inform user that a task has been marked as done.
     *
     * @param task Task to be marked.
     */
    public void showMark(Task task) {
        System.out.println("Furtastic job completing this task: \n" + task.toString());
    }

    /**
     * Prints message to inform user that a task has been marked as undone.
     *
     * @param task Task to be marked.
     */
    public void showUnmark(Task task) {
        System.out.println("That's ruff! I've unmarked this task:  \n" + task.toString());
    }

    /**
     * Prints all existing tasks.
     *
     * @param taskList List of tasks to be displayed.
     */
    public void showList(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
    }

    /**
     * Prints out list of tasks containing a given keyword.
     *
     * @param taskList Initial list of tasks to be filtered.
     * @param keyword String keyword used to filter tasks.
     */
    public void showFindCommandList(ArrayList<Task> taskList, String keyword) {
        if (taskList.isEmpty()) {
           System.out.println("There are no tasks containing: " + keyword);
        } else {
            System.out.println("The following tasks contain '" + keyword + "' :");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i).toString());
            }
        }
    }

}
