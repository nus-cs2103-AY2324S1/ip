package urchatbot.commands;
import urchatbot.exception.URChatBotException;
import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.ui.Ui;

public class Command {
    public String taskDescription;
    /**
     * Constructs the Command class.
     *
     * @param taskDescription Task description of the task.
     */
    public Command(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Returns if the command is the exit command.
     *
     * @return boolean If it is the exit command.
     */
    public boolean isExit(){
        return false;
    }

    /**
     * Executes the tasks.
     *
     * @param tasks Tasklist.
     * @param ui User interface.
     * @param storage Storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws URChatBotException {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}
