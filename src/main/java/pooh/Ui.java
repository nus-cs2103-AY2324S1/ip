package pooh;

public class Ui {

    /**
     * Returns the exit message when the user terminates the chatbot.
     *
     * @return The exit message.
     */
    public String getExitMessage() {
        return "How lucky I am to have something that makes saying goodbye so hard. Bye!";
    }

    /**
     * Returns the input message as a response.
     *
     * @param message The message to be displayed.
     * @return The input message as a response.
     */
    public String respond(String message) {
        return message;
    }

    /**
     * Formats and returns a string representation of the tasks in the provided TaskList.
     *
     * @param taskList The list of tasks.
     * @return A formatted string representation of tasks.
     */
    public String getTasksMessage(TaskList taskList) {
        StringBuilder todoListString = new StringBuilder();
        for (int i = 0; i < taskList.getSize(); i++) {
            String task;
            try {
                task = String.format("      %d. ", i + 1) + taskList.getTask(i) + "\n";
            } catch (InvalidTaskException e) {
                throw new RuntimeException(e);
            }
            todoListString.append(task);
        }
        return "Here are the tasks in your list:\n" + todoListString.toString().stripTrailing();
    }

    /**
     * Formats and returns a string of tasks from a given TaskList that match a specific keyword.
     *
     * @param taskList The TaskList containing tasks that match the keyword.
     * @return A formatted string representation of matching tasks.
     */
    public String getKeywordTasksMessage(TaskList taskList) {
        StringBuilder todoListString = new StringBuilder();
        for (int i = 0; i < taskList.getSize(); i++) {
            String task;
            try {
                task = String.format("      %d. ", i + 1) + taskList.getTask(i) + "\n";
            } catch (InvalidTaskException e) {
                throw new RuntimeException(e);
            }
            todoListString.append(task);
        }
        return "Here are the matching tasks in your list:" + todoListString.toString().stripTrailing();
    }

    /**
     * Returns a message indicating no tasks matched the specified keyword.
     *
     * @return Message indicating no matching tasks found.
     */
    public String getNoKeywordTasksFoundMessage() {
        return "No matching tasks found.";
    }

    /**
     * Returns a message indicating a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return Message indicating task marked as done.
     */
    public String getTaskDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n      " + task;
    }

    /**
     * Returns a message indicating a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     * @return Message indicating task marked as not done.
     */
    public String getTaskUndoneMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n      " + task;
    }

    /**
     * Returns a message indicating a task has been added to the TaskList.
     *
     * @param taskList The updated TaskList.
     * @param task     The task that has been added.
     * @return Message indicating task added.
     */
    public String getAddTaskMessage(TaskList taskList, Task task) {
        return String.format("      Got it. I've added this task:\n          %s\n      Now you have " +
                "%d tasks in the list", task, taskList.getSize());
    }

    /**
     * Returns a message indicating a task has been removed from the TaskList.
     *
     * @param taskList The updated TaskList.
     * @param task     The task that has been removed.
     * @return Message indicating task removed.
     */
    public String getDeleteTaskMessage(TaskList taskList, Task task) {
        return String.format("Noted. I've removed this task:\n          %s\n      Now you have" +
                " %d tasks in the list", task, taskList.getSize());
    }

}
