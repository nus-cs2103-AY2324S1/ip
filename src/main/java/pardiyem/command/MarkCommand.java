package pardiyem.command;

import java.io.IOException;

import pardiyem.storage.Storage;
import pardiyem.task.TaskList;
import pardiyem.ui.Ui;

public class MarkCommand extends Command {
    /**
     * A constructor for the MarkCommand class
     *
     * @param desc index of the item to be marked as done
     */
    public MarkCommand(String desc) {
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
     * Will call the markAsDone method on the given Task object, show the execution message to the user, and modify the data file accordingly
     *
     * @param taskList the TaskList object to modify
     * @param ui the Ui object to send the message to
     * @param storage the Storage object that will handle the saving to the data file
     */
    public String execute(TaskList taskList, Storage storage) throws IOException {
        try {
            int i = Integer.parseInt(desc) - 1;
            if (i < 0 || i >= taskList.size()) {
                throw new ArrayIndexOutOfBoundsException(
                    "Whoops, that number is not an index in the list. Please select a valid index");
            }
            String out = String.format("%s\n%s",
                    taskList.getTask(i).markAsDone(),
                    taskList.getTask(i).toString());
            storage.save(taskList);
            return out;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Whoops, you need to type in a valid integer");
        }
    };

    /**
     * Utility inherited method to compare two objects.
     * Comparison between two MarkCommand objects will be done through comparison of their desc attribute
     *
     * @param obj the object to compare with
     * @return whether the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof MarkCommand) {
            result = this.desc.equals(((MarkCommand) obj).desc);
        }
        return result;
    }
}
