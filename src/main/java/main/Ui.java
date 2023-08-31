package main;

import task.Task;

import java.util.ArrayList;

public class Ui {
    private StringBuilder output;

    Ui() {
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
        addToOutput("Got it. I've added this task:\n");
        addToOutput("\t" + task + "\n");
        addToOutput("Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Prints a success message after a task is deleted from to the list.
     *
     * @param task Task deleted from list.
     * @param size Current size of list after deletion.
     */
    public void printDeleteSuccessMessage(Task task, int size) {
        addToOutput("Noted. I've removed this task:\n");
        addToOutput("\t" + task + "\n");
        addToOutput("Now you have " + size + " tasks in the list.\n");
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

    String getGreeting() {
        return "Hello from Dialogix\nWhat can I do for you?";
    }

    /**
     * Prints the exit message.
     *
     */
    public void printExitMessage() {
        addToOutput("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a given message with formatting.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        addToOutput(message);
    }
}

