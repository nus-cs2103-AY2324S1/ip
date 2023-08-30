package rayshawn.chatbot.ui;

import static rayshawn.chatbot.messages.Messages.GOODBYE_MESSAGE;
import static rayshawn.chatbot.messages.Messages.HELP_MESSAGE;
import static rayshawn.chatbot.messages.Messages.INIT_FAILED_MESSAGE;
import static rayshawn.chatbot.messages.Messages.LINE_BREAK;
import static rayshawn.chatbot.messages.Messages.LIST_HEADER_MESSAGE;
import static rayshawn.chatbot.messages.Messages.WELCOME_MESSAGE;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import rayshawn.chatbot.commands.CommandResult;
import rayshawn.chatbot.tasks.Task;

/**
 * Represents the UI of the application.
 */
public class Ui {
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    private static final String LINE_PREFIX = "> ";
    private static final String LS = System.lineSeparator();
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructor for Ui.
     *
     * @param in input
     * @param out output
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty and pure whitespace.
     *
     * @return command entered by the user
     */
    public String getUserCommand() {
        out.print(LINE_PREFIX + "Enter command: ");
        String fullInputLine = in.nextLine();

        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }

        return fullInputLine;
    }

    /**
     * Shows the user the welcome message.
     */
    public void showWelcomeMessage() {
        showToUser(LINE_BREAK,
                WELCOME_MESSAGE,
                HELP_MESSAGE,
                LINE_BREAK);
    }

    /**
     * Shows the user goodbye message.
     */
    public void showGoodbyeMessage() {
        showToUser(LINE_BREAK,
                GOODBYE_MESSAGE,
                LINE_BREAK);
    }

    /**
     * Shows the user initialisation failure message.
     */
    public void showInitFailedMessage() {
        showToUser(LINE_BREAK,
                INIT_FAILED_MESSAGE,
                LINE_BREAK);
    }

    /**
     * Outputs the different messages for user to see.
     *
     * @param message Strings to be printed
     */
    public void showToUser(String... message) {
        for (String m : message) {
            out.println(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX));
        }
    }

    /**
     * Shows the result of the command to the user.
     *
     * @param result result of the command
     */
    public void showResultToUser(CommandResult result) {
        final Optional<List<Task>> taskList = result.getTaskList();
        if (taskList.isPresent()) {
            showToUser(LIST_HEADER_MESSAGE);
            showTaskList(taskList.get());
        }
        showToUser(result.feedbackToUser, LINE_BREAK);
    }

    private void showTaskList(List<Task> taskList) {
        final List<String> formattedTasks = new ArrayList<>();
        for (Task task : taskList) {
            formattedTasks.add(task.toString());
        }
        showToUserAsIndexList(formattedTasks);
    }

    private void showToUserAsIndexList(List<String> list) {
        showToUser(getIndexedList(list));
    }

    private static String getIndexedList(List<String> list) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : list) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }

    private static String getIndexedListItem(int index, String item) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, index, item);
    }
}
