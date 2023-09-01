package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.task.TaskType;
import chatbot.ui.Ui;

/**
 * Adds a deadline task to the TaskList
 * 
 * @author Owen Yeo
 */
public class AddDeadline extends Command {

    public AddDeadline(String input) {
        super(input);
    }
    
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.addTask(input, TaskType.DEADLINE);

        ui.print(new String[] {
            "What? You ain't finishing it. Added: ",
            tasks.getTask(tasks.getLength()).toString(),
            "Now you have an overwhelming " + tasks.getLength() + " things to do."
        });

        storage.saveTasks(tasks);
    }
}
