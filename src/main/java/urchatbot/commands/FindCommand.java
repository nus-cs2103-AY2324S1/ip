package urchatbot.commands;
import urchatbot.exception.URChatBotException;
import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws URChatBotException {
        ui.showFindMessage();

        for (int i = 0; i < tasks.getTasks().size(); i++) {
            if (tasks.getTasks().get(i).toString().contains(searchWord)) {
                    System.out.println(i + 1 + "." + tasks.getTasks().get(i).toString());
                }
        }
    }
}

