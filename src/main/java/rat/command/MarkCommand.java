package rat.command;

import static rat.io.RatPrinter.printWithLines;

import rat.tasks.RatTaskManager;

public class MarkCommand extends ModifyCommand {

    private String[] inputs;

    public MarkCommand(RatTaskManager ratTaskManager, String[] inputs) {
        super(ratTaskManager);
        this.inputs = inputs;
    }

    @Override
    public void execute() {
        try {
            int index = Integer.parseInt(inputs[1]);
            this.ratTaskManager.markItemDone(index);
        } catch (IndexOutOfBoundsException e) {
            printWithLines(e.getMessage());
        } catch (NumberFormatException e) {
            printWithLines(" \"mark\" command must be followed by a number");
        }
    }
}
