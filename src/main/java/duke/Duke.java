package duke;

import duke.common.Command;
import duke.ui.Exit;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Represents the Duke chatbot.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Exit exit;

    /**
     * Creates a Duke object.
     */
    public Duke() {
        storage = new Storage();
        exit = new Exit();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns the response to the user input.
     * @param input The user input.
     * @return The response to the user input.
     * @throws DukeException If the user input is invalid.
     */
    public String getResponse(String input) throws DukeException {
        Parser parser = new Parser(input);
        String keyword = parser.getCommand();
        Command command = new Command();
        String message = retrieveMessage(keyword, command, parser);
        this.storage.saveTasks(this.tasks.getTasks());
        assert !message.isEmpty() : "Message should not be empty";
        return message;
    }

    /**
     * Returns the message.
     * @param keyword The command keyword.
     * @param command The command.
     * @param parser The parser.
     * @return The message.
     * @throws DukeException If the command is invalid.
     */
    public String retrieveMessage(String keyword, Command command, Parser parser) throws DukeException {
        String message;
        switch (keyword) {
        case "BYE": {
            message = command.handleFarewell();
            exit.start();
            break;
        }
        case "LIST": {
            message = command.handleList(tasks);
            break;
        }
        case "UNMARK": {
            message = command.handleUnmarkTask(tasks, parser.getTaskNumber());
            break;
        }
        case "MARK": {
            message = command.handleMarkTask(tasks, parser.getTaskNumber());
            break;
        }
        case "TODO": {
            message = command.handleAddTodo(tasks, parser.getContentForTodo());
            break;
        }
        case "DEADLINE": {
            String[] inputList = parser.getContentForDeadline();
            message = command.handleAddDeadline(tasks, inputList[0], parser.parseDateTime(inputList[1]));
            break;
        }
        case "EVENT": {
            String[] inputList = parser.getContentForEvent();
            message = command.handleAddEvent(tasks, inputList[0], parser.parseDateTime(inputList[1]),
                    parser.parseDateTime(inputList[2]));
            break;
        }
        case "DELETE": {
            message = command.handleDeleteTask(tasks, parser.getTaskNumber());
            break;
        }
        case "FIND": {
            message = command.handleFindTask(tasks, parser.getSearchTerms());
            break;
        }
        case "TAG": {
            message = command.handleTagTask(tasks, parser.getTaskNumber(), parser.getTags());
            break;
        }
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return message;
    }
}
