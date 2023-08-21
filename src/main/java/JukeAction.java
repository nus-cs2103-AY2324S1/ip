package main.java;

import java.util.Arrays;
import java.util.Optional;

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
    public static final JukeAction of(String command, JukeTaskManager taskManager) throws DukeException {
        String[] parsedArgs = JukeParser.parseBySpace(command);
        return JukeAction.dispatch(parsedArgs, taskManager);
    }

    /**
     * Dispatches the commands to the necessary subclasses of JukeAction.
     * @param args Parsed arguments
     * @param taskManager JukeTaskManager object which manages all tasks.
     * @return
     */
    private static JukeAction dispatch(String[] args, JukeTaskManager taskManager) throws DukeException {
        if (args.length == 0) {
            throw new DukeException("Oh no! No commands are present!");
        }

        String mainCommand = args[0];
        String jukeOpError = "";

        switch (mainCommand) {
            case "list":
                return new JukePrintAction(taskManager);
            case "bye":
                return new JukeExitAction();
            case "mark":
                try {
                    int i = Integer.parseInt(args[1]);
                    return new JukeMarkTaskDoneAction(taskManager, i - 1);
                } catch (NumberFormatException ex) {
                    jukeOpError = "Oh no! You must input a valid task number " +
                            "for the command \"mark\"!";
                    break;
                }
            case "unmark":
                try {
                    int i = Integer.parseInt(args[1]);
                    return new JukeMarkTaskUndoneAction(taskManager, i - 1);
                } catch (NumberFormatException ex) {
                    jukeOpError = "Oh no! You must input a valid task number " +
                            "for the command \"unmark\"!";
                    break;
                }
            case "todo":
                if (args.length == 1) {
                    // contains only the command text
                    throw new DukeException("Oh no! I cannot understand your todo command!\n" +
                                                    "Make sure that your command looks like this: \n" +
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
                if (!JukeParser.isMatchByString(newDeadlineArgs)) {
                    throw new DukeException("Oh no! I cannot understand your deadline command!\n" +
                                                    "Make sure that your command looks like this: \n" +
                                                    "deadline [description] /by [deadline]");
                } else {
                    String[] parsedArguments = JukeParser.parseByByString(newDeadlineArgs);
                    JukeTask jt = new JukeDeadline(parsedArguments[0], parsedArguments[1]);
                    return new JukeAddTaskAction(taskManager, jt);
                }
            case "event":
                // concatenate back the string
                String newEventArgs = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

                // check if fulfills regex
                if (!JukeParser.isMatchFromToString(newEventArgs)) {
                    throw new DukeException("Oh no! I cannot understand your event command!\n" +
                                                    "Make sure that your command looks like this: \n" +
                                                    "deadline [description] /from [from time] /to [to time]");
                } else {
                    String[] parsedArguments = JukeParser.parseByFromToString(newEventArgs);
                    JukeTask jt = new JukeEvent(parsedArguments[0], parsedArguments[1], parsedArguments[2]);
                    return new JukeAddTaskAction(taskManager, jt);
                }
        }

        // checks if there are any operational errors
        // note that this behaves similar to exceptions, in that older exceptions are overwritten by
        // newer ones
        if (!jukeOpError.equals("")) {
            throw new DukeException(jukeOpError);
        }

        throw new DukeException("Oh no! I do not understand that command!");
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     * @return Optional<? extends JukeAction> object, which contains further action objects,
     * made this way to ensure that actions can call other actions and thus lead to chains
     * of actions for added complexity
     */
    abstract public Optional<? extends JukeAction> complete() throws DukeException;
}
