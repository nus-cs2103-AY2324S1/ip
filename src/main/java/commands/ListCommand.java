package commands;

import storage.Storage;
import taskList.TaskList;
import ui.Ui;

public class ListCommand extends Command{
    public ListCommand(String taskDescription) {
        super(taskDescription);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showListMessage();
        for(int i = 0; i < tasks.getTasks().size(); i ++) {
            System.out.println(i + 1 + "." + tasks.getTasks().get(i).toString());
        }
    }

}
