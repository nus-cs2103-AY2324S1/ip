package pogo;

import pogo.commands.Command;
import pogo.commands.CommandResult;
import pogo.common.Messages;
import pogo.parsers.Parser;
import pogo.storage.Storage;
import pogo.storage.TextStorage;
import pogo.tasks.Task;
import pogo.ui.TextUi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pogo {
    /**
     * List of tasks in Pogo.
     */
    private static List<Task> tasks = new ArrayList<>();

    /**
     * Storage object to save and load tasks.
     */
    private static final Storage storage = TextStorage.of();
    private static final TextUi ui = new TextUi();

    private static boolean handleInput(String input) {
        Command command = Parser.parseCommand(input);
        command.setData(tasks);
        CommandResult result = command.execute();

        ui.showCommandResultToUser(result);

        boolean isExit = result.getFeedbackToUser().equals(Messages.EXIT_MESSAGE);
        return isExit;
    }

    public static void main(String[] args) {
        try {
            tasks = storage.load();
            ui.showInitSuccessMessage(tasks.size());
        } catch (IOException e) {
            ui.showInitFailureMessage();
            return;
        }

        ui.showStartupMessage();

        while (true) {
            String input = ui.getUserInput();

            boolean isExit = Pogo.handleInput(input);
            if (isExit) {
                storage.save(tasks);
                break;
            }
        }
    }
}
