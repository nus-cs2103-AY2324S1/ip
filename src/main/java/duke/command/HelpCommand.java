package duke.command;

import duke.TaskList;
import duke.Ui;

public class HelpCommand extends Command{
    protected int helpOption;

    public HelpCommand(int helpOption){
        this.helpOption = helpOption ;
    }

    public String execute(TaskList taskList, Ui ui){
        if (helpOption == 0){
            return ui.showHelpDialogue();
        } else {
            return ui.showHelpAnswer(helpOption);
        }
    }
}
