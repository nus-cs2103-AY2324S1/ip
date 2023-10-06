package seedu.duke.parser;

import java.util.ArrayList;

import seedu.duke.datafile.Storage;
import seedu.duke.exceptions.InvalidDeadlineException;
import seedu.duke.exceptions.InvalidEventException;
import seedu.duke.exceptions.InvalidTaskException;
import seedu.duke.exceptions.InvalidTaskIndexException;
import seedu.duke.exceptions.InvalidTodoException;
import seedu.duke.exceptions.KeywordNotFoundException;
import seedu.duke.exceptions.LemonException;
import seedu.duke.exceptions.NoTasksException;
import seedu.duke.tasklist.TaskList;
import seedu.duke.tasks.Deadline;
import seedu.duke.tasks.Event;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.Todo;
import seedu.duke.ui.Ui;



/**
 * The parser class handles all the processing of commands from the user input.
 */
public class Parser {

    enum Commands {
        BYE,
        MARK,
        UNMARK,
        DELETE,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        FIND,
        RESCHEDULE
    }

    /**
     * Calls methods from ui, tasks, storage to handle the actions corresponding to the commands.
     * @param input the command input by user.
     * @param tasks a TaskList with the tasks by user.
     * @param tasksData the Storage that loads, add and delete tasks.
     * @param ui the Ui object that deals with user input & output messages to users.
     * @throws LemonException thrown when commands are invalid.
     */
    public static String parseTasks(String input, TaskList tasks, Storage tasksData, Ui ui) throws LemonException {
        if (!input.equals("bye")) {
            String commandType = identifyCommand(input);
            return doTask(input, tasks, tasksData, ui, commandType);
        } else {
            javafx.application.Platform.exit();
            return ui.bye();
        }

    }

    /**
     * Identifies command based on user's input.
     * @param input String of user's input
     * @return String representation of user's command
     */
    private static String identifyCommand(String input) {
        String commandType = input.split(" ")[0].toUpperCase();
        return commandType;
    }
    /**
     * Completed the task by calling other methods in Parser.
     * @param input String representation of input by user
     * @param tasks TaskList of tasks
     * @param tasksData Storage where tasks details are stored.
     * @param ui Ui used for Lemon.
     * @param commandType type of command specified by user.
     * @return String representation of response to user's command.
     * @throws LemonException thrown when command does not exist
     */
    private static String doTask(String input, TaskList tasks, Storage tasksData, Ui ui, String commandType)
            throws LemonException {
        try {
            Commands command = Commands.valueOf(commandType);
            switch (command) {
            case LIST:
                return list(tasks, ui);
            case MARK:
                return mark(input, tasks, tasksData, ui);
            case UNMARK:
                return unMark(input, tasks, tasksData, ui);
            case TODO:
                return todo(input, tasks, tasksData, ui);
            case DEADLINE:
                return deadline(input, tasks, tasksData, ui);
            case EVENT:
                return event(input, tasks, tasksData, ui);
            case DELETE:
                return delete(input, tasks, tasksData, ui);
            case FIND:
                return find(input, tasks, ui);
            case RESCHEDULE:
                return reschedule(input, tasks, tasksData, ui);
            default:
                throw new InvalidTaskException(" " + input + " ");
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidTaskException(" " + input + " ");
        }
    }

    /**
     * To return a list of tasks to users.
     * @param tasks TaskList with all the tasks
     * @param ui Ui instance that is used
     * @return String representation of all the tasks
     * @throws NoTasksException thrown when there are no tasks in the task list.
     * @throws LemonException thrown when there is an error loading tasks.
     */
    private static String list(TaskList tasks, Ui ui) throws NoTasksException, LemonException {
        if (tasks.getTasksSize() < 1) {
            throw new NoTasksException("");
        }
        return ui.listAll(tasks);
    }
    /**
     * To mark a particular task as done.
     * @param input user's input after command "mark"
     * @param tasks TaskList with all the tasks.
     * @param tasksData Storage that stores all the data of user.
     * @param ui Ui instance that is used
     * @return String representation of response of Lemon after task is marked done.
     * @throws InvalidTaskIndexException thrown when users try to mark a non-existent task
     *                                  as done.
     */

    private static String mark(String input, TaskList tasks, Storage tasksData, Ui ui)
            throws InvalidTaskIndexException {
        try {
            int indexToMark = Integer.valueOf(input.split(" ")[1]);
            String taskDescription = tasks.markTask(indexToMark - 1);
            tasksData.saveTasks(tasks.getTaskList());
            return ui.markPrint(taskDescription, tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("");
        }
    }

    /**
     * To mark a task as not completed
     * @param input user's input after command "unmark"
     * @param tasks TaskList of user's tasks
     * @param tasksData Storage of user's tasks.
     * @param ui Ui instance that is used.
     * @return String representation of response of Lemon after task is marked as not done.
     * @throws InvalidTaskIndexException thrown when users try to mark a non-existent task
     *                                  as done.
     */
    private static String unMark(String input, TaskList tasks, Storage tasksData, Ui ui)
            throws InvalidTaskIndexException {
        try {
            int indexToUnmark = Integer.valueOf(input.split(" ")[1]);
            String unmarkTaskDescription = tasks.unmarkTask(indexToUnmark - 1);
            tasksData.saveTasks(tasks.getTaskList());
            return ui.unmarkPrint(unmarkTaskDescription, tasks);

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("");
        }
    }

    /**
     * To add a todo task.
     * @param input user's input after command "todo"
     * @param tasks TaskList of user's tasks
     * @param tasksData Storage of user's tasks.
     * @param ui Ui instance that is used.
     * @return String representation of response of Lemon after todo task is added.
     * @throws InvalidTodoException thrown when todo task to add is empty
     */

    private static String todo(String input, TaskList tasks, Storage tasksData, Ui ui)
            throws InvalidTodoException {
        String[] taskSplit = input.split(" ", 2);
        if (taskSplit.length < 2) {
            throw new InvalidTodoException("");
        }
        String taskDescription = taskSplit[1];
        String taskDescriptionTodo = tasks.addTasks(new Todo(taskDescription));
        tasksData.saveTasks(tasks.getTaskList());
        return ui.addTasks(taskDescriptionTodo, tasks);
    }

    /**
     * To add a deadline task.
     * @param input user's input after command "deadline"
     * @param tasks TaskList of user's tasks
     * @param tasksData Storage of user's tasks.
     * @param ui Ui instance that is used.
     * @return String representation of response of Lemon after deadline task is added.
     * @throws InvalidDeadlineException thrown when there is no deadline date added.
     */
    private static String deadline(String input, TaskList tasks, Storage tasksData, Ui ui)
            throws InvalidDeadlineException {
        String task = input.split(" ", 2)[1];
        String[] getDeadlineArray = task.split("/by ", 2);
        if (getDeadlineArray.length < 2) {
            throw new InvalidDeadlineException("");
        }
        String taskDesc = getDeadlineArray[0];
        String by = getDeadlineArray[1];
        Task newDeadlineTask = new Deadline(taskDesc, by);
        String taskDescriptionDeadline = tasks.addTasks(newDeadlineTask);
        tasksData.saveTasks(tasks.getTaskList());
        return ui.addTasks(taskDescriptionDeadline, tasks);
    }

    /**
     * To add an event task.
     * @param input user's input after command "event"
     * @param tasks TaskList of user's tasks
     * @param tasksData Storage of user's tasks.
     * @param ui Ui instance that is used.
     * @return String representation of response of Lemon after event task is added.
     * @throws InvalidEventException thrown when there is no valid start/ end date of event.
     */
    private static String event(String input, TaskList tasks, Storage tasksData, Ui ui)
            throws InvalidEventException {
        String inputTask = input.split(" ", 2)[1];
        String[] getEventFromArray = inputTask.split("/from ", 2);
        if (getEventFromArray.length < 2) {
            throw new InvalidEventException("");
        }
        String taskDetails = getEventFromArray[0];
        String[] getEventToArray = getEventFromArray[1].split(" /to ", 2);
        if (getEventToArray.length < 2) {
            throw new InvalidEventException("");
        }
        String from = getEventToArray[0];
        String to = getEventToArray[1];
        Task newEventTask = new Event(taskDetails, from, to);
        String taskDescriptionEvent = tasks.addTasks(newEventTask);
        tasksData.saveTasks(tasks.getTaskList());
        return ui.addTasks(taskDescriptionEvent, tasks);
    }

    /**
     * To delete a task.
     * @param input user's input after command "delete"
     * @param tasks TaskList of user's tasks
     * @param tasksData Storage of user's tasks.
     * @param ui Ui instance that is used.
     * @return String representation of response of Lemon after task is deleted.
     * @throws InvalidTaskIndexException thrown when task to delete does not exist.
     */
    private static String delete(String input, TaskList tasks, Storage tasksData, Ui ui)
            throws InvalidTaskIndexException {
        int inputDelete = Integer.valueOf(input.split(" ", 2)[1]) - 1;
        try {
            Task taskToDelete = tasks.getTask(inputDelete);
            String taskDescriptionDelete = tasks.deleteTask(taskToDelete);
            tasksData.saveTasks(tasks.getTaskList());
            return ui.deletePrint(taskDescriptionDelete, tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("");
        }
    }

    /**
     * To search for tasks with the keyword in description
     * @param input user's input after command "delete"
     * @param tasks TaskList of user's tasks
     * @param ui Ui instance that is used.
     * @return String representation of a list of tasks with the keyword.
     * @throws KeywordNotFoundException thrown when there are no tasks with
     *                              description that match keyword.
     */
    private static String find(String input, TaskList tasks, Ui ui)
            throws KeywordNotFoundException {
        String[] commandSplit = input.split(" ", 2);
        if (commandSplit.length < 2) {
            throw new KeywordNotFoundException("No Keywords to find!");
        }
        String keyword = input.split(" ")[1];
        ArrayList<Task> matchingTasks = tasks.findKeyword(keyword);
        if (matchingTasks.size() == 0) {
            throw new KeywordNotFoundException("No Tasks found!");
        }
        return ui.listMatching(matchingTasks);
    }

    /**
     * To reschedule a deadline task.
     * @param input user's input after command "reschedule"
     * @param tasks TaskList of user's tasks
     * @param tasksData Storage of user's tasks.
     * @param ui Ui instance that is used.
     * @return String representation of the task that has been rescheduled.
     * @throws InvalidTaskException thrown when task to reschedule does not exist.
     * @throws LemonException thrown when reschedule date is not specified.
     */
    private static String reschedule(String input, TaskList tasks, Storage tasksData, Ui ui)
            throws LemonException {
        try {
            String[] indexTask = input.split(" ", 2);
            if (indexTask.length < 2) {
                throw new LemonException("Please include a task number & date to reschedule to!");
            }
            String[] getRescheduleTask = indexTask[1].split(" /to ", 2);
            if (getRescheduleTask.length < 2) {
                throw new LemonException("Please include a date to reschedule to with /to!");
            }
            int indexToReschedule = Integer.valueOf(getRescheduleTask[0]) - 1;
            String rescheduleDate = getRescheduleTask[1];
            tasks.rescheduleTask(indexToReschedule, rescheduleDate);
            tasksData.saveTasks(tasks.getTaskList());
            String rescheduleTaskDescription = tasks.getTask(indexToReschedule).toString();
            return ui.rescheduleDeadline(rescheduleTaskDescription, tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException("");
        }
    }
}
