package main.java.actions;

import main.java.JukeObject;
import main.java.tasks.JukeTask;
import main.java.tasks.JukeTaskManager;

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
    public static final JukeAction of(String command, JukeTaskManager taskManager) {
        String[] parsedArgs = JukeAction.parse(command);
        return JukeAction.dispatch(parsedArgs, taskManager);
    }

    /**
     * Dispatches the commands to the necessary subclasses of JukeAction.
     * @param args Parsed arguments
     * @param taskManager JukeTaskManager object which manages all tasks.
     * @return
     */
    private static JukeAction dispatch(String[] args, JukeTaskManager taskManager) {
        if (args.length == 0) {
            return new JukeErrorAction("No commands are present!");
        }

        if (args.length == 1) {
            switch (args[0]) {
                case "list":
                    return new JukePrintAction(taskManager);
                case "bye":
                    return new JukeExitAction();
            }
        } else {
            String mainAction = args[0];
            switch (mainAction) {
                case "mark":
                    try {
                        int i = Integer.parseInt(args[1]);
                        return new JukeMarkTaskDoneAction(taskManager, i - 1);
                    } catch (NumberFormatException ex) {
                        return new JukeErrorAction("Oh no! You must input a valid task number " +
                                                           "for the command \"mark\"!");
                    } catch (IndexOutOfBoundsException ex) {
                        return new JukeErrorAction("Oh no! The task you have referenced " +
                                                           "does not exist!");
                    }
                case "unmark":
                    try {
                        int i = Integer.parseInt(args[1]);
                        return new JukeMarkTaskUndoneAction(taskManager, i - 1);
                    } catch (NumberFormatException ex) {
                        return new JukeErrorAction("Oh no! You must input a valid task number " +
                                                           "for the command \"unmark\"!");
                    }
                default:
                    return new JukeAddTaskAction(taskManager, JukeTask.of(String.join(" ", args)));
            }


        }

        return new JukeErrorAction("No commands are present!");
    }

    /**
     * Parses the input and returns to the caller a sanitised list of commands given to
     * Juke.
     *
     * @param command Raw String command
     * @return String[] containing list of String commands
     */
    private static String[] parse(String command) {
        // note: by default, commands are split by the space character
        return command.strip().split(" ");
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     * @return Optional<? extends JukeAction> object, which contains further action objects,
     * made this way to ensure that actions can call other actions and thus lead to chains
     * of actions for added complexity
     */
    abstract public Optional<? extends JukeAction> complete();
}
