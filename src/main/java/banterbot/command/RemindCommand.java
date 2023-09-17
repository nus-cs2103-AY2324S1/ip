package banterbot.command;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import banterbot.helper.Storage;
import banterbot.helper.Ui;
import banterbot.task.Task;
import banterbot.task.TaskList;

/**
 * Represents a Command that specifically reminds upcoming deadlines in the Tasklist.
 */
public class RemindCommand extends Command {
    /** Command the user starts with to activate the ListCommand. */
    public static final String COMMAND_WORD = "remind";
    /** The LocalDate the BanterBot.command was executed */
    private LocalDate today;

    /**
     * Constructs a RemindCommand with an Index.
     * @param index
     */
    public RemindCommand(int index) {
        super(index);
        today = LocalDate.now();
    }

    /**
     * Returns the String representation of all deadlines due in 3 days in the TaskList.
     * @param list
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        assert !list.equals(null) : "list has been initialised";
        StringBuilder message = new StringBuilder("");
        List<Task> totalList = new ArrayList<>();

        int days = 3;
        for (int i = 0; i < days; i++) {
            List<Task> dueList = list.dueOn(today.plusDays(i));

            if (!dueList.isEmpty()) {
                totalList.addAll(dueList);
            }
        }

        if (totalList.isEmpty()) {
            message.append("Nothing to see here...");
        } else {
            message.append("Bro do this:");
            for (Task t : totalList) {
                message.append("\n" + t.toString());
            }
        }
        return message.toString();
    }
}
