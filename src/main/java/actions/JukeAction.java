package main.java.actions;

import main.java.exceptions.JukeException;
import main.java.exceptions.arguments.JukeIllegalCommandArgumentException;
import main.java.parsers.JukeCommandParser;
import main.java.tasks.JukeTaskManager;
import main.java.exceptions.arguments.JukeIllegalArgumentException;
import main.java.primitivies.JukeObject;
import main.java.tasks.JukeDeadline;
import main.java.tasks.JukeEvent;
import main.java.tasks.JukeTask;
import main.java.tasks.JukeTodo;

import java.util.Arrays;

/**
 * Abstract class used to dispatch commands to the respective actions.
 */
public abstract class JukeAction extends JukeObject {
    /**
     * Creates the specified JukeAction of interest.
     * @param command Raw command from the user input
     * @param taskManager JukeTaskManager object which manages all tasks.
     * @return Corresponding JukeAction object
     */
    public static final JukeAction of(String command, JukeTaskManager taskManager) throws JukeException {
        String[] parsedArgs = JukeCommandParser.parseBySpace(command);
        return JukeAction.dispatch(parsedArgs, taskManager);
    }

    /**
     * Dispatches the commands to the necessary subclasses of JukeAction.
     * @param args Parsed arguments
     * @param taskManager JukeTaskManager object which manages all tasks.
     * @return
     */
    private static JukeAction dispatch(String[] args, JukeTaskManager taskManager) throws JukeException {
        if (args.length == 0) {
            throw new JukeException("Oh no! No commands are present!");
        }

        String mainCommand = args[0];
        String jukeOpError = "";

        switch (mainCommand) {
            case "list":
                return new JukePrintAction(taskManager);
            case "bye":
                return new JukeExitAction();
            case "mark":
                if (args.length == 1) {
                    throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your mark command!",
                                                                  "mark [task number]");
                } else {
                    try {
                        int i = Integer.parseInt(args[1]);
                        return new JukeMarkTaskDoneAction(taskManager, i - 1);
                    } catch (NumberFormatException ex) {
                        jukeOpError = "Oh no! You must input a valid task number " +
                                "for the command \"mark\"!";
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
                        return new JukeMarkTaskUndoneAction(taskManager, i - 1);
                    } catch (NumberFormatException ex) {
                        jukeOpError = "Oh no! You must input a valid task number " +
                                "for the command \"unmark\"!";
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
                        return new JukeDeleteTaskAction(taskManager, i - 1);
                    } catch (NumberFormatException ex) {
                        jukeOpError = "Oh no! You must input a valid task number " +
                                "for the command \"unmark\"!";
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
                    return new JukeAddTaskAction(taskManager, jt);
                }
            case "deadline":
                // concatenate back the string
                String newDeadlineArgs = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

                // check if fulfills regex
                if (!JukeCommandParser.isMatchByString(newDeadlineArgs)) {
                    throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your deadline command!",
                                                           "deadline [description] /by [deadline]");
                } else {
                    String[] parsedArguments = JukeCommandParser.parseByByString(newDeadlineArgs);
                    JukeTask jt = new JukeDeadline(parsedArguments[0], parsedArguments[1]);
                    return new JukeAddTaskAction(taskManager, jt);
                }
            case "event":
                // concatenate back the string
                String newEventArgs = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

                // check if fulfills regex
                if (!JukeCommandParser.isMatchFromToString(newEventArgs)) {
                    throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your event command!",
                                                           "event [description] /from [from time] /to [to time]");
                } else {
                    String[] parsedArguments = JukeCommandParser.parseByFromToString(newEventArgs);
                    JukeTask jt = new JukeEvent(parsedArguments[0], parsedArguments[1], parsedArguments[2]);
                    return new JukeAddTaskAction(taskManager, jt);
                }
        }

        // checks if there are any operational errors
        // note that this behaves similar to exceptions, in that older exceptions are overwritten by
        // newer ones
        if (!jukeOpError.isEmpty()) {
            throw new JukeException(jukeOpError);
        }

        throw new JukeException("Oh no! I do not understand that command!");
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     */
    abstract public void complete() throws JukeException;
}
