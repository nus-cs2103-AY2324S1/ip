package Jeoe;

import Jeoe.Commands.Command;
import Jeoe.Commands.CommandParser;
import Jeoe.Exceptions.InitializationException;
import Jeoe.Exceptions.NoCommandException;
import Jeoe.Exceptions.InvalidCommandException;

import Jeoe.Exceptions.RunException;
import Jeoe.Others.StorageManager;
import Jeoe.Others.Ui;
import Jeoe.Tasks.TaskManager;


public class Jeoe {

    private static TaskManager taskManager;
    private static Ui ui;
    private static StorageManager storageManager;
    private static String filePath = System.getProperty("user.dir") + "/storage/taskListData.txt"; // dir is till ip


    private static void initialize() throws InitializationException { // create the initialization exception
        try {
            ui = new Ui();
            storageManager = new StorageManager(filePath); // loading tasks & saving tasks
            // contains list of task, and has operations to add/delete tasks
            taskManager = new TaskManager(storageManager.load()); // load passes the string for TM to add task to arr

            ui.showOpenString();
        } catch (Exception e) {
            System.out.println("Jeoe.Jeoe initialization failed");
        }
    }

    public static void run(){
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.nextLine();
                Command c = CommandParser.parse(fullCommand);
                c.execute(taskManager, ui, storageManager);
                isExit = c.isExit();
            } catch (InvalidCommandException e) {
                // print exception, they will handle their formatting themselves
                System.out.println(e.getMessage());
            } catch (NoCommandException e) { // can think of if tried 3 empty commands, terminate program
                System.out.println(e.getMessage());
            } catch (RunException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            Jeoe.initialize();
            Jeoe.run();
        } catch (InitializationException e) {
            // exception to do with initialization => scanner fails (cannot be file issue)
        }
    }
}