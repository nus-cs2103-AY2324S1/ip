package duke.ui;

import java.util.List;

import duke.task.Task;

/**
 * Helper class for user interface, handles
 * user input and outputting to the user.
 */
public class TextUi implements Ui {

    private static final String DOTTED_LINE = "____________________________________________________________";
    private final StringBuilder buffer;

    private String name;

    /**
     * Initialise Ui handler.
     * @param name Name of chatbot.
     */
    public TextUi(String name) {
        this.name = name;
        this.buffer = new StringBuilder();
    }

    /**
     * Get accumulated output from the UI.
     * @return Text output accumulated so far.
     */
    public String getTextOutput() {
        String out = this.buffer.toString();
        this.buffer.delete(0, this.buffer.length());
        return out;
    }

    @Override
    public void init() {
        // Does nothing for TextUi
    }

    @Override
    public String getInput() {
        // Does nothing for TextUi
        return "";
    }

    @Override
    public void printException(Exception e) {
        this.buffer.append(String.format("[!] %s\n", e.getMessage()));
    }

    @Override
    public void exit() {
        this.buffer.append(TextUi.DOTTED_LINE);
        this.buffer.append("Bye. Hope to see you again soon!\n");
        this.buffer.append(TextUi.DOTTED_LINE);
    }

    @Override
    public void addTask(Task task) {
        this.buffer.append(String.format("%s: [Added] %s\n", this.name, task));
    }

    @Override
    public void deleteTask(Task task) {
        this.buffer.append(String.format("%s: [Deleted] %s\n", this.name, task));
    }

    @Override
    public void markTask(Task task) {
        this.buffer.append(String.format("%s: I've marked this task as done.\n", this.name));
        this.buffer.append(String.format("    %s\n", task));
    }

    @Override
    public void unmarkTask(Task task) {
        this.buffer.append(String.format("%s: I've marked this task as not done.\n", this.name));
        this.buffer.append(String.format("    %s\n", task));
    }

    @Override
    public void listTasks(List<Task> tasks) {
        this.buffer.append(String.format("%s: \n", this.name));
        for (int i = 0; i < tasks.size(); i++) {
            this.buffer.append(String.format("    %d. %s\n", i + 1, tasks.get(i)));
        }
    }

    @Override
    public void invalidCommand(String command) {
        this.buffer.append(String.format("%s: Invalid command (%s)\n", this.name, command));
    }
}
