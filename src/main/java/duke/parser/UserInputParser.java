package duke.parser;

import duke.Action;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidInputException;
import duke.message.ByeMessage;
import duke.task.DeadlinesTask;
import duke.task.EventsTask;
import duke.task.TaskList;
import duke.task.TodoTask;
import duke.templates.MessageTemplates;

import java.util.regex.Pattern;

public class UserInputParser {
    public static boolean isActive = true;
    private static Action getAction(String userInput) throws InvalidInputException, InvalidCommandException {
        if (userInput.equals("bye")) {
            return Action.BYE;
        }
        if (userInput.equals("list")) {
            return Action.LIST;
        }
        if (Pattern.matches("mark \\d+", userInput)) {
            return Action.MARK;
        }
        if (Pattern.matches("unmark \\d+", userInput)) {
            return Action.UNMARK;
        }
        if (Pattern.matches("^todo\\s*$", userInput)) {
            throw new InvalidInputException(MessageTemplates.MESSAGE_INVALID_TODO);
        }
        if (Pattern.matches("todo .+", userInput)) {
            return Action.TODO;
        }
        if (Pattern.matches("deadline .+ /by .+", userInput)) {
            return Action.DEADLINE;
        }
        if (Pattern.matches("event .+ /from .+ /to .+", userInput)) {
            return Action.EVENT;
        }
        if (Pattern.matches("delete \\d+", userInput)) {
            return Action.DELETE;
        }
        throw new InvalidCommandException();
    }
    public static void parse(String userInput, TaskList taskList)
            throws InvalidInputException, InvalidCommandException, InvalidIndexException {
        Action action = UserInputParser.getAction(userInput);
        int num;
        String name, deadline, from, to;
        String[] a1, a2;
        switch (action) {
        case BYE:
            isActive = false;
            new ByeMessage().send();
            break;
        case LIST:
            taskList.printList();
            break;
        case MARK:
            num = Integer.parseInt(userInput.split(" ", 2)[1]);
            taskList.markTask(num);
            break;
        case UNMARK:
            num = Integer.parseInt(userInput.split(" ", 2)[1]);
            taskList.unmarkTask(num);
            break;
        case TODO:
            name = userInput.split(" ", 2)[1];
            taskList.add(new TodoTask(name, false));
            break;
        case DEADLINE:
            // assumes " /by " is not contained in deadline name
            a1 = userInput.split(" /by ", 2);
            name = a1[0].split(" ", 2)[1];
            deadline = DateTimeParser.parseDateTime(a1[1]);
            taskList.add(new DeadlinesTask(name, false, deadline));
            break;
        case EVENT:
            // assumes " /to " is not in event name and from date
            a1 = userInput.split(" /to ", 2);
            // assumes " /from " is not in event name
            a2 = a1[0].split(" /from ", 2);
            name = a2[0].split(" ", 2)[1];
            from = DateTimeParser.parseDateTime(a2[1]);
            to = DateTimeParser.parseDateTime(a1[1]);
            if (!DateTimeParser.isValidPeriod(from, to)) {
                throw new InvalidInputException(MessageTemplates.MESSAGE_INVALID_EVENT_PERIOD);
            }
            taskList.add(new EventsTask(name, false, from, to));
            break;
        case DELETE:
            num = Integer.parseInt(userInput.split(" ", 2)[1]);
            taskList.delete(num);
            break;
        }
    }
}
