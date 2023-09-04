import command.*;
import exception.KoraException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.UI;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    private TaskList taskList;
    private Storage storage;
    private UI ui;
    private boolean isExit = false;
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        taskList = new TaskList();
        try {
            storage.loadTask(taskList);
        } catch (KoraException e) {
            System.out.println(e.getMessage());
        }



/*
        while (!isExit) {
            Scanner scanner = new Scanner(System.in);
            //System.out.println("------------------------------");
            //getResponse(scanner.nextLine());
            //System.out.println("------------------------------");
            while (scanner.hasNextLine()) {
                getResponse(scanner.nextLine());
            }
        }

 */
    }

    public Command getResponse(String userInput) {
        try {
            Command command = Parser.parse(userInput);
            command.execute(taskList);
            System.out.println(command.getCommandMessage());
            return command;
        } catch (KoraException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void run() {
        ui.greet();
        while (!isExit) {
                String userInput = ui.getUserInput();
                Command command = getResponse(userInput);
                isExit = command.isExitYet();

        }
        ui.closeScanner();

    }
    public static void main(String[] args) {
        Duke kora = new Duke("./data/savedtask.txt");
        kora.run();
    }
}
