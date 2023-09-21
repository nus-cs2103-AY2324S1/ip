package pardiyem.command;

import pardiyem.storage.Storage;
import pardiyem.task.TaskList;

public class ListCommand extends Command {
    /**
     * A constructor for the ListCommand class
     *
     * @param desc additional arguments for the command
     * @throws IllegalArgumentException if the string desc is not empty
     */
    public ListCommand(String desc) {
        super(desc);
        if (!this.desc.isEmpty()) {
            throw new IllegalArgumentException(String
                    .format("You used \"%s\" as an argument. A bye command shouldn't have any arguments", desc));
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
     * Will show the full taskList to the user
     *
     * @param taskList the TaskList object to modify
     * @param storage the Storage object that will handle the saving to the data file
     */
    public String execute(TaskList taskList, Storage storage) {
        if (taskList.toString().isEmpty()) {
            return "There are no tasks in your current list, mio amico";
        }
        return taskList.toString();
    };

    /**
     * Utility inherited method to compare two objects.
     * All ListCommand objects are equal
     *
     * @param obj the object to compare with
     * @return whether the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
