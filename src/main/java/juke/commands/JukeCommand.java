package juke.commands;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.regex.Pattern;

import juke.commons.DateUtils;
import juke.commons.classes.JukeObject;
import juke.commons.enums.SortOrderEnum;
import juke.commons.enums.SortTypeEnum;
import juke.commons.exceptions.JukeException;
import juke.commons.exceptions.arguments.JukeIllegalArgumentException;
import juke.commons.exceptions.arguments.JukeIllegalCommandArgumentException;
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
    //@@author asdfghjkxd-reused
    // Regex strings are reused with major modification from ChatGPT, and is built and tested with
    // https://regex101.com/.
    /**
     * Regex to detect "|" in the topic, which is a reserved character for the datafile.
     * <p>
     * Adapted from ChatGPT.
     */
    private static final String ILLEGAL_TOPIC_REGEX = ".*\\|.*";
    //@@author

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
            return JukeCommand.list(args, taskList);
        case "bye":
            return JukeCommand.exit(args);
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
        case "sort":
            return JukeCommand.sort(args, taskList);
        default:
            // exits the switch and throws an exception in the proceeding line
            throw new JukeIllegalArgumentException("Oh no! I do not understand that command!");
        }
    }

    /**
     * Creates a {@code JukePrintCommand} object. Command will print out the task list.
     *
     * @param args Parsed arguments
     * @param taskList {@code TaskList} object which manages all tasks
     * @return {@code JukePrintCommand} object
     */
    private static JukePrintCommand list(String[] args, TaskList taskList) {
        if (args.length != 1) {
            throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your list command!",
                                                          "list");
        }

        return new JukePrintCommand(taskList);
    }

    /**
     * Creates a {@code JukeExitCommand} object. Command will exit Juke.
     *
     * @param args Parsed arguments
     * @return {@code JukeExitCommand} object
     */
    private static JukeExitCommand exit(String[] args) {
        if (args.length != 1) {
            throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your bye command!",
                                                          "bye");
        }

        return new JukeExitCommand();
    }

    /**
     * Creates a {@code JukeMarkTaskDoneCommand} object. Command will mark a task as complete.
     *
     * @param args Parsed arguments
     * @param taskList {@code TaskList} object which manages all tasks
     * @return {@code JukeMarkTaskDoneCommand} object
     */
    private static JukeMarkTaskDoneCommand mark(String[] args, TaskList taskList) {
        if (args.length == 1 || args.length > 2) {
            throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your mark command!",
                                                          "mark [task number]");
        } else {
            try {
                int index = Integer.parseInt(args[1]);
                return new JukeMarkTaskDoneCommand(taskList, index - 1);
            } catch (NumberFormatException ex) {
                throw new JukeIllegalArgumentException("Oh no! You must input a valid task number "
                                                               + "for the command \"mark\"!");
            }
        }
    }

    /**
     * Creates a {@code JukeMarkTaskUndoneCommand} object. Command will mark a task as incomplete.
     *
     * @param args Parsed arguments
     * @param taskList {@code TaskList} object which manages all tasks
     * @return {@code JukeMarkTaskUndoneCommand} object
     */
    private static JukeMarkTaskUndoneCommand unmark(String[] args, TaskList taskList) {
        if (args.length == 1 || args.length > 2) {
            throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your unmark command!",
                                                          "unmark [task number]");
        } else {
            try {
                int index = Integer.parseInt(args[1]);
                return new JukeMarkTaskUndoneCommand(taskList, index - 1);
            } catch (NumberFormatException ex) {
                throw new JukeIllegalArgumentException("Oh no! You must input a valid task number "
                                                               + "for the command \"unmark\"!");
            }
        }
    }

    /**
     * Creates a {@code JukeDeleteTaskCommand} object. Command will delete a task.
     *
     * @param args Parsed arguments
     * @param taskList {@code TaskList} object which manages all tasks
     * @return {@code JukeDeleteTaskCommand} object
     */
    private static JukeDeleteTaskCommand delete(String[] args, TaskList taskList) {
        if (args.length == 1 || args.length > 2) {
            throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your delete command!",
                                                          "delete [task number]");
        } else {
            try {
                int index = Integer.parseInt(args[1]);
                return new JukeDeleteTaskCommand(taskList, index - 1);
            } catch (NumberFormatException ex) {
                throw new JukeIllegalArgumentException("Oh no! You must input a valid task number "
                                                               + "for the command \"unmark\"!");
            }
        }
    }

    /**
     * Creates a {@code JukeAddTaskCommand} object. Command will add a Todo task.
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
            //@@author asdfghjkxd-reused
            // Method is reused from https://www.spigotmc.org/threads/how-to-combine-args.239109/
            String newArgs = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
            //@@author

            if (Pattern.matches(JukeCommand.ILLEGAL_TOPIC_REGEX, newArgs)) {
                throw new JukeIllegalArgumentException("Oh no! The input cannot contain the character \"|\"!");
            }

            JukeTask jt = new JukeTodo(newArgs);
            return new JukeAddTaskCommand(taskList, jt);
        }
    }

    /**
     * Creates a {@code JukeAddTaskCommand} object. Command will add a Deadline task.
     *
     * @param args Parsed arguments
     * @param taskList {@code TaskList} object which manages all tasks
     * @return {@code JukeAddTaskCommand} object
     */
    private static JukeAddTaskCommand deadline(String[] args, TaskList taskList) {
        // concatenate back the string
        //@@author asdfghjkxd-reused
        // Method is reused from https://www.spigotmc.org/threads/how-to-combine-args.239109/
        String newDeadlineArgs = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        //@@author

        // check if fulfills regex
        if (!Parser.isMatchByString(newDeadlineArgs)) {
            throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your deadline command!",
                                                          "deadline [description] /by [DD(-/)MM(-/)YYYY HH(-:)MM "
                                                                  + "or DD(-/)MM(-/)YYYY]\nNote: (..) -> any of");
        } else {
            String[] parsedArguments = Parser.parseByByString(newDeadlineArgs);

            if (Pattern.matches(JukeCommand.ILLEGAL_TOPIC_REGEX, parsedArguments[0])) {
                throw new JukeIllegalArgumentException("Oh no! The input cannot contain the character \"|\"!");
            }

            JukeTask jt = new JukeDeadline(parsedArguments[0], DateTimeParser.parse(parsedArguments[1]));
            return new JukeAddTaskCommand(taskList, jt);
        }
    }

    /**
     * Creates a {@code JukeAddTaskCommand} object. Command will add a Event task.
     *
     * @param args Parsed arguments
     * @param taskList {@code TaskList} object which manages all tasks
     * @return {@code JukeAddTaskCommand} object
     */
    private static JukeAddTaskCommand event(String[] args, TaskList taskList) {
        // concatenate back the string
        //@@author asdfghjkxd-reused
        // Method is reused from https://www.spigotmc.org/threads/how-to-combine-args.239109/
        String newEventArgs = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        //@@author

        // check if fulfills regex
        if (!Parser.isMatchFromToString(newEventArgs)) {
            throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your event command!",
                                                          "event [description] /from [DD(-/)MM(-/)YYYY HH(-:)MM "
                                                                  + "or DD(-/)MM(-/)YYYY] "
                                                                  + "/to [DD(-/)MM(-/)YYYY HH(-:)MM or DD(-/)"
                                                                  + "MM(-/)YYYY]\nNote: (..) -> any of");
        } else {
            String[] parsedArguments = Parser.parseByFromToString(newEventArgs);

            if (Pattern.matches(JukeCommand.ILLEGAL_TOPIC_REGEX, parsedArguments[0])) {
                throw new JukeIllegalArgumentException("Oh no! The input cannot contain the character \"|\"!");
            }

            LocalDateTime startTime = DateTimeParser.parse(parsedArguments[1]);
            LocalDateTime endTime = DateTimeParser.parse(parsedArguments[2]);

            if (DateUtils.isAfter(startTime, endTime)) {
                throw new JukeIllegalArgumentException("Oh no! The \"to\" date cannot be before the "
                                                               + "\"from\" date!");
            }

            JukeTask jt = new JukeEvent(parsedArguments[0], startTime, endTime);
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

        //@@author asdfghjkxd-reused
        // Method is reused from https://www.spigotmc.org/threads/how-to-combine-args.239109/
        String newFindArgs = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        //@@author

        return new JukeFindTaskCommand(taskList, newFindArgs);
    }

    /**
     * Creates a {@code JukeSortListCommand} object.
     *
     * @param args Parsed arguments
     * @param taskList {@code TaskList} object which manages all tasks
     * @return {@code JukeSortListCommand} object
     */
    private static JukeSortListCommand sort(String[] args, TaskList taskList) {
        if (args.length == 1 || args.length > 3) {
            throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your sort command!",
                                                          "sort [a/asc/ascend/ascending | "
                                                                  + "d/desc/descend/descending] "
                                                                  + "[d/des/descript/description | "
                                                                  + "dl/dead/deadln/deadline | "
                                                                  + "s/st/start | "
                                                                  + "e/en/end]");
        }

        try {
            //@@author asdfghjkxd-reused
            // Method is reused from https://www.spigotmc.org/threads/how-to-combine-args.239109/
            String newSortArgs = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
            //@@author

            SortOrderEnum sortOrder = SortOrderEnum.ofOrder(args[1].toLowerCase());
            SortTypeEnum sortType = SortTypeEnum.ofType(newSortArgs.toLowerCase());
            return new JukeSortListCommand(sortOrder, sortType, taskList);
        } catch (IllegalArgumentException ex) {
            throw new JukeIllegalCommandArgumentException("Oh no! I cannot understand your sort command!",
                                                          "sort [a/asc/ascend/ascending | "
                                                                  + "d/desc/descend/descending] "
                                                                  + "[d/des/descript/description | "
                                                                  + "dl/dead/deadln/deadline | "
                                                                  + "s/st/start | "
                                                                  + "e/en/end]");
        }
    }

    /**
     * Invokes an action when the command is executed.
     *
     * @param response {@code Response} object that contains response from Juke and the user
     * @return {@code Response} object composed with response from Juke or the user
     * @throws JukeException or any of its subclasses if there are any issues encountered
     *     during the execution of code
     */
    public abstract Response execute(Response response) throws JukeException;
}
