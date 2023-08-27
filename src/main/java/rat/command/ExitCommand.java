package rat.command;

import static rat.io.RatPrinter.printExit;

import rat.tasks.RatTaskManager;

public class ExitCommand extends RatCommand {

    public ExitCommand(RatTaskManager ratTaskManager) {
        super(ratTaskManager);
    }

    @Override
    public void execute() {
        this.ratTaskManager.save();
        printExit();
        System.exit(0);
    }
}
