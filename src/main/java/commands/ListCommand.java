package commands;

import storage.DataFile;
import tasks.TaskList;

/**
 * Represents a type of command that can be read by the chatbot.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * @param tasks List of tasks.
     * @param dF The file to be edited on.
     */
    @Override
    public String execute(TaskList tasks, DataFile dF) {
        StringBuilder sB = new StringBuilder();
        sB.append("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            String tmp = i + 1 + "." + tasks.getTask(i);
            System.out.println(tmp);
            sB.append(System.lineSeparator()).append(tmp);
        }
        return sB.toString();
    }
}
