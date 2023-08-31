package duke.command;
import duke.TaskList;
import duke.Ui;

import duke.command.Command;

public class ListCommand extends Command {

    public void execute(TaskList taskList, Ui ui){
        ui.showListMessage();
        for (int i = 0; i < taskList.getLengthOfTaskList(); i++){
            System.out.println(Integer.toString(i+1) + "." + taskList.getTaskList().get(i).toString());
        }
    }
}
