package duke.task;

import javax.swing.JTextArea;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the given description.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the todo task into a string and display it.
     *
     * @param chatArea JTextArea where the message will be displayed.
     */
    @Override
    public void display(JTextArea chatArea) {
        chatArea.append("[T]");
        super.display(chatArea);
        chatArea.append("\n");
    }
}
