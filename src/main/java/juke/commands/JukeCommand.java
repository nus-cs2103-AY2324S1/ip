package juke.commands;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.regex.Pattern;

import juke.core.JukeObject;
import juke.exceptions.JukeException;
import juke.exceptions.arguments.JukeIllegalArgumentException;
import juke.exceptions.arguments.JukeIllegalCommandArgumentException;
import juke.parsers.DateTimeParser;
import juke.parsers.Parser;
import juke.responses.Response;
import juke.tasks.JukeDeadline;
import juke.tasks.JukeEvent;
import juke.tasks.JukeTask;
import juke.tasks.JukeTodo;
import juke.tasks.TaskList;

/**
 * Abstract class used to dispatch commands to the respective commands.
 */
public abstract class JukeCommand extends JukeObject {
    /** Regex to detect "|" in the topic, which is a reserved character for the datafile. */
    private static final String ILLEGAL_TOPIC_REGEX = ".*\\|.*";

    /**
     * Creates the specified {@code JukeCommand} of interest.
     *
     * @param command Raw command from the user input
     * @param taskList {@code TaskList} object which manages all tasks.
     * @return Corresponding {@code JukeCommand} object
     */
    public static JukeCommand of(String command, TaskList taskList) throws JukeException {
        return JukeCommand.dispatchCommand(Parser.parseBySpace(command), taskList);
    }

    /**
     * Dispatches the commands to the necessary subclasses of {@code JukeCommand}.
     *
     * @param args Parsed arguments
     * @param taskList {@code TaskList} object which manages all tasks.
     * @return Corresponding {@code JukeCommand} object
     * @throws JukeIllegalArgumentException if the argument provided is not understood
     */
    private static JukeCommand dispatchCommand(String[] args, TaskList taskList) throws JukeIllegalArgumentException {
        if (args.length == 0) {
            throw new JukeException("Oh no! No commands are present!");
        }

        String mainCommand = args[0];

        switch (mainCommand) {
        case "list":
            return new JukePrintCommand(taskList);
        case "bye":
            return new JukeExitCommand();
        case "mark":
            return JukeCommand.mark(args, taskList);
        case "unmark":
            return JukeCommand.unmark(args, taskList);
        case "delete":
            return JukeCommand.delete(args, taskList);
        case "todo":
            return JukeCommand.todo(args, taskList);
        case "deadline":
            return JukeCommand.deadline(args, taskList);
        case "event":
            return JukeCommand.event(args, taskList);
        case "find":
            return JukeCommand.find(args, taskList);
        default:
            // exits the switch and throws an exception in the proceeding line
            break;
        }

        // runs if no commands are matched
        throw new JukeIllegalArgumentException("Oh no! I do not understand that command!");
    }

    /**
     * Creates a {@code JukeMarkTaskDoneCommand} object.
     *
     * @param args Parsed arguments
     * @param taskList {@code TaskList} object which manages all tasks
     * @return {@code JukeMarkTaskDoneCommand} object
     */
    private static JukeMarkTaskDoneCommand mark(String[] args, TaskList taskList) {
        if (args.length == 1) {
            throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your mark command!",
                                                          "mark [task number]");
        } else {
            try {
                int i = Integer.parseInt(args[1]);
                return new JukeMarkTaskDoneCommand(taskList, i - 1);
            } catch (NumberFormatException ex) {
                throw new JukeIllegalArgumentException("Oh no! You must input a valid task number "
                                                               + "for the command \"mark\"!");
            }
        }
    }

    /**
     * Creates a {@code JukeMarkTaskUndoneCommand} object.
     *
     * @param args Parsed arguments
     * @param taskList {@code TaskList} object which manages all tasks
     * @return {@code JukeMarkTaskUndoneCommand} object
     */
    private static JukeMarkTaskUndoneCommand unmark(String[] args, TaskList taskList) {
        if (args.length == 1) {
            throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your unmark command!",
                                                          "unmark [task number]");
        } else {
            try {
                int i = Integer.parseInt(args[1]);
                return new JukeMarkTaskUndoneCommand(taskList, i - 1);
            } catch (NumberFormatException ex) {
                throw new JukeIllegalArgumentException("Oh no! You must input a valid task number "
                                                               + "for the command \"unmark\"!");
            }
        }
    }

    /**
     * Creates a {@code JukeDeleteTaskCommand} object.
     *
     * @param args Parsed arguments
     * @param taskList {@code TaskList} object which manages all tasks
     * @return {@code JukeDeleteTaskCommand} object
     */
    private static JukeDeleteTaskCommand delete(String[] args, TaskList taskList) {
        if (args.length == 1) {
            throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your delete command!",
                                                          "delete [task number]");
        } else {
            try {
                int i = Integer.parseInt(args[1]);
                return new JukeDeleteTaskCommand(taskList, i - 1);
            } catch (NumberFormatException ex) {
                throw new JukeIllegalArgumentException("Oh no! You must input a valid task number "
                                                               + "for the command \"unmark\"!");
            }
        }
    }

    /**
     * Creates a {@code JukeAddTaskCommand} object.
     *
     * @param args Parsed arguments
     * @param taskList {@code TaskList} object which manages all tasks
     * @return {@code JukeAddTaskCommand} object
     */
    private static JukeAddTaskCommand todo(String[] args, TaskList taskList) {
        if (args.length == 1) {
            // contains only the command text
            throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your todo command!",
                                                          "todo [description]");
        } else {
            // concatenate back the string
            String newArgs = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

            if (Pattern.matches(JukeCommand.ILLEGAL_TOPIC_REGEX, newArgs)) {
                throw new JukeIllegalArgumentException("Oh no! The topic cannot contain the character \"|\"!");
            }

            JukeTask jt = new JukeTodo(newArgs);
            return new JukeAddTaskCommand(taskList, jt);
        }
    }

    /**
     * Creates a {@code JukeAddTaskCommand} object.
     *
     * @param args Parsed arguments
     * @param taskList {@code TaskList} object which manages all tasks
     * @return {@code JukeAddTaskCommand} object
     */
    private static JukeAddTaskCommand deadline(String[] args, TaskList taskList) {
        // concatenate back the string
        String newDeadlineArgs = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        // check if fulfills regex
        if (!Parser.isMatchByString(newDeadlineArgs)) {
            throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your deadline command!",
                                                          "deadline [description] /by [DD(-/)MM(-/)YYYY HH(-:)MM "
                                                                  + "or DD(-/)MM(-/)YYYY]\n(..) -> any of");
        } else {
            String[] parsedArguments = Parser.parseByByString(newDeadlineArgs);

            if (Pattern.matches(JukeCommand.ILLEGAL_TOPIC_REGEX, parsedArguments[0])) {
                throw new JukeIllegalArgumentException("Oh no! The topic cannot contain the character \"|\"!");
            }

            JukeTask jt = new JukeDeadline(parsedArguments[0], DateTimeParser.parse(parsedArguments[1]));
            return new JukeAddTaskCommand(taskList, jt);
        }
    }

    /**
     * Creates a {@code JukeAddTaskCommand} object.
     *
     * @param args Parsed arguments
     * @param taskList {@code TaskList} object which manages all tasks
     * @return {@code JukeAddTaskCommand} object
     */
    private static JukeAddTaskCommand event(String[] args, TaskList taskList) {
        // concatenate back the string
        String newEventArgs = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        // check if fulfills regex
        if (!Parser.isMatchFromToString(newEventArgs)) {
            throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your event command!",
                                                          "event [description] /from [DD(-/)MM(-/)YYYY HH(-:)MM "
                                                                  + "or DD(-/)MM(-/)YYYY]\n"
                                                                  + "/to [DD(-/)MM(-/)YYYY HH(-:)MM or DD(-/)"
                                                                  + "MM(-/)YYYY]\n(..) -> any of");
        } else {
            String[] parsedArguments = Parser.parseByFromToString(newEventArgs);

            if (Pattern.matches(JukeCommand.ILLEGAL_TOPIC_REGEX, parsedArguments[0])) {
                throw new JukeIllegalArgumentException("Oh no! The topic cannot contain the character \"|\"!");
            }

            LocalDateTime localTimeOne = DateTimeParser.parse(parsedArguments[1]);
            LocalDateTime localTimeTwo = DateTimeParser.parse(parsedArguments[2]);

            if (localTimeTwo.isBefore(localTimeOne)) {
                throw new JukeIllegalArgumentException("Oh no! The \"to\" date cannot be before the "
                                                               + "\"from\" date!");
            }

            JukeTask jt = new JukeEvent(parsedArguments[0], localTimeOne, localTimeTwo);
            return new JukeAddTaskCommand(taskList, jt);
        }
    }

    /**
     * Creates a {@code JukeFindTaskCommand} object.
     *
     * @param args Parsed arguments
     * @param taskList {@code TaskList} object which manages all tasks
     * @return {@code JukeFindTaskCommand} object
     */
    private static JukeFindTaskCommand find(String[] args, TaskList taskList) {
        if (args.length == 1) {
            throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your find command!",
                                                          "find [word]");
        }

        String newFindArgs = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        return new JukeFindTaskCommand(taskList, newFindArgs);
    }

    /**
     * Carries out an action when the command is executed.
     *
     * @param response {@code Response} object that contains response from Juke and the user
     * @return {@code Response} object that contains response from Juke and the user
     */
    public abstract Response execute(Response response) throws JukeException;
}
