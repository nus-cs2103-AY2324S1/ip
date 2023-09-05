package anto;

import java.util.ArrayList;

/**
 * Ui handles all responses.
 */
public class Ui {

    private TaskList taskList;

    /**
     * Creates a Ui class.
     */
    public Ui() {}

    /**
     * Sets Ui's taskList to the given one.
     *
     * @param taskList TaskList to set it to.
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns string that greets the user.
     *
     * @return String that greets the user.
     */
    public String greet() {
        return "Hello I'm Anto\n"
                + "What can I do for you?";
    }

    /**
     * Says bye to the user.
     *
     * @return String that says bye to the user.
     */
    public String sayBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns string with current list of tasks.
     *
     * @return String with current list of tasks.
     */
    public String printList() {
        ArrayList<Task> storage = taskList.getTaskArrayList();
        int length = taskList.getLength();

        ArrayList<String> stringOutputArr = new ArrayList<>();
        stringOutputArr.add("Here are the tasks in your list:");

        for (int i = 0; i < length; i++) {
            stringOutputArr.add(String.format("%d. %s",
                    i + 1, storage.get(i)));
        }

        return String.join("\n", stringOutputArr);
    }

    /**
     * Returns String with task marked as done.
     *
     * @param index Index of task marked as done.
     * @return String with information of task marked as done.
     */
    public String printMarkAsDone(int index) {
        ArrayList<Task> storage = taskList.getTaskArrayList();

        return String.format("Nice! I've marked this task as done:\n"
                        + "%s",
                storage.get(index));
    }

    /**
     * Returns string with message for task unmarked.
     *
     * @param index Index of task unmarked.
     * @return String with message for task unmarked.
     */
    public String printUnmark(int index) {
        ArrayList<Task> storage = taskList.getTaskArrayList();

        return String.format("Okay, I've marked this task as not done yet:\n"
                        + "%s",
                storage.get(index));
    }

    /**
     * Returns string of message of task added.
     *
     * @param task Task that was added.
     * @return String with message of task added.
     */
    public String printAdded(Task task) {
        return String.format("Got it. I've added this task:\n"
                        + "%s\n"
                        + "Now you have %d tasks in the list.",
                task,
                taskList.getLength());
    }

    /**
     * Returns string of message for exception.
     *
     * @param e AntoException that was thrown.
     * @return String with message of AntoException.
     */
    public String printError(AntoException e) {
        return e.getMessage();
    }

    /**
     * Returns message of task deleted.
     *
     * @param task Task that was deleted.
     * @return String with message of task deleted.
     */
    public String printDelete(Task task) {
        return String.format("Noted. I've removed this task:\n"
                        + "%s\n"
                        + "Now you have %d tasks in the list.",
                task,
                taskList.getLength());
    }

    /**
     * Returns string with message of no tasks.
     *
     * @return String with message of no tasks.
     */
    public String printNoTasks() {
        return "Sorry, you currently have no tasks on the list.";
    }

    /**
     * Returns message that there is no saved file.
     *
     * @return String with message that there is no saved file.
     */
    public String printNoSavedFile() {
        return "No saved file found.";
    }

    /**
     * Returns message with saved file information.
     *
     * @param taskArrayList Current array list of tasks.
     * @return Message with saved file information.
     */
    public String printSavedFileFound(ArrayList<Task> taskArrayList) {
        ArrayList<String> stringOutputArr = new ArrayList<>();
        stringOutputArr.add("Saved file found.");
        stringOutputArr.add("Here are the tasks in your list:");

        int length = taskArrayList.size();
        for (int i = 0; i < length; i++) {
            stringOutputArr.add(String.format("%d. %s",
                    i + 1, taskArrayList.get(i)));
        }

        return String.join("\n", stringOutputArr);
    }

    /**
     * Prints list of tasks found.
     *
     * @param foundTasks List of tasks found.
     * @return String with list of tasks found.
     */
    public String printFoundTasks(ArrayList<Task> foundTasks) {
        if (foundTasks.size() == 0) {
            return "There are no matching tasks in your list.";
        }

        ArrayList<String> stringOutputArr = new ArrayList<>();
        stringOutputArr.add("Here are the matching tasks in your list:");

        int length = foundTasks.size();

        for (int i = 0; i < length; i++) {
            stringOutputArr.add(String.format("%d. %s",
                    i + 1, foundTasks.get(i)));
        }

        return String.join("\n", stringOutputArr);
    }
}
