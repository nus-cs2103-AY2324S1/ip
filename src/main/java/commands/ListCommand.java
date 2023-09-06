package commands;

import storage.DataFile;
import tasks.TaskList;

/**
 * Represents a type of command that can be read by the chatbot.
 */
public class ListCommand extends Command {

    private String tasksList;

    /**
     * Executes the list command.
     * @param tasks List of tasks.
     * @param dF The file to be edited on.
     */
    @Override
    public void execute(TaskList tasks, DataFile dF) {
        StringBuilder sB = new StringBuilder();
        sB.append("Here are the tasks in your list:");
        System.out.println(this);
        for (int i = 0; i < tasks.getSize(); i++) {
            String tmp = i + 1 + "." + tasks.getTask(i);
            System.out.println(tmp);
            sB.append(System.lineSeparator()).append(tmp);
        }
        tasksList = sB.toString();
    }

    /**
     * Returns the string representation of list command.
     * @return String representation of list command.
     */
    @Override
    public String toString() {
        return tasksList;
    }
}
