package juke.commands;

import java.time.LocalDateTime;
import java.util.Arrays;

import juke.core.JukeObject;
import juke.exceptions.JukeException;
import juke.exceptions.arguments.JukeIllegalArgumentException;
import juke.exceptions.arguments.JukeIllegalCommandArgumentException;
import juke.parsers.DateTimeParser;
import juke.parsers.Parser;
import juke.tasks.JukeDeadline;
import juke.tasks.JukeEvent;
import juke.tasks.JukeTask;
import juke.tasks.JukeTodo;
import juke.tasks.TaskList;

/**
 * Abstract class used to dispatch commands to the respective commands.
 */
public abstract class JukeCommand extends JukeObject {
    /**
     * Creates the specified JukeCommand of interest.
     * @param command Raw command from the user input
     * @param taskList TaskList object which manages all tasks.
     * @return Corresponding JukeCommand object
     */
    public static final JukeCommand of(String command, TaskList taskList) throws JukeException {
        return JukeCommand.dispatchCommand(Parser.parseBySpace(command), taskList);
    }

    /**
     * Dispatches the commands to the necessary subclasses of JukeCommand.
     * @param args Parsed arguments
     * @param taskList TaskList object which manages all tasks.
     * @return Corresponding JukeCommand object
     */
    private static JukeCommand dispatchCommand(String[] args, TaskList taskList) throws JukeException {
        if (args.length == 0) {
            throw new JukeException("Oh no! No commands are present!");
        }

        String mainCommand = args[0];
        String jukeOpError = "";

        switch (mainCommand) {
        case "list":
            return new JukePrintCommand(taskList);
        case "bye":
            return new JukeExitCommand();
        case "mark":
            if (args.length == 1) {
                throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your mark command!",
                                                              "mark [task number]");
            } else {
                try {
                    int i = Integer.parseInt(args[1]);
                    return new JukeMarkTaskDoneCommand(taskList, i - 1);
                } catch (NumberFormatException ex) {
                    jukeOpError = "Oh no! You must input a valid task number "
                            + "for the command \"mark\"!";
                    break;
                }
            }
        case "unmark":
            if (args.length == 1) {
                throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your unmark command!",
                                                              "unmark [task number]");
            } else {
                try {
                    int i = Integer.parseInt(args[1]);
                    return new JukeMarkTaskUndoneCommand(taskList, i - 1);
                } catch (NumberFormatException ex) {
                    jukeOpError = "Oh no! You must input a valid task number "
                            + "for the command \"unmark\"!";
                    break;
                }
            }
        case "delete":
            if (args.length == 1) {
                throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your delete command!",
                                                       "delete [task number]");
            } else {
                try {
                    int i = Integer.parseInt(args[1]);
                    return new JukeDeleteTaskCommand(taskList, i - 1);
                } catch (NumberFormatException ex) {
                    jukeOpError = "Oh no! You must input a valid task number "
                            + "for the command \"unmark\"!";
                    break;
                }
            }
        case "todo":
            if (args.length == 1) {
                // contains only the command text
                throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your todo command!",
                                                       "todo [description]");
            } else {
                // concatenate back the string
                String newArgs = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                JukeTask jt = new JukeTodo(newArgs);
                return new JukeAddTaskCommand(taskList, jt);
            }
        case "deadline":
            // concatenate back the string
            String newDeadlineArgs = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

            // check if fulfills regex
            if (!Parser.isMatchByString(newDeadlineArgs)) {
                throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your deadline command!",
                                                       "deadline [description] /by [DD(-/|)MM(-/|)YYYY HH(-:)MM "
                                                               + "or DD(-/|)MM(-/|)YYYY]\n(..) -> any of");
            } else {
                String[] parsedArguments = Parser.parseByByString(newDeadlineArgs);
                JukeTask jt = new JukeDeadline(parsedArguments[0], DateTimeParser.parse(parsedArguments[1]));
                return new JukeAddTaskCommand(taskList, jt);
            }
        case "event":
            // concatenate back the string
            String newEventArgs = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

            // check if fulfills regex
            if (!Parser.isMatchFromToString(newEventArgs)) {
                throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your event command!",
                                                       "event [description] /from [DD(-/|)MM(-/|)YYYY HH(-:)MM "
                                                               + "or DD(-/|)MM(-/|)YYYY]\n"
                                                               + "/to [DD(-/|)MM(-/|)YYYY HH(-:)MM or DD(-/|)"
                                                               + "MM(-/|)YYYY]\n(..) -> any of");
            } else {
                String[] parsedArguments = Parser.parseByFromToString(newEventArgs);
                LocalDateTime localTimeOne = DateTimeParser.parse(parsedArguments[1]);
                LocalDateTime localTimeTwo = DateTimeParser.parse(parsedArguments[2]);

                if (localTimeTwo.isBefore(localTimeOne)) {
                    throw new JukeIllegalArgumentException("Oh no! The \"to\" date cannot be before the "
                                                                   + "\"from\" date!");
                }

                JukeTask jt = new JukeEvent(parsedArguments[0], localTimeOne, localTimeTwo);
                return new JukeAddTaskCommand(taskList, jt);
            }
        default:
            jukeOpError = "Oh no! I do not understand that command!";
            break;
        }

        // checks if there are any operational errors
        // note that this behaves similar to exceptions, in that older exceptions are overwritten by
        // newer ones
        throw new JukeIllegalArgumentException(jukeOpError);
    }

    /**
     * Necessary method that is invoked when the command is carried out.
     */
    public abstract void execute() throws JukeException;
}
