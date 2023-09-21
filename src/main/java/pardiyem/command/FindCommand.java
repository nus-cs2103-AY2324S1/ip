package pardiyem.command;

import pardiyem.storage.Storage;
import pardiyem.task.Task;
import pardiyem.task.TaskList;

public class FindCommand extends Command {
    /**
     * A constructor for the FindCommand class
     *
     * @param desc additional arguments for the command
     * @throws IllegalArgumentException if the string desc is not empty
     */
    public FindCommand(String desc) {
        super(desc);
        if (desc.isEmpty()) {
            throw new IllegalArgumentException("The find description cannot be empty, mio amico");
        }
    }

    /**
     * Utility inherited method to indicate whether this command will end the program
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Inherited method to execute a command.
     * Will return a string representation of the taskList containing all related tasks
     *
     * @param taskList the TaskList object to search through
     * @param storage the Storage object that will handle the saving to the data file
     */
    public String execute(TaskList taskList, Storage storage) {
        String out = taskList.findAndList(desc);
        if (out.isEmpty()) {
            return "Scusa mi. I cannot find any task with a matching description";
        }
        return out;
    }

    /**
     * Utility inherited method to compare two objects.
     * Comparison between two FindCommand objects will be done through comparison of their desc attribute
     *
     * @param obj the object to compare with
     * @return whether the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof FindCommand) {
            result = this.desc.equals(((FindCommand) obj).desc);
        }
        return result;
    }
}
