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
        return (horizontalLine + "Hello ┌[-.-]┘! I'm Bot.\n "
                + "What can I do for you?\n" + horizontalLine);
    }

    /**
     * Displays a farewell message when the program exits.
     */
    public static String sayBye() {
        return (horizontalLine
                + "Goodbye Human ┌[-.-]┘. I'll be here recharging, ready to assist you anytime!\n" + horizontalLine);
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

    /**
     * Returns help message to guide user in navigating the bot.
     *
     * @return String message to help user navigate the bot.
     */
    public static String showHelp() {
        return "Try entering...\n"
                + "1. **todo** \n- Create a todo task\n"
                + "2. **deadline [] /by []** \n- Create a deadline \n"
                + "3. **event [] /from [] /to []** \n- Create an event\n"
                + "4. **list** \n- List all your events\n"
                + "5. **mark [index]** \n- Mark done for task at index\n"
                + "5. **unmark [index]** \n- Unmark done for task at index\n"
                + "6. **delete [index]** \n- Delete task at index\n"
                + "7. **find [keyword]** \n- Find tasks with keyword\n"
                + "8. **help** \n- Show all commands\n"
                + "9. **bye** \n- Quit bot";
    }

}
