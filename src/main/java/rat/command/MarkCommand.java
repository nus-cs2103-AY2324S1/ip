package rat.command;

import static rat.io.RatPrinter.printWithLines;

import rat.tasks.RatTaskManager;

/**
 * This class encapsulates a command that marks a task as done.
 */
public class MarkCommand extends ModifyCommand {

    /**
     * The index of the first character of the parameters in the user input.
     */
    private static final int MARK_PARAMS_START = 1;

    /**
     * The user input passed from RatInput that contains the index of the task to be marked as done.
     */
    private String[] inputs;

    /**
     * Constructor for a MarkCommand object.
     * @param ratTaskManager The RatTaskManager object used to store and process the user's tasks.
     * @param inputs The user input passed from RatInput that contains the index of the task to be marked as done.
     */
    public MarkCommand(RatTaskManager ratTaskManager, String... inputs) {
        super(ratTaskManager);
        this.inputs = inputs;
    }

    @Override
    public String getResponse() {
        try {
            int index = Integer.parseInt(inputs[MARK_PARAMS_START]);
            return this.ratTaskManager.markItemDone(index);
        } catch (IndexOutOfBoundsException e) {
            printWithLines(e.getMessage());
            return e.getMessage();
        } catch (NumberFormatException e) {
            printWithLines(" \"mark\" command must be followed by a number");
            return " \"mark\" command must be followed by a number";
        }
    }

    @Override
    public void execute() {
        try {
            int index = Integer.parseInt(inputs[MARK_PARAMS_START]);
            this.ratTaskManager.markItemDone(index);
        } catch (IndexOutOfBoundsException e) {
            printWithLines(e.getMessage());
        } catch (NumberFormatException e) {
            printWithLines(" \"mark\" command must be followed by a number");
        }
    }
}
