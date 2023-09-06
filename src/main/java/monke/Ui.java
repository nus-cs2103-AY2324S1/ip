package monke;

import monke.tasks.Task;

import java.util.List;

/**
 * The Ui class is responsible for handling user interface interactions.
 * It handles reading user input and displaying output.
 */
public class Ui {
    /**
     * Returns the welcome message to the user.
     *
     * @return Welcome message as a string.
     */
    public String getWelcomeMessage() {
        return "Hello, I'm Monke. OOGA BOOGA!\nWhat can I do for you?";
    }

    /**
     * Returns list of tasks to the user as a string.
     *
     * @param taskList The list of tasks as a string.
     * @return list of tasks as a string.
     */
    public String getListString(TaskList taskList) {
        List<Task> tasks = taskList.toList();
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listString.append(i + 1);
            listString.append(". ");
            listString.append(tasks.get(i));
            listString.append("\n");
        }
        return listString.toString();
    }

    /**
     * Shows a message confirming the addition of a task to task list.
     *
     * @param task      The task that was added.
     * @param tasksSize The total number of tasks in the list after the addition.
     * @return
     */
    public String showAddTask(Task task, int tasksSize) {
        return "Got it. I've added this task:\n" +
                task +
                "\nNow you have " + tasksSize + " tasks in the list.\n";
    }
}
