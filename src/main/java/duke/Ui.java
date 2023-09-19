package duke;

import duke.task.Task;
import duke.task.TaskList;

/**
 * This class deals with interactions with the user.
 */
public class Ui {
    /**
     * Displays a default start message when the program starts.
     *
     * @return Start message.
     */
    public String startMessage() {
        return "I'm Boo, nice to meet you! "
                + "You can start adding your tasks :-)\n\n"
                + "Type 'help' to see the list of available commands.";
    }

    /**
     * Displays the default end message when the user exits the program.
     *
     * @return End message.
     */
    public String endMessage() {
        return "Bye for now, hope to see you soon!";
    }

    /**
     * Displays the default help message to show all available commands.
     *
     * @return Help message.
     */
    public String helpMessage() {
        StringBuilder helpMessage = new StringBuilder("Available commands:\n");
        helpMessage.append("1. list - Lists all your tasks.\n");
        helpMessage.append("2. mark <task number> - Marks a task as completed.\n");
        helpMessage.append("3. unmark <task number> - Unmarks a task.\n");
        helpMessage.append("4. todo <description> - Adds a todo task.\n");
        helpMessage.append("5. deadline <description> /by <date> (in dd/MM/yyyy HHmm) - "
                + "Adds a deadline task.\n");
        helpMessage.append("6. event <description> /from <start date> /to <end date> - "
                + "Adds an event task.\n");
        helpMessage.append("7. delete <task number> - Deletes the task listed at that "
                + "number.\n");
        helpMessage.append("8. find <keyword> - Finds tasks containing the keyword.\n");
        helpMessage.append("9. status - Shows your completed tasks and progress statistics.\n");
        helpMessage.append("10. bye - Exits the application.\n");

        return helpMessage.toString();
    }

    /**
     * Displays a message when a task is added to the task list.
     *
     * @param task The task that is added.
     * @param totalTasks The total number of tasks in the list after adding.
     * @return String representation of a task being added.
     */
    public String showTaskAdded(Task task, int totalTasks) {
        return "Got it. I've added this task:\n  " + task.toString()
                + "\nNow you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Displays a message when a task is deleted from the task list.
     *
     * @param task The task that is deleted.
     * @param totalTasks The total number of tasks in the list after deleting.
     * @return String representation of a task being deleted.
     */
    public String showTaskDeleted(Task task, int totalTasks) {
        return "Noted. I've removed this task:\n  " + task.toString()
                + "\nNow you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that is marked as done.
     * @return String representation of a task being marked.
     */
    public String showTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n" + "[X] "
                + task.getDescription();
    }

    /**
     * Displays a message when a task is unmarked.
     *
     * @param task The task that is unmarked.
     * @return String representation of a task being unmarked.
     */
    public String showTaskUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n" + "[ ] "
                + task.getDescription();
    }

    /**
     * Displays the user's task list.
     *
     * @param taskList The task list to be displayed.
     * @return String representation of the user's list of task/s.
     */
    public String showList(TaskList taskList) {
        assert taskList != null : "taskList should not be null";
        StringBuilder list = new StringBuilder();
        list.append("Here are the tasks in your list:\n");

        for (int i = 1; i <= taskList.getSize(); i++) {
            // Adding toString() to use the overridden one in duke.task.Task, etc.
            list.append(i)
                    .append(". ")
                    .append(taskList.getTask(i - 1).toString())
                    .append("\n");;
        }

        if (taskList.getSize() == 0) {
            return "Oops! Your task list is empty.";
        }

        return list.toString();
    }

    /**
     * Displays the list of matching task from the user's task list.
     *
     * @param matchingTaskList The task list to be displayed.
     * @return String representation of the list of matching task from the user's task list.
     */
    public String showMatchingList(TaskList matchingTaskList) {
        assert matchingTaskList != null : "taskList should not be null";
        StringBuilder matchingList = new StringBuilder();
        matchingList.append("Here are the matching tasks in your list:\n");

        for (int i = 1; i <= matchingTaskList.getSize(); i++) {
            // Adding toString() to use the overridden one in duke.task.Task, etc.
            matchingList.append(i)
                    .append(". ")
                    .append(matchingTaskList.getTask(i - 1).toString())
                    .append("\n");
        }

        if (matchingTaskList.getSize() == 0) {
            return "Oops! There is no matching task.";
        }

        return matchingList.toString();
    }

    /**
     * Displays the list of completed task from the user's task list.
     *
     * @param completedTaskList The task list to be displayed.
     * @paran percentageCompleted Percentage of completed tasks.
     * @return String representation of the list of completed task from the user's task list.
     */
    public String showCompletedList(TaskList completedTaskList, double percentageCompleted) {
        assert completedTaskList != null : "taskList should not be null";
        StringBuilder completedList = new StringBuilder();
        completedList.append("You have completed " + completedTaskList.getSize()
                + " tasks so far, good job! :-)\n\n");
        completedList.append("Here are the completed tasks:\n");

        for (int i = 1; i <= completedTaskList.getSize(); i++) {
            // Adding toString() to use the overridden one in duke.task.Task, etc.
            completedList.append(i)
                    .append(". ")
                    .append(completedTaskList.getTask(i - 1).toString())
                    .append("\n");
        }

        if (completedTaskList.getSize() == 0) {
            return "Hang in there! You haven't completed any tasks, but it's alright :-)";
        }

        completedList.append("\nProgress: ")
                .append(String.format("%.2f", percentageCompleted))
                .append(" % completed, keep it up!");

        return completedList.toString();
    }

}


