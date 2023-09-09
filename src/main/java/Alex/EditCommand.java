package Alex;

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
    public void execute() {
        switch (this.editType) {
        case MARK:
            try {
                int index = Integer.parseInt(command.substring(5));
                Task targetedTask = TaskList.getTaskByIndex(index);
                targetedTask.mark();
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                Ui.printAlertForMark();
            } catch (AlexException e) {
                System.out.println(Ui.horizontalLine
                        + e.getMessage() + "\n"
                        + Ui.horizontalLine
                );
            } finally {
                break;
            }

        case UNMARK:
            try {
                int index = Integer.parseInt(command.substring(7));
                Task targetedTask = TaskList.getTaskByIndex(index);
                targetedTask.unmark();
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                Ui.printAlertForUnmark();
            } catch (AlexException e) {
                System.out.println(Ui.horizontalLine
                        + e.getMessage() + "\n"
                        + Ui.horizontalLine
                );
            } finally {
                break;
            }

        case DELETE:
            try {
                int toDeleteIndex = Integer.parseInt(command.substring(7, 8));
                TaskList.delete(toDeleteIndex);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                Ui.printAlertForDelete();
            } catch (AlexException e) {
                System.out.println(Ui.horizontalLine
                        + e.getMessage() + "\n"
                        + Ui.horizontalLine
                );
            } finally {
                break;
            }
        }
    }


}
