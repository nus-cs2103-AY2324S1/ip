package duke.parse.command;

import duke.Duke;

public class MarkCommand implements Command {
    private boolean isDone;
    private int index;

    public MarkCommand(boolean isDone, int index) {
        this.isDone = isDone;
        this.index = index;
    }

    @Override
    public boolean execute(Duke bot) {
        if (this.isDone) {
            bot.markTaskAsDone(this.index);
        } else {
            bot.markTaskAsNotDone(this.index);
        }
        return true;
    }

    @Override
    public boolean equals(Object another) {
        if (another instanceof MarkCommand) {
            MarkCommand anotherMark = (MarkCommand) another;
            return this.isDone == anotherMark.isDone
                    && this.index == anotherMark.index;
        }
        return false;
    }
}
