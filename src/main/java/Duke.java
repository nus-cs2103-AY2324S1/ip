import Storage.Dbops;
import LogicHandlers.CentralCommandHandler;
import Models.TaskArray;

import java.util.Scanner;

import static Ui.BasicOutputPrinter.printBasicOutput;

public class Duke {
    private static boolean isRunning;
    private static TaskArray tasks;
    private static CentralCommandHandler commandHandler;

    /**
     * Initializes the bot.
     */
    private static void initializeBot() {
        Duke.isRunning = true;
        Duke.tasks = Dbops.initializeDatabase();
        Duke.commandHandler = CentralCommandHandler.initializeCommandHandler(tasks);
    }

    public static void main(String[] args) {
        Duke.initializeBot();

        String introduction = " Hello! I'm EGGBOT!\n" +
                " Type 'help' to show available commands!";

        printBasicOutput(introduction);
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            System.out.println("Please type a command, or type 'help' to show available commands:");

            String input = scanner.nextLine().strip();  // Read input
            Duke.commandHandler.parseInput(input);
        }

    }
}
