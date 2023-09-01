package duke.commands;

import java.util.Optional;

import duke.records.ChatRecord;

/**
 * The command to find tasks with the given keyword
 * @author Toh Li Yuan (A0255811H)
 */
public class FindCommand extends Command {
    public static final String COMMAND_PHRASE = "find";
    public static final String COMMAND_DESC = "These are the tasks with the matching keyword:";
    public static final String NO_RESULT = "You have no tasks in the list with the matching keyword!";
    private String findWord;
    private ChatRecord records;
    public FindCommand(String findWord) {
        this.findWord = findWord;
    }

    @Override
    public void init(ChatRecord records) {
        this.records = records;
    }

    @Override
    public String execute() {
        Optional<String> res = records.findMessage(findWord);
        if (res.isPresent()) {
            return COMMAND_DESC + "\n" + res.get();
        }
        return NO_RESULT;
    }

    @Override
    public String toString() {
        return COMMAND_PHRASE + " " + findWord;
    }
}
