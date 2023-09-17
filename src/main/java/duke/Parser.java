package duke;

import command.*;
import exception.DukeException;

/**
 * Class that handles userInput and return appropriate action.
 *
 * @author syamfarh
 */
public class Parser {

    /**
     * parse UserInput and passed the result to ui to output the result.
     *
     * @param input User input from main.
     * @param tasks current Tasklist from chatBot.
     * @param ui ui that control output result to user.
     */
    public static String replyUser(String input, TaskList tasks, Ui ui) throws DukeException {
        String result = "";
        String[] inputArr = input.split(" ");

        switch (inputArr[0]) {
        case "bye":
            result = ui.exitGreeting();
            break;
        case "barbie":
            result = ui.customReply();
            break;
        case "list":
            result = ui.outputList(tasks);
            break;
        case "help":
            result = ui.helpGenerator();
            break;
        case "mark":
            result = MarkCommand.process(input, tasks, ui);
            break;
        case "unmark":
            result = UnMarkCommand.process(input, tasks, ui);
            break;
        case "find":
            result = FindCommand.process(input, tasks, ui);
            break;
        case "todo":
            result = ToDoCommand.process(input, tasks, ui);
            break;
        case "deadline":
            result = DeadlineCommand.process(input, tasks, ui);
            break;
        case "event":
            result = EventCommand.process(input, tasks, ui);
            break;
        case "delete":
            result = DeleteCommand.process(input, tasks, ui);
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(",
                new RuntimeException());
        }

        return result;

    }

}
