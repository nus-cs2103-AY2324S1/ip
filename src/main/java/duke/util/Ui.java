package duke.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Ui class handles the interaction with the user.
 * It contains methods to print messages to the user.
 * It also contains methods to read user input.
 */
public class Ui {

    protected static final String NAME = "Task404Bot";

    /**
     * Shows the welcome message to the user.
     *
     * @return the response message to the user.
     */
    public Response showWelcome() {
        return new Response(String.format("Greetings! I am %s. How can I assist you?", NAME));
    }

    /**
     * Shows the loading error message to the user, when the file loads unsuccessfully.
     *
     * @return the response message to the user.
     */
    public Response showLoadingError() {
        return new Response("OOPS!!! Something terrible happened to the data file.\n\n"
                + "Don't worry I will clean up the mess!", true);
    }

    /**
     * Shows the error message to the user.
     *
     * @param message the error message.
     * @return the response message to the user.
     */
    public Response showError(String message) {
        return new Response(String.format("%s\n", message), true);
    }

    /**
     * Shows the exit message to the user.
     * Closes the scanner.
     *
     * @return the response message to the user.
     */
    public Response showExit() {
        return new Response("Bye. Hope to see you again soon!", false, true);
    }

    /**
     * Shows the added task message to the user, that contains the task
     * detail and number of tasks in the task list.
     *
     * @param task the task to be added.
     * @param taskListSize the number of tasks in the task list.
     * @return the response message to the user.
     */
    public Response showAddTask(Task task, int taskListSize) {
        assert task != null : "Task should not be null";

        return new Response(String.format("Awesome! I've added the following task:\n"
                           + "  %s\n"
                           + "Now you have %d tasks.\n",
                task, taskListSize));
    }

    /**
     * Shows the delete task message to the user, that contains the task
     * detail and number of tasks in the task list.
     *
     * @param removedTask the task to be deleted.
     * @param taskListSize the number of tasks in the task list.
     * @return the response message to the user.
     */
    public Response showDeleteTask(Task removedTask, int taskListSize) {
        assert removedTask != null : "Removed task should not be null";

        return new Response(String.format("Noted. I've removed this task:\n"
                           + "  %s\n"
                           + "Now you have %d tasks in the list.\n",
                 removedTask, taskListSize));
    }

    /**
     * Shows the mark or unmark task message to the user, that contains the task detail.
     *
     * @param isMark whether to mark or unmark the task.
     * @param task the task to be marked.
     * @return the response message to the user.
     */
    public Response showMarkTask(boolean isMark, String task) {
        String message = isMark
                ? "Nice! I've marked this task as done:"
                : "OK, I've unmarked this task:";
        return new Response(String.format("%s\n  %s\n", message, task));
    }

    /**
     * Shows the manipulating all task messages to the user.
     *
     * @param keyword the keyword of the command.
     * @return the response message to the user.
     */
    public Response showManipulateAllTask(String keyword) {
        return new Response(String.format("Noted. I will %s all tasks.\n", keyword));
    }

    /**
     * Shows the list task message to the user,
     * and lists all the tasks in the task list.
     *
     * @param tasks the string representation of the tasks in the task list.
     * @return the response message to the user.
     */
    public Response showListTask(String[] tasks) {
        assert tasks != null : "Tasks should not be null";

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Here are the tasks in your list:\n");
        IntStream.range(0, tasks.length)
                 .forEach(i ->
                        strBuilder.append(String.format("  %d. %s\n", i + 1, tasks[i])));
        return new Response(strBuilder.toString());
    }

    /**
     * Shows the print date task message to the user,
     * and lists all the tasks that are happening on the specified date.
     *
     * @param tasksOnDate the string representation of the tasks happening on the specified date.
     * @param date the String representation of the specified date.
     * @return the response message to the user.
     */
    public Response showPrintDateTask(String[] tasksOnDate, String date) {
        assert tasksOnDate != null : "Tasks on date should not be null";

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(String.format("Here are the %d tasks happening on %s:\n",
                tasksOnDate.length, date));
        Arrays.stream(tasksOnDate)
              .forEach(task -> strBuilder.append(String.format("  %s\n", task)));
        return new Response(strBuilder.toString());
    }

    /**
     * Shows the find task message to the user,
     * and lists all the tasks that contain the task keyword.
     *
     * @param tasksFound the array containing string representation
     *                   of the tasks containing the task keyword.
     * @param indices the array containing string representation for the
     *                indices of the tasks in tasksFound.
     * @return the response message to the user.
     */
    public Response showFindTask(String[] tasksFound, String[] indices) {
        assert tasksFound != null : "Tasks found should not be null";
        assert indices != null : "Indices should not be null";

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Here are the matching tasks in your list:\n");
        IntStream.range(0, tasksFound.length)
                 .forEach(i -> strBuilder.append(String.format("%s.%s\n",
                         indices[i],
                         tasksFound[i])));
        return new Response(strBuilder.toString());
    }

    /**
     * Connects two Strings, such that it is uniform with formatting
     * of the output display messages (e.g., indentations).
     *
     * @param lines strings to be connected in order.
     * @return the connected string.
     */
    public static String connectLines(String... lines) {
        assert lines != null : "Lines should not be null";
        assert lines.length > 1 : "Lines should have at least 2 elements";

        StringBuilder strBuilder = new StringBuilder();
        Arrays.stream(lines)
              .forEach(line ->
                      strBuilder.append(String.format("%s\n", line)));
        return strBuilder.substring(0, strBuilder.length() - 1);
    }

    /**
     * Shows the loading message to the user.
     * @param isLoadDefault whether to load the default file.
     * @param fileName      the fileName.
     * @return the response message to the user.
     */
    public Response showLoad(boolean isLoadDefault, String fileName) {
        return new Response(String.format("Loading task list from %s...\n\ndone!\n",
                              isLoadDefault ? "default file" : fileName));
    }

    /**
     * Shows the sorting message to the user.
     *
     * @param isDefaultSort whether to sort by default.
     * @param sortType     the sort type.
     * @return the response message to the user.
     */
    public Response showSort(boolean isDefaultSort, String sortType) {
        return new Response(String.format("Sorting task list by %s...\n\ndone!\n",
                isDefaultSort ? "name" : sortType));
    }

    /**
     * Shows the added or removed alias message to the user.
     *
     * @param isAddAlias whether to add or remove alias.
     * @param aliasFrom  the word to be used as alias.
     * @param aliasTo    the word to be replaced by alias.
     * @return the response message to the user.
     */
    public Response showAlias(boolean isAddAlias, String aliasFrom, String aliasTo) {
        String message = isAddAlias
                ? "Nice! I've added this alias: "
                : "OK, I've removed this alias: ";
        message += aliasFrom + " -> " + aliasTo + "\n";
        return new Response(message);
    }

    /**
     * Shows the list of aliases to the user.
     *
     * @return the response message to the user.
     */
    public Response showAliasList() {
        List<String> aliasList = Alias.saveAliasFormat();
        if (aliasList.isEmpty()) {
            return new Response("There is no alias yet.\n");
        }

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Here are the current aliases:\n");
        for (String alias : aliasList) {
            strBuilder.append(String.format("  %s", alias));
        }
        return new Response(strBuilder.toString());
    }

    /**
     * Shows the general help message to the user.
     *
     * @return the response message to the user.
     */
    public Response showGeneralHelp() {
        return new Response(Help.generalHelp());
    }

    /**
     * Shows the help message for a particular command to the user.
     *
     * @param key The keyword of the command that the user is requesting help.
     * @return   The response message to the user.
     */
    public Response showHelp(Keyword key) throws DukeException {
        switch (key) {
        case BYE:
            return new Response(Help.byeHelp());
        case SORT:
            return new Response(Help.sortHelp());
        case ALIAS:
            return new Response(Help.aliasHelp());
        case LOAD:
            return new Response(Help.loadHelp());
        case LIST:
            return new Response(Help.listHelp());
        case MARK:
            return new Response(Help.markHelp());
        case UNMARK:
            return new Response(Help.unmarkHelp());
        case DELETE:
            return new Response(Help.deleteHelp());
        case TODO:
            return new Response(Help.todoHelp());
        case FIND:
            return new Response(Help.findHelp());
        case PRINT_DATE:
            return new Response(Help.printDateHelp());
        case EVENT:
            return new Response(Help.eventHelp());
        case DEADLINE:
            return new Response(Help.deadlineHelp());
        case HELP:
            throw new DukeException("Umm... I think you are asking for help for the help command.");
        default:
            throw new DukeException("OOPS!!! I might not be able to provide help for the command.");
        }
    }
}
