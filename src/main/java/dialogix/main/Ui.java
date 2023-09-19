package dialogix.main;

import java.util.ArrayList;
import java.util.List;

import dialogix.task.Task;

/**
 * Ui of Dialogix.
 */
public class Ui {
    private StringBuilder output;

    public Ui() {
        output = new StringBuilder();
    }

    void resetOutput() {
        output.setLength(0);
    }

    String getOutput() {
        return output.toString();
    }

    private void addToOutput(String str) {
        output.append(str);
    }

    /**
     * Prints a success message after an undo operation is successfully performed.
     *
     * @param stepsToUndo Number of times to perform undo operation.
     */
    public void printUndoSuccessMessage(int stepsToUndo) {
        addToOutput("Performed undo " + stepsToUndo + " times!");
    }

    /**
     * Prints a success message after a task is successfully added to the list.
     *
     * @param task Task added to list.
     * @param size Current size of list after addition.
     */
    public void printAddSuccessMessage(Task task, int size) {
        addToOutput("Yeah. I have added the task\n");
        addToOutput("\t" + task + "\n");
        addToOutput("Now you have " + size + " tasks in your list.\n");
    }

    /**
     * Prints a success message after a task is deleted from to the list.
     *
     * @param task Task deleted from list.
     * @param size Current size of list after deletion.
     */
    public void printDeleteSuccessMessage(Task task, int size) {
        addToOutput("I have removed task:\n");
        addToOutput("\t" + task + "\n");
        addToOutput("Now you have " + size + " tasks in your list.\n");
    }

    /**
     * Prints the given list with formatting.
     *
     * @param tasks List of tasks to be printed.
     * @param message The message to be printed before printing the list.
     */
    public void printList(ArrayList<Task> tasks, String message) {
        addToOutput(message + "\n");
        for (int i = 0; i < tasks.size(); i++) {
            int oneBasedIndex = i + 1;
            String formattedOutput = String.format("%d. %s\n", oneBasedIndex, tasks.get(i));
            addToOutput(formattedOutput);
        }
    }

    /**
     * Prints the exit message.
     *
     */
    public void printExitMessage() {
        addToOutput("Bye bye. Dialogix hopes to see you soon.");
    }

    /**
     * Prints a given message with formatting.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        addToOutput(message);
    }

    /**
     * Displays a list of matching tasks to the console.
     *
     * @param matchingTasks The list of tasks that match the search criteria.
     */
    public void showSearchResults(List<Task> matchingTasks) {
        System.out.println("Here are some tasks that I can find for you: ");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + "." + matchingTasks.get(i).toString());
        }
    }

}

