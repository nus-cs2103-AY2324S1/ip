package duke.commands;

import java.time.LocalDate;

import duke.records.ChatRecord;

/**
 * The command to list all urgent tasks within a time period.
 * @author Toh Li Yuan (A0255811H)
 */
public class RemindCommand extends Command {

    public static final String COMMAND_PHRASE = "remind";
    private LocalDate currDate;
    private int days;

    /**
     * Creates a command to display reminder to the user.
     *
     * @param days The number of days to seek for urgent tasks.
     */
    public RemindCommand(int days) {
        this.currDate = LocalDate.now();
        this.days = days;
    }

    @Override
    public void init(ChatRecord records) {
        this.chatRecord = records;
    }

    /**
     * Executes the created Remind Command.
     *
     * @return The reminder list to be displayed to the user.
     */
    @Override
    public String execute() {
        String reminders = this.chatRecord.getReminder(currDate, days);
        if (reminders.length() < 1) {
            return "You have no urgent tasks!";
        }
        return String.format("These are the tasks you need to finish by next %d day(s):\n%s", this.days, reminders);
    }

}
