package urchatbot.commands;
import urchatbot.exception.URChatBotException;
import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.ui.Ui;

/**
 * Finds and lists all tasks in tasklist which the description contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {
    private String searchWord;

    /**
     * Constructs the FindCommand class.
     *
     * @param searchWord Word that the users want to search.
     */
    public FindCommand(String searchWord) {
        super("Find");
        this.searchWord = searchWord;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws URChatBotException {
        return ui.showFindMessage(tasks, searchWord);
    }

}
