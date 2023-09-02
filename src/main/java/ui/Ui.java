package ui;

import exception.URChatBotException;
import taskList.TaskList;
import tasks.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static common.Messages.*;

public class Ui {
    private final Scanner in;
    private final PrintStream out;
    private int taskNumber;

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }
    public Ui() {
        this(System.in, System.out);
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
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }
    private boolean isCommentLine(String rawInputLine) {
        return rawInputLine.toUpperCase().trim().matches("\\b(?: BYE | DELETE | CLEAR |" +
                " LIST | MARK | UNMARK |" +
                " TODO | DEADLINE | EVENT | PRINT)\\b");
    }


    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     */
    public String readCommand() throws URChatBotException {
        System.out.println("Enter command: ");
        String fullInputLine = in.nextLine();

        if (shouldIgnore(fullInputLine)) {
            throw new URChatBotException("Wrong input!");
        } else {
            return fullInputLine;
        }
    }

    public void showByeMessage() {
        System.out.println(MESSAGE_GOODBYE);
    }
    public void showClearMessage() {
        System.out.println(MESSAGE_CLEAR);
    }
    public void showListMessage() {
        System.out.println(MESSAGE_LIST);
    }
    public void showPrintMessage(int count, String formattedDate) {
        System.out.println(MESSAGE_PRINT + count
                + MESSAGE_PRINT_TWO + formattedDate);
    }
    public void showPrintMessagePlural(int count, String formattedDate) {
        System.out.println(MESSAGE_PRINT + count
                + MESSAGE_PRINT_TWO_PLURAL + formattedDate);
    }
    public void showMarkMessage(String taskName) {
        System.out.println(MESSAGE_MARK + taskName);
    }
    public void showUnmarkMessage(String taskName) {
        System.out.println(MESSAGE_UNMARK + taskName);
    }
    public void showDeleteMessage(String deletedTask, int taskSize) {
        System.out.println(MESSAGE_ADD + deletedTask + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_TASK_IN_THE_LIST);
    }

    public void showDeleteMessagePlural(String deletedTask, int taskSize) {
        System.out.println(MESSAGE_ADD + deletedTask + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_MESSAGE_TASK_IN_THE_LIST_PLURAL);
    }
    public void showTodoMessage(String taskDescription, int taskSize) {
        System.out.println(MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_TASK_IN_THE_LIST);
    }

    public void showTodoMessagePlural(String taskDescription, int taskSize) {
        System.out.println(MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_MESSAGE_TASK_IN_THE_LIST_PLURAL);
    }
    public void showDeadlineMessage(String taskDescription, int taskSize) {
        System.out.println(MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_TASK_IN_THE_LIST);
    }

    public void showDeadlineMessagePlural(String taskDescription, int taskSize) {
        System.out.println(MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_MESSAGE_TASK_IN_THE_LIST_PLURAL);
    }
    public void showEventMessage(String taskDescription, int taskSize) {
        System.out.println(MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_TASK_IN_THE_LIST);
    }

    public void showEventMessagePlural(String taskDescription, int taskSize) {
        System.out.println(MESSAGE_ADD + taskDescription + MESSAGE_NOW_YOU_HAVE
                + taskSize + MESSAGE_MESSAGE_TASK_IN_THE_LIST_PLURAL);
    }



    public void showLine() {
        System.out.println("_______________________________");
    }
    public void showLoadingError() {
        System.out.println(MESSAGE_LOADING_ERROR);
    }

    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }
}
