package urchatbot.ui;

import urchatbot.exception.URChatBotException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static urchatbot.common.Messages.*;

public class Ui {
    private final Scanner in;
    private final PrintStream out;
    private int taskNumber;

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
    public void showWelcome() {
        System.out.println(MESSAGE_WELCOME);
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
        return rawInputLine.toUpperCase().trim().matches("\\b(?: BYE | DELETE | CLEAR |" +
                " LIST | MARK | UNMARK | FIND" +
                " TODO | DEADLINE | EVENT | PRINT)\\b");
    }


    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     */
    public String readCommand() throws URChatBotException {
        out.println("Enter command: ");
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
    public void showByeMessage() {
        System.out.println(MESSAGE_GOODBYE);
    }
    /**
     * Shows cleared message for ClearCommand.
     */
    public void showClearMessage() {
        System.out.println(MESSAGE_CLEAR);
    }
    /**
     * Shows list message for ListCommand.
     */
    public void showListMessage() {
        out.println(MESSAGE_LIST);
    }
    /**
     * Shows print message for PrintCommand.
     */
    public void showPrintMessage(int count, String formattedDate) {
        out.println(MESSAGE_PRINT + count
                + MESSAGE_PRINT_TWO + formattedDate);
    }
    /**
     * Shows print message for PrintCommand if there are more than 1 task to print.
     */
    public void showPrintMessagePlural(int count, String formattedDate) {
        out.println(MESSAGE_PRINT + count
                + MESSAGE_PRINT_TWO_PLURAL + formattedDate);
    }
    /**
     * Shows find message for FindCommand.
     */
    public void showFindMessage() {
        out.println(MESSAGE_FIND);
    }

    /**
     * Shows mark message for MarkCommand.
     */
    public void showMarkMessage(String taskName) {
        out.println(MESSAGE_MARK + taskName);
    }
    /**
     * Shows unmark message for UnmarkCommand.
     */
    public void showUnmarkMessage(String taskName) {
        out.println(MESSAGE_UNMARK + taskName);
    }
    /**
     * Shows delete message for DeleteCommand.
     */
    public void showDeleteMessage(String deletedTask, int taskSize) {
        out.println(MESSAGE_DELETE + deletedTask + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_TASK_IN_THE_LIST);
    }

    /**
     * Shows delete message for DeleteCommand if there are more 1 task in tasklist.
     */
    public void showDeleteMessagePlural(String deletedTask, int taskSize) {
        out.println(MESSAGE_ADD + deletedTask + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_MESSAGE_TASK_IN_THE_LIST_PLURAL);
    }
    /**
     * Shows todo task message for TodoCommand.
     */
    public void showTodoMessage(String taskDescription, int taskSize) {
        out.println(MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_TASK_IN_THE_LIST);
    }
    /**
     * Shows todo task message for TodoCommand if there are more 1 task in tasklist.
     */
    public void showTodoMessagePlural(String taskDescription, int taskSize) {
        out.println(MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_MESSAGE_TASK_IN_THE_LIST_PLURAL);
    }

    /**
     * Shows deadline task message for DeadlineCommand.
     */
    public void showDeadlineMessage(String taskDescription, int taskSize) {
        out.println(MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_TASK_IN_THE_LIST);
    }
    /**
     * Shows deadline task message for DeadlineCommand if there are more 1 task in tasklist.
     */
    public void showDeadlineMessagePlural(String taskDescription, int taskSize) {
        out.println(MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_MESSAGE_TASK_IN_THE_LIST_PLURAL);
    }
    /**
     * Shows event task message for EventCommand.
     */
    public void showEventMessage(String taskDescription, int taskSize) {
        out.println(MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_TASK_IN_THE_LIST);
    }
    /**
     * Shows event task message for EventCommand if there are more 1 task in tasklist.
     */
    public void showEventMessagePlural(String taskDescription, int taskSize) {
        out.println(MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_MESSAGE_TASK_IN_THE_LIST_PLURAL);
    }

    /**
     * Shows dot line.
     */
    public void showLine() {
        out.println("_______________________________");
    }
    /**
     * Shows loading error message.
     */
    public void showLoadingError() {
        out.println(MESSAGE_LOADING_ERROR);
    }
    /**
     * Shows error message.
     */
    public void showError(String errorMessage) {
        out.println("Error: " + errorMessage);
    }
}
