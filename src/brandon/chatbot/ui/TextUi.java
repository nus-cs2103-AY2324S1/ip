package ui;

import java.io.InputStream;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import commands.CommandResult;
import tasks.Task;
import tasks.TaskList;

public class TextUi {
    private static final String DIVIDER = "--------------------------------";
    private final Scanner in;
    private final PrintStream out;
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";


    public TextUi() {
        this(System.in, System.out);
    }
    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() {
        String fullInputLine = in.nextLine();

        showToUser("[Command entered:" + fullInputLine + "]");
        return fullInputLine;
    }

    public void showToUser(String... message) {
        for (String m : message) {
            out.println(m);
        }
    }

    public void showResultToUser(CommandResult result) {
        final Optional<TaskList> resultTasks = result.getTasks();
        showToUser(result.feedbackToUser);
        if (resultTasks.isPresent()) {
            showTasks(resultTasks.get());
        }
        showToUser(DIVIDER);
    }

    private void showTasks(TaskList tasks) {
        final List<String> formattedTasks = new ArrayList<>();
        for (Task t : tasks.getList()) {
            formattedTasks.add(t.getStatus());
        }
        showToUserAsIndexedList(formattedTasks);
    }

    /** Shows a list of strings to the user, formatted as an indexed list. */
    private void showToUserAsIndexedList(List<String> list) {
        showToUser(getIndexedListForViewing(list));
    }

    /** Formats a list of strings as a viewable indexed list. */
    private static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 1;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }

    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
    public void showWelcomeMessage() {
        out.println("Hello.. I'm ekuD..");
        out.println("I probably won't be much of a help.. But ask me something..");
        out.println(DIVIDER);
    }
}
