package pardiyem.command;

import java.io.IOException;

import pardiyem.storage.Storage;
import pardiyem.task.TaskList;

public class DeleteCommand extends Command {
    /**
     * A constructor for the DeleteCommand class
     *
     * @param desc index of the item to be deleted
     */
    public DeleteCommand(String desc) {
        super(desc);
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
     * Will call the delete method from the TaskList class, show the deletion message to the user, and modify the data file accordingly
     *
     * @param taskList the TaskList object to modify
     * @param storage the Storage object that will handle the saving to the data file
     */
    public String execute(TaskList taskList, Storage storage) throws IOException {
        try {
            int i = Integer.parseInt(desc) - 1;
            String out = taskList.delete(i);
            storage.save(taskList);
            return out;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Whoops, you need to type in a valid integer");
        }
    }

    /**
     * Utility inherited method to compare two objects.
     * Comparison between two DeleteCommand objects will be done through comparison of their desc attribute
     *
     * @param obj the object to compare with
     * @return whether the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof DeleteCommand) {
            result = this.desc.equals(((DeleteCommand) obj).desc);
        }
        return result;
    }
}
