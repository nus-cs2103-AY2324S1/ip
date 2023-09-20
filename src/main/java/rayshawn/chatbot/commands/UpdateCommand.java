package rayshawn.chatbot.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rayshawn.chatbot.messages.Messages;
import rayshawn.chatbot.tasks.Deadline;
import rayshawn.chatbot.tasks.Event;
import rayshawn.chatbot.tasks.Task;
import rayshawn.chatbot.tasks.ToDo;

/**
 *
 */
public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Use to update a task description, date, start time or end time.\n"
            + "Parameters: INDEX description/date/start/end UpdatedInfo\n"
            + "Example: " + COMMAND_WORD + " 1 date 2023-09-17 \n"
            + "** Description can be updated for all tasks type, however,\n"
            + "   date can only be updated for deadline and event tasks"
            + "     and start and end can only be updated for event task!\n"
            + "Date format: YYYY-MM-DD\n" + "Time format: HH(AM/PM)"
            + "Example: update 1 description party";
    private static final String MESSAGE_SUCCESS = "I've updated the %s!\n %s";
    private static final String INVALID_UPDATE_INFO = "Unable to update info. Please check the given instructions";
    private static final Pattern DATE_FORMAT =
            Pattern.compile("(?<date>(19|20)\\d{2}(\\/|-)(0[1-9]|1[1,2])(\\/|-)(0[1-9]|[12][0-9]|3[01]))");
    private static final Pattern TIME_FORMAT =
            Pattern.compile("(?<time>([0-9]|0[0-9]|1[0-2])(AM|PM))");

    private String update;
    private String info;

    /**
     * Constructor for UpdateCommand.
     *
     * @param index index of task to be updated
     * @param update type of update
     * @param info info to be updated
     */
    public UpdateCommand(int index, String update, String info) {
        super(index);
        this.update = update;
        this.info = info;
    }

    @Override
    public CommandResult execute() {
        try {
            assert taskList != null : "TaskList should not be null";
            final Task task = taskList.getTask(getIndex() - 1);
            return parse(task);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.INVALID_TASK_NUMBER_MESSAGE);
        }
    }

    private CommandResult parse(Task task) {
        switch (update) {
        case "description":
            return updateDescription(task);
        case "date":
            return updateDate(task);
        case "start":
            return updateStart(task);
        case "end":
            return updateEnd(task);
        default:
            return new CommandResult(INVALID_UPDATE_INFO);
        }
    }

    private CommandResult updateDescription(Task task) {
        task.updateDescription(info);
        return new CommandResult(String.format(MESSAGE_SUCCESS, update, task));
    }

    private CommandResult updateDate(Task task) {
        if (task instanceof ToDo) {
            return new CommandResult(INVALID_UPDATE_INFO);
        }

        final Matcher matcher = DATE_FORMAT.matcher(info.trim());
        if (!matcher.matches()) {
            return new CommandResult(String.format(Messages.INVALID_COMMAND_FORMAT_MESSAGE, MESSAGE_USAGE));
        }

        if (task instanceof Deadline) {
            Deadline temp = (Deadline) task;
            temp.updateDate(info);
            return new CommandResult(String.format(MESSAGE_SUCCESS, update, temp));
        }

        if (task instanceof Event) {
            Event temp = (Event) task;
            temp.updateDate(info);
            return new CommandResult(String.format(MESSAGE_SUCCESS, update, temp));
        }

        return new CommandResult(INVALID_UPDATE_INFO);
    }

    private CommandResult updateStart(Task task) {
        if (isToDoOrDeadline(task)) {
            return new CommandResult(INVALID_UPDATE_INFO);
        }

        final Matcher matcher = TIME_FORMAT.matcher(info.trim());
        if (!matcher.matches()) {
            return new CommandResult(String.format(Messages.INVALID_COMMAND_FORMAT_MESSAGE, MESSAGE_USAGE));
        }

        if (task instanceof Event) {
            Event temp = (Event) task;
            temp.updateStart(info);
            return new CommandResult(String.format(MESSAGE_SUCCESS, update, temp));
        }

        return new CommandResult(INVALID_UPDATE_INFO);
    }

    private CommandResult updateEnd(Task task) {
        if (isToDoOrDeadline(task)) {
            return new CommandResult(INVALID_UPDATE_INFO);
        }

        final Matcher matcher = TIME_FORMAT.matcher(info.trim());
        if (!matcher.matches()) {
            return new CommandResult(String.format(Messages.INVALID_COMMAND_FORMAT_MESSAGE, MESSAGE_USAGE));
        }

        if (task instanceof Event) {
            Event temp = (Event) task;
            temp.updateEnd(info);
            return new CommandResult(String.format(MESSAGE_SUCCESS, update, temp));
        }

        return new CommandResult(INVALID_UPDATE_INFO);
    }

    private boolean isToDoOrDeadline(Task task) {
        return task instanceof ToDo || task instanceof Deadline;
    }
}
