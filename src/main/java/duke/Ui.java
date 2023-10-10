package duke;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents the user interface for interacting with the chatbot.
 */
public class Ui {

    /**
     * Constructs a new UI instance with a scanner for user input.
     */
    public Ui() {
    }

    /**
     * Returns a greeting message.
     */
    public static String getGreeting() {
        return "Hello! I'm yourChatBot\n" +
                "What can I do for you?\n";
    }

    /**
     * Returns a message displaying the list of tasks in the task list.
     *
     * @param taskList The task list containing the tasks to be displayed.
     * @return A message displaying the list of tasks.
     */
    public static String getList(TaskList taskList) {
        StringBuilder todolistoutput = new StringBuilder();

        // Assertion: The taskList should not be null.
        assert taskList != null : "TaskList is null.";

        ArrayList<Task> tasks = taskList.getTasks();

        // Add a section for upcoming deadlines
        todolistoutput.append("Upcoming Deadlines:\n");

        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                LocalDate currentDate = LocalDate.now();
                LocalDate taskDate = deadline.getDate();

                // Check if currentDate and taskDate are not null
                System.out.println(taskDate);
                if (currentDate != null && taskDate != null) {
                    long daysUntilDeadline = ChronoUnit.DAYS.between(currentDate, taskDate);

                    // Display the task name and days until the deadline
                    todolistoutput.append(daysUntilDeadline).append(" days left - ").append(deadline.getDescription())
                            .append('\n');
                }
            }
        }

        // Add a separator between upcoming deadlines and the existing task list
        todolistoutput.append("\nTask List:\n");

        for (int i = 0; i < tasks.size(); i++) {
            todolistoutput.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }

        // Assertion: The output should not be null.
        assert todolistoutput.toString() != null : "List output is null.";

        return todolistoutput.toString();
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return A message indicating the task has been marked as done.
     */
    public static String getMarkedAsDone(Task task) {
        // Assertion: The task should not be null.
        assert task != null : "Task is null.";

        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Returns a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     * @return A message indicating the task has been marked as not done.
     */
    public static String getUnmarked(Task task) {
        // Assertion: The task should not be null.
        assert task != null : "Task is null.";

        return "OK, I've marked this task as not done yet:\n" + task + "\n";
    }

    /**
     * Returns a message indicating that a task has been removed.
     *
     * @param task       The task that has been removed.
     * @param totalTasks The total number of tasks remaining.
     * @return A message indicating the task has been removed.
     */
    public static String getRemoved(Task task, int totalTasks) {
        // Assertions: The task and totalTasks should not be null.
        assert task != null : "Task is null.";
        assert totalTasks >= 0 : "TotalTasks is negative.";

        return "Noted. I've removed this task:\n" + task + "\n" +
                "Now you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Returns an error message.
     *
     * @param errorMessage The error message to be displayed.
     * @return An error message.
     */
    public static String getError(String errorMessage) {
        // Assertion: The errorMessage should not be null.
        assert errorMessage != null : "Error message is null.";

        return errorMessage;
    }

    /**
     * Returns a confirmation message after adding a task.
     *
     * @param task       The task that has been added.
     * @param totalTasks The total number of tasks in the list.
     * @return A confirmation message.
     */
    public static String getAddConfirmation(Task task, int totalTasks) {
        // Assertions: The task and totalTasks should not be null.
        assert task != null : "Task is null.";
        assert totalTasks >= 0 : "TotalTasks is negative.";

        return "Got it. I've added this task:\n" + task + "\n" +
                "Now you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Returns a farewell message.
     *
     * @return A farewell message.
     */
    public static String getByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a list of tasks that match the given keyword to the user.
     *
     * @param matchingTasks The list of tasks that match the keyword.
     * @return A list of matching tasks.
     */
    public static String getMatchingTasks(ArrayList<Task> matchingTasks) {
        StringBuilder matchingTasksOutput = new StringBuilder();

        // Assertion: The matchingTasks should not be null.
        assert matchingTasks != null : "Matching tasks list is null.";

        matchingTasksOutput.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            matchingTasksOutput.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
        }

        // Assertion: The output should not be null.
        assert matchingTasksOutput.toString() != null : "Matching tasks output is null.";

        return matchingTasksOutput.toString();
    }
}
