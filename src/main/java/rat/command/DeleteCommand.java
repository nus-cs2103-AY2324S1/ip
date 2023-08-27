package rat.command;

import static rat.io.RatPrinter.printWithLines;

import rat.tasks.RatTaskManager;

public class DeleteCommand extends RatCommand {

    private String input;

    public DeleteCommand(RatTaskManager ratTaskManager, String input) {
        super(ratTaskManager);
        this.input = input;
    }

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
