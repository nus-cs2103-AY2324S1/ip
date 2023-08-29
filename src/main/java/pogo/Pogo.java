package pogo;

import pogo.commands.Command;
import pogo.commands.CommandResult;
import pogo.common.Messages;
import pogo.parsers.Parser;
import pogo.storage.Storage;
import pogo.storage.TextStorage;
import pogo.tasks.Task;

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

    private static boolean handleInput(String input) {
        Command command = Parser.parseCommand(input);
        command.setData(tasks);
        CommandResult result = command.execute();

        System.out.println(result.getFeedbackToUser());

        storage.save(tasks);
        boolean isExit = result.getFeedbackToUser().equals(Messages.EXIT_MESSAGE);
        return isExit;
    }

    public static void main(String[] args) {
        try {
            tasks = storage.load();
            System.out.println(String.format(Messages.TASK_LOAD_SUCCESS, tasks.size()));
        } catch (IOException e) {
            System.out.println(Messages.TASK_LOAD_FAILURE);
            return;
        }

        System.out.println(Messages.STARTUP_MESSAGE);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            System.out.println(Messages.HORIZONTAL_DIVIDER);

            boolean isExit = Pogo.handleInput(input);
            if (isExit) {
                break;
            } else {
                System.out.println(Messages.HORIZONTAL_DIVIDER);
            }
        }
    }
}
