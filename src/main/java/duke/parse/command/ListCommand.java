package duke.parse.command;

import duke.Duke;

import java.time.LocalDate;

public class ListCommand implements Command {
    private boolean isExcludingDone;
    private LocalDate date;
    private Type type;

    public enum Type {
        TODO,
        DEADLINE,
        EVENT,
        DEFAULT,
    }

    public ListCommand(boolean isExcludingDone, LocalDate date, Type type) {
        this.isExcludingDone = isExcludingDone;
        this.date = date;
        this.type = type;
    }

    @Override
    public boolean execute(Duke bot) {
        switch (this.type) {
            case TODO:
                bot.showTodos(this.isExcludingDone);
                break;
            case DEADLINE:
                bot.showDeadlines(this.isExcludingDone, this.date);
                break;
            case EVENT:
                bot.showEvents(this.isExcludingDone, this.date);
                break;
            case DEFAULT:
                bot.showList(this.isExcludingDone, this.date);
                break;
        }
        return true;
    }

    @Override
    public boolean equals(Object another) {
        if (another instanceof ListCommand) {
            ListCommand anotherList = (ListCommand) another;
            return this.isExcludingDone == anotherList.isExcludingDone
                    && ((this.date == null && anotherList.date == null)
                        || this.date.equals(anotherList.date))
                    && this.type.equals(anotherList.type);
        }
        return false;
    }
}