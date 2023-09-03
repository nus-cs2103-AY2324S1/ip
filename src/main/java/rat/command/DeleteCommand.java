package rat.command;

import static rat.io.RatPrinter.printWithLines;

import rat.tasks.RatTaskManager;

/**
 * This class encapsulates a command that deletes a task from the task list.
 */
public class DeleteCommand extends RatCommand {

    /**
     * The user input passed from RatInput that contains the index of the task to be deleted.
     */
    private String input;

    /**
     * Constructor for a DeleteCommand object.
     * @param ratTaskManager The RatTaskManager object used to store and process the user's tasks.
     * @param input The user input passed from RatInput that contains the index of the task to be deleted.
     */
    public DeleteCommand(RatTaskManager ratTaskManager, String input) {
        super(ratTaskManager);
        this.input = input;
    }

    @Override
    public String getResponse() {
        try {
            String param = this.input.substring(7);
            if (param.equals("all")) {
                return this.ratTaskManager.deleteAll();
            } else {
                int index = Integer.parseInt(param);
                return this.ratTaskManager.deleteItem(index);
            }
        } catch (IndexOutOfBoundsException e) {
            printWithLines(e.getMessage());
            return e.getMessage();
        } catch (NumberFormatException e) {
            printWithLines(" \"delete\" command must be followed by a number");
            return " \"delete\" command must be followed by a number";
        }
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute() {
        try {
            String param = this.input.substring(7);
            if (param.equals("all")) {
                this.ratTaskManager.deleteAll();
            } else {
                int index = Integer.parseInt(param);
                this.ratTaskManager.deleteItem(index);
            }
        } catch (IndexOutOfBoundsException e) {
            printWithLines(e.getMessage());
        } catch (NumberFormatException e) {
            printWithLines(" \"delete\" command must be followed by a number");
        }
    }
}
