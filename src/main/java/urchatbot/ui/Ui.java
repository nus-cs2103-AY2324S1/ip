package urchatbot.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import urchatbot.exception.URChatBotException;
import urchatbot.taskList.TaskList;

import static urchatbot.common.Messages.*;


/**
 * Deals with interactions with the user
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;

    /**
     * Construct the Ui class.
     */
    public Ui() {
        this(System.in, System.out);
    }
    /**
     * Construct the Ui class in an alternative way.
     *
     * @param in Input by the users.
     * @param out Output by the App.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }


    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public String showWelcome() {
        return MESSAGE_WELCOME;
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine Full raw user input line.
     * @return true If the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }
    private boolean isCommentLine(String rawInputLine) {
        return rawInputLine.toUpperCase().trim().matches("\\b(?: BYE | DELETE | CLEAR |"
                + " LIST | MARK | UNMARK | FIND"
                + " TODO | DEADLINE | EVENT | PRINT)\\b");
    }


    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     */
    public String readCommand() throws URChatBotException {
        String fullInputLine = in.nextLine();

        if (shouldIgnore(fullInputLine)) {
            throw new URChatBotException("Wrong input!");
        } else {
            return fullInputLine;
        }
    }

    /**
     * Shows goodbye message for ExitCommand.
     */
    public String showByeMessage() {
        return MESSAGE_GOODBYE;
    }
    /**
     * Shows cleared message for ClearCommand.
     */
    public String showClearMessage() {
        return MESSAGE_CLEAR;
    }
    /**
     * Shows list message for ListCommand.
     */
    public String showListMessage(TaskList tasks) {
        StringBuilder listTasks = new StringBuilder();

        for (int i = 0; i < tasks.getTasks().size(); i++) {
            listTasks.append("\n").append(i + 1).append(".").append(tasks.getTasks().get(i).toString());
        }

        return MESSAGE_LIST + listTasks.toString();
    }
    /**
     * Shows print message for PrintCommand.
     */
    public String showPrintMessage(int count, String formattedDate) {
        return (MESSAGE_PRINT + count
                + MESSAGE_PRINT_TWO + formattedDate);
    }
    /**
     * Shows print message for PrintCommand if there are more than 1 task to print.
     */
    public String showPrintMessagePlural(int count, String formattedDate) {
        return (MESSAGE_PRINT + count
                + MESSAGE_PRINT_TWO_PLURAL + formattedDate);
    }
    /**
     * Shows find message for FindCommand.
     */
    public String showFindMessage(TaskList tasks, String searchWord) {
        StringBuilder foundTasks = new StringBuilder();

        for (int i = 0; i < tasks.getTasks().size(); i++) {
            if (tasks.getTasks().get(i).toString().contains(searchWord)) {
                foundTasks.append("\n").append(i + 1).append(".")
                        .append(tasks.getTasks().get(i).toString());
            }
        }

        return MESSAGE_FIND + foundTasks.toString();
    }

    /**
     * Shows mark message for MarkCommand.
     */
    public String showMarkMessage(String taskName) {
        return (MESSAGE_MARK + taskName);
    }
    /**
     * Shows unmark message for UnmarkCommand.
     */
    public String showUnmarkMessage(String taskName) {
        return (MESSAGE_UNMARK + taskName);
    }
    /**
     * Shows delete message for DeleteCommand.
     */
    public String showDeleteMessage(String deletedTask, int taskSize) {
        return (MESSAGE_DELETE + deletedTask + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_TASK_IN_THE_LIST);
    }

    /**
     * Shows delete message for DeleteCommand if there are more 1 task in tasklist.
     */
    public String showDeleteMessagePlural(String deletedTask, int taskSize) {
        return (MESSAGE_DELETE + deletedTask + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_MESSAGE_TASK_IN_THE_LIST_PLURAL);
    }
    /**
     * Shows todo task message for TodoCommand.
     */
    public String showTodoMessage(String taskDescription, int taskSize) {
        return (MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_TASK_IN_THE_LIST);
    }
    /**
     * Shows todo task message for TodoCommand if there are more 1 task in tasklist.
     */
    public String showTodoMessagePlural(String taskDescription, int taskSize) {
        return (MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_MESSAGE_TASK_IN_THE_LIST_PLURAL);
    }

    /**
     * Shows deadline task message for DeadlineCommand.
     */
    public String showDeadlineMessage(String taskDescription, int taskSize) {
        return (MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_TASK_IN_THE_LIST);
    }
    /**
     * Shows deadline task message for DeadlineCommand if there are more 1 task in tasklist.
     */
    public String showDeadlineMessagePlural(String taskDescription, int taskSize) {
        return (MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_MESSAGE_TASK_IN_THE_LIST_PLURAL);
    }
    /**
     * Shows event task message for EventCommand.
     */
    public String showEventMessage(String taskDescription, int taskSize) {
        return (MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_TASK_IN_THE_LIST);
    }
    /**
     * Shows event task message for EventCommand if there are more 1 task in tasklist.
     */
    public String showEventMessagePlural(String taskDescription, int taskSize) {
        return (MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_MESSAGE_TASK_IN_THE_LIST_PLURAL);
    }

    /**
     * Shows free time slots found.
     */
    public String showFindFreeTimeMessage(List<LocalDate> freeTimeSlots) {
        StringBuilder result = new StringBuilder();
        for (LocalDate slot : freeTimeSlots) {
            result.append(slot.toString()).append("\n");
        }
        return (MESSAGE_FIND_FREE_TIME + ("\n") + result);
    }
    /**
     * Shows no free time slot is found.
     */
    public String showNoFreeTimeFoundMessage() {
        return (MESSAGE_NO_FREE_TIME);
    }
    /**
     * Shows dot line.
     */
    public String showLine() {
        return ("_______________________________");
    }
    /**
     * Shows loading error message.
     */
    public String showLoadingError() {
        return MESSAGE_LOADING_ERROR;
    }
    /**
     * Shows error message.
     */
    public String showError(String errorMessage) {
        return "Error: " + errorMessage;
    }
}
