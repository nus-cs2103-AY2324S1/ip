package duke;

import java.util.List;

/**
 * The `Ui` class handles user interface interactions and provides methods
 * for displaying messages and reading user input.
 */
public class Ui {

    /**
     * A horizontal line used for separating messages.
     */
    public static String horizontalLine = "_".repeat(34) + "\n";

    /**
     * Displays a greeting message when the program starts.
     */
    public static String greet() {
        return (horizontalLine + "Hello! I'm Bot\n"
                + "What can I do for you?\n" + horizontalLine);
    }

    /**
     * Displays a farewell message when the program exits.
     */
    public static String sayBye() {
        return (horizontalLine
                + "Bye. Hope to see you again soon!\n" + horizontalLine);
    }

    /**
     * Displays search results based on a keyword in the task list.
     *
     * @param taskList The task list to search within.
     * @param keyword  The keyword to search for in task descriptions.
     * @return A formatted string containing matching tasks or appropriate messages.
     */
    public static String returnSearchResults(TaskList taskList, String keyword) {
        List<Task> allTasks = taskList.getTasks();
        StringBuilder resultBuilder = new StringBuilder();

        if (allTasks.isEmpty()) {
            resultBuilder.append(horizontalLine
                    + "You have no tasks in your list.\n"
                    + horizontalLine);
            return resultBuilder.toString();
        }
        TaskList filteredList = new TaskList();
        keyword = keyword.toLowerCase();
        for (Task task : allTasks) {
            if (task.getTask().toLowerCase().contains(keyword)) {
                filteredList.addTask(task);
            }
        }
        if (filteredList.getTasks().isEmpty()) {
            resultBuilder.append(horizontalLine
                    + "You have no matching tasks in your list.\n"
                    + horizontalLine);
            return resultBuilder.toString();
        }
        resultBuilder.append(horizontalLine).append("Here are the matching items in your list: \n\n");
        int count = 0;
        for (Task t : filteredList.getTasks()) {
            resultBuilder.append(++count).append(". ").append(t.toString()).append("\n");
        }
        resultBuilder.append("\n").append(horizontalLine);
        return resultBuilder.toString();
    }


}
