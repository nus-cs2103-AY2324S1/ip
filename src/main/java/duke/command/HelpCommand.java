package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Provides support to the user
 */

public class HelpCommand extends Command {
    protected int helpOption;

    public HelpCommand(int helpOption) {
        this.helpOption = helpOption;
    }

    /**
     * Provides 3 help options to the user at the start, then provides the answer based on the selected option.
     * @param taskList the existing task list of the user
     * @param ui the ui that handles successful/unsuccessful messages
     * @return help dialogue if helpOption is 0 or the answer
     */
    public String execute(TaskList taskList, Ui ui) {
        if (helpOption == 0) {
            return ui.showHelpDialogue();
        } else {
            return ui.showHelpAnswer(helpOption);
        }
    }
}
