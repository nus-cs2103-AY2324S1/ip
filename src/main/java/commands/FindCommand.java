package commands;

import storage.DataFile;
import tasks.TaskList;

/**
 * Represents a type of command that can be read by the chatbot.
 */
public class FindCommand extends Command {
    private final String keyWord;
    private String tasksList;

    /**
     * FindCommand constructor that takes in a String.
     * @param keyWord The word to be searched.
     */
    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Executes the find command.
     * @param tasks List of tasks.
     * @param dF The file to be edited on.
     */
    @Override
    public void execute(TaskList tasks, DataFile dF) {
        StringBuilder sB = new StringBuilder();
        sB.append("Here are the matching tasks from you list:");
        System.out.println(this);
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).getDesc().contains(keyWord)) {
                String tmp = i + 1 + "." + tasks.getTask(i);
                System.out.println(tmp);
                sB.append(System.lineSeparator()).append(tmp);
            }
        }
        tasksList = sB.toString();
    }

    /**
     * Returns the string representation of find command.
     * @return String representation of find command.
     */
    @Override
    public String toString() {
        return tasksList;
    }
}
