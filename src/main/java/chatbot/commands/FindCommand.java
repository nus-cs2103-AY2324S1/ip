package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.tasks.TaskList;
import chatbot.ui.Ui;

public class FindCommand extends Command{

    private String input;

    public FindCommand(String input){
        this.input = input;
    }

    /**
     * Returns a boolean value to indicate whether to exit the program
     * @return A boolean value
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to exit the program
     * @param tasksList Task list which contains the tasks
     * @param ui        A UI instance that displays a message to the user
     * @param storage   Storage instance that represents the storage of the file
     */
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        ui.showFindResults(tasksList, input);
    }
}
