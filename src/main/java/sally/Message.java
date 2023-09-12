package sally;

/**
 * Represents a message to be displayed to the user.
 */
public class Message {

    public Message() {
    }

    /**
     * Returns a string indicating the task has been deleted.
     *
     * @param task The task that has been deleted.
     * @param size The size of the TaskList after deletion.
     * @return A string indicating the task has been deleted.
     */
    public String deleteMessage(Task task, int size) {
        return "Noted. I've removed this task:" + "\n" + task + "\n" + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Returns a string listing out all the tasks in the TaskList.
     *
     * @param tasks The tasks currently saved.
     * @return A string indicating all the tasks in the TaskList.
     */
    public String listMessage(TaskList tasks) {
        String res = "My list:";
        for (int i = 0; i < tasks.getSize(); i++) {
            res += "\n" + (i + 1) + "." + tasks.getTask(i);
        }
        return res;
    }

    /**
     * Returns a string indicating the task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return A string indicating the task has been marked as done.
     */
    public String markMessage(Task task) {
        return "Nice! I've marked this task as done:" + "\n" + task;
    }

    /**
     * Returns a string indicating the task has been marked as not done yet.
     *
     * @param task The task that has been marked as not done yet.
     * @return A string indicating the task has been marked as not done yet.
     */
    public String unmarkMessage(Task task) {
        return "Ok, I've marked this task as not done yet:" + "\n" + task;
    }

    /**
     * Returns a string indicating the task has been added to the TaskList.
     *
     * @param task The task that has been added to the TaskList.
     * @param size The size of the TaskList after adding the task.
     * @return A string indicating the task has been added to the TaskList.
     */
    public String addMessage(Task task, int size) {
        return "Added to My List: " + "\n" + task + "\n" + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Returns a string indicating the list of tasks that contain the keyword.
     *
     * @param tasks The list of tasks saved.
     * @return A string indicating the list of tasks that contain the keyword.
     */
    public String findMessage(TaskList tasks) {
        String res = "Here are the matching tasks in your list:";
        for (int i = 0; i < tasks.getSize(); i++) {
            res += "\n" + (i + 1) + "." + tasks.getTask(i);
        }
        return res;
    }
}
