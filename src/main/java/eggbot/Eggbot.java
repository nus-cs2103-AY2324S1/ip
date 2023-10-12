package eggbot;

import static ui.printers.Printer.printBasicOutput;

import logic.CentralCommandHandler;
import models.TaskArray;
import storage.Dbops;

public class Eggbot {
    private static TaskArray tasks;
    private static CentralCommandHandler commandHandler;


    /**
     * Initializes the bot.
     */
    static void initializeBot() {
        System.out.println("Initializing database...");
        Eggbot.tasks = Dbops.initializeDatabase();
        System.out.println("Initializing command handler...");
        Eggbot.commandHandler = CentralCommandHandler.initializeCommandHandler(tasks);
        String introduction = " Hello! I'm EGGBOT!\n" +
                " Type 'help' to show available commands!";

        printBasicOutput(introduction);
    }

    /**
     * Handles the input given by the user.
     * @param input Input provided by user.
     */
    public void handleInput(String input) {
        Eggbot.commandHandler.parseInput(input);
    }

}
