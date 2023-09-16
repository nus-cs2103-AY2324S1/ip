package mattbot.ui;

import mattbot.command.TaskList;
import mattbot.task.Task;


/**
 * Generates responses for MattBot.
 */
public class ResponseEngine {
    private static final String START_MESSAGE = "I am MattBot, your personal digital assistant.\n"
            + " On a scale of 1 to 10, how busy are you?";
    private static final String EXIT_MESSAGE = "See ya real soon!";
    private static final String INSUFFICIENT_ARGS = "Yikes, that ain't long enough!";
    private static final String COMPLETED = "Great job! You've completed the task ";
    private static final String UNCOMPLETED = "Oh no. It looks like you have not completed ";
    private static final String NEW_TODO = "I have added a new Todo to your list, called ";
    private static final String NEW_DEADLINE = "I have added a new Deadline to your list, called ";
    private static final String NEW_EVENT = "I have added a new Event to your list, called ";
    private static final String WRONG_DATE_FORMAT = "Your date is invalid. It should be in the form YYYYMMDDTHHMM."
            + " An example is 20231231T2359.";
    private static final String DELETE_IMPOSSIBLE = "Oops, you're deleting a task that doesn't exist.";
    private static final String DELETE_SUCCESS = "I have remove this task for you. It was ";
    private static final String FIND_FAIL = "Oh no. It looks like I cannot find any matching tasks.";
    private static final String FIND_SUCCESS = "Here's what I have found for you.";
    private static final String DO_NOT_UNDERSTAND = "Oh no. I do not understand your input.";

    /**
     * Create a new ResponseEngine
     */
    public ResponseEngine() {
    }

    /**
     * Returns startup message.
     * @return String with startup message.
     */
    public String getStartMessage() {
        return START_MESSAGE;
    }
    /**
     * Returns exit message.
     * @return String with exit message.
     */
    public String getExitMessage() {
        return EXIT_MESSAGE;
    }

    /**
     * Returns list of tasks in MattBot
     * @param tasks TaskList describing current tasks.
     * @return String of current tasks.
     */
    public String getTasks(TaskList tasks) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.getTask(i + 1);
            output.append(String.format("%d. %s", i + 1, t));
            if (i < tasks.size() - 1) {
                output.append("\n");
            }
        }
        return output.toString();
    }

    /**
     * Returns back error message with insufficient arguments.
     * @param command Command used.
     * @return String with the invalid command.
     */
    public String errMissingArgs(String command) {
        return INSUFFICIENT_ARGS + "\n" + command + " requires an argument.";
    }

    public String getMarked(Task t) {
        return COMPLETED + t.showName();
    }

    public String getUnmarked(Task t) {
        return UNCOMPLETED + t.showName();
    }

    public String getNewTodo(Task t) {
        return NEW_TODO + t.showName();
    }

    public String getNewDeadline(Task t) {
        return NEW_DEADLINE + t.showName();
    }
    public String getNewEvent(Task t) {
        return NEW_EVENT + t.showName();
    }
    public String errWrongDateFormat() {
        return WRONG_DATE_FORMAT;
    }

    public String errImpossibleTask() {
        return DELETE_IMPOSSIBLE;
    }

    public String getDelete(Task t) {
        return DELETE_SUCCESS + t.showName();
    }

    public String getFind(TaskList found) {
        Task t;
        StringBuilder output = new StringBuilder();
        output.append("I have found " + found.size() + " tasks matching your search!\n");
        output.append(FIND_SUCCESS);
        for (int i = 0; i < found.size(); i++) {
            t = found.getTask(i + 1);
            output.append(String.format("%d. %s", i + 1, t));
            if (i < found.size() - 1) {
                output.append("\n");
            }
        }
        return output.toString();
    }

    public String errFind() {
        return FIND_FAIL;
    }
    public String errParseUnsure() {
        return DO_NOT_UNDERSTAND;
    }
}
