package duke.commands;

import duke.records.ChatRecord;

import java.time.LocalDate;

public class RemindCommand extends Command {

    public static final String COMMAND_PHRASE = "remind";
    private LocalDate currDate;
    private int days;
    public RemindCommand(int days) {
        this.currDate = LocalDate.now();
        this.days = days;
    }

    @Override
    public void init(ChatRecord records) {
        this.chatRecord = records;
    }

    @Override
    public String execute() {
        String reminders = this.chatRecord.getReminder(currDate, days);
        if (reminders.length() < 1) {
            return "You have no urgent tasks!";
        }
        return String.format("These are the tasks you need to finish by next %d day(s):\n%s", this.days, reminders);
    }

}
