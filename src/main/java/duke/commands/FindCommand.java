package duke.commands;

import duke.exceptions.InvalidKeywordException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The `FindCommand` class represents a command for finding tasks that match a keyword.
 * It is responsible for parsing the user input to extract the keyword and then finding
 * tasks that contain that keyword.
 */
public class FindCommand extends Command {
    private String userInput;

    /**
     * Constructs a new `FindCommand` object with the specified user input containing the keyword.
     *
     * @param userInput The user input string containing the keyword for task searching.
     */
    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the "Find" command. It attempts to find tasks in the task list that match the specified keyword,
     * and returns a response message containing the found tasks.
     *
     * @param storage  The storage component responsible for saving and loading task data.
     * @param taskList The task list to search for matching tasks.
     * @param ui       The user interface for displaying messages to the user.
     * @return A response message containing the tasks that match the specified keyword.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            return ui.findTaskReply(userInput, Parser.findKeyword(userInput, taskList));
        } catch (InvalidKeywordException e) {
            return ui.invalidKeywordExceptionReply();
        }
    }
}
