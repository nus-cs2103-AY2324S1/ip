package Alex.commands;


import Alex.AlexException;
import Alex.ui.Ui;
import Alex.tasks.Task;
import Alex.tasks.TaskList;

/**
 * A class that extends from Command class. It represents a command that is trying to mark a task bo be done, unmark
 * a task or delete a task in TaskList.
 */
public class EditCommand extends Command {
    private String command;
    private Edit editType;

    /**
     * The constructor of EditCommand when the user input string and editType is given.
     * @param command The user input string.
     * @param editType The type of edit command which consist of "MARK", "UNMARK" and "DELETE".
     */
    public EditCommand(String command, Edit editType) {
        this.command = command;
        this.editType = editType;
    }

    /**
     * Override the method from the abstract Command class.
     * The execute method of EditCommand class is to actually edit the task specified by user to the TaskList such as
     * mark task, unmark task and delete an existing class in TaskList.
     */
    @Override
    public String execute() {
        String output = null;
        switch (this.editType) {
        case MARK:
            try {
                int index = Integer.parseInt(command.substring(5));
                Task targetedTask = TaskList.getTaskByIndex(index);
                output = targetedTask.mark();
                return output;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                output = Ui.getAlertForMark();
                return output;
            } catch (AlexException e) {
                output = e.getMessage();
                return output;
            } finally {
                break;
            }

        case UNMARK:
            try {
                int index = Integer.parseInt(command.substring(7));
                Task targetedTask = TaskList.getTaskByIndex(index);
                output = targetedTask.unmark();
                return output;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                output = Ui.getAlertForUnmark();
                return output;
            } catch (AlexException e) {
                output = e.getMessage();
                return output;
            } finally {
                break;
            }

        case DELETE:
            try {
                int toDeleteIndex = Integer.parseInt(command.substring(7, 8));
                output = TaskList.delete(toDeleteIndex);
                return output;
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                output = Ui.getAlertForDelete();
                return output;
            } finally {
                break;
            }
        }
        return output;
    }


}
