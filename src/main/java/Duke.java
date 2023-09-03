import Database.Dbops;
import LogicHandlers.CentralCommandHandler;
import Models.TaskArray;

import java.util.Scanner;

import static Printers.BasicOutputPrinter.printBasicOutput;

public class Duke {
    private static TaskArray tasks;
    private static CentralCommandHandler commandHandler;

    private static void initializeBot() {
        Duke.tasks = Dbops.initializeDatabase();
        Duke.commandHandler = CentralCommandHandler.initializeCommandHandler(tasks);
    }
    public static void main(String[] args) {
        Duke.initializeBot();

        String introduction = " Hello! I'm EGGBOT!\n" +
                " Type 'help' to show available commands!";

        printBasicOutput(introduction);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please type a command, or type 'help' to show available commands:");

            String input = scanner.nextLine().strip();  // Read input
            Duke.commandHandler.parseInput(input);
        }

    }
}
