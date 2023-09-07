package duke;

import duke.tasks.Task;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private String line = "--------------------------------------------------------------------";

    /**
     * Prints welcome words to the user.
     */
    public void greeting() {
        String intro = "(｡･o･｡)ﾉ Hey there! I'm BUTTER.\n" +
                "How can I help you today?\n";
        System.out.println(line + "\n" + intro + line);
    }

    /**
     * Prints goodbye words to the user.
     */
    public void bye() {
        String bye = "彡໒(⊙ᴗ⊙)७彡 Signing off, see you later!\n";
        System.out.println(bye + line);
    }

    /**
     * Prints the number of tasks the user has in their list.
     * Called to update the user when the number of tasks in the list changes.
     *
     * @param size the size of the list.
     */
    public void showNumberOfTasks(int size) {
        System.out.println(size == 1
                ? "Now you have " + size + " task in the list!\n" + line
                : "Now you have " + size + " tasks in the list!\n" + line);
    }

    /**
     * Prints when there are 0 tasks in the list, and list is the command.
     */
    public void showNoTasks() {
        System.out.println("(o´ω`o)ﾉ You have no upcoming tasks!\n" + line);
    }

    /**
     * Prints when there are at least 1 task in the list, and list is the command.
     *
     * @param tasks tasks to be printed.
     */
    public void showTasks(String tasks) {
        System.out.println("(⇀‸↼‶)⊃━☆ﾟ.*･｡ﾟ Here are your tasks for the day:");
        System.out.println(tasks + line);
    }

    /**
     * Prints when the user adds a tasks successfully.
     *
     * @param task     task that was added.
     * @param listSize task list size.
     */
    public void showTaskAdded(Task task, int listSize) {
        System.out.println("(｀･ω･´)ﾉ New task added:\n" + task);
        this.showNumberOfTasks(listSize);
    }

    /**
     * Prints when the user deletes a task successfully.
     *
     * @param toRemove task to remove.
     * @param listSize task list size.
     */
    public void showDeleteTask(Task toRemove, int listSize) {
        System.out.println("ଘ(੭ˊᵕˋ)੭ Ok! I've removed this task:");
        System.out.println(toRemove);
        this.showNumberOfTasks(listSize);
    }

    /**
     * Prints when the user attempts to mark a task as completed.
     * If task is not marked, success message is printed out. Else,
     * unsuccessful message printed.
     *
     * @param isMarked true if the task is already marked.
     * @param task     task to be marked.
     */
    public void showMarkTask(boolean isMarked, Task task) {
        if (!isMarked) {
            System.out.println("ଘ(੭ˊᵕˋ)੭ Yay! This task is completed:\n" + task.toString());
            System.out.println(line);
        } else {
            System.out.println("┐(´～｀)┌ This task is already marked as completed!");
            System.out.println(line);
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
    public void showUnMarkTask(boolean isMarked, Task task) {
        if (isMarked) {
            System.out.println("໒( ̿･ ᴥ ̿･ )ʋ All righty, I've marked this task as uncompleted:\n" +
                    task.toString());
            System.out.println(line);
        } else {
            System.out.println("┐(´～｀)┌ This task is already marked as uncompleted!");
            System.out.println(line);
        }
    }

    /**
     * Prints the inputted error message.
     *
     * @param message error message to be printed.
     */
    public void showError(String message) {
        System.out.println(message);
        System.out.println(line);
    }

    /**
     * Prints a custom error message for invalid datetime formats.
     */
    public void showInvalidDateFormat() {
        System.out.println("(・´з`・) Uh oh...dates must be of YYYY-MM-DD HH:mm format");
        System.out.println(line);
    }

    /**
     * Prints the given list of tasks, which contain tasks matching the
     * keyword specified by user.
     *
     * @param tasks String format of tasks that match keyword.
     */
    public void showMatches(String tasks) {
        if (!tasks.isEmpty()) {
            System.out.println("(⇀‸↼‶)⊃━☆ﾟ.*･｡ﾟ Here are the matching tasks in your list:");
            System.out.println(tasks + line);
        } else {
            System.out.println("(・´з`・) Uh oh...there are no matching tasks!");
            System.out.println(line);
        }
    }
}
