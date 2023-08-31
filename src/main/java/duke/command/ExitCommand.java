package duke.command;
import duke.TaskList;
import duke.Ui;
import duke.messages.Messages;

import duke.command.Command;

public class ExitCommand extends Command {

    public void execute(TaskList taskList, Ui ui){
        System.out.println(Messages.GOODBYE_MESSAGE.getMessage());
    }
}
