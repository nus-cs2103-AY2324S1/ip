package duke.components;

import duke.tasks.Task;

/**
 * Handles interactions with the user.
 */
public class Ui {

    /**
     * Prints goodbye words to the user.
     */
    public String bye() {
        String bye = "໒(⊙ᴗ⊙)७ Signing off, see you later!";
        return bye;
    }

    /**
     * Prints the number of tasks the user has in their list.
     * Called to update the user when the number of tasks in the list changes.
     *
     * @param size the size of the list.
     */
    public String showNumberOfTasks(int size) {
        return size == 1
                ? "Now you have " + size + " task in the list!"
                : "Now you have " + size + " tasks in the list!";
    }

    /**
     * Prints when there are 0 tasks in the list, and list is the command.
     */
    public String showNoTasks() {
        return "(o´ω`o)ﾉ You have no upcoming tasks!";
    }

    /**
     * Prints when there are at least 1 task in the list, and list is the command.
     *
     * @param tasks tasks to be printed.
     */
    public String showTasks(String tasks) {
        String header = "(⇀‸↼‶)⊃━☆ﾟ.*･｡ﾟHere are your tasks for the day:\n";
        return header + tasks;
    }

    /**
     * Prints when the user adds a tasks successfully.
     *
     * @param task     task that was added.
     * @param listSize task list size.
     */
    public String showTaskAdded(Task task, int listSize) {
        String header = "(｀･ω･´)ﾉ New task added:\n" + task + "\n";
        String numberOfTasksLeft = this.showNumberOfTasks(listSize);
        return header + numberOfTasksLeft;
    }

    /**
     * Prints when the user deletes a task successfully.
     *
     * @param toRemove task to remove.
     * @param listSize task list size.
     */
    public String showDeleteTask(Task toRemove, int listSize) {
        String header = "ଘ(੭ˊᵕˋ)੭ Ok! I've removed this task:\n" + toRemove + "\n";
        String numberOfTasksLeft = this.showNumberOfTasks(listSize);
        return header + numberOfTasksLeft;
    }

    /**
     * Prints when the user attempts to mark a task as completed.
     * If task is not marked, success message is printed out. Else,
     * unsuccessful message printed.
     *
     * @param isMarked true if the task is already marked.
     * @param task     task to be marked.
     */
    public String showMarkTask(boolean isMarked, Task task) {
        if (!isMarked) {
            return "ଘ(੭ˊᵕˋ)੭ Yay! This task is completed:\n" + task.toString();
        } else {
            return "┐(´～｀)┌ This task is already marked as completed!";
        }
    }

    /**
     * Prints when the user attempts to unmark a task.
     * If task is marked, success message is printed out. Else,
     * an unsuccessful message is printed.
     *
     * @param isMarked true if the task is already marked.
     * @param task     task to be unmarked.
     */
    public String showUnMarkTask(boolean isMarked, Task task) {
        if (isMarked) {
            return "໒( ̿･ ᴥ ̿･ )ʋ All righty, I've marked this task as uncompleted:\n"
                    + task.toString();
        } else {
            return "┐(´～｀)┌ This task is already marked as uncompleted!";
        }
    }

    /**
     * Prints a custom error message for invalid datetime formats.
     */
    public String showInvalidDateFormat() {
        return "(・´з`・) Uh oh...dates must be of YYYY-MM-DD HH:mm format";
    }

    /**
     * Prints the given list of tasks, which contain tasks matching the
     * keyword specified by user.
     *
     * @param tasks String format of tasks that match keyword.
     */
    public String showMatches(String tasks) {
        if (!tasks.isEmpty()) {
            String header = "(⇀‸↼‶)⊃━☆ﾟ.*･｡ﾟ Here are the matching tasks in your list:\n";
            return header + tasks;
        } else {
            return "(・´з`・) Uh oh...there are no matching tasks!";
        }
    }
}
