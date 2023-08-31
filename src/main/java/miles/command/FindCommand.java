package miles.command;

import miles.Storage;
import miles.TaskList;
import miles.Ui;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor to create a new delete command.
     * 
     * @param keyword the keyword to find
     */
    public FindCommand(String keyword) {
        this.keyword = getKeywordToFind(keyword);
    }

    /**
     * Extract the keyword from the input string when the command is a "find".
     * 
     * @param input the input string
     * @return      the keyword to find
     */
    public String getKeywordToFind(String input) {
        String keyword = input.replace("find ", "");
        return keyword.trim();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.displayListWithKeyword(this.keyword);
    }
}
