package rat.command;

import static rat.io.RatPrinter.printWithLines;

import rat.tasks.RatTaskManager;

public class UnmarkCommand extends ModifyCommand {
    private String[] inputs;

    public UnmarkCommand(RatTaskManager ratTaskManager, String[] inputs) {
        super(ratTaskManager);
        this.inputs = inputs;
    }

    @Override
    public void execute() {
        try {
            int index = Integer.parseInt(inputs[1]);
            this.ratTaskManager.unmarkItemDone(index);
        } catch (IndexOutOfBoundsException e) {
            printWithLines(e.getMessage());
        } catch (NumberFormatException e) {
            printWithLines(" \"mark\" command must be followed by a number");
        }
    }
}
