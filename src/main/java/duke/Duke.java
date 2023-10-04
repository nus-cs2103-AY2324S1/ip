package duke;

import java.time.LocalDate;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.parser.CommandParser;
import duke.records.ChatRecord;

/**
 * @author Toh Li Yuan (A0255811H)
 */
public class Duke {
    private static final String INIT_PHRASE = "KnowledgeYuan, at your service!\nWhat can I do for you today?";
    private static final String REMINDER_PHRASE =
            "The following tasks are urgent! Please remove them from the list after completing!";
    private boolean isEnd = false;
    private CommandParser cp;
    private ChatRecord chatRecord;

    /**
     * Initialises the Duke program.
     *
     * @return the greeting string.
     */
    public String init() {
        cp = new CommandParser();
        chatRecord = new ChatRecord();
        chatRecord.loadData();
        String reminders = chatRecord.getReminder(LocalDate.now(), 1);
        if (reminders.length() >= 1) {
            return INIT_PHRASE + "\n" + REMINDER_PHRASE + "\n" + reminders;
        }
        return INIT_PHRASE;
    }

    /**
     * Returns a response from Duke from a given input.
     *
     * @param input the user input.
     * @return the response from duke.
     */
    public String getResponse(String input) {
        Command cmd = cp.parseCommand((input));
        if (ByeCommand.isBye(cmd)) {
            isEnd = true;
        }
        cmd.init(chatRecord);
        String out = cmd.execute();
        assert out != null;
        return out;
    }

    public boolean isTerminate() {
        return isEnd;
    }

}
