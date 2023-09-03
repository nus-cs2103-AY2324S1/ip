package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * The Ui class deals wuthe interactions with the user.
 *
 * @author Inez Kok
 */
public class Ui {
    private static final String NAME = "Beep Boop Bot";
    private StringBuilder output;

    /**
     * The constructor for a Ui.
     */
    public Ui() {
        this.output = new StringBuilder();
    }

    public void resetOutput() {
        output.setLength(0);
    }

    public String getOutput() {
        return output.toString();
    }

    public void appendOutput(String str) {
        output.append(str);
    }

    public void printMessage(String message) {
        appendOutput(message);
    }

    /**
     * This method is used to print the greeting message.
     */
    public String getGreetingMessage() {
        String greetingMessage = String.format("Hello! I'm %s!\nHow can I help you?\n", NAME);
        return greetingMessage;
    }

    /**
     * This method is used to print the exit message.
     */
    public void printExitMessage() {
        String exitMessage = "Bye Bye! Hope to see you again soon! Beep Boop!";
        appendOutput(exitMessage);
    }

    /**
     * This method is used to print the list of tasks.
     *
     * @param list The array list of tasks.
     */
    public void printList(ArrayList<Task> list, String header) {
        appendOutput(header + "\n");
        for (int i = 0; i < list.size(); i++) {
            String taskString = String.format("%d. %s\n", i + 1, list.get(i));
            appendOutput(taskString);
        }
    }

    /**
     * This method is used to print the message that should appear when a task is successfully added.
     *
     * @param task The task that was successfully added.
     * @param list The updated list of tasks.
     */
    public void printAddSuccessMessage(Task task, ArrayList<Task> list) {
        appendOutput("Got it. I've added this task:\n");
        String taskString = String.format("%s\n", task.toString());
        String sizeString = String.format("Now you have %d tasks in the list.\n", list.size());
        appendOutput(taskString);
        appendOutput(sizeString);
    }

    /**
     * This method is used to print the message that should appear when a task is successfully deleted.
     *
     * @param index The zero-based index of the task that was successfully deleted.
     * @param list The list of tasks before being updated.
     */
    public void printDeleteSuccessMessage(int index, ArrayList<Task> list) {
        appendOutput("Noted. I've removed this task:\n");
        String taskString = String.format("%s\n", list.get(index).toString());
        String sizeString = String.format("Now you have %d tasks in the list.\n", list.size() - 1);
        appendOutput(taskString);
        appendOutput(sizeString);
    }
}
