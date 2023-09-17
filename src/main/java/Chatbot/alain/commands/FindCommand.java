package chatbot.alain.commands;

import chatbot.alain.uis.GuiUi;
import chatbot.alain.Storage;
import chatbot.alain.TaskList;
import chatbot.alain.uis.Ui;

/**
 * Represents an abstract command that can be executed. This serves as the base class
 * for various specific command implementations in the system.
 *
 * <p>Each command is associated with a {@code TaskList} to keep track of tasks,
 * a textual representation or description of the command itself, and a {@code Storage}
 * instance for persistence concerns.</p>
 */
public class FindCommand extends Command {
    public FindCommand(TaskList list, String text, Storage storage) {
        super(list, text, storage);
    }
    @Override
    public String processCommand() {
        String keyWord = text.substring(4);
        TaskList tmpList = new TaskList();
        for (int i = 0; i < list.size(); i++) {
            if (list.getTask(i).descriptionContain(keyWord)) {
                tmpList.addTask(list.getTask(i));
            }
        }
        Ui.showListContainingKeyword(tmpList);
        return GuiUi.showListContainingKeyword(tmpList);
    }
}
