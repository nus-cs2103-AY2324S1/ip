package duke.parse.command;

import duke.Duke;

import java.time.LocalDate;

/**
 * Represents a list command.
 * Contains the filters as requested by user.
 * Filters include: whether to exclude tasks done, date to match tasks, and type of task.
 */
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

    /**
     * Instantiates a list task with the given filter
     * @param isExcludingDone whether to exclude tasks already done
     * @param date the date to filter in deadlines before / events happening on,
     *             null if not filtering by date
     * @param type the type of task to include,
     *             DEFAULT if not filtering by task type
     */
    public ListCommand(boolean isExcludingDone, LocalDate date, Type type) {
        this.isExcludingDone = isExcludingDone;
        this.date = date;
        this.type = type;
    }

    /**
     * Commands the bot to list out the task with the given filters.
     * @param bot the bot to execute the command
     * @return true, as this allows the user to continue with the programme
     */
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

    /**
     * Checks whether this list command is the same as another, for testing purposes.
     * True if both are list commands, and the filters are the same.
     * @param another the object to compare with
     * @return whether this list command is the same as another
     */
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