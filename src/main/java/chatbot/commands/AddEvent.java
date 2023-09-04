package chatbot.commands;

import chatbot.exceptions.InvalidDescriptionException;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.task.TaskType;
import chatbot.ui.Ui;

/**
 * Add an event task to the TaskList
 * 
 * @author Owen Yeo
 */
public class AddEvent extends Command {

    public AddEvent(String input, CommandType commandType) {
        super(input, commandType);
    }
    
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            tasks.addTask(input, TaskType.EVENT);

            ui.print(new String[] {
                "What? You ain't finishing it. Added: ",
                tasks.getTask(tasks.getLength()).toString(),
                "Now you have an overwhelming " + tasks.getLength() + " things to do."
            });

            storage.saveTasks(tasks);
        } catch (InvalidDescriptionException e) {
            ui.showError(e);
        }
    }
}
